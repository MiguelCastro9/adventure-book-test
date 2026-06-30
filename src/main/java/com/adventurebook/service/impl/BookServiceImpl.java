package com.adventurebook.service.impl;

import com.adventurebook.converters.BookConsequenceConverter;
import com.adventurebook.converters.BookConverter;
import com.adventurebook.converters.BookOptionConverter;
import com.adventurebook.converters.BookSectionConverter;
import com.adventurebook.dto.response.BookPlayResponse;
import com.adventurebook.dto.response.BookResponse;
import com.adventurebook.enums.CategoryEnum;
import com.adventurebook.enums.ConsequenceTypeEnum;
import com.adventurebook.enums.DifficultyEnum;
import com.adventurebook.enums.SectionTypeEnum;
import com.adventurebook.exception.MessageCustomException;
import com.adventurebook.model.BookCategoryModel;
import com.adventurebook.model.BookModel;
import com.adventurebook.model.BookOptionConsequenceModel;
import com.adventurebook.model.BookSectionModel;
import com.adventurebook.model.BookSectionOptionModel;
import com.adventurebook.repository.BookRepository;
import com.adventurebook.repository.BookSectionRepository;
import com.adventurebook.service.BookService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookSectionRepository sectionRepository;

    private final BookConverter bookConverter;

    private final BookSectionConverter bookSectionConverter;

    private final BookOptionConverter bookOptionConverter;

    private final BookConsequenceConverter bookConsequenceConverter;

    private int playerHealth = 10;

    @Override
    public List<BookResponse> findAll() {

        List<BookResponse> items = bookRepository.findAll().stream()
                .map(bookConverter::convertToDto)
                .toList();

        return items;
    }

    @Override
    public BookResponse findById(Long id) {

        BookModel bookModel = bookRepository.findById(id)
                .orElseThrow(() -> new MessageCustomException("Book not found"));

        return bookConverter.convertToDto(bookModel);
    }

    @Override
    public List<BookResponse> search(String title, String author, CategoryEnum category, DifficultyEnum difficulty) {
        return bookRepository.searchBooks(
                title,
                author,
                category == null ? null : category.name(),
                difficulty == null ? null : difficulty.name()
        ).stream()
                .map(bookConverter::convertToDto)
                .toList();
    }

    @Override
    public BookResponse save(BookModel book) {
        validateBook(book);
        BookModel savedBook = bookRepository.save(book);
        return bookConverter.convertToDto(savedBook);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void addCategory(Long bookId, CategoryEnum category) {
        BookModel book = bookRepository.findById(bookId)
                .orElseThrow(() -> new MessageCustomException("Book not found"));

        boolean alreadyAdded = book.getCategories().stream()
                .anyMatch(categoryModel -> categoryModel.getCategory() == category);

        if (alreadyAdded) {
            throw new MessageCustomException("Category already exists for this book");
        }

        var categoryModel = new BookCategoryModel();
        categoryModel.setCategory(category);
        categoryModel.setBook(book);
        book.getCategories().add(categoryModel);
        bookRepository.save(book);
    }

    @Override
    public void removeCategory(Long bookId, CategoryEnum category) {
        BookModel book = bookRepository.findById(bookId)
                .orElseThrow(() -> new MessageCustomException("Book not found"));

        boolean exists = book.getCategories().stream()
                .anyMatch(categoryModel -> categoryModel.getCategory() == category);

        if (!exists) {
            throw new MessageCustomException("Category no longer exists for this book");
        }

        book.getCategories().removeIf(categoryModel -> categoryModel.getCategory() == category);
        bookRepository.save(book);
    }

    @Override
    public BookResponse findSection(Long bookId, Integer sectionNumber) {
        
        BookModel book = bookRepository.findById(bookId)
                .orElseThrow(() -> new MessageCustomException("Book not found"));

        BookSectionModel section = sectionRepository
                .findByBookIdAndSectionNumber(bookId, sectionNumber)
                .orElseThrow(() -> new MessageCustomException("Section not found"));

        BookResponse response = bookConverter.convertToDto(book);
        response.setSections(List.of(bookSectionConverter.convertToDto(section)));
        return response;
    }

    @Override
    public BookPlayResponse chooseOption(Long bookId, Integer sectionNumber, Long optionId) {

        BookSectionModel section = sectionRepository.findByBookIdAndSectionNumber(bookId, sectionNumber)
                .orElseThrow(() -> new MessageCustomException("Section not found"));

        BookSectionOptionModel option = section.getOptions().stream()
                .filter(currentOption -> currentOption.getId() != null && currentOption.getId().equals(optionId))
                .findFirst()
                .orElseThrow(() -> new MessageCustomException("Option not found"));

        BookOptionConsequenceModel consequence = option.getConsequence();
        if (consequence == null) {
            throw new MessageCustomException("Option consequence not found");
        }

        BookSectionModel nextSection = sectionRepository.findByBookIdAndSectionNumber(bookId, option.getGotoSectionNumber())
                .orElseThrow(() -> new MessageCustomException("Next section not found"));

        if (consequence.getType() == ConsequenceTypeEnum.LOSE_HEALTH) {
            playerHealth -= consequence.getValue();
        } else if (consequence.getType() == ConsequenceTypeEnum.GAIN_HEALTH) {
            playerHealth += consequence.getValue();
        }

        if (playerHealth <= 0) {
            playerHealth = 10; 
            throw new MessageCustomException("Player died");
        }

        if (nextSection.getType() == SectionTypeEnum.END) {
            playerHealth = 10;
        }

        return new BookPlayResponse(
                bookOptionConverter.convertToDto(option),
                bookConsequenceConverter.convertToDto(consequence),
                bookSectionConverter.convertToDto(nextSection),
                playerHealth
        );
    }

    private void validateBook(BookModel book) {

        if (book == null || book.getSections() == null || book.getSections().isEmpty()) {
            throw new MessageCustomException("Book must contain at least one section");
        }

        Map<Integer, BookSectionModel> sectionsByNumber = new HashMap<>();

        for (BookSectionModel section : book.getSections()) {
            if (section.getSectionNumber() == null) {
                throw new MessageCustomException("Section number must be provided");
            }
            if (sectionsByNumber.containsKey(section.getSectionNumber())) {
                throw new MessageCustomException("Duplicate section numbers are not allowed");
            }

            sectionsByNumber.put(section.getSectionNumber(), section);
        } 

        long beginCount = book.getSections().stream()
                .filter(section -> section.getType() == SectionTypeEnum.BEGIN)
                .count();
        if (beginCount != 1) {
            throw new MessageCustomException("Book must have exactly one begin section");
        }

        boolean hasEnding = book.getSections().stream()
                .anyMatch(section -> section.getType() == SectionTypeEnum.END);
        if (!hasEnding) {
            throw new MessageCustomException("Book must have at least one end section");
        }

        for (BookSectionModel section : book.getSections()) {
            
            if (section.getType() != SectionTypeEnum.END) {
                if (section.getOptions() == null || section.getOptions().isEmpty()) {
                    throw new MessageCustomException("begin or node sections must have at least one option");
                }
            }

            if (section.getOptions() != null) {
                for (var option : section.getOptions()) {
                    if (option.getGotoSectionNumber() == null) {
                        throw new MessageCustomException("Option goto section number is required");
                    }
                    if (!sectionsByNumber.containsKey(option.getGotoSectionNumber())) {
                        throw new MessageCustomException("Invalid next section id: " + option.getGotoSectionNumber());
                    }
                }
            }
        }
    }
}

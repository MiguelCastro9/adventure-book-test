package com.adventurebook.converters;

import com.adventurebook.dto.request.BookRequest;
import com.adventurebook.dto.response.BookResponse;
import com.adventurebook.enums.CategoryEnum;
import com.adventurebook.model.BookCategoryModel;
import com.adventurebook.model.BookModel;
import com.adventurebook.model.BookOptionConsequenceModel;
import com.adventurebook.model.BookSectionModel;
import com.adventurebook.model.BookSectionOptionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookConverter {

    private final BookSectionConverter bookSectionConverter;

    public BookResponse convertToDto(BookModel book) {

        if (book == null) {
            return null;
        }

        BookResponse response = new BookResponse();

        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setDifficulty(book.getDifficulty());

        response.setCategories(book.getCategories().stream()
                .map(BookCategoryModel::getCategory)
                .toList()
        );

        response.setSections(book.getSections().stream()
                .map(bookSectionConverter::convertToDto)
                .toList()
        );

        return response;
    }

    public BookModel convertToEntity(BookRequest request) {
        
        BookModel book = new BookModel();
        
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDifficulty(request.getDifficulty());

        if (request.getCategories() != null) {
            for (CategoryEnum category : request.getCategories()) {
                BookCategoryModel categoryModel = new BookCategoryModel();
                categoryModel.setCategory(category);
                categoryModel.setBook(book);
                book.getCategories().add(categoryModel);
            }
        }

        if (request.getSections() != null) {
            for (var sectionRequest : request.getSections()) {
                BookSectionModel section = new BookSectionModel();
                section.setSectionNumber(sectionRequest.getSectionNumber());
                section.setText(sectionRequest.getText());
                section.setType(sectionRequest.getType());
                section.setBook(book);

                if (sectionRequest.getOptions() != null) {
                    for (var optionRequest : sectionRequest.getOptions()) {
                        BookSectionOptionModel option = new BookSectionOptionModel();
                        option.setDescription(optionRequest.getDescription());
                        option.setGotoSectionNumber(optionRequest.getGotoSectionNumber());
                        option.setSection(section);

                        if (optionRequest.getConsequence() != null) {
                            BookOptionConsequenceModel consequence = new BookOptionConsequenceModel();
                            consequence.setType(optionRequest.getConsequence().getType());
                            consequence.setValue(optionRequest.getConsequence().getValue());
                            consequence.setText(optionRequest.getConsequence().getText());
                            consequence.setOption(option);
                            option.setConsequence(consequence);
                        }
                        section.getOptions().add(option);
                    }
                }
                book.getSections().add(section);
            }
        }

        return book;
    }
}

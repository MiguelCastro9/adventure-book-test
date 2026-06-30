package com.adventurebook.service;

import com.adventurebook.dto.response.BookPlayResponse;
import com.adventurebook.dto.response.BookResponse;
import com.adventurebook.enums.CategoryEnum;
import com.adventurebook.enums.DifficultyEnum;
import com.adventurebook.model.BookModel;
import java.util.List;

public interface BookService {

    List<BookResponse> findAll();

    BookResponse findById(Long id);

    List<BookResponse> search(String title, String author, CategoryEnum category, DifficultyEnum difficulty);

    BookResponse save(BookModel book);

    void delete(Long id);

    void addCategory(Long bookId, CategoryEnum category);

    void removeCategory(Long bookId, CategoryEnum category);

    BookResponse findSection(Long bookId, Integer sectionNumber);

    BookPlayResponse chooseOption(Long bookId, Integer sectionNumber, Long optionId);
}

package com.adventurebook.controller;

import com.adventurebook.converters.BookConverter;
import com.adventurebook.dto.request.BookRequest;
import com.adventurebook.dto.request.CategoryActionRequest;
import com.adventurebook.dto.response.BookPlayResponse;
import com.adventurebook.dto.response.BookResponse;
import com.adventurebook.enums.CategoryEnum;
import com.adventurebook.enums.DifficultyEnum;
import com.adventurebook.exception.MessageException;
import com.adventurebook.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Book", description = "Endpoints")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    
    private final BookConverter bookConverter;

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) CategoryEnum category,
            @RequestParam(required = false) DifficultyEnum difficulty
    ) {
        List<BookResponse> response = bookService.search(title, author, category, difficulty);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id) {
        BookResponse response = bookService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        BookResponse response = bookService.save(bookConverter.convertToEntity(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageException> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        MessageException responseMessage = new MessageException("Book successfully removed!", "Successful book removal operation.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/{id}/categories")
    public ResponseEntity<MessageException> addCategory(@PathVariable Long id, @Valid @RequestBody CategoryActionRequest request) {
        bookService.addCategory(id, request.getCategory());
        MessageException responseMessage = new MessageException("Category successfully created!", "Successful category registration operation.");
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/categories")
    public ResponseEntity<MessageException> removeCategory(@PathVariable Long id, @Valid @RequestBody CategoryActionRequest request) {
        bookService.removeCategory(id, request.getCategory());
        MessageException responseMessage = new MessageException("Category successfully removed!", "Category removal operation successful.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{bookId}/sections/{sectionNumber}")
    public ResponseEntity<BookResponse> getSection(@PathVariable Long bookId, @PathVariable Integer sectionNumber) {
        BookResponse response = bookService.findSection(bookId, sectionNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{bookId}/sections/{sectionNumber}/options/{optionId}/choose")
    public ResponseEntity<BookPlayResponse> chooseOption(@PathVariable Long bookId, @PathVariable Integer sectionNumber, @PathVariable Long optionId) {
        BookPlayResponse response = bookService.chooseOption(bookId, sectionNumber, optionId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

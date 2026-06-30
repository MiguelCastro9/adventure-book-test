package com.adventurebook.converters;

import com.adventurebook.dto.response.BookOptionResponse;
import com.adventurebook.model.BookSectionOptionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookOptionConverter {

    private final BookConsequenceConverter bookConsequenceConverter;

    public BookOptionResponse convertToDto(BookSectionOptionModel option) {
        
        if (option == null) {
            return null;
        }

        BookOptionResponse response = new BookOptionResponse();
        
        response.setId(option.getId());
        response.setDescription(option.getDescription());
        response.setGotoSectionNumber(option.getGotoSectionNumber());
        response.setConsequence(bookConsequenceConverter.convertToDto(option.getConsequence()));
        
        return response;
    }
}

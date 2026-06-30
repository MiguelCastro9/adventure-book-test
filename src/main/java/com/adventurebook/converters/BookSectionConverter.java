package com.adventurebook.converters;

import com.adventurebook.dto.response.BookSectionResponse;
import com.adventurebook.model.BookSectionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSectionConverter {

    private final BookOptionConverter bookOptionConverter;

    public BookSectionResponse convertToDto(BookSectionModel section) {

        if (section == null) {
            return null;
        }

        BookSectionResponse response = new BookSectionResponse();
        response.setId(section.getId());
        response.setSectionNumber(section.getSectionNumber());
        response.setText(section.getText());
        response.setType(section.getType());
        response.setOptions(section.getOptions().stream()
                .map(bookOptionConverter::convertToDto)
                .toList()
        );
        
        return response;
    }
}

package com.adventurebook.converters;

import com.adventurebook.dto.response.BookConsequenceResponse;
import com.adventurebook.model.BookOptionConsequenceModel;
import org.springframework.stereotype.Component;

@Component
public class BookConsequenceConverter {

    public BookConsequenceResponse convertToDto(BookOptionConsequenceModel consequence) {
        
        if (consequence == null) {
            return null;
        }

        BookConsequenceResponse response = new BookConsequenceResponse();
        
        response.setId(consequence.getId());
        response.setType(consequence.getType());
        response.setValue(consequence.getValue());
        response.setText(consequence.getText());
        
        return response;
    }
}

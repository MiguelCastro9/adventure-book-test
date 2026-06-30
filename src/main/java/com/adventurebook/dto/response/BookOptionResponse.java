package com.adventurebook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookOptionResponse {

    private Long id;
    
    private String description;
    
    private Integer gotoSectionNumber;
    
    private BookConsequenceResponse consequence;
}

package com.adventurebook.dto.response;

import com.adventurebook.enums.ConsequenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookConsequenceResponse {

    private Long id;
    
    private ConsequenceTypeEnum type;
    
    private Integer value;
    
    private String text;
}

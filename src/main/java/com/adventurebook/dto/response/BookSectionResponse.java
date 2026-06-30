package com.adventurebook.dto.response;

import com.adventurebook.enums.SectionTypeEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookSectionResponse {

    private Long id;
    
    private Integer sectionNumber;
    
    private String text;
    
    private SectionTypeEnum type;
    
    private List<BookOptionResponse> options;
}

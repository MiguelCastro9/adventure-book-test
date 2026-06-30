package com.adventurebook.dto.response;

import com.adventurebook.enums.CategoryEnum;
import com.adventurebook.enums.DifficultyEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private Long id;
    
    private String title;
    
    private String author;
    
    private DifficultyEnum difficulty;
    
    private List<CategoryEnum> categories;
    
    private List<BookSectionResponse> sections;
}

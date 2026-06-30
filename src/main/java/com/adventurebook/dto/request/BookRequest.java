package com.adventurebook.dto.request;

import com.adventurebook.enums.CategoryEnum;
import com.adventurebook.enums.DifficultyEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "The title of the required book")
    private String title;

    @NotBlank(message = "The author of the required book")
    private String author;

    @NotNull(message = "The challenge of the required book")
    private DifficultyEnum difficulty;
    
    @NotNull(message = "The book categories are mandatory.")
    private List<CategoryEnum> categories;

    @NotNull(message = "The sections of the book are mandatory.")
    private List<BookSectionRequest> sections;
}

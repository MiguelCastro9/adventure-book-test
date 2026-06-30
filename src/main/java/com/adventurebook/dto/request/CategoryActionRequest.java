package com.adventurebook.dto.request;

import com.adventurebook.enums.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryActionRequest {

    @NotNull(message = "The book category is mandatory.")
    private CategoryEnum category;
}

package com.adventurebook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookOptionRequest {

    @NotBlank(message = "The option description is mandatory.")
    private String description;

    @NotNull(message = "The number of the next section is mandatory.")
    private Integer gotoSectionNumber;

    @NotNull(message = "The consequence is mandatory.")
    private BookConsequenceRequest consequence;
}

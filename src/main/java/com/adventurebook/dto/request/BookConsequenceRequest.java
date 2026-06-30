package com.adventurebook.dto.request;

import com.adventurebook.enums.ConsequenceTypeEnum;
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
public class BookConsequenceRequest {

    @NotNull(message = "The consequence type is mandatory.")
    private ConsequenceTypeEnum type;

    @NotNull(message = "The value of the consequence is mandatory.")
    private Integer value;

    @NotBlank(message = "The consequence text is mandatory.")
    private String text;
}

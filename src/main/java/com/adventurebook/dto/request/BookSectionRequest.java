package com.adventurebook.dto.request;

import com.adventurebook.enums.SectionTypeEnum;
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
public class BookSectionRequest {

    @NotNull(message = "The section number is mandatory.")
    private Integer sectionNumber;

    @NotBlank(message = "The section text is mandatory.")
    private String text;

    @NotNull(message = "The section type is mandatory.")
    private SectionTypeEnum type;
    
    @NotNull(message = "The options in the section are mandatory.")
    private List<BookOptionRequest> options;
}

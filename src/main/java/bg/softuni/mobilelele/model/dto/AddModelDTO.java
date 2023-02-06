package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.Category;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddModelDTO {
    @Size(min = 2, message = "Model name must be more than 2 symbols.")
    private String name;

    @NotNull(message = "Category is required.")
    private Category category;

    @Size(min = 3, message = "Brand name must be more than 3 symbols.")
    private String brandName;

    @Size(min = 8, max = 512, message = "Enter a valid image url.")
    private String imageUrl;

    @NotNull(message = "Start year is required.")
    @Min(value = 1886, message = "Start year cannot be before 1886.")
    @Max(value = 2023, message = "Start year cannot be in the future.")
    private Integer startYear;

    @NotNull(message = "End year is required.")
    @Min(value = 1886, message = "End year cannot be before 1886.")
    @Max(value = 2023, message = "End year cannot be in the future.")
    private Integer endYear;
}

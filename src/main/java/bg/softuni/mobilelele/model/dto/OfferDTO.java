package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.entities.Model;
import bg.softuni.mobilelele.model.enums.Engine;
import bg.softuni.mobilelele.model.enums.Transmission;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OfferDTO {
    @NotNull(message = "Vehicle model is required.")
    private Model model;

    @NotNull(message = "Suggested price is required.")
    private Double price;

    @NotNull(message = "Engine is required.")
    private Engine engine;

    @NotNull(message = "Transmission is required.")
    private Transmission transmission;

    @NotNull(message = "Manufacturing year is required.")
    private Integer year;

    @NotNull(message = "Mileage is required.")
    private Integer mileage;

    @NotEmpty(message = "Description is required.")
    private String description;

    @NotEmpty(message = "Vehicle image URL is required.")
    private String imageUrl;
}

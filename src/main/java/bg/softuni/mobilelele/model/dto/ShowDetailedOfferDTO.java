package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.Engine;
import bg.softuni.mobilelele.model.enums.Transmission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ShowDetailedOfferDTO {
    private Long id;

    private String brandName;

    private String modelName;

    private Integer mileage;

    private Double price;

    private Engine engine;

    private Transmission transmission;

    private String imageUrl;

    private Integer year;

    private LocalDateTime created;

    private LocalDateTime modified;

    private String sellerFirstName;

    private String sellerLastName;
}

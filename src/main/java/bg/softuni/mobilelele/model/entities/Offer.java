package bg.softuni.mobilelele.model.entities;

import bg.softuni.mobilelele.model.enums.Engine;
import bg.softuni.mobilelele.model.enums.Transmission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
@NoArgsConstructor
@Getter
@Setter
public class Offer extends BaseEntity {
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Engine engine;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer mileage;

    @Column(columnDefinition = "DECIMAL(19,2)", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmission transmission;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private LocalDateTime created;

    private LocalDateTime modified;

    @ManyToOne(optional = false)
    private Model model;

    @ManyToOne(optional = false)
    private User seller;
}

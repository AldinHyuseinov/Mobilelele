package bg.softuni.mobilelele.model.entities;

import bg.softuni.mobilelele.model.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
@NoArgsConstructor
@Getter
@Setter
public class Model extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer startYear;

    @Column(nullable = false)
    private Integer endYear;

    @Column(nullable = false)
    private LocalDateTime created;

    private LocalDateTime modified;

    @ManyToOne(optional = false)
    private Brand brand;
}

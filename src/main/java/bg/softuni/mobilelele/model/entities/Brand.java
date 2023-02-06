package bg.softuni.mobilelele.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
@NoArgsConstructor
@Getter
@Setter
public class Brand extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime created;

    private LocalDateTime modified;
}

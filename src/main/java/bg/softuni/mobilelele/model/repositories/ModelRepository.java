package bg.softuni.mobilelele.model.repositories;

import bg.softuni.mobilelele.model.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findByName(String name);
}

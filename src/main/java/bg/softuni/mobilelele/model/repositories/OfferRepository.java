package bg.softuni.mobilelele.model.repositories;

import bg.softuni.mobilelele.model.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Modifying
    @Transactional
    void deleteById(Long id);
}

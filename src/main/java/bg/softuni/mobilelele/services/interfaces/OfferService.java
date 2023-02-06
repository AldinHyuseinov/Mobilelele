package bg.softuni.mobilelele.services.interfaces;

import bg.softuni.mobilelele.model.dto.OfferDTO;
import bg.softuni.mobilelele.model.dto.ShowDetailedOfferDTO;
import bg.softuni.mobilelele.model.dto.ShowOfferDTO;

import java.util.List;

public interface OfferService {
    void uploadOffer(OfferDTO offerDTO);

    List<ShowOfferDTO> allOffers();

    ShowDetailedOfferDTO showDetailedOffer(Long offerId);

    void updateOffer(Long offerId, OfferDTO offerDTO);

    void removeOffer(Long offerId);
}

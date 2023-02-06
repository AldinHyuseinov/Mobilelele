package bg.softuni.mobilelele.services.impl;

import bg.softuni.mobilelele.model.dto.OfferDTO;
import bg.softuni.mobilelele.model.dto.ShowDetailedOfferDTO;
import bg.softuni.mobilelele.model.dto.ShowOfferDTO;
import bg.softuni.mobilelele.model.entities.Offer;
import bg.softuni.mobilelele.model.repositories.ModelRepository;
import bg.softuni.mobilelele.model.repositories.OfferRepository;
import bg.softuni.mobilelele.services.interfaces.OfferService;
import bg.softuni.mobilelele.utils.CurrentUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    private final ModelRepository modelRepository;

    private CurrentUser currentUser;

    private final ModelMapper mapper;

    @Override
    public void uploadOffer(OfferDTO offerDTO) {
        Offer offer = mapper.map(offerDTO, Offer.class);
        offer.setCreated(LocalDateTime.now());
        offer.setSeller(currentUser.getUser());

        offerRepository.saveAndFlush(offer);
    }

    @Override
    public List<ShowOfferDTO> allOffers() {
        List<ShowOfferDTO> offers = offerRepository.findAll().stream().map(offer -> mapper.map(offer, ShowOfferDTO.class))
                .collect(Collectors.toList());
        offers.forEach(showOfferDTO -> showOfferDTO
                .setBrandName(modelRepository.findByName(showOfferDTO.getModelName()).getBrand().getName()));

        return offers;
    }

    @Override
    public ShowDetailedOfferDTO showDetailedOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId).orElse(null);

        ShowDetailedOfferDTO offerDTO = mapper.map(offer, ShowDetailedOfferDTO.class);
        offerDTO.setBrandName(offer.getModel().getBrand().getName());

        return offerDTO;
    }

    @Override
    public void updateOffer(Long offerId, OfferDTO offerDTO) {
        Offer offer = offerRepository.findById(offerId).orElse(null);
        offer.setModel(modelRepository.findByName(offerDTO.getModel().getName()));
        offer.setPrice(offerDTO.getPrice());
        offer.setEngine(offerDTO.getEngine());
        offer.setTransmission(offerDTO.getTransmission());
        offer.setYear(offerDTO.getYear());
        offer.setMileage(offerDTO.getMileage());
        offer.setDescription(offerDTO.getDescription());
        offer.setImageUrl(offerDTO.getImageUrl());
        offer.setModified(LocalDateTime.now());

        offerRepository.saveAndFlush(offer);
    }

    @Override
    public void removeOffer(Long offerId) {
        offerRepository.deleteById(offerId);
    }
}

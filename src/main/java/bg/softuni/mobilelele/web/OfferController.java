package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.OfferDTO;
import bg.softuni.mobilelele.model.repositories.ModelRepository;
import bg.softuni.mobilelele.services.interfaces.OfferService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class OfferController {
    private final OfferService offerService;

    private final ModelRepository modelRepository;

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("models", modelRepository.findAll());

        return "offer-add";
    }

    @ModelAttribute("offerModel")
    public OfferDTO initOffer() {
        return new OfferDTO();
    }

    @PostMapping("/add")
    public String addOffer(@Valid OfferDTO offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offers/add";
        }
        offerService.uploadOffer(offerModel);

        return "redirect:/offers/all";
    }

    @GetMapping("/all")
    public String allOffers(Model model, HttpSession httpSession) {
        model.addAttribute("offers", offerService.allOffers());

        if (httpSession.getAttribute("offerId") != null) {
            httpSession.removeAttribute("offerId");
        }
        return "offers";
    }

    @GetMapping("/details")
    public String offerDetails(@RequestParam(required = false) Long offerId, Model model, HttpSession httpSession) {

        if (httpSession.getAttribute("offerId") == null) {
            httpSession.setAttribute("offerId", offerId);
        }
        model.addAttribute("detailedOffer", offerService.showDetailedOffer((Long) httpSession.getAttribute("offerId")));

        return "details";
    }

    @GetMapping("/update")
    public String offerUpdate(Model model) {
        model.addAttribute("models", modelRepository.findAll());

        return "update";
    }

    @PostMapping("/update")
    public String offerUpdate(@Valid OfferDTO offerModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offers/update";
        }
        offerService.updateOffer((Long) httpSession.getAttribute("offerId"), offerModel);

        return "redirect:/offers/details";
    }

    @GetMapping("/delete")
    public String deleteOffer(HttpSession httpSession) {
        offerService.removeOffer((Long) httpSession.getAttribute("offerId"));

        return "redirect:/offers/all";
    }
}

package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.repositories.BrandRepository;
import bg.softuni.mobilelele.services.interfaces.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class BrandController {
    private final BrandRepository brandRepository;

    private final ModelService modelService;

    @GetMapping("/all")
    public String allBrands(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("models", modelService.allModels());

        return "brands";
    }
}

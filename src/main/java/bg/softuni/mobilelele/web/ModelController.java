package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.AddModelDTO;
import bg.softuni.mobilelele.services.interfaces.ModelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/models")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ModelController {
    private final ModelService modelService;

    @GetMapping("/add")
    public String addModel() {
        return "model-add";
    }

    @ModelAttribute("vehicleModel")
    public AddModelDTO initVehicleModel() {
        return new AddModelDTO();
    }

    @PostMapping("/add")
    public String addModel(@Valid AddModelDTO vehicleModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("vehicleModel", vehicleModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vehicleModel",
                    bindingResult);
            return "redirect:/models/add";
        }
        modelService.addVehicleModel(vehicleModel);

        return "redirect:/brands/all";
    }

    @GetMapping("/delete")
    public String deleteModel(Model model) {
        model.addAttribute("models", modelService.allModels());

        return "model-delete";
    }

    @PostMapping("/delete")
    public String deleteModel(@RequestParam(required = false) Long modelId, RedirectAttributes redirectAttributes) {

        if (!modelService.deleteModel(modelId)) {
            redirectAttributes.addFlashAttribute("modelNotDeleted", "Selecting a model to delete is required.");

            return "redirect:/models/delete";
        }

        return "redirect:/brands/all";
    }
}

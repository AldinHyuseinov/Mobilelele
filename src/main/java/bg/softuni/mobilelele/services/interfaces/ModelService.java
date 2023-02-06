package bg.softuni.mobilelele.services.interfaces;

import bg.softuni.mobilelele.model.dto.AddModelDTO;
import bg.softuni.mobilelele.model.entities.Model;

import java.util.List;

public interface ModelService {
    void addVehicleModel(AddModelDTO addModelDTO);

    List<Model> allModels();

    boolean deleteModel(Long modelId);
}

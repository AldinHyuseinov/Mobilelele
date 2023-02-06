package bg.softuni.mobilelele.services.impl;

import bg.softuni.mobilelele.model.dto.AddModelDTO;
import bg.softuni.mobilelele.model.entities.Brand;
import bg.softuni.mobilelele.model.entities.Model;
import bg.softuni.mobilelele.model.repositories.BrandRepository;
import bg.softuni.mobilelele.model.repositories.ModelRepository;
import bg.softuni.mobilelele.services.interfaces.ModelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

    private final BrandRepository brandRepository;

    private final ModelMapper mapper;

    @Override
    public void addVehicleModel(AddModelDTO addModelDTO) {
        Model model = mapper.map(addModelDTO, Model.class);
        model.setCreated(LocalDateTime.now());

        Optional<Brand> optBrand = brandRepository.findByName(addModelDTO.getBrandName());
        Brand brand;

        if (optBrand.isEmpty()) {
            brand = new Brand();
            brand.setName(addModelDTO.getBrandName());
            brand.setCreated(LocalDateTime.now());

            brandRepository.saveAndFlush(brand);
        } else {
            brand = optBrand.get();
        }
        model.setBrand(brand);

        modelRepository.saveAndFlush(model);
    }

    @Override
    public List<Model> allModels() {
        return modelRepository.findAll();
    }

    @Override
    public boolean deleteModel(Long modelId) {

        if (modelId == null) {
            return false;
        }
        modelRepository.deleteById(modelId);

        return true;
    }
}

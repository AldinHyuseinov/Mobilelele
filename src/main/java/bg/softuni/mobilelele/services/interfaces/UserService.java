package bg.softuni.mobilelele.services.interfaces;

import bg.softuni.mobilelele.model.dto.LoginUserDTO;
import bg.softuni.mobilelele.model.dto.RegisterUserDTO;
import bg.softuni.mobilelele.model.dto.ShowUserInfoDTO;
import bg.softuni.mobilelele.utils.CurrentUser;

import java.util.List;

public interface UserService {
    void registerUser(RegisterUserDTO registerUserDTO);

    void loginUser(LoginUserDTO loginUserDTO, CurrentUser currentUser);

    List<ShowUserInfoDTO> allUsers();

    void banUser(Long userId);

    void promoteUser(Long userId);

    void demoteUser(Long userId);
}

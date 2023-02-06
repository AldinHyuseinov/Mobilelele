package bg.softuni.mobilelele.services.impl;

import bg.softuni.mobilelele.model.dto.LoginUserDTO;
import bg.softuni.mobilelele.model.dto.RegisterUserDTO;
import bg.softuni.mobilelele.model.dto.ShowUserInfoDTO;
import bg.softuni.mobilelele.model.entities.User;
import bg.softuni.mobilelele.model.enums.Role;
import bg.softuni.mobilelele.model.repositories.UserRepository;
import bg.softuni.mobilelele.model.repositories.UserRoleRepository;
import bg.softuni.mobilelele.services.interfaces.UserService;
import bg.softuni.mobilelele.utils.CurrentUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final ModelMapper mapper;

    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        User user = mapper.map(registerUserDTO, User.class);
        user.setCreated(LocalDateTime.now());
        user.setRole(userRoleRepository.findByRole(registerUserDTO.getRole()));

        userRepository.saveAndFlush(user);
    }

    @Override
    public void loginUser(LoginUserDTO loginUserDTO, CurrentUser currentUser) {
        Optional<User> user = userRepository.findByUsernameAndPassword(loginUserDTO.getUsername(), loginUserDTO.getPassword());

        if (user.isPresent()) {
            currentUser.setUser(user.get());
            currentUser.setLoggedIn(true);
        }
    }

    @Override
    public List<ShowUserInfoDTO> allUsers() {
        return userRepository.findAll().stream().map(user -> mapper.map(user, ShowUserInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void banUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void promoteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user.getRole().getRole().compareTo(Role.User) == 0) {
            user.setRole(userRoleRepository.findByRole(Role.Admin));
            user.setModified(LocalDateTime.now());
        }

        userRepository.saveAndFlush(user);
    }

    @Override
    public void demoteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user.getRole().getRole().compareTo(Role.Admin) == 0) {
            user.setRole(userRoleRepository.findByRole(Role.User));
            user.setModified(LocalDateTime.now());
        }

        userRepository.saveAndFlush(user);
    }
}

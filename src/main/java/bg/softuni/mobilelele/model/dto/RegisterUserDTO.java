package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterUserDTO {
    @Size(min = 2, max = 20, message = "First name is required and should be between 2 and 20 symbols.")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last name is required and should be between 2 and 20 symbols.")
    private String lastName;

    @Size(min = 2, max = 20, message = "Username is required and should be between 2 and 20 symbols.")
    private String username;

    @Size(min = 5, message = "Password is required and should be at least 5 symbols.")
    private String password;

    @NotNull(message = "Selecting a role is required.")
    private Role role;
}

package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ShowUserInfoDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private Role role;

    private LocalDateTime created;

    private LocalDateTime modified;
}

package bg.softuni.mobilelele.utils;

import bg.softuni.mobilelele.model.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@NoArgsConstructor
@Getter
@Setter
public class CurrentUser {
    private User user;

    private boolean loggedIn;
}

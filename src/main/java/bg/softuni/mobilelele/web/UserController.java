package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.LoginUserDTO;
import bg.softuni.mobilelele.model.dto.RegisterUserDTO;
import bg.softuni.mobilelele.services.interfaces.UserService;
import bg.softuni.mobilelele.utils.CurrentUser;
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
@RequestMapping("/users")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserService userService;

    private CurrentUser currentUser;

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @ModelAttribute("userModel")
    public RegisterUserDTO userModelInit() {
        return new RegisterUserDTO();
    }

    @PostMapping("/register")
    public String register(@Valid RegisterUserDTO userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/users/register";
        }
        userService.registerUser(userModel);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @ModelAttribute("loginUser")
    public LoginUserDTO loginUserInit() {
        return new LoginUserDTO();
    }

    @PostMapping("/login")
    public String login(LoginUserDTO loginUser, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        userService.loginUser(loginUser, currentUser);

        if (currentUser.isLoggedIn()) {
            httpSession.setAttribute("loggedInUser", currentUser);
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("invalidUser", "Invalid username or password.");

        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        currentUser.setLoggedIn(false);
        currentUser.setUser(null);
        httpSession.invalidate();

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.allUsers());

        return "users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long userId) {
        userService.banUser(userId);

        return "redirect:/users/all";
    }

    @GetMapping("/promote")
    public String promoteUser(@RequestParam Long userId) {
        userService.promoteUser(userId);

        return "redirect:/users/all";
    }

    @GetMapping("/demote")
    public String demoteUser(@RequestParam Long userId) {
        userService.demoteUser(userId);

        return "redirect:/users/all";
    }
}

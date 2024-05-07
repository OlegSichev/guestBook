package oleg.sichev.guestbook.controller;

import oleg.sichev.guestbook.DTO.UserDTO;
import oleg.sichev.guestbook.model.User;
import oleg.sichev.guestbook.repository.UserRepository;
import oleg.sichev.guestbook.service.UserService;
import oleg.sichev.guestbook.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) throws Exception {
        // Проверка того, что пароль и подтверждение совпадают
        if (!password.equals(confirmPassword)) {
            return "redirect:/register?error";
        }

        // Зашифровать пароль
        String encodedPassword = passwordEncoder.encode(password);

        // Создать нового пользователя (пример: сохранение в базе данных)
        UserDTO userDTO = new UserDTO(username, encodedPassword);
        //UserServiceImpl userService = new UserServiceImpl();
        userService.registerNewUserAccount(userDTO);

        // Перенаправить на страницу входа после успешной регистрации
        return "redirect:/login";
    }
}

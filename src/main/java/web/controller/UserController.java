package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Страница со всеми пользователями
    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    // Страница добавления пользователя
    @GetMapping("/new")
    public String newUser() {
        return "new-user";
    }

    // Сохранение нового пользователя
    @PostMapping("/save")
    public String saveUser(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email) {

        User user = new User(name, lastName, email);
        userService.save(user);

        return "redirect:/users";
    }

    // Страница редактирования пользователя
    @GetMapping("/edit")
    public String editUser(
            @RequestParam("id") Long id,
            Model model) {

        User user = userService.findById(id);

        if (user == null) {
            return "redirect:/users";
        }

        model.addAttribute("user", user);
        return "edit-user";
    }

    // Обновление пользователя
    @PostMapping("/update")
    public String updateUser(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email) {

        User user = userService.findById(id);

        if (user != null) {
            user.setName(name);
            user.setLastName(lastName);
            user.setEmail(email);

            userService.update(user);
        }

        return "redirect:/users";
    }

    // Удаление пользователя
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);

        return "redirect:/users";
    }
}

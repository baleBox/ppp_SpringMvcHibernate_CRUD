package balebox.springmvc.controller;

import balebox.springmvc.model.User;
import balebox.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/users")
    public String getAllUsers(@RequestParam(value = "alluser", required = false) String usernum, Model model) {
        model.addAttribute("users", userService.getAllUsers(usernum));
        return "users";
    }

    @GetMapping("/user_id")
    public String getUser(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("user", userService.getUserById(Long.parseLong(id)));
        return "show";
    }

    @GetMapping("/new_user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam("name") String name, @RequestParam("lastName") String lastName,
                             @RequestParam("age") int age, @RequestParam("email") String email, Model model) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);
        model.addAttribute("user", user);
        userService.addUser(user);

        return "redirect:/users";
    }

    @PostMapping("/edit_user")
    public String editUser(@RequestParam("id") String id, Model model) {
        model.addAttribute("user", userService.getUserById(Long.parseLong(id)));
        return "edit_user";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") String id, @ModelAttribute("user") User user) {
        userService.editUser(user, Long.parseLong(id));
        return "redirect:/users";
    }

    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam(value = "id") String id) {
        userService.deleteUser(Long.parseLong(id));
        return "redirect:/users";
    }
}

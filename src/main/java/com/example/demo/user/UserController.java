package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/{userId}")
    public String getOneUser(@PathVariable int userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "user-details";
    }

    @GetMapping("/major/{major}")
    public String getUsersByMajor(@PathVariable String major, Model model) {
        model.addAttribute("userList", userService.getUsersByMajor(major));
        return "user-list";
    }

    @GetMapping("")
    public String getUsersByName(@RequestParam(name="name") String name, Model model) {
        List<User> userList;

        if (name != null)
            userList = userService.getUsersByName(name);
        else
            userList = userService.getAllUsers();

        model.addAttribute("userList", userList);
        return "user-list";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        if (userService.authenticateUser(email, password))
            return "redirect:/dashboard";  // dashboard.html
        else {
            model.addAttribute("error", "Invaild email or password");
            return "login-register";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password, Model model) {
        if (userService.registerUser(name, email, password))
            return "redirect:/login";
        else {
            model.addAttribute("error", "Email is already used");
            return "login-register";
        }
    }

    @GetMapping("/createForm")
    public String showCreateForm() {
        return "user-create";
    }
    @PostMapping("/new")
    public String addNewUser(User user) {
        userService.saveUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/update/{userId}")
    public String showUpdateForm(@PathVariable int userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "user-update";
    }
    @PostMapping("/update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/users/" + user.getUserId();
    }

    @GetMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable int userId) {
        userService.deleteUserById(userId);
        return "redirect:/users/all";
    }
}

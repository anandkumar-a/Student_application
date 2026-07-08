package com.demo.studentapp.controller;

    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import com.demo.studentapp.entity.User;
    import com.demo.studentapp.service.UserService;

    @Controller
    public class UserController {

        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        // Display Login Page
        @GetMapping("/")
        public String loginPage() {
            return "login";
        }

        // Process Login
        @PostMapping("/login")
        public String login(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {

            User user = userService.login(username, password);

            if (user != null) {

                model.addAttribute("username", user.getUsername());

                return "dashboard";
            }

            model.addAttribute("error", "Invalid Username or Password");

            return "login";
        }

        // Display Registration Page
        @GetMapping("/register")
        public String registerPage(Model model) {

            model.addAttribute("user", new User());

            return "register";
        }

        // Save New User
        @PostMapping("/register")
        public String registerUser(@ModelAttribute User user,
                                Model model) {

            try {
                userService.saveUser(user);
                model.addAttribute("success", "Registration Successful! Please Login.");
                return "login";
            } catch (Exception e) {
                model.addAttribute("error", "Registration failed. Please try again.");
                model.addAttribute("user", user);
                return "register";
            }
        }

        // Dashboard
        @GetMapping("/dashboard")
        public String dashboard() {

            return "dashboard";
        }

        // Logout
        @GetMapping("/logout")
        public String logout() {

            return "redirect:/";
        }

    }
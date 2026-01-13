package com.example.dailytasks.model.web;

import com.example.dailytasks.model.dto.UserRegisterDTO;
import com.example.dailytasks.model.entity.UserEntity;
import com.example.dailytasks.model.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class RegistrationController {

    private final UserEntityService userEntityService;

    public RegistrationController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }


    @ModelAttribute("registerDTO")
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }


    @GetMapping("/register")
    public String register() {
        return "registration";
    }


    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO registerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerDTO", bindingResult);
            redirectAttributes.addFlashAttribute("errorMessage", "The password confirmation does not match!");


            return "redirect:/users/register";
        }

        boolean success = userEntityService.registerUser(registerDTO);

        if (!success) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email or username already exists!");
            return "redirect:/users/register";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Registration was successful! Please log in.");
        return "redirect:/users/login";
    }
}
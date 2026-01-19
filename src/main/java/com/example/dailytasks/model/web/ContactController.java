package com.example.dailytasks.model.web;

import com.example.dailytasks.model.dto.ContactDTO;
import com.example.dailytasks.model.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @ModelAttribute("contactDTO")
    public ContactDTO contactDTO() {
        return new ContactDTO();
    }

    @GetMapping("/contact")
    public String showContactForm() {
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute("contactDTO") ContactDTO contactDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please fill all fields correctly.");
            return "redirect:/contact";
        }

        contactService.saveContact(contactDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Your message has been sent!");
        return "redirect:/contact";
    }
}

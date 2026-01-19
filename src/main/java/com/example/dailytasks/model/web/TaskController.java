package com.example.dailytasks.model.web;

import com.example.dailytasks.model.dto.TaskDTO;
import com.example.dailytasks.model.dto.UserRegisterDTO;
import com.example.dailytasks.model.entity.Task;
import com.example.dailytasks.model.entity.UserEntity;
import com.example.dailytasks.model.service.TaskService;
import com.example.dailytasks.model.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TaskController {


    private final TaskService taskService;
    private final UserEntityService userEntityService;

    public TaskController(TaskService taskService, UserEntityService userEntityService) {
        this.taskService = taskService;
        this.userEntityService = userEntityService;
    }


    @ModelAttribute("taskDTO")
    public TaskDTO taskDTO() {
        return new TaskDTO();
    }


    @GetMapping("/task")
    public String task(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        UserEntity user = userEntityService.findByUsername(userDetails.getUsername());
        List<Task> tasks = taskService.getTasksForUser(user);

        model.addAttribute("tasks", tasks);
        return "task";
    }



    @PostMapping("/task")
    public String saveTask(@Valid TaskDTO taskDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskDTO", taskDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.taskDTO", bindingResult);
            redirectAttributes.addFlashAttribute("errorMessage", "Information does not match!");

            return "redirect:/task";
        }

        try {
            UserEntity user = userEntityService.findByUsername(userDetails.getUsername());
            taskService.saveTask(taskDTO, user);
            redirectAttributes.addFlashAttribute("successMessage", "Task save was successful!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Task save was not successful!");
        }

        return "redirect:/task";
    }


    @PostMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetails userDetails,
                             RedirectAttributes redirectAttributes) {

        try {
            UserEntity user = userEntityService.findByUsername(userDetails.getUsername());
            taskService.deleteTask(id, user);
            redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete task!");
        }

        return "redirect:/task";
    }


}

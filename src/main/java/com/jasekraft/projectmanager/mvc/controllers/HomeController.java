package com.jasekraft.projectmanager.mvc.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jasekraft.projectmanager.mvc.models.LoginUser;
import com.jasekraft.projectmanager.mvc.models.Project;
import com.jasekraft.projectmanager.mvc.models.User;
import com.jasekraft.projectmanager.mvc.services.ProjectService;
import com.jasekraft.projectmanager.mvc.services.UserService;

    
@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;
    private ProjectService projectServ;
    
    
    public HomeController(UserService userServ, ProjectService projectServ) {
		super();
		this.userServ = userServ;
		this.projectServ = projectServ;
	}

	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
    	if(session.getAttribute("user_id") != null) {
    		long user_id = (long) session.getAttribute("user_id");
    		User user = userServ.getUserById(user_id);
    		model.addAttribute("user", user);
    		List<Project> allProjects = projectServ.allProjects();
    		model.addAttribute("allProjects", allProjects);
    		return "dashboard.jsp";
    	}
    	else {
    		return "redirect:/";
    	}
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/dashboard";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
    
}

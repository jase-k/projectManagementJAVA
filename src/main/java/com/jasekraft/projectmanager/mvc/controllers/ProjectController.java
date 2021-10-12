package com.jasekraft.projectmanager.mvc.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jasekraft.projectmanager.mvc.models.Project;
import com.jasekraft.projectmanager.mvc.models.User;
import com.jasekraft.projectmanager.mvc.services.ProjectService;
import com.jasekraft.projectmanager.mvc.services.UserService;

@Controller
public class ProjectController {
	private final ProjectService projectServ;
	private final UserService userServ;
	
	
	public ProjectController(ProjectService projectServ, UserService userServ) {
		this.projectServ = projectServ;
		this.userServ = userServ;
	}


	@RequestMapping("/project/new")
    public String newProject(@ModelAttribute("project") Project proj, Model model, HttpSession session) {
    	if(session.getAttribute("user_id") != null) {
    		Long user_id = (long) session.getAttribute("user_id");
			model.addAttribute("user", userServ.getUserById(user_id));
	        return "/projects/new.jsp";
    	}    	else {
    		return "redirect:/";
    	}
    }
	
	@RequestMapping(value="/projects", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("project") Project proj, BindingResult result) {
        if (result.hasErrors()) {
            return "/projects/new.jsp";
        } else {
            projectServ.createProject(proj);
            return "redirect:/dashboard";
        }
    }
	
	@RequestMapping(value="/projects", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("project") Project proj, BindingResult result) {
        if (result.hasErrors()) {
            return "/projects/edit.jsp";
        } else {
            projectServ.updateProject(proj);
            return "redirect:/dashboard";
        }
    }
	
	@RequestMapping("/projects/{id}")
    public String showProject(HttpSession session, Model model, @PathVariable("id") long id) {
    	if(session.getAttribute("user_id") != null) {
    		Long user_id = (long) session.getAttribute("user_id");
    		model.addAttribute("project", projectServ.findProject(id));
			model.addAttribute("user", userServ.getUserById(user_id));
	        return "/projects/show.jsp";
    	}    	else {
    		return "redirect:/";
    	}
    }
	
	@RequestMapping("/projects/{id}/edit")
    public String editProject(HttpSession session, Model model, @PathVariable("id") long id) {
    	if(session.getAttribute("user_id") != null) {
    		Long user_id = (long) session.getAttribute("user_id");
    		model.addAttribute("project", projectServ.findProject(id));
			model.addAttribute("user", userServ.getUserById(user_id));
	        return "/projects/edit.jsp";
    	}    	else {
    		return "redirect:/";
    	}
    }
	
	@RequestMapping(value="/projects/{id}/join")
    public String joinProject(HttpSession session, Model model, @PathVariable("id") long id) {
    	if(session.getAttribute("user_id") != null) {
    		Long user_id = (long) session.getAttribute("user_id");
    		User user = userServ.getUserById(user_id);
    		Project project = projectServ.findProject(id);
    		project.addTeamMember(user);
    		projectServ.updateProject(project);
	        return "redirect:/dashboard";
    	}    	else {
    		return "redirect:/";
    	}
    }
	
	@RequestMapping(value="/projects/{id}/leave")
    public String leaveProject(HttpSession session, Model model, @PathVariable("id") long id) {
    	if(session.getAttribute("user_id") != null) {
    		Long user_id = (long) session.getAttribute("user_id");
    		User user = userServ.getUserById(user_id);
    		Project project = projectServ.findProject(id);
    		project.removeTeamMember(user);
    		projectServ.updateProject(project);
	        return "redirect:/dashboard";
    	}    	else {
    		return "redirect:/";
    	}
    }
}

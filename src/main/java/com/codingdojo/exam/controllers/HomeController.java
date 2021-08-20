package com.codingdojo.exam.controllers;
    
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.exam.models.LoginUser;
import com.codingdojo.exam.models.Task;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.services.TaskService;
import com.codingdojo.exam.services.UserService;
    

    
@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;
    
    @Autowired
    private TaskService taskServ;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
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
        return "redirect:/tasks";
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
        return "redirect:/tasks";
    }
    
    @RequestMapping("/tasks")
    public String home(HttpSession session, Model model) {
    	
    	if(session.getAttribute("user_id") == null) {
    		return "redirect:/";
    	} else {
	    	Long userId = (Long) session.getAttribute("user_id");
	    	User u = userServ.findUserById(userId);
	    	List<Task> tasks = taskServ.allTasks();
	    	model.addAttribute("tasks", tasks);
	    	model.addAttribute("user", u);
	    	return "/homePage.jsp";
    	}
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    
    @RequestMapping("/tasks/new")
    public String createTaskPage(@ModelAttribute("task") Task task, Model model, HttpSession session) {
    	List<User> users = userServ.allUsers();
    	Long id = (Long) session.getAttribute("user_id");
    	User user = userServ.findUserById(id);
    	model.addAttribute("user", user);
    	model.addAttribute("users", users);
    	return "/newTask.jsp";
    }
    
    @RequestMapping(value="/tasks", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		List<User> users = userServ.allUsers();
    		model.addAttribute("users", users);
			return "/newTask.jsp";
		} else {
    	taskServ.createTask(task);
    	return "redirect:/tasks";
		}
    }
    
    @RequestMapping("/tasks/{taskId}")
    public String showTaskPage(@PathVariable("taskId") Long taskId, Model model) {
    	Task task = taskServ.findTask(taskId);
    	model.addAttribute("task", task);
    	return "taskPage.jsp";
    }
    
    @RequestMapping("/tasks/{taskId}/edit")
    public String edit(@PathVariable("taskId") Long taskId, Model model, HttpSession session) {
    	Task t = taskServ.findTask(taskId);
    	List<User> users = userServ.allUsers();
    	User user = t.getCreator();
    	model.addAttribute("user", user);  
    	model.addAttribute("task", t);
    	model.addAttribute("users", users);
    	return "/editTask.jsp";
    }
    
    @RequestMapping(value="/tasks/{id}", method=RequestMethod.PUT)
    public String update(Model model, HttpSession session, @Valid @ModelAttribute("task") Task task, BindingResult result) {
    	if(result.hasErrors()) {
    		List<User> users = userServ.allUsers();
    		model.addAttribute("users", users);
    		Long id = (Long) session.getAttribute("user_id");
        	User user = userServ.findUserById(id);
        	model.addAttribute("user", user);
			return "/editTask.jsp";
    	} else {
    		taskServ.updateTask(task.getId(), task.getContent(), task.getPriority(), task.getCreator(), task.getAssignee());
    		return "redirect:/tasks";
    	}
    }
    
	
    
    
}
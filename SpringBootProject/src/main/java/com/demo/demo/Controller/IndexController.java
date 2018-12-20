package com.demo.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping(value= "/")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping(value = "/index")
    public String main(Model model) {
        return "home";
    }

    @GetMapping(value = "/upload")
	public String uploadMaterial() {
		return "upload_material.html";
	}

	@GetMapping(value = "/project_list")
	public String approvedProjectList() {
		return "/middle/approved_project_list";
	}
}

package com.demo.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.demo.DemoApplication;
import com.demo.demo.Service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	public ProjectService projectService;
	
	@GetMapping("/list")
	public String projectList(Model model, @PageableDefault(size = DemoApplication.PAGE_SIZE) Pageable pageable) {
		model.addAttribute("datas", projectService.findAll(pageable));
		return "project/list";
	}
}

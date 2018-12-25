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
}

package com.myclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.dto.UserDto;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<UserDto> list = userService.findAll();
		model.addAttribute("users", list);
		return "user/index";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		List<UserDto> list = userService.search(keyword);
		model.addAttribute("users", list);
		return "user/index";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("user", new UserDto());
		model.addAttribute("roles", roleService.findAll());
		return "user/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String post(ModelMap model, @ModelAttribute("user") UserDto user, 
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "user/add";
		}
		
		try {
			userService.add(user);
			return "redirect:/user";
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Thêm mới thất bại!");
		return "user/add";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, ModelMap model) {
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("roles", roleService.findAll());
		return "user/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(ModelMap model, @ModelAttribute("user") UserDto user,
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "user/edit";
		}
		
		try {
			System.out.println(user.getId());
			userService.update(user);
			return "redirect:/user";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Cập nhật thất bại!");
		return "user/edit";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		try {
			userService.delete(id);			
		} catch (Exception e) {

		}
		return "redirect:/user";
	}
}

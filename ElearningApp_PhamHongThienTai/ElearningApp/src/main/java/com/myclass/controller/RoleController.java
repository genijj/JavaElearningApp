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

import com.myclass.dto.RoleDto;
import com.myclass.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<RoleDto> list = roleService.findAll();
		model.addAttribute("roles", list);
		return "role/index";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		List<RoleDto> list = roleService.search(keyword);
		model.addAttribute("roles", list);
		return "role/index";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("role", new RoleDto());
		return "role/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String post(ModelMap model, @ModelAttribute("role") RoleDto role, 
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "role/add";
		}
		
		try {
			roleService.add(role);
			return "redirect:/role";
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Thêm mới thất bại!");
		return "role/add";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, ModelMap model) {

		model.addAttribute("role", roleService.findById(id));
		return "role/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(ModelMap model, @ModelAttribute("role") RoleDto role,
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "role/edit";
		}
		
		try {
			System.out.println(role.getId());
			roleService.update(role);
			return "redirect:/role";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Cập nhật thất bại!");
		return "role/edit";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		try {
			roleService.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/role";
	}
}

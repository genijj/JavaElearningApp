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

import com.myclass.dto.TargetDto;
import com.myclass.service.TargetService;

@Controller
@RequestMapping("/target")
public class TargetController {
	@Autowired
	private TargetService targetService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<TargetDto> list = targetService.findAll();
		model.addAttribute("targets", list);
		return "target/index";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		List<TargetDto> list = targetService.search(keyword);
		model.addAttribute("targets", list);
		return "target/index";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("target", new TargetDto());
		return "target/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String post(ModelMap model, @ModelAttribute("target") TargetDto target, 
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "target/add";
		}
		
		try {
			targetService.add(target);
			return "redirect:/target";
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Thêm mới thất bại!");
		return "target/add";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, ModelMap model) {

		model.addAttribute("target", targetService.findById(id));
		return "target/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(ModelMap model, @ModelAttribute("target") TargetDto target,
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "target/edit";
		}
		
		try {
			System.out.println(target.getId());
			targetService.update(target);
			return "redirect:/target";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Cập nhật thất bại!");
		return "target/edit";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		try {
			targetService.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/target";
	}
}

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

import com.myclass.dto.CourseDto;
import com.myclass.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<CourseDto> list = courseService.findAll();
		model.addAttribute("roles", list);
		return "course/index";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		List<CourseDto> list = courseService.search(keyword);
		model.addAttribute("roles", list);
		return "course/index";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("role", new CourseDto());
		return "course/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String post(ModelMap model, @ModelAttribute("course") CourseDto course, 
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "course/add";
		}
		
		try {
			courseService.add(course);
			return "redirect:/course";
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Thêm mới thất bại!");
		return "course/add";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, ModelMap model) {

		model.addAttribute("role", courseService.findById(id));
		return "role/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(ModelMap model, @ModelAttribute("course") CourseDto course,
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "course/edit";
		}
		
		try {
			System.out.println(course.getId());
			courseService.update(course);
			return "redirect:/course";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Cập nhật thất bại!");
		return "course/edit";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		try {
			courseService.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/course";
	}
}

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

import com.myclass.dto.VideoDto;
import com.myclass.service.VideoService;

@Controller
@RequestMapping("/video")
public class VideoController {
	@Autowired
	private VideoService videoService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<VideoDto> list = videoService.findAll();
		model.addAttribute("videos", list);
		return "video/index";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		List<VideoDto> list = videoService.search(keyword);
		model.addAttribute("videos", list);
		return "video/index";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("video", new VideoDto());
		return "video/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String post(ModelMap model, @ModelAttribute("video") VideoDto video, 
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "video/add";
		}
		
		try {
			videoService.add(video);
			return "redirect:/video";
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Thêm mới thất bại!");
		return "video/add";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, ModelMap model) {

		model.addAttribute("video", videoService.findById(id));
		return "video/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(ModelMap model, @ModelAttribute("video") VideoDto video,
			BindingResult errors) {
		if(errors.hasErrors()) {
			return "video/edit";
		}
		
		try {
			System.out.println(video.getId());
			videoService.update(video);
			return "redirect:/video";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Cập nhật thất bại!");
		return "video/edit";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		try {
			videoService.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/video";
	}
}

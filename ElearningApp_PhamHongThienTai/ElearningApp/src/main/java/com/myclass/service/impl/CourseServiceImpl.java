package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.CourseDto;
import com.myclass.entity.Category;
import com.myclass.entity.Course;
import com.myclass.repository.CategoryRepository;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CourseDto> findAll() {
		List<CourseDto> dtos = new ArrayList<CourseDto>();
		List<Course> courses = courseRepository.findAll();
		for(Course course : courses) {
			Category category = categoryRepository.findById(course.getId()).get();
			dtos.add(new CourseDto(course.getId(), course.getTitle(), course.getImage(), course.getLecturesCount(), course.getHourCount(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getPromotionPrice(), course.getDescription(), course.getContent(), course.getCategoryId(), course.getLastUpdate(), category.getTitle()));
		}
		return dtos;
	}

	@Override
	public CourseDto findById(int id) {
		Course course = courseRepository.findById(id).get();
		Category category = categoryRepository.findById(course.getId()).get();
		return new CourseDto(course.getId(), course.getTitle(), course.getImage(), course.getLecturesCount(), course.getHourCount(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getPromotionPrice(), course.getDescription(), course.getContent(), course.getCategoryId(), course.getLastUpdate(), category.getTitle());
	}

	@Override
	public void add(CourseDto dto) {
		Course course = new Course();
		course.setTitle(dto.getTitle());
		course.setImage(dto.getImage());
		course.setLecturesCount(dto.getLecturesCount());
		course.setHourCount(dto.getHourCount());
		course.setViewsCount(dto.getViewCount());
		course.setPrice(dto.getPrice());
		course.setDiscount(dto.getDiscount());
		course.setPromotionPrice(dto.getPromotionPrice());
		course.setDescription(dto.getDescription());
		course.setContent(dto.getContent());
		course.setCategoryId(dto.getCategoryId());
		courseRepository.save(course);
	}

	@Override
	public void update(CourseDto dto) {
		Course course = courseRepository.findById(dto.getId()).get();
		course.setTitle(dto.getTitle());
		course.setImage(dto.getImage());
		course.setLecturesCount(dto.getLecturesCount());
		course.setHourCount(dto.getHourCount());
		course.setViewsCount(dto.getViewCount());
		course.setPrice(dto.getPrice());
		course.setDiscount(dto.getDiscount());
		course.setPromotionPrice(dto.getPromotionPrice());
		course.setDescription(dto.getDescription());
		course.setContent(dto.getContent());
		course.setCategoryId(dto.getCategoryId());
		courseRepository.save(course);		
	}

	@Override
	public void delete(int id) {
		courseRepository.deleteById(id);		
	}

	@Override
	public List<CourseDto> search(String keyword) {
		List<CourseDto> dtos = new ArrayList<CourseDto>();
		List<Course> courses = courseRepository.search(keyword);
		for(Course course : courses) {
			Category category = categoryRepository.findById(course.getId()).get();
			dtos.add(new CourseDto(course.getId(), course.getTitle(), course.getImage(), course.getLecturesCount(), course.getHourCount(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getPromotionPrice(), course.getDescription(), course.getContent(), course.getCategoryId(), course.getLastUpdate(), category.getTitle()));
		}
		return dtos;
	}	
}

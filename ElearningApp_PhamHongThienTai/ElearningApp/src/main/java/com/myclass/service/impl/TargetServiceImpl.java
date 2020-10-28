package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.TargetDto;
import com.myclass.entity.Course;
import com.myclass.entity.Target;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.TargetRepository;
import com.myclass.service.TargetService;
@Service
public class TargetServiceImpl implements TargetService{
	
	@Autowired
	private TargetRepository targetRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<TargetDto> findAll() {
		List<TargetDto> dtos = new ArrayList<TargetDto>();
		List<Target> targets = targetRepository.findAll();
		for(Target target : targets) {
			Course course = courseRepository.findById(target.getCourseId()).get();
			dtos.add(new TargetDto(target.getId(), target.getTitle(), target.getCourseId(), course.getTitle()));
		}
		return dtos;
	}

	@Override
	public TargetDto findById(int id) {
		Target target = targetRepository.findById(id).get();
		Course course = courseRepository.findById(target.getCourseId()).get();
		return new TargetDto(target.getId(), target.getTitle(), target.getCourseId(), course.getTitle());
	}

	@Override
	public void add(TargetDto dto) {
		Target target = new Target();
		target.setTitle(dto.getTitle());
		target.setCourseId(dto.getCourseId());
		targetRepository.save(target);
	}

	@Override
	public void update(TargetDto dto) {
		Target target = targetRepository.findById(dto.getId()).get();
		target.setTitle(dto.getTitle());
		target.setCourseId(dto.getCourseId());
		targetRepository.save(target);
	}

	@Override
	public void delete(int id) {
		targetRepository.deleteById(id);
	}

	@Override
	public List<TargetDto> search(String keyword) {
		List<TargetDto> dtos = new ArrayList<TargetDto>();
		List<Target> targets = targetRepository.findByTitle(keyword);
		for(Target target : targets) {
			Course course = courseRepository.findById(target.getCourseId()).get();
			dtos.add(new TargetDto(target.getId(), target.getTitle(), target.getCourseId(), course.getTitle()));
		}
		return dtos;
	}

}

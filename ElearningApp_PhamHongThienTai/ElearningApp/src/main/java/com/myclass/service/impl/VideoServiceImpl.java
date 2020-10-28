package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.VideoDto;
import com.myclass.entity.Course;
import com.myclass.entity.Video;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService{
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<VideoDto> findAll() {
		List<VideoDto> dtos = new ArrayList<VideoDto>();
		List<Video> videos = videoRepository.findAll();
		for(Video video : videos) {
			Course course = courseRepository.findById(video.getCourseId()).get();
			dtos.add(new VideoDto(video.getId(), video.getTitle(), video.getUrl(), video.getTimeCount(), video.getCourseId(), course.getTitle()));
		}
		return null;
	}

	@Override
	public VideoDto findById(int id) {
		Video video = videoRepository.findById(id).get();
		Course course = courseRepository.findById(video.getCourseId()).get();
		return new VideoDto(video.getId(), video.getTitle(), video.getUrl(), video.getTimeCount(), video.getCourseId(), course.getTitle());
	}

	@Override
	public void add(VideoDto dto) {
		Video video = new Video();
		video.setTitle(dto.getTitle());
		video.setUrl(dto.getUrl());
		video.setTimeCount(dto.getTimeCount());
		video.setCourseId(dto.getCourseId());
		videoRepository.save(video);
	}

	@Override
	public void update(VideoDto dto) {
		Video video = videoRepository.findById(dto.getId()).get();
		video.setTitle(dto.getTitle());
		video.setUrl(dto.getUrl());
		video.setTimeCount(dto.getTimeCount());
		video.setCourseId(dto.getCourseId());
		videoRepository.save(video);
	}

	@Override
	public void delete(int id) {
		videoRepository.deleteById(id);
	}

	@Override
	public List<VideoDto> search(String keyword) {
		List<VideoDto> dtos = new ArrayList<VideoDto>();
		List<Video> videos = videoRepository.findByTitle(keyword);
		for(Video video : videos) {
			Course course = courseRepository.findById(video.getCourseId()).get();
			dtos.add(new VideoDto(video.getId(), video.getTitle(), video.getUrl(), video.getTimeCount(), video.getCourseId(), course.getTitle()));
		}
		return null;
	}
}

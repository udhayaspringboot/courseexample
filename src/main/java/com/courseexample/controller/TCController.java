package com.courseexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.courseexample.model.Course;
import com.courseexample.model.Lesson;
import com.courseexample.model.Topic;
import com.courseexample.service.Courseservice;
import com.courseexample.service.LessonService;
import com.courseexample.service.TopicService;

@RestController
public class TCController {
	
	@Autowired
	TopicService tpService;
	@Autowired
	LessonService lessService;
	@Autowired
	Courseservice courseServ;
	@PostMapping("/topics")
	public String  saveTopic(@RequestBody Topic topic)
	{
		System.out.println("controller value"+topic.getToId());
		tpService.saveTopic(topic);
		return "Data Addedd Successfully";
	}

	@GetMapping("/topics")
	public List<Topic> getAllTopics()
	{
		
		List<Topic> lisTopic = tpService.getAllTopics();
		/*
		 * List<Course> lisCourse = new ArrayList<Course>(); for (Topic topic :
		 * lisTopic) { lisCourse.addAll(courseServ.getAllCourse(topic.getToId())); }
		 */
		return lisTopic;
	}
	
	@GetMapping("/topics/{tId}")
	public Topic getTopic(@PathVariable String tId)
	{
		Topic top = tpService.getTopic(tId);
		
		return top;
	}
	
	@PutMapping("/topics/{tId}")
	public String updateTopic(@RequestBody Topic topic,@PathVariable String tId)
	{
		topic.setToId(tId);
		tpService.update(topic);
		
		return "updated successfully";
	}
	
	@DeleteMapping("topics/{tId}")
	public String deleteTopic(@PathVariable String tId)
	{
		tpService.deleteTopic(tId);
		return "deleted successfully";
	}
	
	@PostMapping("/topics/{tId}/courses")
	public String  saveCourse(@RequestBody Course course,@PathVariable String tId)
	{
		//System.out.println("controller value"+topic.gettId());
		Topic topi = new Topic();
		topi.setToId(tId);
		course.setTopic(topi);
		courseServ.saveCourse(course);
		return "Data Addedd Successfully";
	}

	@GetMapping("/topics/{tId}/courses")
	public List<Course> getAllCourses(@PathVariable String tId)
	{
		System.out.println(" id at controller "+tId);
		
		List<Course> lisCour = courseServ.getAllCourse(tId);
		
		return lisCour;
	}
	
	@GetMapping("/topics/{tId}/courses/{cId}")
	public Course getCourse(@PathVariable String tId,@PathVariable String cId)
	{
		Course cou = courseServ.getCourse(tId, cId);
		
		return cou;
	}
	
	@PutMapping("/topics/{tId}/courses/{cId}")
	public String updateCourse(@RequestBody Course course,@PathVariable String tId,@PathVariable String cId)
	{
		Topic toe = new Topic();
		toe.setToId(tId);
		course.setTopic(toe);
		course.setCoId(cId);
		courseServ.updateCourse(course);
		return "updated successfully";
	}
	
	@DeleteMapping("topics/{tId}/courses/{cId}")
	public String deleteCourse(@PathVariable String tId,@PathVariable String cId)
	{
		
		courseServ.deleteCourse(tId, cId);
		return "deleted successfully";
	}
	
	@PostMapping("/topics/{tId}/courses/{cId}/lessons")
	public String  saveLessons(@RequestBody Lesson lesson,@PathVariable String cId)
	{
		//System.out.println("controller value"+topic.gettId());
		Course cour = new Course();
		cour.setCoId(cId);
		lesson.setCourse(cour);
		lessService.saveLesson(lesson);
		return "Data Addedd Successfully";
	}

	@GetMapping("/topics/{tId}/courses/{cId}/lessons")
	public List<Lesson> getAllLessons(@PathVariable String cId)
	{
		System.out.println(" id at controller "+cId);
		
		List<Lesson> lisLess = lessService.getAllLesson(cId);
		
		return lisLess;
	}
	
	@GetMapping("/topics/{tId}/courses/{cId}/lessons/{lId}")
	public Lesson getLessons(@PathVariable String cId,@PathVariable String lId)
	{
		Lesson less = lessService.getLesson(lId, cId);
		
		return less;
	}
	
	@PutMapping("/topics/{tId}/courses/{cId}/lessons/{lId}")
	public String updateLessons(@RequestBody Lesson lesson,@PathVariable String lId,@PathVariable String cId)
	{
Course cor = new Course();
cor.setCoId(cId);
lesson.setLesId(lId);
lesson.setCourse(cor);
lessService.saveLesson(lesson);

		return "updated successfully";
	}
	
	@DeleteMapping("topics/{tId}/courses/{cId}/lessons/{lId}")
	public String deleteLessons(@PathVariable String lId,@PathVariable String cId)
	{
		
		lessService.deleteLesson(lId, cId);
		return "deleted successfully";
	}
}

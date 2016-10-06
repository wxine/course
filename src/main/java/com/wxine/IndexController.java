package com.wxine;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wxine.domain.User;
import com.wxine.repository.UserDao;
import com.wxine.repository.UserRepository;

@Controller
public class IndexController {
	
	private String idd;
	
	@Resource
	private UserRepository userRepository;
	
	@Resource
	private UserDao userDao;
	
	@RequestMapping("/save")
	public String course_save() {
		User user = new User();
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setCourse_id("12");
		user.setCourse_name("sss");
		userDao.save(user);
		//List<User> list = userRepository.findByAccount("zhangsan");
		return "course";
	}
	
	@RequestMapping("/get") 
	public String course_get(){
		System.out.println(userDao.get("2a51d993b6564a55aeaf5215e9d66b30").getCourse_name());
		return "course";

	}
	
	@RequestMapping("/delete/{id}")
	public String course_delete(@PathVariable("id")String id){
		userDao.delete(id);
		return "redirect:/";
	}
	
	@RequestMapping("/upd") 
	public String course_update(String upnumber, String upcoursename){
		User user = userDao.get(idd);
		user.setCourse_id(upnumber);
		user.setCourse_name(upcoursename);
		userDao.update(user);
		return "redirect:/";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String course_upd(@RequestParam("id") String id){
		System.out.println(id);
		idd = id;
		return "index";
	}

	@RequestMapping("/addCourse")
	public String addCourse(String number, String coursename){
		User user = new User();
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setCourse_id(number);
		user.setCourse_name(coursename);
		userDao.save(user);
		return "redirect:/";
	}
	
	@RequestMapping("/")
	public String showCourse(Model model){
		List<User> user = userDao.findAll();
		model.addAttribute("posts", user);
		return "course";
	}
		

	
}

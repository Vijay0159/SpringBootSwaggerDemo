package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.business.UserBean;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	@Value("from value annotation")
	String status1;
	@Value("${my.property}")
    String status2;
	
	@RequestMapping(value="/api/call", method=RequestMethod.GET)
	public String call() {
		return "Hello! There";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addUser(@RequestBody UserBean userBean) throws Exception{
		UserBean user = userService.adduser(userBean);		
		return ("User Data Added Successfully. User ID: " + user.getUserId());		
	}
	
	@RequestMapping(value = "/addWithConstructor", method=RequestMethod.POST)
	public String adduserwithconstructor(@RequestBody UserBean userBean) throws Exception{
		UserBean user = userService.adduser(userBean);		
		return ("User Data Added Successfully. User ID: " + user.getUserId());		
	}
	
	@RequestMapping(value="/getbyuserid/{id}", method=RequestMethod.GET)
	public UserBean getUser(@PathVariable int id) throws Exception {
		UserBean user = userService.getUserByID(id);
		return user;
	}
	
	@RequestMapping(value="/deleteAllRecords", method=RequestMethod.DELETE)
    public String deleteAllRecords() {
        userService.deleteAllRecords();
        return "All records deleted";
    }
	
    @RequestMapping(value="/deleteById/{id}", method=RequestMethod.DELETE)
    public String deleteById(@PathVariable int id) {
        userService.deleteById(id);
        return "User record with User Id " + id + " has successfully deleted.";
    }
    
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<UserBean> getAllUsers() {
		return userService.getAllUsers();
    }
    
    @RequestMapping(value="/value", method=RequestMethod.GET)
	public String value() {
		//propertyValue = env.getProperty("propertyName");
		return status1 + "\n" + status2;
	}
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable int id, @RequestBody UserBean userBean) throws Exception {
        UserBean updatedUser = userService.updateUser(id, userBean);
        return "User Data Updated Successfully. User ID: " + updatedUser.getUserId();
    }
}

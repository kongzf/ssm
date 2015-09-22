package blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import core.ajaxResult.AjaxResult;
import core.exception.MyException;
import core.utils.MD5Util;
import blog.dao.User;
import blog.service.IUserService;
import blog.service.imp.UserService;

/**

 *  create by Liujishuai on 2015年9月21日

 */
@Controller
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/hello",method=RequestMethod.POST)
	public String getByController(String id){
		return "hello";
	}
    @RequestMapping(value="/user",method=RequestMethod.POST)
   	public ModelAndView getTeemo(Integer id){
    	ModelAndView modelAndView=new ModelAndView();
    	modelAndView.addObject("userName",userService.selectByPrimaryKey(id).getName());
    	modelAndView.addObject("userDesc",userService.selectByPrimaryKey(id).getDes());
    	modelAndView.setViewName("user");
   		return modelAndView;
   	}
   @ResponseBody
    @RequestMapping(value="/getById",method=RequestMethod.POST)
   	public AjaxResult getById(Integer id){
    	
   		return AjaxResult.getOK(userService.selectByPrimaryKey(id));
   	}
   @ResponseBody
   @RequestMapping(value="/addUser",method=RequestMethod.POST)
  	public AjaxResult addUser(@RequestBody User user){
	   if(user.getPassword()==null){
		   throw new MyException("密码不能为空");
	   }
	   //对密码进行MD5加密
	    user.setPassword(MD5Util.getMD5(user.getPassword().getBytes()));
   	    userService.insertSelective(user);
  		return AjaxResult.getOK();
  	}
}


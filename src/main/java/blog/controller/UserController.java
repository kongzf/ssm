package blog.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import core.ajaxResult.AjaxResult;
import core.common.PageInfo;
import core.controller.BaseController;
import core.exception.MyException;
import core.utils.MD5Util;
import core.utils.StringUtil;
import blog.dao.Role;
import blog.dao.User;
import blog.dto.input.UserEditDetails;
import blog.dto.output.UserDetails;
import blog.service.IUserService;
import blog.service.imp.UserService;

/**
 * 
 * create by Liujishuai on 2015年9月21日
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public String getByController(String id) {
		return "hello";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView getTeemo(Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userName", userService.selectByPrimaryKey(id)
				.getName());
		modelAndView.addObject("userDesc", userService.selectByPrimaryKey(id)
				.getDes());
		modelAndView.setViewName("user");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	public AjaxResult getById(Integer id) {

		return AjaxResult.getOK(userService.selectByPrimaryKey(id));
	}

	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public AjaxResult addUser(@RequestBody User user) {
		if (user.getPassword() == null) {
			throw new MyException("密码不能为空");
		}
		// 对密码进行MD5加密
		user.setPassword(MD5Util.getMD5(user.getPassword().getBytes()));
		userService.insertSelective(user);
		return AjaxResult.getOK();
	}

	/**
	 * 会员登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public AjaxResult loginAction(String username, String password) {
		UserDetails userDetails = userService.login(username, password);
		if(userDetails==null){
			return AjaxResult.getOK("用户名或密码错误",userDetails);
		}
		return AjaxResult.getOK(userDetails);
	}

	/**
	 * 会员注册
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public AjaxResult createUserAction(String username, String password,
			String des, String tel, String address) {
		userService.createUser(username, password, des, tel, address);
		return AjaxResult.getOK();
	}

	/**
	 * 获取会员详情
	 * 
	 * @param id
	 *            会员id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserDetails", method = RequestMethod.POST)
	public AjaxResult getUserDetails(@RequestParam(value = "id") Integer id) {
		if (id == null) {
			throw new MyException("id不能为空");
		}
		UserDetails userDetails = userService.getUserDetails(id);
		return AjaxResult.getOK(userDetails);
	}

	/**
	 * 获取会员列表
	 * 
	 * @param level
	 *            等级
	 * @param username
	 *            用户名（模糊查询）
	 * @param pageIndex
	 *            页索引
	 * @param pageSize
	 *            页大小
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	public AjaxResult getUserList(
			@RequestParam(value = "level", required = false) Integer level,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

		List<User> userList=userService.getUserList(level, username,new RowBounds(pageIndex,pageSize));
		return AjaxResult.getOK(userList);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param userEditDetails
	 *            用户信息详情 roleIds 多个用,隔开
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserDetails", method = RequestMethod.POST)
	public AjaxResult updateUserDetails(
			@RequestBody UserEditDetails userEditDetails,
			HttpServletRequest request) {
		Integer userId = getNotNullUserId(request);
		if (userEditDetails.getUser() == null
				|| userEditDetails.getUser().getId() == null) {
			throw new MyException("要修改的用户不能为空");
		}
		userService.updateUserDetails(userId, userEditDetails);
		return AjaxResult.getOK();
	}

	/**
	 * 删除用户信息
	 * 
	 * @param ids
	 *            用户id，多个用,隔开
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUserList", method = RequestMethod.POST)
	public AjaxResult delteUserList(String ids) {

		if (StringUtil.isEmpty(ids)) {
			throw new MyException("要删除的用户id不能为空");
		}
		userService.deleteUser(ids);
		return AjaxResult.getOK();
	}
	/**
	 * 获取角色信息（id为空全部获取）
	 * @param id 角色id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoles", method = RequestMethod.POST)
	public AjaxResult getRoles(Integer id) {
		List<Role> roles=userService.getRoles(id);
		return AjaxResult.getOK(roles);
	}
	/**
	 * 增加角色
	 * @param roleName 角色名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public AjaxResult addRole(String roleName,HttpServletRequest request) {
		Integer userId=getNotNullUserId(request);
		if(!userId.equals(1)){
			throw new MyException("非管理员不能增加角色");
		}
		userService.addRole(roleName);
		return AjaxResult.getOK();
	}
}

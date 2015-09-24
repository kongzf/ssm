package blog.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import core.common.PageInfo;
import core.service.IBaseService;
import blog.dao.Role;
import blog.dao.User;
import blog.dto.input.UserEditDetails;
import blog.dto.output.UserDetails;

/**
 * 
 * create by Liujishuai on 2015年9月21日
 */
public interface IUserService extends IBaseService<User> {
	/**
	 * 获取用户角色列表
	 * @param userId
	 * @return
	 */
	public List<Integer> getUserRoleList(Integer userId);
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public UserDetails login(String username,String password);
	/**
	 * 创建新用户
	 * @param username 用户名
	 * @param password  密码
	 * @param des  简介
	 * @param tel 电话
	 * @param address 地址
	 */
	public void createUser(String username,String password,String des,String tel,String address);
	/**
	 * 获取自己的用户详情
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public UserDetails getUserDetails(Integer userId);

	/**
	 * 获取用户详情列表
	 * 
	 * @param level
	 *            等级
	 * @param username
	 *            用户名（模糊查询）
	 * @return
	 */
	public List<User> getUserList(Integer level,String username,RowBounds rowBounds);

	/**
	 * 修改用户信息（若是管理员可以修改其角色信息）
	 * 
	 * @param userId
	 *            操作者id
	 * @param userEditDetails
	 *            用户详情
	 */
	public void updateUserDetails(Integer userId,
			UserEditDetails userEditDetails);

	/**
	 * 批量删除用户信息
	 * 
	 * @param ids
	 *            用户id 多个用,分开
	 */
	public void deleteUser(String ids);
	/**
	 * 获取角色列表（id为空全部获取）
	 * @param id 角色id
	 * @return
	 */
	public List<Role> getRoles(Integer id);
	/**
	 * 增加角色
	 * @param roleName 角色信息
	 */
	public void addRole(String roleName);
}

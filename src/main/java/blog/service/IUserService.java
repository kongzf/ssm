package blog.service;

import blog.dao.User;

/**

 *  create by Liujishuai on 2015年9月21日

 */
public interface IUserService {
        
	public User  getUserById(Integer id);
	public void insertUser(User user);
	
}


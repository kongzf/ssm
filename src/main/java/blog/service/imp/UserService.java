package blog.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.User;
import blog.mapper.UserMapper;
import blog.service.IUserService;

/**

 *  create by Liujishuai on 2015年9月21日

 */
@Service("userService")
public class UserService implements IUserService {
    @Autowired
	private UserMapper userMapper;
	

	@Override
	public User getUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertSelective(user);
		
	}

}


package blog.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.mapper.IBaseMapper;
import core.service.BaseService;
import blog.dao.User;
import blog.mapper.UserMapper;
import blog.service.IUserService;

/**

 *  create by Liujishuai on 2015年9月21日

 */
@Service("userService")
public class UserService extends BaseService<User>  implements IUserService {
    @Autowired
	private UserMapper userMapper;

	@Override
	public IBaseMapper<User> getBaseMapper() {
		// TODO Auto-generated method stub
		return userMapper;
	}

	
	
	

	

}


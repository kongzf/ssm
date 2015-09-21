package serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import base.BaseControllerTest;
import blog.dao.User;
import blog.service.imp.UserService;

/**
 * 
 * create by Liujishuai on 2015年9月21日
 */
public class UserServerTest extends BaseControllerTest {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    @Test
    public void testInsertUser(){
    	User user=new User();
    	user.setName("张磊");
    	user.setPassword("123123");
    	user.setLevel(1);
    	user.setDes("中国好声音第四季张磊");
    	user.setAddress("浙江杭州");
    	userService.insertUser(user);
    }
    @Test
    public void testGetUserById(){
    	User user=userService.getUserById(6);
    	System.out.println(user.getName()+":"+user.getDes());
    }
}

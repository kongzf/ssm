package serviceTest;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import core.common.PageInfo;
import base.BaseControllerTest;
import blog.dao.User;
import blog.service.imp.UserService;

/**
 * 
 * create by Liujishuai on 2015年9月21日
 */
public class UserServerTest extends BaseControllerTest {
	@Autowired
	private UserService userService;

    @Test
    public void testInsertUser(){
    	User user=new User();
    	user.setName("张磊");
    	user.setPassword("123123");
    	user.setLevel(1);
    	user.setDes("中国好声音第四季张磊");
    	user.setAddress("浙江杭州");
    	userService.insert(user);
    }
    @Test
    public void testGetUserById(){
    	Integer id=1;
    	User user=userService.selectByPrimaryKey(id);
    	System.out.println(user.getName()+":"+user.getDes());
    }
    @Test
    public void testGetUserList(){
    	String username="sk";
    	
      List<User> pageInfo= userService.getUserList(null, username, new RowBounds(1,2));
      String objString=new Gson().toJson(pageInfo);
      System.out.println(objString);
    }
}

package controllerTest;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.gson.Gson;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import base.BaseControllerTest;
import blog.dao.User;

/**

 *  create by Liujishuai on 2015年9月21日

 */
public class UserControllerTest extends BaseControllerTest {
    
	@Test
	public void getByIdTest(){
		 try{
	            mockMvc.perform(
	                    MockMvcRequestBuilders.post("/getById")
	                    .param("id", "6")
	            ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	}
	@Test
	public void addUserTest(){
		User user=new User();
		user.setName("李安");
		user.setDes("中国好声音第四季周杰伦组成员");
		user.setLevel(1);
		user.setPassword("123");
		user.setAddress("上海");
		String objString=new Gson().toJson(user); 
		 try{
	            mockMvc.perform(
	                    MockMvcRequestBuilders.post("/addUser")
	                    .content(objString)
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .characterEncoding("UTF-8")
	                    .accept(MediaType.APPLICATION_JSON)
	            ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	}
	
}


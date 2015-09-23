package controllerTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.gson.Gson;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import base.BaseControllerTest;
import blog.dao.User;
import blog.dto.input.UserEditDetails;
import blog.service.IUserService;

/**
 * 
 * create by Liujishuai on 2015年9月21日
 */
public class UserControllerTest extends BaseControllerTest {
	@Autowired
	IUserService iUserService;

	@Test
	public void getByIdTest() {
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/user/getById").param("id",
							"6")).andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addUserTest() {
		User user = new User();
		user.setName("李安");
		user.setDes("中国好声音第四季周杰伦组成员");
		user.setLevel(1);
		user.setPassword("123");
		user.setAddress("上海");
		String objString = new Gson().toJson(user);
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/user/addUser")
							.content(objString)
							.contentType(MediaType.APPLICATION_JSON)
							.characterEncoding("UTF-8")
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试获取会员详情
	@Test
	public void getUserDetails() {
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/user/getUserDetails").param(
							"id", "1")).andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试获取会员列表
	@Test
	public void getUserList() {
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/user/getUserList")
							.param("level", "1").param("username", "Jeyson")
							.param("pageIndex", "2").param("pageSize", "2"))
					.andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试修改用户详情
	@Test
	public void updateUserDetails() {
		User user = iUserService.selectByPrimaryKey(4);
		user.setAddress("山东省青岛市");
		UserEditDetails userEditDetails = new UserEditDetails();
		userEditDetails.setRoleIds("2");
		userEditDetails.setUser(user);
		String objString = new Gson().toJson(userEditDetails);
		System.out.println(objString);
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/user/updateUserDetails")
							.content(objString).characterEncoding("UTF-8")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON)
							.header("userId", "1")).andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试删除
	@Test
	public void deleteUserList() {
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/user/deleteUserList").param(
							"ids", "2,3")

			).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 测试获取角色信息
		@Test
		public void getRolse() {
			try {
				mockMvc.perform(
						MockMvcRequestBuilders.post("/user/getRoles")
						//.param("id", "2")

				).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 测试增加角色信息
				@Test
				public void addtRolse() {
					try {
						mockMvc.perform(
								MockMvcRequestBuilders.post("/user/addRole")
								.param("roleName", "金牌会员")

						).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
}

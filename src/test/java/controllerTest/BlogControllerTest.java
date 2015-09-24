package controllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.gson.Gson;

import base.BaseControllerTest;
import blog.dao.Blog;
import blog.dao.User;

/**
 *
 * @Message:  created by Liujishuai on 2015年9月24日
 * 
 * @Description:
 */
public class BlogControllerTest extends BaseControllerTest {
	
  /**
   * 写博客测试
   */
	@Test
	public void writeBlog() {
		Blog blog =new Blog();
		blog.setTitle("9.24小韩要闻5");
		blog.setDes("小韩关于调戏妹子的几点问题5");
		blog.setContext("问题1：。。。。。。问题2.。。。。。");
		String objString=new Gson().toJson(blog);
		System.out.println(objString);
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/blog/writeBlog")
					.header("userId", "4")
					.content(objString)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 改博客测试
	 */
	@Test
	public void updateBlog() {
		Blog blog =new Blog();
		blog.setId(8);
		blog.setTitle("9.24小韩要闻4.1");
		blog.setDes("小韩关于调戏妹子的几点问题4.1");
		blog.setContext("问题1：。。。。。。问题2.。。。。。");
		blog.setUserid(4);
		String objString=new Gson().toJson(blog);
		System.out.println(objString);
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/blog/updateBlog")
					.header("userId", "4")
					.content(objString)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除博客测试
	 */
	@Test
	public void deleteBlog() {
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/blog/deleteBlog")
					.param("blogId","4")
					 .header("userId", "4")
					)
				
					.andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取博客列表测试
	 */
	@Test
	public void getBlogList() {
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/blog/getBlogList")
					//.param("userId","4")
					.header("userId", "4")).andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取博客详情
	 */
	@Test
	public void getBlogDetails() {
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/blog/getBlogDetails")
					.param("blogId","5")).andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


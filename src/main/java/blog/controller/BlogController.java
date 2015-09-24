package blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.dao.Blog;
import blog.dto.output.BlogDetails;
import blog.service.IBlogService;
import blog.service.IUserService;
import core.ajaxResult.AjaxResult;
import core.controller.BaseController;
import core.exception.MyException;
import core.utils.StringUtil;

/**
 *
 * @Message:  created by Liujishuai on 2015年9月24日
 * 
 * @Description:
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserService userService;
    /**
     * 写博客
     * @param blog
     * @param request
     * @return
     */
    @ResponseBody
	@RequestMapping(value="/writeBlog",method=RequestMethod.POST)
	public AjaxResult createBlog(@RequestBody Blog blog,HttpServletRequest request){
		Integer userId=getNotNullUserId(request);
		if(StringUtil.isEmpty(blog.getTitle())||StringUtil.isEmpty(blog.getDes())||StringUtil.isEmpty(blog.getContext())){
			throw new MyException("信息不全");
		}
		blogService.createBlog(blog, userId);
		return AjaxResult.getOK();
	}
	/**
	 * 该博客
	 * @param blog
	 * @param request
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value="/updateBlog",method=RequestMethod.POST)
	public AjaxResult updateBlog(@RequestBody Blog blog,HttpServletRequest request){
		Integer userId=getNotNullUserId(request);
		if(StringUtil.isEmpty(blog.getTitle())||StringUtil.isEmpty(blog.getDes())||StringUtil.isEmpty(blog.getContext())||blog.getUserid()==null){
			throw new MyException("信息不全");
		}
		blogService.updateBlog(blog, userId);
		return AjaxResult.getOK();
	}
	/**
	 * 删除博客
	 * @param blogId
	 * @param request
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value="/deleteBlog",method=RequestMethod.POST)
	public AjaxResult deleteBlog(Integer blogId,HttpServletRequest request){
		Integer userId=getNotNullUserId(request);
		
		blogService.deleteBlog(blogId, userId);
		return AjaxResult.getOK();
	}
	/**
	 * 获取博客列表
	 * @param blogId
	 * @param request
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value="/getBlogList",method=RequestMethod.POST)
	public AjaxResult getBlogList(@RequestParam(value="userId",required=false)Integer userId,HttpServletRequest request){
		Integer id=getNotNullUserId(request);
		List<BlogDetails> blogDetailsList=new ArrayList<BlogDetails>();
		if(userService.getUserRoleList(id).contains(1)){
			if(userId==null){
				blogDetailsList=blogService.getUserBlogList(id);
			}else {
				blogDetailsList=blogService.getUserBlogList(userId);
			}
		}else {
			blogDetailsList=blogService.getUserBlogList(id);
		};
		
		return AjaxResult.getOK(blogDetailsList);
	}
	/**
	 * 获取博客详情
	 * @param blogId
	 * @param request
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value="/getBlogDetails",method=RequestMethod.POST)
	public AjaxResult getBlogDetails(Integer blogId){
		Blog blog= blogService.selectByPrimaryKey(blogId);
		return AjaxResult.getOK(blog);
	}
}


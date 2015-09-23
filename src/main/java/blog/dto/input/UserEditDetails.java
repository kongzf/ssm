package blog.dto.input;

import java.util.List;

import blog.dao.Role;
import blog.dao.User;

/**
 *
 * @Message: created by Liujishuai on 2015年9月23日
 * 
 * @Description: 新增、修改会员信息详情
 */
public class UserEditDetails  {
	private User user;
	/**
	 * 会员角色信息
	 */
	private String roleIds;

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	
}

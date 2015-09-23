package blog.dto.output;

import java.util.List;

import blog.dao.Role;
import blog.dao.User;

/**
 *
 * @Message:  created by Liujishuai on 2015年9月23日
 * 
 * @Description:
 */
public class UserDetails extends User{
    /**
     * 角色信息
     */
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}


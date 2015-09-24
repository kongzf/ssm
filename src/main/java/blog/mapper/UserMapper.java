package blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.junit.runners.Parameterized.Parameters;

import core.mapper.IBaseMapper;
import blog.dao.User;
import blog.dto.output.UserDetails;

public interface UserMapper extends IBaseMapper<User> {
	//通过会员id获取其详情
   public UserDetails getUserDetailsById(Integer id);
    //通过等级，用户名（模糊查询）获取会员列表
   public List<User> getUserListByLevel(@Param("level")Integer level,@Param("username")String username);
   //批量删除
   public void deleteByIdList(@Param("idList")List<Integer> idList);
   //查询一个用户名的
   public Integer getUserNameCount(String username);
   //根据用户名查询
   public User getByUserName(String username);
}
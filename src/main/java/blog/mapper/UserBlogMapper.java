package blog.mapper;

import blog.dao.UserBlog;

public interface UserBlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBlog record);

    int insertSelective(UserBlog record);

    UserBlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBlog record);

    int updateByPrimaryKey(UserBlog record);
}
package core.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import blog.dao.User;

/**
     
 *  create by Liujishuai on 2015年9月22日

 */
public interface IBaseMapper<T> {
	int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
    List<T> getAllByPage(RowBounds rowBounds);
}


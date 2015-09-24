package core.service;

/**

 *  create by Liujishuai on 2015年9月22日

 */
public interface IBaseService<T> {
	    int deleteByPrimaryKey(Integer id);

	    int insert(T record);

	    int insertSelective(T record);

	    T selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(T record);

	    int updateByPrimaryKey(T record);

}


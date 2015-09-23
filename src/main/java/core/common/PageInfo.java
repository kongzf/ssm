package core.common;

import java.util.List;

/**
 *
 * @Message:  created by Liujishuai on 2015年9月23日
 * 
 * @Description: 自定义分页
 */
public class PageInfo<T> {

	/**
     * 记录总数
     */
    private Long total;

    /**
     * 页号
     */
    private Integer  pageIndex;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 对象集合
     */
    private List<T> list;

    /**
     * 获取 记录总数
     *
     * @return 记录总数
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 设置 记录总是
     *
     * @param total 记录总数
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * 获取 页号
     *
     * @return 页号
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置 页号
     *
     * @param pageIndex 页号
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * 获取 页大小
     *
     * @return 页大小
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置 页大小
     *
     * @param pageSize 页大小
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取 对象集合
     *
     * @return 对象集合
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置 对象集合
     *
     * @param list 对象集合
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * pageInfo对象转化
     *
     * @param result com.github.pagehelper.PageInfo对象
     * @return pageInfo对象
     */
    public static PageInfo trans(com.github.pagehelper.PageInfo result) {

        if(result==null)return null;

        PageInfo pageInfo = new PageInfo();

        pageInfo.setPageIndex(result.getPageNum());

        pageInfo.setPageSize(result.getPageSize());

        pageInfo.setList(result.getList());

        pageInfo.setTotal(result.getTotal());

        return pageInfo;
    }
}


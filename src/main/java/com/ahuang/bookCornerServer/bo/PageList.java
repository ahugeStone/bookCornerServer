package com.ahuang.bookCornerServer.bo;

import java.util.List;
import lombok.Data;

/**
 * 
* @ClassName: PageList
* @Description: 翻页实体类
* @author ahuang
* @date 2018年6月2日 下午10:00:30
* @version V1.0
* @param <T>
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PageList<T> {
	/**每页数量**/
	Integer pageSize;
	/**当前页起始数**/
	Integer startNum;
	/**当前页结束数**/
	Integer endNum;
	/**总条数**/
	Integer totalNum;
	/**总页数**/
	Integer totalPageNum;
	/**当前页数**/
	Integer currentPageNum;
	/**是否为最后一页**/
	boolean lastPage;
	/**当前页具体内容**/
	List<T> objectList;
	/**
	 * 构造同时生成翻页相关属性值
	 */
	public PageList(Integer startNum, Integer totalNum, Integer pageSize, List<T> list) {
		if(null == startNum || null == pageSize) {
			startNum = 0;
			pageSize = list.size();
		}
		startNum = startNum < 0 ? 0:startNum;
		if(null == totalNum) {
			totalNum = list.size();
		}
		Integer totalPageNum = totalNum%pageSize == 0? totalNum/pageSize:totalNum/pageSize+1;// 总页数
		Integer endNum = startNum + pageSize; 
		endNum  = endNum > totalNum? totalNum:endNum; // 当前页最后元素id不能大于总数
		startNum = startNum > endNum? endNum:startNum; // 当前页第一项不能大于最后一项
		Integer currentPageNum = (startNum+1)%pageSize == 0? startNum/pageSize:startNum/pageSize+1;//计算当前页
		currentPageNum = currentPageNum==0? 1:currentPageNum; //当前页不能为0
		currentPageNum = currentPageNum > totalPageNum? totalPageNum:currentPageNum;//当前页不能大于总页数
		boolean isLastPage = endNum == totalNum; //是否为最后一页
		currentPageNum = isLastPage? totalPageNum:currentPageNum;//如果是最后一页，设定当前页为最后一页
		pageSize = list.size();//设置当前页面大小
		
		this.startNum = startNum;
		this.totalPageNum = totalPageNum;
		this.endNum = endNum;
		this.currentPageNum = currentPageNum;
		this.lastPage = isLastPage;
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		this.objectList = list;
	}
}

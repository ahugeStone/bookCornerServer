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
		Integer totalPageNum = totalNum%pageSize == 0? totalNum/pageSize:totalNum/pageSize+1;
		Integer endNum = startNum + pageSize;
		endNum  = endNum > totalNum? totalNum:endNum;
		startNum = startNum > endNum? endNum:startNum;
		Integer currentPageNum = endNum%pageSize == 0? startNum/pageSize:startNum/pageSize+1;
		currentPageNum = currentPageNum==0? 1:currentPageNum;
		currentPageNum = currentPageNum > totalPageNum? totalPageNum:currentPageNum;
		boolean isLastPage = endNum == totalNum;
		
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

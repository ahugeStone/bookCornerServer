package com.ahuang.bookCornerServer.bo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}

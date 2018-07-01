package com.ahuang.bookCornerServer.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @ClassName: BookList
* @Description: 图书返回列表类
* @author ahuang
* @date 2018年6月11日 下午9:30:19
* @version V1.0
* @param <T>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BookList<T>  extends PageList<T>{
	@JsonProperty(value = "bookList")
	private List<T> objectList;
	public BookList(Integer startNum, Integer totalNum, Integer pageSize, List<T> list) {
		super(startNum, totalNum, pageSize, list);
		this.objectList = list;
	}

}

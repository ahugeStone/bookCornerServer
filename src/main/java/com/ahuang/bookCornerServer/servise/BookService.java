package com.ahuang.bookCornerServer.servise;

import java.util.Map;

import com.ahuang.bookCornerServer.bo.PageList;
import com.ahuang.bookCornerServer.entity.BookBaseInfoEntity;

public interface BookService {
	public PageList<BookBaseInfoEntity> queryBookListPage(Map<String, Object> param); 
}

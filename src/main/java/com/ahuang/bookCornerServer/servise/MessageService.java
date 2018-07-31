package com.ahuang.bookCornerServer.servise;

import com.ahuang.bookCornerServer.bo.PageList;
import com.ahuang.bookCornerServer.entity.*;
import com.ahuang.bookCornerServer.exception.BaseException;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: MessageService
* @Description: 公告栏信息服务接口
* @author lct
* @date 2018年7月26日 上午11:21:50
* @version V1.0
 */
public interface MessageService {



	List<MessageBaseInfoEntity> queryMessageList(Integer num);

}

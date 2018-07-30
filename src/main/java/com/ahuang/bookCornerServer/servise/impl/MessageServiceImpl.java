package com.ahuang.bookCornerServer.servise.impl;

import com.ahuang.bookCornerServer.bo.BookList;
import com.ahuang.bookCornerServer.entity.*;
import com.ahuang.bookCornerServer.exception.BaseException;
import com.ahuang.bookCornerServer.mapper.*;
import com.ahuang.bookCornerServer.servise.BookService;
import com.ahuang.bookCornerServer.servise.MessageService;
import com.ahuang.bookCornerServer.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lct
 * @version V1.0
 * @ClassName: MessageServiceImpl
 * @Description: 图书服务类
 * @date 2018年7月26日 上午11:21:50
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

	private final MessageBaseInfoMapper messageBaseInfoMapper;

    @Autowired
    public MessageServiceImpl(MessageBaseInfoMapper messageBaseInfoMapper) {
        this.messageBaseInfoMapper = messageBaseInfoMapper;

    }



    @Override
    public List<MessageBaseInfoEntity> queryMessageList(Integer num) {
        return messageBaseInfoMapper.queryMessageList(num);
    }
}

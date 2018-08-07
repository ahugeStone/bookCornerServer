package com.ahuang.bookCornerServer.servise.impl;

import com.ahuang.bookCornerServer.entity.MessageBaseInfoEntity;
import com.ahuang.bookCornerServer.mapper.MessageBaseInfoMapper;
import com.ahuang.bookCornerServer.servise.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

package com.kangde.collection.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.CallRecordMapper;
import com.kangde.collection.model.CallRecord;
import com.kangde.collection.service.CallRecordService;
import com.kangde.commons.service.AbstractService;

/**
 * 电催统计 Service实现类
 * 
 * @author zhangyj
 * @date 2016年12月29日
 */
@Service("callRecordService")
public class CallRecordServiceImpl extends AbstractService<CallRecord, String> implements CallRecordService {

//	@Autowired
//	private CallRecordMapper callRecordMapper;

}

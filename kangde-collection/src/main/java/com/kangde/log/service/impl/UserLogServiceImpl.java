package com.kangde.log.service.impl;

import org.springframework.stereotype.Service;

import com.kangde.commons.service.AbstractService;
import com.kangde.log.model.UserLogModel;
import com.kangde.log.service.UserLogService;

/**
 * 日志 Service 实现类
 * @author lisuo
 *
 */
@Service("userLogService")
public class UserLogServiceImpl extends AbstractService<UserLogModel, String> implements UserLogService {
}

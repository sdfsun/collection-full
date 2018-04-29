package com.kangde.log.service.impl;

import org.springframework.stereotype.Service;

import com.kangde.commons.service.AbstractService;
import com.kangde.log.model.LogModel;
import com.kangde.log.service.LogService;

/**
 * 日志 Service 实现类
 * @author lisuo
 *
 */
@Service("logService")
public class LogServiceImpl extends AbstractService<LogModel, String> implements LogService {
}

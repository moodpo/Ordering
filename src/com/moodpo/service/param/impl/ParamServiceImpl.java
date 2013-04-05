package com.moodpo.service.param.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moodpo.dao.param.IParamDao;
import com.moodpo.domain.Param;
import com.moodpo.exception.DBException;
import com.moodpo.exception.ServiceException;
import com.moodpo.service.param.IParamService;
import com.moodpo.utils.OtherConstants;
import com.moodpo.utils.SqlConstants;

/**
 * param服务类实现
 * @author xiaoxie
 * @date 2013-4-5 下午02:55:17
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
@Service
@Transactional
public class ParamServiceImpl implements IParamService{
	
	private static Logger logger = Logger.getLogger(ParamServiceImpl.class);
	
	@Resource
	IParamDao paramDaoImpl;
	
	public Param findParamByCode(String code) throws ServiceException {
		Param param = new Param();
		param.setParamCode(code);
		try {
			@SuppressWarnings("rawtypes")
			List list = paramDaoImpl.query(param, SqlConstants.PARAM_FIND_BY_CODE);
			if(list != null && list.size() > 0){
				return (Param)list.get(0);
			}
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			throw new ServiceException(OtherConstants.DB_ERROR);
		}
		return null;
	}

}

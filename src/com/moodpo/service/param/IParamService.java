package com.moodpo.service.param;

import com.moodpo.domain.Param;
import com.moodpo.exception.ServiceException;

/**
 * param服务接口
 * @author xiaoxie
 * @date 2013-4-5 下午02:55:35
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public interface IParamService {
	
	/**
	 * 通过paramcode查询系统参数
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public Param findParamByCode(String code) throws ServiceException;
	
}

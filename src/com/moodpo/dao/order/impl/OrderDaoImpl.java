package com.moodpo.dao.order.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moodpo.common.DaoSupport;
import com.moodpo.dao.order.IOrderDao;

/**
 * 订单dao实现
 * @author xiaoxie
 * @date 2013-4-8 上午08:42:54
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
@Repository
@Transactional
public class OrderDaoImpl extends DaoSupport implements IOrderDao{

}

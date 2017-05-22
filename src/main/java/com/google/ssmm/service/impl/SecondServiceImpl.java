package com.google.ssmm.service.impl;

import com.google.ssmm.dao.TableTwoMapper;
import com.google.ssmm.entity.TableTwo;
import com.google.ssmm.service.SecondServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuhan on 16-12-13.
 */
public class SecondServiceImpl implements SecondServiceInterface{
    @Autowired
    private TableTwoMapper twoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long insertTableTwo(TableTwo one) {
        twoMapper.insertSelective(one);
        return 0l;
        //return twoMapper.getLastInsertedKey();
    }

    @Override public long updateTableTwo(TableTwo one) {
        return 0;
    }
}

package com.google.ssmm.service.impl;

import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.google.ssmm.dao.TableOneMapper;
import com.google.ssmm.dao.TableTwoMapper;
import com.google.ssmm.entity.TableOne;
import com.google.ssmm.entity.TableTwo;
import com.google.ssmm.service.FirstServiceInterface;
import com.google.ssmm.service.SecondServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuhan on 16-12-13.
 */
public class FirstServiceImpl implements FirstServiceInterface {

    @Autowired
    private TableOneMapper oneMapper;

    @Autowired
    private SecondServiceInterface secondService;

    @Override
    @Transactional //(rollbackFor = Exception.class)
    public long insertTableOne(TableOne one) {
        try{
            oneMapper.insertSelective(one);
            TableTwo one2 = new TableTwo();
            secondService.insertTableTwo(one2);
            return oneMapper.getLastInsertedKey();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override public long updateTableOne(TableOne one) {
        throw new RuntimeException();
    }
}

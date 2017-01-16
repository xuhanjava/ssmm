package com.google.ssmm.dao;

import com.google.ssmm.entity.Fruit;

import java.util.List;
import java.util.Map;

/**
 * Created by xuhan on 17-1-3.
 */
public interface FruitMapper {
    Fruit selectById(Long id);
    List<Map<Object,Object>> selectExample();
}

package com.google.ssmm.service;

import com.google.ssmm.entity.TableTwo;

/**
 * Created by xuhan on 16-12-13.
 */
public interface SecondServiceInterface {
    long insertTableTwo(TableTwo one);

    long updateTableTwo(TableTwo one);
}

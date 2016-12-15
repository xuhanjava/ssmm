package com.google.ssmm.service;

import com.google.ssmm.entity.TableOne;

/**
 * Created by xuhan on 16-12-13.
 */
public interface FirstServiceInterface {
    long insertTableOne(TableOne one);

    long updateTableOne(TableOne one);
}

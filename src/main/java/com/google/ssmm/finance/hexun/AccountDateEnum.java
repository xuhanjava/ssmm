package com.google.ssmm.finance.hexun;

/**
 * @author xuhan
 * @date 2023/7/24 5:04 下午
 */
public enum AccountDateEnum {
    FIRST_SEASON(".03.15"),
    HALF_YEAR(".06.30"),
    THREE_SEASON(".09.30"),
    ALL_YEAR(".12.31"),
    ;
    private String accountDate;

    AccountDateEnum(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getAccountDate() {
        return accountDate;
    }
}
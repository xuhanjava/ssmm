package com.google.ssmm.entity;

import java.util.Date;

public class FinancePoint {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column finance_point.id
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column finance_point.date
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    private String date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column finance_point.time_point
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    private String timePoint;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column finance_point.price
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    private String price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column finance_point.create_time
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column finance_point.finance_pointcol
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    private String financePointcol;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column finance_point.stock_no
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    private String stockNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column finance_point.stock_name
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    private String stockName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column finance_point.out_ext
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    private String outExt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column finance_point.id
     *
     * @return the value of finance_point.id
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column finance_point.id
     *
     * @param id the value for finance_point.id
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column finance_point.date
     *
     * @return the value of finance_point.date
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public String getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column finance_point.date
     *
     * @param date the value for finance_point.date
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column finance_point.time_point
     *
     * @return the value of finance_point.time_point
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public String getTimePoint() {
        return timePoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column finance_point.time_point
     *
     * @param timePoint the value for finance_point.time_point
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint == null ? null : timePoint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column finance_point.price
     *
     * @return the value of finance_point.price
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column finance_point.price
     *
     * @param price the value for finance_point.price
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column finance_point.create_time
     *
     * @return the value of finance_point.create_time
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column finance_point.create_time
     *
     * @param createTime the value for finance_point.create_time
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column finance_point.finance_pointcol
     *
     * @return the value of finance_point.finance_pointcol
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public String getFinancePointcol() {
        return financePointcol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column finance_point.finance_pointcol
     *
     * @param financePointcol the value for finance_point.finance_pointcol
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public void setFinancePointcol(String financePointcol) {
        this.financePointcol = financePointcol == null ? null : financePointcol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column finance_point.stock_no
     *
     * @return the value of finance_point.stock_no
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public String getStockNo() {
        return stockNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column finance_point.stock_no
     *
     * @param stockNo the value for finance_point.stock_no
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public void setStockNo(String stockNo) {
        this.stockNo = stockNo == null ? null : stockNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column finance_point.stock_name
     *
     * @return the value of finance_point.stock_name
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public String getStockName() {
        return stockName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column finance_point.stock_name
     *
     * @param stockName the value for finance_point.stock_name
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column finance_point.out_ext
     *
     * @return the value of finance_point.out_ext
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public String getOutExt() {
        return outExt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column finance_point.out_ext
     *
     * @param outExt the value for finance_point.out_ext
     *
     * @mbg.generated Sun Aug 06 20:57:22 CST 2023
     */
    public void setOutExt(String outExt) {
        this.outExt = outExt == null ? null : outExt.trim();
    }
}
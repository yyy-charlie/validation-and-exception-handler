package com.charlie.validationandexceptionhandler.vo;

import java.util.List;

/**
 * @ClassName TableData
 * @Description TODO
 * @Author ycn
 * @Date 2020-05-06
 **/
public class TableData<T> {
    long total;
    List<T> rows;

    public TableData(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public TableData() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

package com.charlie.validationandexceptionhandler.vo;

import com.charlie.validationandexceptionhandler.enums.ResponseCode;

import java.util.List;

/**
 * @ClassName TableResult
 * @Description TODO
 * @Author ycn
 * @Date 2020-05-06
 **/
public class TableResult<T> extends BaseResponse {
    TableData<T> data;

    public TableResult(long total, List<T> rows) {
        this.data = new TableData<T>(total, rows);
    }

    @Override
    public TableData<T> getData() {
        return data;
    }

    public void setData(TableData<T> data) {
        this.data = data;
    }
}

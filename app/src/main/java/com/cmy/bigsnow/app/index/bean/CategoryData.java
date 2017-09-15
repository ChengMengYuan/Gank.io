package com.cmy.bigsnow.app.index.bean;

import java.util.List;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/8/7
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class CategoryData {
    private boolean error;
    private List<DailyResults> results;


    public void setError(boolean error) {
        this.error = error;
    }

    public boolean getError(){
        return error;
    }

    public boolean isError() {
        return error;
    }

    public List<DailyResults> getResults() {
        return results;
    }

    public void setResults(List<DailyResults> results) {
        this.results = results;
    }
}

package com.cmy.bigsnow.app.index.bean;

import com.cmy.bigsnow.bean.HistoryResults;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/7/31
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class CallBack implements Serializable {
    private boolean error;
    private List<HistoryResults> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean getError() {
        return this.error;
    }

    public void setResults(List<HistoryResults> results) {
        this.results = results;
    }

    public List<HistoryResults> getResults() {
        return this.results;
    }
}

package com.cmy.bigsnow.bean;

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
    private List<Results> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean getError() {
        return this.error;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public List<Results> getResults() {
        return this.results;
    }
}

package com.cmy.bigsnow.app.index.bean;

import com.cmy.bigsnow.bean.HistoryResults;

import java.io.Serializable;
import java.util.List;

/**
 * The type Call back.
 *
 * @Author : mengyuan.cheng
 * @Version : 2017/7/31
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */
public class CallBack implements Serializable {
    private boolean error;
    private List<HistoryResults> results;

    /**
     * Is error boolean.
     *
     * @return the boolean
     */
    public boolean isError() {
        return error;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public boolean getError() {
        return this.error;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults(List<HistoryResults> results) {
        this.results = results;
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    public List<HistoryResults> getResults() {
        return this.results;
    }
}

package com.cmy.bigsnow.app.index.bean;

import java.util.List;

/**
 * The type Category data.
 *
 * @Author : mengyuan.cheng
 * @Version : 2017/8/7
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */
public class CategoryData {
    private boolean error;
    private List<DailyResults> results;


    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * Get error boolean.
     *
     * @return the boolean
     */
    public boolean getError(){
        return error;
    }

    /**
     * Is error boolean.
     *
     * @return the boolean
     */
    public boolean isError() {
        return error;
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    public List<DailyResults> getResults() {
        return results;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults(List<DailyResults> results) {
        this.results = results;
    }
}

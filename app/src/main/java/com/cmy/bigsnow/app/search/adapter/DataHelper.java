package com.cmy.bigsnow.app.search.adapter;

import android.content.Context;
import android.util.Log;

import com.cmy.bigsnow.greendao.GreenDaoManager;
import com.cmy.bigsnow.greendao.MSuggestionDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Data helper.
 *
 * @Author : mengyuan.cheng
 * @Version : 2017/9/5
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */
public class DataHelper {
    private static MSuggestionDao mDao = GreenDaoManager.getInstance().getSession().getMSuggestionDao();
    private static List<MSuggestion> sColorSuggestions =
            new ArrayList<>();


    /**
     * 获取历史记录
     *
     * @param context the context
     * @param count   the count
     * @return history
     */
    public static List<MSuggestion> getHistory(Context context, int count) {
        List<MSuggestion> suggestionList = new ArrayList<>();
        MSuggestion colorSuggestion;
        sColorSuggestions = mDao.loadAll();
        Collections.reverse(sColorSuggestions);
        for (int i = 0; i < sColorSuggestions.size(); i++) {
            colorSuggestion = sColorSuggestions.get(i);
            Log.d("DataHelper", "getHistory: " + colorSuggestion.getBody());
            colorSuggestion.setIsHistory(true);
            suggestionList.add(colorSuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }

    /**
     * 设置历史记录
     *
     * @param context the context
     * @param string  the string
     */
    public static void setHistory(Context context, String string) {
        MSuggestion mSuggestion = new MSuggestion(string);
        mSuggestion.setIsHistory(true);
        Log.d("DataHelper", "setHistory: " + string);
        for (MSuggestion m :mDao.loadAll()) {
            if (m.getBody().equals(string)){
                mDao.delete(m);
            }
        }
        mDao.insertOrReplaceInTx(mSuggestion);
    }

    /**
     * 重置历史建议
     */
    public static void resetSuggestionsHistory() {
        mDao.deleteInTx(mDao.loadAll());
    }

}

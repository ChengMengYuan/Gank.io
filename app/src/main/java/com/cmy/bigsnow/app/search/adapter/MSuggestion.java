package com.cmy.bigsnow.app.search.adapter;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * The type M suggestion.
 *
 * @Author : mengyuan.cheng
 * @Version : 2017/9/5
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */
@Entity
public class MSuggestion implements SearchSuggestion {
    @NotNull
    private String suggestion;
    private boolean mIsHistory = false;

    /**
     * Instantiates a new M suggestion.
     *
     * @param suggestion the suggestion
     */
    public MSuggestion(String suggestion) {
        this.suggestion = suggestion.toLowerCase();
    }

    /**
     * Instantiates a new M suggestion.
     *
     * @param source the source
     */
    public MSuggestion(Parcel source) {
        this.suggestion = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    /**
     * Instantiates a new M suggestion.
     *
     * @param suggestion the suggestion
     * @param mIsHistory the m is history
     */
    @Generated(hash = 1374882516)
    public MSuggestion(@NotNull String suggestion, boolean mIsHistory) {
        this.suggestion = suggestion;
        this.mIsHistory = mIsHistory;
    }

    /**
     * Instantiates a new M suggestion.
     */
    @Generated(hash = 1525577166)
    public MSuggestion() {
    }

    /**
     * Sets is history.
     *
     * @param isHistory the is history
     */
    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    /**
     * The constant CREATOR.
     */
    public static final Creator<MSuggestion> CREATOR = new Creator<MSuggestion>() {
        @Override
        public MSuggestion createFromParcel(Parcel in) {
            return new MSuggestion(in);
        }

        @Override
        public MSuggestion[] newArray(int size) {
            return new MSuggestion[size];
        }
    };


    @Override
    public String getBody() {
        return suggestion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(suggestion);
        dest.writeInt(mIsHistory ? 1 : 0);
    }

    /**
     * Gets is history.
     *
     * @return the is history
     */
    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    /**
     * Gets suggestion.
     *
     * @return the suggestion
     */
    public String getSuggestion() {
        return this.suggestion;
    }

    /**
     * Sets suggestion.
     *
     * @param suggestion the suggestion
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    /**
     * Gets m is history.
     *
     * @return the m is history
     */
    public boolean getMIsHistory() {
        return this.mIsHistory;
    }

    /**
     * Sets m is history.
     *
     * @param mIsHistory the m is history
     */
    public void setMIsHistory(boolean mIsHistory) {
        this.mIsHistory = mIsHistory;
    }
}

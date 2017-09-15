package com.cmy.bigsnow.app.search.adapter;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
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

    public MSuggestion(String suggestion) {
        this.suggestion = suggestion.toLowerCase();
    }

    public MSuggestion(Parcel source) {
        this.suggestion = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    @Generated(hash = 1374882516)
    public MSuggestion(@NotNull String suggestion, boolean mIsHistory) {
        this.suggestion = suggestion;
        this.mIsHistory = mIsHistory;
    }

    @Generated(hash = 1525577166)
    public MSuggestion() {
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

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

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    public String getSuggestion() {
        return this.suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public boolean getMIsHistory() {
        return this.mIsHistory;
    }

    public void setMIsHistory(boolean mIsHistory) {
        this.mIsHistory = mIsHistory;
    }
}

package com.cmy.bigsnow.app.search.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.cmy.bigsnow.R;
import com.cmy.bigsnow.app.search.adapter.DataHelper;
import com.cmy.bigsnow.app.search.adapter.MSuggestion;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private Context context;
    //hamburger
    private FloatingSearchView floatingSearchView;
    //hamburger下拉菜单
    private DrawerLayout mDrawerLayout;
    private String mLastQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //浅色状态栏设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            SearchActivity.this
                    .getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        context = getApplicationContext();
        floatingSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        /**
         * 输入的字符发生改变
         */
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                Log.d(TAG, "onSearchTextChanged: " + oldQuery + newQuery);
                mLastQuery = oldQuery + newQuery;
            }
        });

        /**
         * 清除按钮出现
         */
        floatingSearchView.setOnClearSearchActionListener(new FloatingSearchView.OnClearSearchActionListener() {
            @Override
            public void onClearSearchClicked() {
                Log.d(TAG, "onClearSearchClicked: ");
            }
        });

        /**
         * Menu中的按钮被点击
         */
        floatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                Log.d(TAG, "onActionMenuItemSelected: " + item.getTitle());
                switch (item.getTitle().toString()) {
                    case "action_voice_rec":
                        Log.d(TAG, "onActionMenuItemSelected: 请调用语音识别API");
                        break;
                    case "action_search":

                        DataHelper.setHistory(context, mLastQuery);
                        Log.d(TAG, "onActionMenuItemSelected: 请调用搜索API");
                        break;
                }
            }
        });

        /**
         * 搜索时回调
         */
        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                //搜索提示被点击时
                MSuggestion mSuggestion = (MSuggestion) searchSuggestion;

                Log.d(TAG, "onSuggestionClicked: " + mSuggestion.getBody());

                mLastQuery = searchSuggestion.getBody();

                DataHelper.setHistory(context, mLastQuery);
            }

            @Override
            public void onSearchAction(String currentQuery) {
                mLastQuery = currentQuery;

                Log.d(TAG, "onSearchAction: " + currentQuery);
            }
        });

        /**
         * 设置搜索历史提示
         */
        floatingSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                floatingSearchView.swapSuggestions(
                        DataHelper.getHistory(SearchActivity.this,5));
                Log.d(TAG, "onFocus()");
            }

            @Override
            public void onFocusCleared() {
                floatingSearchView.setSearchBarTitle(mLastQuery);
                Log.d(TAG, "onFocusCleared()");
            }
        });

        /**
         * 控制提示的各种属性
         */
        floatingSearchView.setOnBindSuggestionCallback(
                new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
                    @Override
                    public void onBindSuggestion(View suggestionView,
                                                 ImageView leftIcon,
                                                 TextView textView,
                                                 SearchSuggestion item,
                                                 int itemPosition) {
                        Log.d(TAG, "onBindSuggestion: ");
                        MSuggestion mSuggestion = (MSuggestion) item;

                        if (mSuggestion.getIsHistory()) {
                            leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                                    R.drawable.ic_history_black_24dp, null));
                            leftIcon.setAlpha(.36f);
                        } else {
                            leftIcon.setAlpha(0.0f);
                            leftIcon.setImageDrawable(null);
                        }
                        textView.setTextColor(Color.parseColor("#000000"));
                        String text = mSuggestion.getBody()
                                .replaceFirst(floatingSearchView.getQuery(),
                                        "<font color=\"" + "#787878" + "\">" +
                                                floatingSearchView.getQuery() + "</font>");
                        textView.setText(Html.fromHtml(text));
                    }
                    //here you can set some attributes for the suggestion's left icon and text. For example,
                    //you can choose your favorite image-loading library for setting the left icon's image.
                });

        floatingSearchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
            @Override
            public void onMenuOpened() {
                Log.d(TAG, "onMenuOpened: ");
            }

            @Override
            public void onMenuClosed() {
                Log.d(TAG, "onMenuClosed: ");
            }
        });

    }


}

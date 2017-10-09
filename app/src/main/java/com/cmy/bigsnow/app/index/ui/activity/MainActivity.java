package com.cmy.bigsnow.app.index.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.app.index.adapter.MainFragmentPageAdapter;
import com.cmy.bigsnow.app.index.ui.fragment.AndroidFragment;
import com.cmy.bigsnow.app.index.ui.fragment.HtmlFragment;
import com.cmy.bigsnow.app.index.ui.fragment.IosFragment;
import com.cmy.bigsnow.app.index.ui.fragment.NewFragment;
import com.cmy.bigsnow.app.search.activity.SearchActivity;
import com.cmy.bigsnow.app.welfare.activity.WelfareActivity;
import com.cmy.bigsnow.utils.ActivityUtil;
import com.cmy.bigsnow.utils.Regex;
import com.cmy.bigsnow.utils.SnackbarUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private long mExitTime;//退出时间标志

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private MainFragmentPageAdapter fragmentPageAdapter;
    //    TableLayout标签集合
    private ArrayList<String> titleList = new ArrayList<String>() {
        {
            add("每日");
            add("安卓");
            add("IOS");
            add("前端");
        }
    };
    //首页Fragment集合
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>() {{
        add(new NewFragment());
        add(new AndroidFragment());
        add(new IosFragment());
        add(new HtmlFragment());
    }};

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityUtil.getInstance().addActivity(this);

        //浅色状态栏设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MainActivity.this
                    .getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Logger.t("UiVisibility").d("test Logger is ok");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //浮动按钮点击监听
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到搜索页面
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        //侧滑菜单设置
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //        drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //默认选中
        navigationView.getMenu().getItem(0).setChecked(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        fragmentPageAdapter = new MainFragmentPageAdapter(
                getSupportFragmentManager(),
                titleList,
                fragmentList);

        viewPager.setAdapter(fragmentPageAdapter);
        //设置ViewPage的缓存个数（实际有4个，缓存3个+正在显示的1个）
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager, true);
        tabLayout.setTabsFromPagerAdapter(fragmentPageAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //置弹出菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //监听弹出选项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //侧滑菜单点击监听
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gank) {

        } else if (id == R.id.nav_welfare) {
            Intent intent = new Intent(MainActivity.this, WelfareActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_collection) {

        } else if (id == R.id.nav_submit) {

        } else if (id == R.id.nav_ours) {
            // FIXME: 2017/9/16 测试代码
            Regex.getTitleImg(Regex.content);
            String s = Regex.getDailyContent(Regex.content, "Android");
            Regex.getDailyDetail(s);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 双击返回退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Log.d("onKeyDown", "" + keyCode);
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Snackbar sb = SnackbarUtil.ShortSnackbar(viewPager,
                        "再按一次退出",
                        SnackbarUtil.red).
                        setActionTextColor(Color.WHITE).
                        setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                throw null;
                            }
                        });
                sb.show();
                mExitTime = System.currentTimeMillis();
            } else {
                ActivityUtil.getInstance().destory();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.cmy.bigsnow.utils;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/9/16
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class Regex {
    public static String content = "<p><img alt=\\\"\\\" src=\\\"https://ws1.sinaimg.cn/large/610dc034ly1fjgfyxgwgnj20u00gvgmt.jpg\\\" /></p>\\r\\n\\r\\n<h3>iOS</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"http://www.jianshu.com/p/2dc3c9e0ca3e\\\" target=\\\"_blank\\\">iOS UITableView 动画</a>&nbsp;(王小树)\\r\\n\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<h3>Android</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"http://rkhcy.github.io/2017/09/12/ViewStub%E5%AD%A6%E4%B9%A0/\\\" target=\\\"_blank\\\">ViewStub学习</a>&nbsp;(HuYounger)\\r\\n\\r\\n\\t<ul>\\r\\n\\t\\t<li><a href=\\\"http://rkhcy.github.io/2017/09/12/ViewStub%E5%AD%A6%E4%B9%A0/\\\" target=\\\"_blank\\\"><img src=\\\"http://img.gank.io/9f51b7bc-73d3-4ce4-bd59-c61cb0c64325\\\" title=\\\"ViewStub学习\\\" /></a></li>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"https://github.com/zjutkz/ASnowflake\\\" target=\\\"_blank\\\">基于Snowflake算法而写的一种unique id生成器</a>&nbsp;(None)\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&amp;mid=2247486906&amp;idx=1&amp;sn=d6f473abffdea7b2ece6f6bb1f72e084\\\" target=\\\"_blank\\\">看我逆向小米rom层应用做碎片化适配</a>&nbsp;(陈宇明)\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"https://github.com/jungletian/BreakWords\\\" target=\\\"_blank\\\">一个用 Kotlin 写的查单词 idea 插件</a>&nbsp;(ZhangTitanjum)\\r\\n\\t<ul>\\r\\n\\t\\t<li><a href=\\\"https://github.com/jungletian/BreakWords\\\" target=\\\"_blank\\\"><img src=\\\"http://img.gank.io/4939784c-eea2-4b60-9c7a-d1b4d9f9c8b5\\\" title=\\\"一个用 Kotlin 写的查单词 idea 插件\\\" /></a></li>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n\\t<li><a href=\\\"https://github.com/drakeet/Floo\\\" target=\\\"_blank\\\">Floo 开源了，一个支持 AOP、栈控制、跨页面带信、和动态变更映射的 URL 路由</a>&nbsp;(drakeet)\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<h3>前端</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"https://github.com/WeiChiaChang/easter-egg-collection\\\" target=\\\"_blank\\\">帶給你滿滿的彩蛋 library, 持續新增中～</a>&nbsp;(WesleyChang)\\r\\n\\r\\n\\t<ul>\\r\\n\\t\\t<li><a href=\\\"https://github.com/WeiChiaChang/easter-egg-collection\\\" target=\\\"_blank\\\"><img src=\\\"http://img.gank.io/1b9eaccb-84e5-43f8-a440-9f6ae87f32cc\\\" title=\\\"帶給你滿滿的彩蛋 library, 持續新增中～\\\" /></a></li>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<h3>拓展资源</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"http://www.jianshu.com/p/e190978a5085\\\" target=\\\"_blank\\\">vim模糊查找插件隆重推荐</a>&nbsp;(Yggdroot)\\r\\n\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<h3>休息视频</h3>\\r\\n\\r\\n<ul>\\r\\n\\t<li><a href=\\\"http://www.bilibili.com/video/av14410929/\\\" target=\\\"_blank\\\">一万美元拍出来的著名科幻电影！五分钟看完经典科幻片《这个男人来自地球》</a>&nbsp;(LHF)\\r\\n\\r\\n\\t<ul>\\r\\n\\t</ul>\\r\\n\\t</li>\\r\\n</ul>\\r\\n\\r\\n<p>感谢所有默默付出的编辑们，愿大家有美好一天。</p>\\r\\n";
    //正则表达式
    private static String regex = "";
    //存放标题和url的map
    private static HashMap<String, String> dataMap = new HashMap<>();
    //存放上面map的list
    private static ArrayList<HashMap<String, String>> dataList = new ArrayList<>();


    /**
     * 从传入的content中获取到第一张图片的url,如果没有图片,则返回默认的图片url
     *
     * @param content
     * @return
     */
    public static String getTitleImg(String content) {
        // TODO: 2017/9/16 如果没抓取到就显示默认的图片
        String ImgURL = "";
        regex = "src=\"(.+?)\"";
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
        Pattern pattern = Pattern.compile(regex);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(content);
        // 如果找到了
        if (matcher.find()) {
            // 打印出结果
            ImgURL = matcher.group(1);
        }
        Logger.t("REGEX").d(ImgURL);
        return ImgURL;
    }

    /**
     * 返回所有该类别干货content
     *
     * @param content
     * @param daily   想要获取干货的类别
     * @return
     */
    public static String getDailyContent(String content, String daily) {
        String webContent = "";
        Pattern pattern;
        Matcher matcher;
        switch (daily) {
            case "iOS":
                regex = "<h3>iOS</h3>(.+?)<h3>";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(content);
                while (matcher.find()) {
                    webContent = matcher.group(1);
                    Logger.t("getDailyContent,iOS").d(webContent);
                }
                break;

            case "Android":
                regex = "<h3>Android</h3>(.+?)<h3>";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(content);
                while (matcher.find()) {
                    webContent = matcher.group(1);
                    Logger.t("getDailyContent,iOS").d(webContent);
                }
                break;

            case "休息视频":
                regex = "<h3>休息视频</h3>(.+?)</ul>";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(content);
                while (matcher.find()) {
                    webContent = matcher.group(1);
                    Logger.t("getDailyContent,休息视频").d(webContent);
                }
                break;

            case "拓展资源":
                regex = "<h3>拓展资源</h3>(.+?)<h3>";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(content);
                while (matcher.find()) {
                    webContent = matcher.group(1);
                    Logger.t("getDailyContent,拓展资源").d(webContent);
                }
                break;

            case "瞎推荐":
                regex = "<h3>瞎推荐</h3>(.+?)<h3>";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(content);
                while (matcher.find()) {
                    webContent = matcher.group(1);
                    Logger.t("getDailyContent,瞎推荐").d(webContent);
                }
                break;

            case "福利":
                regex = "<h3>福利</h3>(.+?)<h3>";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(content);
                while (matcher.find()) {
                    webContent = matcher.group(1);
                    Logger.t("getDailyContent,福利").d(webContent);
                }
                break;

            case "App":
                regex = "<h3>App</h3>(.+?)<h3>";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(content);
                while (matcher.find()) {
                    webContent = matcher.group(1);
                    Logger.t("getDailyContent,App").d(webContent);
                }
                break;

            case "前端":
                regex = "<h3>前端</h3>(.+?)<h3>";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(content);
                while (matcher.find()) {
                    webContent = matcher.group(1);
                    Logger.t("getDailyContent,前端").d(webContent);
                }
                break;

            default:
                throw new RuntimeException("非法参数");
        }
        return webContent;
    }

    /**
     * 获取
     *
     * @param webContent
     * @return
     */
    public static void getDailyDetail(String webContent) {
        regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
        String all = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(webContent);
        while (matcher.find()) {
            all = matcher.group(0);
            Logger.t("getDailyDetail").d(all);
        }
    }

}

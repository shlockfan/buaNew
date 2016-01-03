package chenanze.com.buanews;

import android.app.Application;

import com.orhanobut.logger.Logger;

import chenanze.com.buanews.network.crawler.WebCrawler;

/**
 * Created by duian on 15/12/22.
 */
public class MyApplication extends Application {

    private WebCrawler webCrawler;
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Web爬虫单例
        WebCrawler.init(Config.baseTargetUrl);
        // 初始化 Logger 日志美化组件
        Logger.init();
    }

}

package chenanze.com.buanews.network.crawler;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import chenanze.com.buanews.entity.WebContent;

/**
 * Created by duian on 15/12/21.
 */
public class WebCrawler {

    private ArrayList<String> webContentUrls;
    private ArrayList<WebContent> webContents = new ArrayList<>();
    private Document doc;
    private String baseTargetUrl;
    private static WebCrawler webCrawler;

    public static WebCrawler getInstance() {
        if (webCrawler != null)
            return webCrawler;
        return null;
    }

    public static void init(String url) {
        if (webCrawler == null)
            webCrawler = new WebCrawler(url);
    }

    public WebCrawler(String url) {
            baseTargetUrl = url;
    }

    /**
     * 获取内容数据列表
     */
    public ArrayList<HashMap<String, Object>> getContentListData() {

        if (doc == null)
            return null;

        Elements links = doc.select("a[target=_blank]");
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        webContentUrls = new ArrayList<>();
        String webContentUrl;
        for(Element link : links){
            HashMap<String,Object> map = new HashMap<String, Object>();
            if(!"".equals(link.attr("title"))){
                map.put("title", link.attr("title"));
                //map.put("href", link.attr("href"));
                webContentUrl = link.attr("href");
                data.add(map);
                webContentUrls.add(webContentUrl);
                Log.i("XmlService", link.attr("title"));
                Log.i("XmlService", link.attr("href"));
            }
        }

        return data;
    }

    public ArrayList<WebContent> getWebContents() {
        if (doc == null)
            return null;

        Elements links = doc.select("a[target=_blank]");
        webContents.clear();
        for(Element link : links){
            if(!"".equals(link.attr("title"))) {
                WebContent webContent = new WebContent();
                webContent.setTitle(link.attr("title"));
                webContent.setUrl(link.attr("href"));
                webContents.add(webContent);
            }
        }

        return webContents;
    }


    /**
     * 获取图片链接列表
     * @return
     */
    public ArrayList<String> getImageUrls() {

        if (doc == null)
            return null;

        ArrayList<String> imageUrls;
        Elements events = doc.select("div[id=kinMaxShow] img");
        imageUrls = new ArrayList<String>();
        String imageUrl = new String();
        for (Element event : events) {
            imageUrl = event.attr("src");
            imageUrls.add(baseTargetUrl + imageUrl);
            Log.i("XmlService", imageUrl);
        }
        return imageUrls;
    }

    /**
     * 执行爬取
     */
    public void execteCrawls() {
        try {
            doc = Jsoup.connect(baseTargetUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 爬取网页内容并格式化返回
     * @param path
     * @return
     * @throws IOException
     */
    public WebContent getWebContent(String path) throws IOException{
        Document doc = Jsoup.connect(path).get();
        Elements titleEvents= doc.select("span[objparam=field:strArticleTitle]");
        Elements timeEvents = doc.select("span[objparam=field:strArticleReleaseTime]");
        Elements contentEvents= doc.select("span[objparam=field:strArticleWebContent] span");
        Log.i("getContent", titleEvents.text());
        Log.i("getContent", timeEvents.text());
        Log.i("getContent", contentEvents.text());

        WebContent content = new WebContent(titleEvents.text(), timeEvents.text(),contentEvents.text());
        return content;
    }
}

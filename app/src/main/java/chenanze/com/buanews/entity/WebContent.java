package chenanze.com.buanews.entity;

import java.io.Serializable;

public class WebContent implements Serializable{
    private String title;//
    private String creationTime;//
    private String content;//
    private String url;//


    /**
     * 网页内容
     *
     * @param title        内容标题
     * @param creationTime 内容生成时间
     * @param content      内容体
     */
    public WebContent(String title, String creationTime, String content) {
        super();
        this.title = title;
        this.creationTime = creationTime;
        this.content = content; }

    public WebContent() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

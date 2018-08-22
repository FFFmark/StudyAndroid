package com.zptc.domebilibili.fargment.tab.JsonBean;

/*
* 这个类用于json拿到数据后解析各个字段
* 所需构造方法，toString，get，set等方法
* */

/*
*
*           "id":71571,
            "movieName":"《无敌破坏王2》新预告",
            "coverImg":"http://img5.mtime.cn/mg/2018/08/11/091008.76329000_120X90X4.jpg",
            "movieId":226450,
            "url":"http://vfx.mtime.cn/Video/2018/08/11/mp4/180811091117469119.mp4",
            "hightUrl":"http://vfx.mtime.cn/Video/2018/08/11/mp4/180811091117469119.mp4",
            "videoTitle":"无敌破坏王2 预告盖尔加朵配音亮相",
            "videoLength":60,
            "rating":-1,
            "type":[
                "动画",
                "冒险",
                "喜剧",
                "家庭",
                "奇幻",
                "科幻"
            ],
            "summary":"迪士尼诸多公主大聚会"
*
* */

import java.util.Arrays;

public class Trailers {

    private int id,movieId,videoLength,rating;
    private String movieName,coverImg,url,hightUrl,videoTitle,summary;
    private String[] type;

    public Trailers (){

    }

    public Trailers(int id, int movieId, int videoLength, int rating, String movieName, String coverImg, String url, String hightUrl, String videoTitle, String summary, String[] type) {
        this.id = id;
        this.movieId = movieId;
        this.videoLength = videoLength;
        this.rating = rating;
        this.movieName = movieName;
        this.coverImg = coverImg;
        this.url = url;
        this.hightUrl = hightUrl;
        this.videoTitle = videoTitle;
        this.summary = summary;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(int videoLength) {
        this.videoLength = videoLength;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHightUrl() {
        return hightUrl;
    }

    public void setHightUrl(String hightUrl) {
        this.hightUrl = hightUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Trailers{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", videoLength=" + videoLength +
                ", rating=" + rating +
                ", movieName='" + movieName + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", url='" + url + '\'' +
                ", hightUrl='" + hightUrl + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", summary='" + summary + '\'' +
                ", type=" + Arrays.toString(type) +
                '}';
    }


}

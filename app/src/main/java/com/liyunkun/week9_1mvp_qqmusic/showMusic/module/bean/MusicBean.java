package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public class MusicBean {

    /**
     * songname : 是你
     * seconds : 231
     * albummid : 000JN9VH0F2Lfk
     * songid : 107899426
     * singerid : 34412
     * albumpic_big : http://i.gtimg.cn/music/photo/mid_album_300/f/k/000JN9VH0F2Lfk.jpg
     * albumpic_small : http://i.gtimg.cn/music/photo/mid_album_90/f/k/000JN9VH0F2Lfk.jpg
     * downUrl : http://dl.stream.qqmusic.qq.com/107899426.mp3?vkey=09A63325B0A7D639CDDF4FDE91B8D65D180698AA9762497F49765B52664E1583C08FA1D3CC7D86DDDC3D62A73B46E1DAA55086AC8D948BB3&guid=2718671044
     * url : http://ws.stream.qqmusic.qq.com/107899426.m4a?fromtag=46
     * singername : TFBOYS
     * albumid : 1544479
     */

    private String songname;
    private int seconds;
    private String albummid;
    private int songid;
    private int singerid;
    private String albumpic_big;
    private String albumpic_small;
    private String downUrl;
    private String url;
    private String singername;
    private int albumid;


    public MusicBean() {
    }

    public MusicBean(String songname, int seconds, String albummid, int songid, int singerid, String albumpic_big, String albumpic_small, String downUrl, String url, String singername, int albumid) {
        this.songname = songname;
        this.seconds = seconds;
        this.albummid = albummid;
        this.songid = songid;
        this.singerid = singerid;
        this.albumpic_big = albumpic_big;
        this.albumpic_small = albumpic_small;
        this.downUrl = downUrl;
        this.url = url;
        this.singername = singername;
        this.albumid = albumid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getAlbummid() {
        return albummid;
    }

    public void setAlbummid(String albummid) {
        this.albummid = albummid;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public int getSingerid() {
        return singerid;
    }

    public void setSingerid(int singerid) {
        this.singerid = singerid;
    }

    public String getAlbumpic_big() {
        return albumpic_big;
    }

    public void setAlbumpic_big(String albumpic_big) {
        this.albumpic_big = albumpic_big;
    }

    public String getAlbumpic_small() {
        return albumpic_small;
    }

    public void setAlbumpic_small(String albumpic_small) {
        this.albumpic_small = albumpic_small;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }
}

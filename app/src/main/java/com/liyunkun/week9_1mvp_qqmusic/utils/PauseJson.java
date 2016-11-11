package com.liyunkun.week9_1mvp_qqmusic.utils;

import com.liyunkun.lrcview.Bean.LrcBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyunkun on 2016/10/19 0019.
 * private String songname;
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
 */
public class PauseJson {
    public static List<MusicBean> getData(String json){
        List<MusicBean> list=new ArrayList<>();
        try {
            JSONArray songList = new JSONObject(json).optJSONObject("showapi_res_body").optJSONObject("pagebean").optJSONArray("songlist");
            for (int i = 0; i < songList.length(); i++) {
                JSONObject song = songList.optJSONObject(i);
                String songname = song.optString("songname");
                String albummid = song.optString("albummid");
                String albumpic_big = song.optString("albumpic_big");
                String albumpic_small = song.optString("albumpic_small");
                String downUrl = song.optString("downUrl");
                String url = song.optString("url");
                String singername = song.optString("singername");
                int seconds = song.optInt("seconds");
                int songid = song.optInt("songid");
                int singerid = song.optInt("singerid");
                int albumid = song.optInt("albumid");
                list.add(new MusicBean(songname,seconds,
                        albummid,songid,singerid,
                        albumpic_big,albumpic_small,downUrl,url,singername,albumid));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<LrcBean> getData2(String json){
        List<LrcBean> list=new ArrayList<>();
        try {
            String lrc = new JSONObject(json)
                    .optJSONObject("showapi_res_body")
                    .optString("lyric")
                    .replaceAll("&#58;", ":")
                    .replaceAll("&#10;", "\n")
                    .replaceAll("&#46;", ".")
                    .replaceAll("&#32;", " ")
                    .replaceAll("&#45;", "-")
                    .replaceAll("&#13;", "\r")
                    .replaceAll("&#39;","'");
            String[] array = lrc.split("\n");
            for (int i = 0; i < array.length; i++) {
                String result = array[i];
                if(result.contains(".")){
                    String timeOne = result.substring(result.indexOf("[")+1, result.indexOf(":"));
                    String timeTwo = result.substring(result.indexOf(":")+1, result.indexOf("."));
                    String timeThree = result.substring(result.indexOf(".")+1, result.indexOf("]"));
                    String text = result.substring(result.indexOf("]")+1);
                    if(text == null || "".equals(text)){
                        text="music";
                    }
                    long startTime=Long.valueOf(timeOne)*1000*60+Long.valueOf(timeTwo)*1000+Long.valueOf(timeThree)*10;
                    list.add(new LrcBean(startTime,0,text));
                    /**
                     * 次数   list.size   end(boolean)        add(end)
                     *  1       1               false           null
                     *  2       2               false           添加startTime给list集合中的endTime（索引为0）
                     */
                    if (list.size() > 1) {
                        list.get(list.size() - 2).setEnd(startTime);
                    }
                    if (i == array.length - 1) {
                        list.get(list.size() - 1).setEnd(startTime + 100000);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}

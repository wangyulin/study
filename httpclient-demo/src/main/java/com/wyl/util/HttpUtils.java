package com.wyl.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by wangyulin on 24/02/2017.
 */
public class HttpUtils {

    public static CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
    public static Map<String, Boolean> tagStore = new HashMap<>();
    public static List<String> originTags = new ArrayList<>();
    public static String baseDir = "/Users/wangyulin/work/MiTV_Wallpaper/";

    public static void main(String[] args) throws Exception {
        String url = "http://wallpaper.pandora.xiaomi.com/api/a1/gallery/search/seek?search_type=0&page_size=10000&keyword=%s";

        /*
        for(String oringinTag : originTags) {
            System.out.println(oringinTag);
            JSONObject res = getResponse(String.format(url, oringinTag));
            Map<String, Boolean> tmp = getTags(res);
            for(String newtag : tmp.keySet()) {
                if(!tagStore.containsKey(newtag)) {
                    tagStore.put(newtag, false);
                }
            }
        }
        Path path = Paths.get("/Users/wangyulin/work/tags_v2.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for(String tag : tagStore.keySet()) {
                writer.write(tag + "\n");
            }
        }
        */

        JSONObject res = getResponse(String.format(url, "aaaaa"));
        if(res != null) {
            JSONArray items = res.getJSONArray("items");
            for(int i = 0; i < items.size(); i++ ) {
                JSONObject one = (JSONObject) items.get(i);
                if(one.containsKey("id")) {
                    String id = one.getString("id");
                    String wrapperDirStr = baseDir + id;
                    File wrapperDir = new File(wrapperDirStr);
                    if(!wrapperDir.exists()) {
                        if(wrapperDir.mkdir()) {
                            Path path = Paths.get(wrapperDirStr + "/" + "item.json");
                            try (BufferedWriter writer = Files.newBufferedWriter(path)){
                                writer.write(one.toJSONString());
                            }
                        } else {

                        }
                    }
                }
            }
        }
        httpclient.close();
        System.exit(0);
    }

    public static Map<String, Boolean> getTags(JSONObject response) {
        Map<String, Boolean> res = new HashMap<>();
        if(response != null) {
            JSONArray items = response.getJSONArray("items");
            if(items != null && items.size() > 0) {
                for (int i = 0; i < items.size(); i++) {
                    JSONObject one = (JSONObject) items.get(i);
                    if(one.containsKey("meta")) {
                        JSONObject meta = (JSONObject) one.getJSONObject("meta");
                        if(meta.containsKey("tags")) {
                            JSONArray tags = meta.getJSONArray("tags");
                            if(tags != null && tags.size() > 0) {
                                for(int e = 0; e < tags.size();e++ ) {
                                    String tag = (String) tags.get(e);
                                    res.put(tag, false);
                                }
                            }
                        }
                    }
                }
            }
            return res;
        }
        return null;
    }

    public static JSONObject getResponse(String url) throws Exception {
        try {
            httpclient.start();
            HttpGet request = new HttpGet(url);
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            HttpEntity entity = response.getEntity();

            String charset = EntityUtils.getContentCharSet(entity);

            JSONObject json = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            return json;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            //httpclient.close();
        }
    }

    static {
        originTags.add("aaaa");
        /*originTags.add("白雪");
        originTags.add("流星");
        originTags.add("奇幻");
        originTags.add("欧洲");
        originTags.add("女孩");
        originTags.add("书籍");
        originTags.add("客厅");
        originTags.add("地毯");
        originTags.add("篮球");
        originTags.add("运动");
        originTags.add("章子怡");
        originTags.add("美丽");
        originTags.add("日系车");
        originTags.add("运动感");
        originTags.add("公路");
        originTags.add("神秘");
        originTags.add("人像");
        originTags.add("模特");
        originTags.add("Dior");
        originTags.add("春夏");
        originTags.add("奢侈品");
        originTags.add("小人");
        originTags.add("健康");
        originTags.add("蔬菜");
        originTags.add("有机");
        originTags.add("蛋糕");
        originTags.add("香甜");
        originTags.add("玫瑰");
        originTags.add("泡芙");*/
    }

}

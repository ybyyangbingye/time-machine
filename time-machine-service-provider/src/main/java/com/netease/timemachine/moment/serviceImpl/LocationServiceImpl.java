package com.netease.timemachine.moment.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.timemachine.moment.service.LocationService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 20:29 2018/7/25
 **/
@Service
public class LocationServiceImpl implements LocationService {

    @Override
    public JSONArray searchPosByPoint(String location, Integer page_size, Integer page_index) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder();
        sb.append("https://apis.map.qq.com/ws/geocoder/v1/?");
        sb.append("location=" + location + "&");
        sb.append("key=" + "SJNBZ-GASWX-WTO4H-7TV46-PGPIO-HZFJT" + "&");
        sb.append("get_poi="+ 1 + "&");
        sb.append("poi_options=");
        sb.append("page_size="+ page_size + ";");
        sb.append("page_index="+ page_index);

        HttpGet httpGet = new HttpGet(sb.toString());
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpResponse response = httpClient.execute(httpGet);
        String str = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSONObject.parseObject(str);
        return jsonObject.getJSONObject("result").getJSONArray("pois");
    }

    @Override
    public JSONArray searchPosByKeyWord(String location, String keyword, Integer page_size, Integer page_index) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder();
        sb.append("https://apis.map.qq.com/ws/place/v1/suggestion/?");
        sb.append("location=" + location + "&");
        sb.append("key=" + "SJNBZ-GASWX-WTO4H-7TV46-PGPIO-HZFJT" + "&");
        sb.append("keyword="+ keyword + "&");
        sb.append("region_fix="+ 1 + "&");
        sb.append("page_size="+ page_size + "&");
        sb.append("page_index="+ page_index);

        HttpGet httpGet = new HttpGet(sb.toString());
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpResponse response = httpClient.execute(httpGet);
        String str = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSONObject.parseObject(str);
        return jsonObject.getJSONArray("data");
    }
}

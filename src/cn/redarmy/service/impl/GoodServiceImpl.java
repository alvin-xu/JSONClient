package cn.redarmy.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import cn.redarmy.domain.Good;
import cn.redarmy.service.GoodService;

public class GoodServiceImpl implements GoodService{

	 // ��ȡ���µ���Ʒ��Ϣ   
    /* (non-Javadoc)  
     * @see cn.redarmy.service.Impl.GoodService#findAll()  
     */  
    @Override  
    public List<Good> findAll() {   
        // ��������HttpClient�ͻ���   
        HttpClient httpClient = new DefaultHttpClient();   
        // ���������url   
        String url = "http://192.168.3.101:8080/StrutsJson/csdn/listNewsGoods.action";   
        try {   
            // ��������Ķ���   
            HttpGet get = new HttpGet(new URI(url));   
            // ����get����   
            HttpResponse httpResponse = httpClient.execute(get);   
            // �������ɹ�������Ӧ   
            if (httpResponse.getStatusLine().getStatusCode() == 200) {   
                HttpEntity entity = httpResponse.getEntity();   
                if (entity != null) {   
                    // ��ȡ��������Ӧ��json�ַ���   
                    String json = EntityUtils.toString(entity);
                    return parseArrayJosn(json);   
                }   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return null;   
    }   
  
    //����json�������   
    private List<Good> parseArrayJosn(String json) {   
        List<Good> goods = new ArrayList<Good>();   
        try {   
            JSONArray array = new JSONObject(json).getJSONArray("goods");   
            for (int i = 0; i < array.length(); i++) {   
                JSONObject obj = array.getJSONObject(i);   
                Good good = new Good(obj.getInt("id"), obj.getString("name"),   
                        (float) obj.getDouble("price"));   
                goods.add(good);   
            }   
        } catch (JSONException e) {   
            e.printStackTrace();   
        }   
        return goods;   
    }   
  
    // ��ȡ���µĵ�����Ʒ����ϸ��Ϣ   
    /* (non-Javadoc)  
     * @see cn.redarmy.service.Impl.GoodService#findById()  
     */  
    @Override  
    public Good findById() {   
        // ��������HttpClient�ͻ���   
        HttpClient httpClient = new DefaultHttpClient();   
        // ���������url   
        String url = "http://192.168.3.101:8080/StrutsJson/csdn/findGood.action";   
        try {   
        	Log.d("GET", "start request");
            // ��������Ķ���   
            HttpGet get = new HttpGet(url);   
            // ����get����   
            HttpResponse httpResponse = httpClient.execute(get);
            Log.d("GET","get status"+httpResponse.getStatusLine().getStatusCode());
            
            
            // �������ɹ�������Ӧ   
            if (httpResponse.getStatusLine().getStatusCode() == 200) {   
                HttpEntity entity = httpResponse.getEntity();   
                if (entity != null) {   
                    // ��ȡ��������Ӧ��json�ַ���   
                    String json = EntityUtils.toString(entity);   
                    return parseObjJosn(json);   
                }   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return null;   
    }   
  
    //����json�ĵ�������   
    private Good parseObjJosn(String json) {   
        JSONObject obj = null;   
        try {   
            obj = new JSONObject(json).getJSONObject("good");   
            Good good = new Good(obj.getInt("id"), obj.getString("name"),   
                    (float) obj.getDouble("price"));   
            return good;   
        } catch (JSONException e) {   
            e.printStackTrace();   
        }   
        return null;   
    }   
	
}

package cn.redarmy.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import cn.redarmy.domain.Good;
import cn.redarmy.service.GoodService;

public class GoodServiceImpl implements GoodService{

	String url="http://192.168.3.101:8080/StrutsJson/csdn/";
	 // ��ȡ���µ���Ʒ��Ϣ   
    /* (non-Javadoc)  
     * @see cn.redarmy.service.Impl.GoodService#findAll()  
     */  
    @Override  
    public List<Good> findAll() {   
        // ��������HttpClient�ͻ���   
        HttpClient httpClient = new DefaultHttpClient();   
        
        // ���������url��ͨ��get��������������ݲ���  
        String url1 =url+"listNewsGoods.action?param=tttt";   
        
        try {   
            // ��������Ķ���   
            HttpGet get = new HttpGet(new URI(url1));   
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
        String url2 = url+"findGood.action";   
        try {   
        	Log.d("POST", "start request");
            // ����post����Ķ���   
            HttpPost post=new HttpPost(url2);
            
            JSONObject data=new JSONObject();
            data.put("name", "xubinbin");
            data.put("age", 22);
            data.put("gender","��");
            
            post.setEntity(new StringEntity(data.toString(), HTTP.UTF_8));
            
            // ����post����   
            HttpResponse httpResponse = httpClient.execute(post);
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

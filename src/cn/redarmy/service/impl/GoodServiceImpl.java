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
	 // 获取最新的商品信息   
    /* (non-Javadoc)  
     * @see cn.redarmy.service.Impl.GoodService#findAll()  
     */  
    @Override  
    public List<Good> findAll() {   
        // 创建请求HttpClient客户端   
        HttpClient httpClient = new DefaultHttpClient();   
        
        // 创建请求的url，通过get方法向服务器传递参数  
        String url1 =url+"listNewsGoods.action?param=tttt";   
        
        try {   
            // 创建请求的对象   
            HttpGet get = new HttpGet(new URI(url1));   
            // 发送get请求   
            HttpResponse httpResponse = httpClient.execute(get);   
            // 如果服务成功返回响应   
            if (httpResponse.getStatusLine().getStatusCode() == 200) {   
                HttpEntity entity = httpResponse.getEntity();   
                if (entity != null) {   
                    // 获取服务器响应的json字符串   
                    String json = EntityUtils.toString(entity);
                    return parseArrayJosn(json);   
                }   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return null;   
    }   
  
    //解析json数组对象   
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
  
    // 获取最新的单个商品的详细信息   
    /* (non-Javadoc)  
     * @see cn.redarmy.service.Impl.GoodService#findById()  
     */  
    @Override  
    public Good findById() {   
        // 创建请求HttpClient客户端   
        HttpClient httpClient = new DefaultHttpClient();   
        // 创建请求的url   
        String url2 = url+"findGood.action";   
        try {   
        	Log.d("POST", "start request");
            // 创建post请求的对象   
            HttpPost post=new HttpPost(url2);
            
            JSONObject data=new JSONObject();
            data.put("name", "xubinbin");
            data.put("age", 22);
            data.put("gender","男");
            
            post.setEntity(new StringEntity(data.toString(), HTTP.UTF_8));
            
            // 发送post请求   
            HttpResponse httpResponse = httpClient.execute(post);
            Log.d("GET","get status"+httpResponse.getStatusLine().getStatusCode());
            
            
            // 如果服务成功返回响应   
            if (httpResponse.getStatusLine().getStatusCode() == 200) {   
                HttpEntity entity = httpResponse.getEntity();   
                if (entity != null) {   
                    // 获取服务器响应的json字符串   
                    String json = EntityUtils.toString(entity);   
                    return parseObjJosn(json);   
                }   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return null;   
    }   
  
    //解析json的单个对象   
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

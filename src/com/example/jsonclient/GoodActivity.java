package com.example.jsonclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import cn.redarmy.domain.Good;
import cn.redarmy.service.GoodService;
import cn.redarmy.service.impl.GoodServiceImpl;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GoodActivity extends Activity {
	 
	private GoodService goodService = new GoodServiceImpl(); 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_good);
		 //解析集合   
		   /*   try {  
		            List<Good> goods = goodService.findAll();  
		            ListView listView = (ListView) this.findViewById(R.id.goods);  
		            List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();  
		            for (Good good : goods) {  
		                HashMap<String, Object> item = new HashMap<String, Object>();  
		                item.put("name", good.getName());  
		                item.put("price", getResources().getString(R.string.price)+good.getPrice());  
		                item.put("id", good.getId());  
		                data.add(item);  
		            }  
		            SimpleAdapter adapter = new SimpleAdapter(this, data,  
		                    R.layout.good, new String[] { "name", "price" },  
		                    new int[] { R.id.name, R.id.price });  
		 
		            listView.setAdapter(adapter);  
		        } catch (Exception e) {  
		            Log.v("error", "网络连接失败");  
		            e.printStackTrace();  
		        }*/
		     //解析单个对象   
		      /* try {   
		        	Log.d("MAIN", "find by id");
		            Good good = goodService.findById();   
		            ListView listView = (ListView) this.findViewById(R.id.goods);   
		            List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();   
		               
		                HashMap<String, Object> item = new HashMap<String, Object>();   
		                item.put("name", good.getName());   
		                item.put("price", getResources().getString(R.string.price)+good.getPrice());   
		                item.put("id", good.getId());   
		                data.add(item);   
		               
		            SimpleAdapter adapter = new SimpleAdapter(this, data,   
		                    R.layout.good, new String[] { "name", "price" },   
		                    new int[] { R.id.name, R.id.price });   
		  
		            listView.setAdapter(adapter);   
		        } catch (Exception e) {   
		            Log.v("error", "网络连接失败");   
		            e.printStackTrace();   
		        }   */
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient httpClient=new DefaultHttpClient();
				String url="http://192.168.3.106:8080/SSHJsonServer/student/getStudent.action?id=1";
				HttpGet httpGet=new HttpGet(url);
				try {
					Log.d("Client", "send request with get");
					HttpResponse response=httpClient.execute(httpGet);
					Log.d("Client","status:"+response.getStatusLine().getStatusCode());
					
					if(response.getStatusLine().getStatusCode()==200){
						Log.d("Client","result:"+response.getEntity().toString());
					}
					
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.good, menu);
		return true;
	}

}

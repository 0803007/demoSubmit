package demoSubmit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * Five Steps to Capture the Table Data of Website
 * @author smallsun
 *
 */
public class main {
	static CloseableHttpClient httpclient;
	public static void main(String[] args) throws ClientProtocolException, IOException  {
	
		httpclient = HttpClients.createDefault();
		//-------Step 0: get session id------
		String _uri = "http://c.lrits.net:9090/";
		HttpGet httpGet = new HttpGet(_uri);
		CookieStore cookieStore = new BasicCookieStore();
		HttpContext httpContext = new BasicHttpContext();
		httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
		CloseableHttpResponse sessionResponse = httpclient.execute(httpGet);
		
		//System.out.println("response content:" + EntityUtils.toString(sessionResponse.getEntity())); 
		String tmp = sessionResponse.getFirstHeader("Set-Cookie").getValue();
		String session_str = (String) tmp.subSequence(43,tmp.length()-1);
		System.out.println(session_str);	
		System.out.println(sessionResponse.getFirstHeader("Set-Cookie").getValue());
		//-------Step 1: login & go into default page------
		
		//Use Defalut value	
		String uri = "http://c.lrits.net:9090/" + session_str +"/$/";
		HttpPost httpPost = new HttpPost(uri);
		System.out.println(uri);	
		
		//Set POST pairs
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("IWEDIT2","tori"));
		postData.add(new BasicNameValuePair("IWEDIT3","tori22"));
		postData.add(new BasicNameValuePair("IW_FormName","IWForm1"));
		postData.add(new BasicNameValuePair("IW_FormClass","TIWForm1"));
		postData.add(new BasicNameValuePair("IW_width","1300"));
		postData.add(new BasicNameValuePair("IW_height","957"));
		postData.add(new BasicNameValuePair("IW_Action","IWIMAGE2"));
		postData.add(new BasicNameValuePair("IW_ActionParam",""));
		postData.add(new BasicNameValuePair("IW_SessionID_",session_str));
		postData.add(new BasicNameValuePair("IW_TrackID_","50"));
		
		//Execute & Get Response
		httpPost.setEntity(new UrlEncodedFormEntity(postData));
		CloseableHttpResponse response = httpclient.execute(httpPost);
	    HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity);
		
		//Print response
		try {
		    System.out.println(response.getStatusLine());   
            if (entity != null) {  
                //System.out.println("contentEncoding:" + entity.getContentEncoding());
                //System.out.println("response content:" + html);
            }
            //release
		    //EntityUtils.consume(entity);
            //httpclient.close();  
		} finally {
		    response.close();
		}
	
		//-------Step 2: Click Page2------
		//New url
		HttpPost httpPost2 = new HttpPost(uri);
		//reset new Post pairs
		List<NameValuePair> postData2 = new ArrayList<NameValuePair>();
		postData2.add(new BasicNameValuePair("IWBUTTON1",""));
		postData2.add(new BasicNameValuePair("IWDBEDIT7",""));
		postData2.add(new BasicNameValuePair("IWDBEDIT1",""));
		postData2.add(new BasicNameValuePair("IWCOMBOBOX1","6"));
		postData2.add(new BasicNameValuePair("IWDBEDIT9",""));
		postData2.add(new BasicNameValuePair("IWBUTTON7",""));
		postData2.add(new BasicNameValuePair("IWBUTTON8",""));
		postData2.add(new BasicNameValuePair("IWCOMBOBOX2","-1"));
		postData2.add(new BasicNameValuePair("IWBUTTON36",""));
		postData2.add(new BasicNameValuePair("TIWDBADVWEBGRID1","000000000000000|R0|0^0|x|||||||||||"));
		postData2.add(new BasicNameValuePair("TIWDBADVWEBGRID2","000000000000000|R0|0^0|x|||||||||"));
		postData2.add(new BasicNameValuePair("TIWDATEPICKER2","2/9/2017"));
		postData2.add(new BasicNameValuePair("TIWDATEPICKER1","2/6/2017"));
		postData2.add(new BasicNameValuePair("IWBUTTON11",""));
		postData2.add(new BasicNameValuePair("IWBUTTON12",""));
		postData2.add(new BasicNameValuePair("IWBUTTON2",""));
		postData2.add(new BasicNameValuePair("IWTABCONTROL1_input","1"));
		postData2.add(new BasicNameValuePair("TIWWEBGMAPS1","39.342794408952365^-149.8699951171875^37.9032^211.320898^0^0^0^0^0^0^0^0^0^^8^48.85904^2.294297^0^0^0^0"));
		postData2.add(new BasicNameValuePair("IW_FormName","IWFormTORIC"));
		postData2.add(new BasicNameValuePair("IW_FormClass","TIWFormTORIC"));
		postData2.add(new BasicNameValuePair("IW_width","1027"));
		postData2.add(new BasicNameValuePair("IW_height","974"));
		postData2.add(new BasicNameValuePair("IW_Action","TIWDBADVWEBGRID1"));
		postData2.add(new BasicNameValuePair("IW_ActionParam","Goto1"));//default "" ,page0->"Goto0",page1->"Goto1",page2->"Goto2"......
		postData2.add(new BasicNameValuePair("IW_SessionID_",session_str));
		postData2.add(new BasicNameValuePair("IW_TrackID_","99"));

		//Execute & Get Response
		httpPost2.setEntity(new UrlEncodedFormEntity(postData2));
		CloseableHttpResponse response2 = httpclient.execute(httpPost2);
	    HttpEntity entity2 = response2.getEntity();
	    String html2 = EntityUtils.toString(entity2);
		
		//Print response
		try {
		    System.out.println(response2.getStatusLine());
            if (entity2 != null) {
                //System.out.println("contentEncoding:" + entity2.getContentEncoding());  
                //System.out.println("response content:" + html);  
            }
            //release
		    //EntityUtils.consume(entity2);
            //httpclient.close();
		}finally{
		    response2.close();
		}
		//-------Step 3: Click Page3------
		//New url
		HttpPost httpPost3 = new HttpPost(uri);
		//reset new Post pairs
		List<NameValuePair> postData3 = new ArrayList<NameValuePair>();
		postData3.add(new BasicNameValuePair("IWBUTTON1",""));
		postData3.add(new BasicNameValuePair("IWDBEDIT7",""));
		postData3.add(new BasicNameValuePair("IWDBEDIT1",""));
		postData3.add(new BasicNameValuePair("IWCOMBOBOX1","6"));
		postData3.add(new BasicNameValuePair("IWDBEDIT9",""));
		postData3.add(new BasicNameValuePair("IWBUTTON7",""));
		postData3.add(new BasicNameValuePair("IWBUTTON8",""));
		postData3.add(new BasicNameValuePair("IWCOMBOBOX2","-1"));
		postData3.add(new BasicNameValuePair("IWBUTTON36",""));
		postData3.add(new BasicNameValuePair("TIWDBADVWEBGRID1","000000000000000|R0|0^0|x|||||||||||"));
		postData3.add(new BasicNameValuePair("TIWDBADVWEBGRID2","000000000000000|R0|0^0|x|||||||||"));
		postData3.add(new BasicNameValuePair("TIWDATEPICKER2","2/9/2017"));
		postData3.add(new BasicNameValuePair("TIWDATEPICKER1","2/6/2017"));
		postData3.add(new BasicNameValuePair("IWBUTTON11",""));
		postData3.add(new BasicNameValuePair("IWBUTTON12",""));
		postData3.add(new BasicNameValuePair("IWBUTTON2",""));
		postData3.add(new BasicNameValuePair("IWTABCONTROL1_input","1"));
		postData3.add(new BasicNameValuePair("TIWWEBGMAPS1","39.342794408952365^-149.8699951171875^37.9032^211.320898^0^0^0^0^0^0^0^0^0^^8^48.85904^2.294297^0^0^0^0"));
		postData3.add(new BasicNameValuePair("IW_FormName","IWFormTORIC"));
		postData3.add(new BasicNameValuePair("IW_FormClass","TIWFormTORIC"));
		postData3.add(new BasicNameValuePair("IW_width","1027"));
		postData3.add(new BasicNameValuePair("IW_height","974"));
		postData3.add(new BasicNameValuePair("IW_Action","TIWDBADVWEBGRID1"));
		postData3.add(new BasicNameValuePair("IW_ActionParam","Goto2"));//default "" ,page0->"Goto0",page1->"Goto1",page2->"Goto2"......
		postData3.add(new BasicNameValuePair("IW_SessionID_",session_str));
		postData3.add(new BasicNameValuePair("IW_TrackID_","99"));

		//Execute & Get Response
		httpPost3.setEntity(new UrlEncodedFormEntity(postData3));
		CloseableHttpResponse response3 = httpclient.execute(httpPost3);
	    HttpEntity entity3 = response3.getEntity();
	    String html3 = EntityUtils.toString(entity3);
		
		//Print response
		try {
		    System.out.println(response3.getStatusLine());
            if (entity3 != null) {
                //System.out.println("contentEncoding:" + entity2.getContentEncoding());  
                //System.out.println("response content:" + html);  
            }
            //release
		    EntityUtils.consume(entity3);
            httpclient.close();
		}finally{
		    response3.close();
		}
		      
		//-------------Step 4: transfer HTML to DOM  and Get row data By ID-----------------
		//ArrayList<String> record = new ArrayList<String>(); 
		ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
		//Page 1 :
		Document doc = Jsoup.parse(html);
		Elements trs = doc.select("#TIWDBADVWEBGRID1_").select("tr");
		for(int i = 0;i<trs.size();i++){
			ArrayList<String> record = new ArrayList<String>(); 
            Elements tds = trs.get(i).select("td");
            for(int j = 0;j<tds.size();j++){
                String text = tds.get(j).text();
                //System.out.println(text);
                record.add(text);
            }
            lists.add(record);
        }
		//Page 2 :
		doc = Jsoup.parse(html2);
		trs = doc.select("#TIWDBADVWEBGRID1_").select("tr");
		for(int i = 0;i<trs.size();i++){
			ArrayList<String> record = new ArrayList<String>(); 
            Elements tds = trs.get(i).select("td");
            for(int j = 0;j<tds.size();j++){
                String text = tds.get(j).text();
                System.out.println(text);
                record.add(text);
            }
            lists.add(record);
        }
		//Page 3 :
		doc = Jsoup.parse(html3);
		trs = doc.select("#TIWDBADVWEBGRID1_").select("tr");
		for(int i = 0;i<trs.size();i++){
			ArrayList<String> record = new ArrayList<String>(); 
            Elements tds = trs.get(i).select("td");
            for(int j = 0;j<tds.size();j++){
                String text = tds.get(j).text();
                System.out.println(text);
                record.add(text);
            }
            lists.add(record);
        }
		//-------------Step 5: Connection to DB and insert data-----------------
		Connection conn = null;
		Statement stmt = null;
		Properties p = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver"); 
			p = new Properties();
	        p.put("user","smallsun");
	        p.put("password","711113");
			conn = DriverManager.getConnection("jdbc:mariadb://10.110.21.71:3306/smallsun_drifter", p);
			stmt = conn.createStatement();;
			if(!conn.isClosed()) {
		        System.out.println("資料庫連線成功"); 
		        for(int i=0;i<lists.size();i++){
		        	if (lists.get(i).size() == 11 ){	        	
			        	String Id = lists.get(i).get(3);
			        	String Status = lists.get(i).get(2);
			        	String TimeStamp = lists.get(i).get(4).replace("/", "-");
			        	String Lat = lists.get(i).get(5);
			        	String Lon = lists.get(i).get(6);
			        	String Temp = lists.get(i).get(7);
			        	String Vol = lists.get(i).get(8);
			        	String Dir = lists.get(i).get(9);
			        	String Vel = lists.get(i).get(10);
			        	String str = "'" + Id + "','" +  Status + "','" + TimeStamp + "'," + Lat + "," + Lon + "," + Temp + "," + Vol + "," + Dir + "," + Vel  ;
			        	System.out.println(str); 
			        	if (!Status.equals("需要檢查")){  //ignore the data need "需要檢查"
			        		String sql = "REPLACE INTO Drifter_Data(Drifter_ID,Status,TimeStamp,Lat,Lon,Temp,Vol,Dir,Vel) VALUES (" + str +")"; 
			        		try {
								stmt.executeUpdate(sql);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								System.out.println("Sql語法錯誤"); 
								e.printStackTrace();
							}
			        	}
		        	}
		        }
			}
		    conn.close();  
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("資料庫連線失敗"); 
		} 
		
    }
}

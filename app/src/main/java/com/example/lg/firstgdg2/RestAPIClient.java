package com.example.lg.firstgdg2;

import android.text.Html;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class RestAPIClient {

   final static String myURL = "https://apis.daum.net/search/image?apikey=b62b20a07b737c1cca5b88737980adb11809762a";

    public ArrayList getData(String input){
        //input : 키워드
        ArrayList dataList;

        try {

           //realInput : 입력 값을 한글로 인코딩
            String realInput = URLEncoder.encode(input, "UTF-8");
            String query = "&q="+realInput+"&output=json";
            String urlString = myURL + query;

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject jobj = new JSONObject(getStringFromInputStream(in));
            //new JSONObject(result);

            //parse JSON
            dataList = parseJSON(jobj);

        }catch(MalformedURLException e){
            System.err.println("Malformed URL");
            e.printStackTrace();
            return null;

        }catch(JSONException e) {
            System.err.println("JSON parsing error");
            e.printStackTrace();
            return null;
        }catch(IOException e){
            System.err.println("URL Connection failed");
            e.printStackTrace();
            return null;
        }

        return dataList;
    }

    //JSON을 DATA로 변환 작업
    public ArrayList parseJSON(JSONObject jobj){
        ArrayList<Data> dataArrayList = new ArrayList<Data>();
        try {
            JSONObject main = jobj.getJSONObject("channel");
            String item = main.getString("item");
            JSONArray itemArray = new JSONArray(item);
            //아이템 받아오기


            //sharedGpsActivity 참고하기
            //파싱 받은 데이터들을 Data 타입으로 ArrayList에 저장
            for(int i = 0 ;i<itemArray.length();i++){
                Log.v("itemListttttt", Html.fromHtml(itemArray.getString(i)).toString());//파싱 받아온 아이템들
                Data dataTemp = new Data();


                JSONObject a = (JSONObject)itemArray.get(i);
                dataTemp.setTitle(Html.fromHtml(Html.fromHtml(a.getString("title")).toString()).toString());
                dataTemp.setImage(Html.fromHtml(Html.fromHtml(a.getString("image")).toString()).toString());
                dataTemp.setLink(Html.fromHtml(Html.fromHtml(a.getString("link")).toString()).toString());
                dataTemp.setThumbnail(Html.fromHtml(Html.fromHtml(a.getString("thumbnail")).toString()).toString());
                dataArrayList.add(dataTemp);


            }

            // Html.fromHtml(itemArray.getString(i)).toString() html 변환하기



        }catch (JSONException e){
            e.printStackTrace();
        }
        return dataArrayList;
    }

    private static String getStringFromInputStream(InputStream is){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try{
            br = new BufferedReader(new InputStreamReader(is));
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }//try catch 끝

        return sb.toString();


    }
}

package com.example.yasisi28.portal_parse;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class DownloadTask extends AsyncTask<String, Void, Elements> {
    //非同期処理

    private TextView textView;
    private Object title;

    public DownloadTask(TextView textView) {
        super();
        this.textView = textView;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Elements doInBackground(String... params) {
        Elements title = null;
        String url = "https://akira-watson.com/android/custom-listener.html";
        Elements body = null;
        Elements value = null;
        try {
            //ログインページのHTMLを取得
            Connection.Response res = Jsoup.connect("https://navi.mars.kanazawa-it.ac.jp/portal/student/")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                    .header("Referer", "https://navi.mars.kanazawa-it.ac.jp/portal/student/")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "ja-JP,ja;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Host", "navi.mars.kanazawa-it.ac.jp")
                    .header("Origin", "https://navi.mars.kanazawa-it.ac.jp")
                    .header("Connection", "keep-alive")
                    .header("Cache-Control", "max-age=0")
                    .header("Upgrade-Insecure-Requests", "1")
                    .method(Connection.Method.GET).execute();

            Document doc = res.parse();

            Map<String , String> cookies = res.cookies();

            final  String csrf = doc.select("input[name=_csrf]").first().attr("value");

            final String login = "1601721";

            final String password = "h091207";

            Map<String , String > formData = new LinkedHashMap<>();
            formData.put("_csrf", csrf);
            formData.put("password", "");
            formData.put("uid", login);
            formData.put("pw", password);

            StringBuilder sb = new StringBuilder();
            for(Map.Entry<String, String > entry: formData.entrySet()){
                if(sb.length() > 0){
                    sb.append("&");
                }
                sb.append(entry.getKey() + "=" +URLEncoder.encode(entry.getValue(), "UTF-8").replace("%20", "+"));
            }
            String requestBody = new String(sb);

            //ログイン処理へのリクエスト
            Connection con2 = Jsoup.connect("https://navi.mars.kanazawa-it.ac.jp/portal/student/inKITP0000001Login")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Referer", "https://navi.mars.kanazawa-it.ac.jp/portal/student/")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "ja-JP,ja;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Host", "navi.mars.kanazawa-it.ac.jp")
                    .header("Origin", "https://navi.mars.kanazawa-it.ac.jp")
                    .header("Connection", "keep-alive")
                    .header("Cache-Control", "max-age=0")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("Content-Length", Integer.toString(requestBody.length()))
                    .cookies(cookies)
                    .requestBody(requestBody)
                    .method(Connection.Method.POST);

            Connection.Response res2 = con2.execute();

            //ログイン後に発行されるクッキーの取得
            Map<String, String> cookies2 = res2.cookies();

            Connection.Response res3 = Jsoup.connect("https://navi.mars.kanazawa-it.ac.jp/portal/student/KITP0010001")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                    .header("Referer", "https://navi.mars.kanazawa-it.ac.jp/portal/student/")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "ja-JP,ja;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Host", "navi.mars.kanazawa-it.ac.jp")
                    .header("Connection", "keep-alive")
                    .cookies(cookies2)
                    .method(Connection.Method.GET)
                    .execute();

            Document doc3 = res3.parse();
            //String plan = doc3.select("");
            value = doc3.getElementsByAttributeValue("class", "KITP00200Portfolio");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    protected void onPostExecute(Elements result){
        //super.onPostExecute(result);
//         String str = result.toString();
       textView.setText(result.toString());
        if(textView.length() == 0){
            textView.setText(R.string.error);
        }

    }

}

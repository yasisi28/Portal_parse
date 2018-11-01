package com.example.yasisi28.portal_parse;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

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
        try {
            Document document = Jsoup.connect(url).get();

            title = document.getElementsByTag("title");

            body = document.getElementsByTag("body");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    @Override
    protected void onPostExecute(Elements result){
        super.onPostExecute(result);
        String str = result.toString();
        textView.setText(str);

    }

}

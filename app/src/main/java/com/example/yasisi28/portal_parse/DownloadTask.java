package com.example.yasisi28.portal_parse;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DownloadTask extends AsyncTask<String, Void, Elements> {
    //非同期処理

    private TextView textView;

    public DownloadTask(TextView textView){
        super();
        this.textView = textView;
    }

    @Override
    protected Elements doInBackground(String... params) {
        //String url = "https://techbooster.org/";

        //HTMLのドキュメントを取得
        Elements title = null;
        Elements body;
        try {
            Document document = Jsoup.connect(String.valueOf(params)).get();

            //titleタグを取得
            title = document.getElementsByTag("title");
            //String title1 = document.title();

            //bodyタグをIDから取得
            body = document.getElementsByTag("body");
            //Element body1 = document.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return title;
    }

    @Override
    protected void onPostExecute(Elements title){
        textView.setText(title.toString());
    }


}

package com.example.yasisi28.portal_parse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String param0;
    private DownloadTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button downloadBt = findViewById(R.id.parse_button);
        final TextView textView = findViewById(R.id.View_html);
        final EditText editText = findViewById(R.id.editText);
        downloadBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // param0 = editText.getText().toString();
                param0 = "https://techbooster.org/android/mashup/13635/";

                param0 = editText.getText().toString();

                if(param0.length() != 0){
                    //ボタンをタップして非同期処理の開始
                    task = new DownloadTask(textView);
                    //リスナーを設定
                    task.execute(param0);
                }

            }
        });
    }

}
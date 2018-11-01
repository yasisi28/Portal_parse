package com.example.yasisi28.portal_parse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private EditText editText;
    private com.example.yasisi28.portal_parse.DownloadTask task;
//    EditText editText = this.<EditText>findViewById(R.id.urlText);
    TextView tv = (TextView) findViewById(R.id.View_html);

    String param0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button downloadBt = findViewById(R.id.parse_button);
        downloadBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // param0 = editText.getText().toString();
                param0 = "https://techbooster.org/android/mashup/13635/";

                if(param0.length() != 0){
                    //ボタンをタップして非同期処理の開始
                    task = new com.example.yasisi28.portal_parse.DownloadTask(tv);
                    //リスナーを設定
                    task.execute(param0);
                }

            }
        });
    }
}
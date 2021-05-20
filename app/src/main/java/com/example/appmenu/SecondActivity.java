package com.example.appmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    ImageView imageView2;
    TextView textViewNameSA, textViewAgeSA, textViewQueQuanSA, textViewTongSA;
    Button buttonBack;
    Double tong = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        textViewNameSA = (TextView) findViewById(R.id.textViewNameSA);
        textViewAgeSA = (TextView) findViewById(R.id.textViewAgeSA);
        textViewQueQuanSA = (TextView) findViewById(R.id.textViewQueQuanSA);
        textViewTongSA = (TextView) findViewById(R.id.textViewTong);
        buttonBack = (Button) findViewById(R.id.buttonBackSA);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("fa");
        textViewNameSA.setText("Họ tên: " + bundle.getString("nameFA"));
        textViewAgeSA.setText("Năm sinh: " + bundle.getString("ageFA"));
        String str = bundle.getString("queQuanFA");
        textViewQueQuanSA.setText("Khu vực: " + str);
        imageView2.setImageResource(bundle.getInt("anhFA"));

        if (str.equals("KV1")) {
            tong = 1.0;
        } else {
            tong = 2.0;
        }

        tong += bundle.getDouble("Toan") + bundle.getDouble("Van");
        textViewTongSA.setText("Tổng Điểm: " + tong);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("tong", tong + "");
//                intent1.putExtra("tong", tong);
                setResult(RESULT_OK, intent1);
                finish();
                tong = 0.0;

            }
        });
    }
}
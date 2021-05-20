package com.example.appmenu;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    ImageView imageView, imageViewA, imageViewB, imageViewC;
    EditText editTextNameFA, editTextAgeFA, editTextToan, editTextVan;
    TextView textViewTong;
    Spinner spinnerFA;
    Button buttonSubmit;
    int val = R.drawable.anha;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            textViewTong.setVisibility(View.VISIBLE);
            textViewTong.setText("Tong: " + data.getStringExtra("tong"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // Ánh Xạ
        imageView = (ImageView) findViewById(R.id.imageView);
        imageViewA = (ImageView) findViewById(R.id.imageViewA);
        imageViewB = (ImageView) findViewById(R.id.imageViewB);
        imageViewC = (ImageView) findViewById(R.id.imageViewC);
        editTextNameFA = (EditText) findViewById(R.id.editTextNameFA);
        editTextToan = (EditText) findViewById(R.id.editTextToanFA);
        editTextVan = (EditText) findViewById(R.id.editTextVan);
        textViewTong = (TextView) findViewById(R.id.textViewTongDiem);
        editTextAgeFA = (EditText) findViewById(R.id.editTextAgeFA);
        spinnerFA = (Spinner) findViewById(R.id.spinnerQueQuanFA);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmitFA);

        // Dữ liệu cho Spinner
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("KV1");
        arrayList.add("KV2");
        // adapter in Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FirstActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinnerFA.setAdapter(adapter);


        // Click Button Submit
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                Bundle bundle = new Bundle();
                if (editTextNameFA.getText().toString().trim().length() > 0 && editTextToan.getText().toString().trim().length() > 0 && editTextVan.getText().toString().trim().length() > 0 &&editTextAgeFA.getText().toString().trim().length() > 0) {
                    bundle.putString("nameFA", editTextNameFA.getText().toString().trim());
                    bundle.putString("ageFA", editTextAgeFA.getText().toString().trim());
                    bundle.putString("queQuanFA", spinnerFA.getSelectedItem().toString().trim());
                    bundle.putInt("anhFA", imageView.getImageAlpha());
                    bundle.putInt("anhFA", val);
                    bundle.putDouble("Toan", Double.parseDouble(editTextToan.getText().toString().trim()));
                    bundle.putDouble("Van", Double.parseDouble(editTextVan.getText().toString().trim()));
//                    bundle.putInt("anhFA", imageView.getResources());

                    intent.putExtra("fa", bundle);
                    startActivityForResult(intent, 1);
                } else {
                    // Báo chưa nhập đủ dũ liệu
                    Toast.makeText(FirstActivity.this, "Vui lòng điền đầy đủ thông tin!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Click ảnh 1
        imageViewA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.anha);
                val = R.drawable.anha;
            }
        });

        // Click ảnh 2
        imageViewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.anhb);
                val = R.drawable.anhb;
            }
        });

        // Click ảnh 3
        imageViewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.anhc);
                val = R.drawable.anhc;
            }
        });

        //

    }
}
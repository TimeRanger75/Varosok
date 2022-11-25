package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class InsertActivity extends AppCompatActivity {

    String data="";
    private EditText editTextvaros,editTextorszag,editTextlakossag;
    private Button btnVissza, btnFelvesz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnFelvesz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String varos = editTextvaros.getText().toString().trim();
                String orszag = editTextorszag.getText().toString().trim();
                String lakossag = editTextlakossag.getText().toString().trim();
                String json = "{ \"varos\" : \""+varos+"\", \"orszag\" : \""+orszag+
                        "\", \"lakossag\" : \""+lakossag+"\" }";

                PerformNetworkRequest request = new PerformNetworkRequest("POST", json);
                request.execute();
            }
        });

    }


    private void init(){
        editTextvaros=findViewById(R.id.editTextVaros);
        editTextorszag=findViewById(R.id.editTextOrszag);
        editTextlakossag=findViewById(R.id.editTextLakossag);
        btnFelvesz=findViewById(R.id.btnFelvesz);
        btnVissza=findViewById(R.id.btnVissza);
    }




    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        String method;
        String content;

        public PerformNetworkRequest(String method) {
            this.method = method;
            this.content = "";
        }

        public PerformNetworkRequest(String method, String content) {
            this.method = method;
            this.content = content;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                if (method.equals("GET")){
                    data = Request.getData();
                } else {
                    data = Request.postData(content);
                }
            } catch (IOException e) {
                data = e.getMessage();
            }

            return data;
        }
    }
}
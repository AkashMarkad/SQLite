package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText Email2 , password2;
    Button logout , delete , update;
    MySQLiteOpenHelper mySQLiteOpenHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        logout = findViewById(R.id.btn_logout);
        Email2 = findViewById(R.id.txt2_email);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        password2 = findViewById(R.id.txt2_password);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email2.getText().toString();
                String password = password2.getText().toString();
                mySQLiteOpenHelper.updateRecord(email , password);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email2.getText().toString();
                String password = password2.getText().toString();
                mySQLiteOpenHelper.deleteRecord(email);
            }
        });

    }
}
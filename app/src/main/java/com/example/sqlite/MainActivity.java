package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt_email , txt_password;
    Button register , login;
    MySQLiteOpenHelper mySQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(MainActivity.this);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                mySQLiteOpenHelper.insertRecord(email , password);

                Toast.makeText(MainActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 0;
                Cursor cursor = mySQLiteOpenHelper.selectRecords();
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                
                if (cursor != null)
                {
                    while (cursor.moveToNext())
                    {
                        Toast.makeText(MainActivity.this, "Data is : "+ cursor.getString(0) + " And "+ cursor.getString(1), Toast.LENGTH_SHORT).show();
                        if (email.equals(cursor.getString(0)) && password.equals(cursor.getString(1)))
                        {
                            flag = 1;
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this , MainActivity2.class);
                            startActivity(intent);
                            break;
                        }
                    }
                }
                
                if(flag == 0)
                {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

                txt_email.setText("");
                txt_password.setText("");
            }
        });
    }
}
package com.avtest.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    EditText product, price, discount, quantity;
    ImageView link;
    Button add;
    DBrod db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add);


        product = (EditText) findViewById(R.id.pname);
        price = (EditText) findViewById(R.id.price);
        discount = (EditText) findViewById(R.id.discount);
        quantity = (EditText) findViewById(R.id.quentity);
        add = (Button) findViewById(R.id.insert);
        link =  findViewById(R.id.linked);

        db = new DBrod(this);

        link.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String pod = product.getText().toString();
                String pz = price.getText().toString();
                String dis = discount.getText().toString();
                String qt = quantity.getText().toString();



                if (pod.equals("") || pz.equals("") || dis.equals("") || qt.equals("")) {
                    Toast.makeText(Add.this, "Fill all the fields. ", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean regResult = db.insertData(pod, pz, dis, qt);
                    if (regResult == true) {
                        Toast.makeText(Add.this, " add success.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProductMenu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Add.this, "product add Failed.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    }



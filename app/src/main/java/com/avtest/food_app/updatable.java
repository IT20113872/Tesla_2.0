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

public class updatable extends AppCompatActivity {

    EditText product, price, discount, quantity;
    Button update;

    DBrod db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_updatable);

        product = (EditText) findViewById(R.id.uname);
        price = (EditText) findViewById(R.id.uprice);
        discount = (EditText) findViewById(R.id.udiscount);
        quantity = (EditText) findViewById(R.id.pquentity);
        update = (Button) findViewById(R.id.update);



        db = new DBrod(this);



        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String pod = product.getText().toString();
                String pz = price.getText().toString();
                String dis = discount.getText().toString();
                String qt = quantity.getText().toString();


                if (pod.equals("") || pz.equals("") || dis.equals("") || qt.equals("")) {
                    Toast.makeText(updatable.this, "Fill all the fields. ", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean regResult = db.updateData(pod, pz, dis, qt);
                    if (regResult == true) {
                        Toast.makeText(updatable.this, " Update success.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProductMenu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(updatable.this, "product Update Failed.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
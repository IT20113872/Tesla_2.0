package com.avtest.food_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ProductMenu extends AppCompatActivity {

    Button insert, update, delete,view;
    ImageView back;
    DBrod db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_product_menu);

        insert = (Button)findViewById(R.id.insertproduct);
        update = (Button)findViewById(R.id.updateproduct);
        delete = (Button)findViewById(R.id.deleteproduct);
        view = (Button)findViewById(R.id.view);
        back = findViewById(R.id.back);

        db = new DBrod(this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Add.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), updatable.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), delete.class);
                startActivity(intent);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t = db.getinfo();
                if(t.getCount()==0)
                {
                    Toast.makeText(ProductMenu.this, "No data found. ", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                buffer.append("Number of Item :- "  +t.getCount()+"\n\n");

                while (t.moveToNext())
                {
                    buffer.append("Name        - " +t.getString(0)+"\n");
                    buffer.append("Price       - Rs." +t.getString(1)+"\n");
                    buffer.append("Discount    - " +t.getString(2)+"\n");
                    buffer.append("Quantity    - " +t.getString(3)+"\n");
                    buffer.append("Total Price - Rs." +(t.getInt(3)*t.getInt(1)-t.getInt(2))+"\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(ProductMenu.this);
                builder.setCancelable(true);
                builder.setTitle("Product Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
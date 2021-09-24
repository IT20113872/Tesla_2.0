package com.avtest.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button go,mycart;
    private TextView count;
    private Context context;
    private Dbhandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        go = findViewById(R.id.gotocart);
        mycart = findViewById(R.id.mycart);
        count = findViewById(R.id.count);

        dbhandler = new Dbhandler(this);

        // count.setText("aaaaa"); meken ona ekak display karanwa

        //dbhandler eke countodo cal kara
        int countToDo = dbhandler.countToDo();
        count.setText( "Size :- " + countToDo);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,AddToCart.class));
            }
        });


        mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,cart.class));
            }
        });

    }
}
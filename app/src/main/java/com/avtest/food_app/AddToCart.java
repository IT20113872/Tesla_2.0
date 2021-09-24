package com.avtest.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddToCart extends AppCompatActivity {

    private EditText title, desc;
    private Button add;
    private Dbhandler dbhandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        title = findViewById(R.id.editTextTitle);
        desc = findViewById(R.id.editTextDescription);
        add = findViewById(R.id.buttonAdd);
        context = this;
        //Dbhandler = new Dbhandler(context);
      dbhandler = new Dbhandler(context);

   add.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String userTitle = title.getText().toString();
           String userDesc = desc.getText().toString();
           long started = System.currentTimeMillis();

           model mod = new model(userTitle,userDesc,started,0);
           dbhandler.addToCart(mod);

           startActivity(new Intent(context,MainActivity.class));
       }
   });

    }
}
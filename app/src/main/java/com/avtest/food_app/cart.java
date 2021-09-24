package com.avtest.food_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {

    private  Dbhandler dbhandler;
    private List<model> models;
    private Context context;
    private ListView listView;
    private Button home;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = this;
        dbhandler = new Dbhandler(context);
        listView = findViewById(R.id.listcart);

        home = findViewById(R.id.gohome);


        models = new ArrayList<>();

        models=dbhandler.getAllToDos();


        CartAdapter adapter = new CartAdapter(context,R.layout.singleitem,models);

        listView.setAdapter(adapter);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MainActivity.class));
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                final model mod = models.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(mod.getTitle());
                builder.setMessage(mod.getDescription());
                builder.show();




                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,EditCart.class);
                        intent.putExtra( "id", String.valueOf(mod.getId()));
                        startActivity(intent);
                    }
                });






                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbhandler.deletecart(mod.getId());
                        startActivity(new Intent(context,cart.class));
                    }
                });

                builder.show();

            }
        });

    }
}
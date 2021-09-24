package com.avtest.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditCart extends AppCompatActivity {

    private EditText title,des;
    private Button edit;
    private Dbhandler dbhandler;
    private Context context;
    private Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart);

        context = this;
        dbhandler = new Dbhandler(context);
        title = findViewById(R.id.editTitle);
        des = findViewById(R.id.editDescription);
        edit = findViewById(R.id.updatecart);

        final String id = getIntent().getStringExtra("id");
        model mod = dbhandler.getSingleitem(Integer.parseInt(id));

        title.setText(mod.getTitle());
        des.setText(mod.getDescription());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString();
                String desText = des.getText().toString();
                updateDate = System.currentTimeMillis();

                model mod = new model(Integer.parseInt(id),titleText,desText,updateDate,0);
                int state = dbhandler.updateSingleitem(mod);

                startActivity(new Intent(context,cart.class));
            }
        });

    }
}
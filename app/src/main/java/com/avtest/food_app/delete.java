package com.avtest.food_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete extends AppCompatActivity {

    EditText name;
    Button delete;
    AlertDialog.Builder builder;
    DBrod db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delete);

        name = (EditText) findViewById(R.id.dname);
        delete = (Button) findViewById(R.id.delete);
        builder = new AlertDialog.Builder(this);

        db = new DBrod(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Alert!!")
                        .setMessage("Do you want to Delete this product..")
                        .setCancelable(true)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String pod = name.getText().toString();

                                if (pod.equals("") ) {
                                    Toast.makeText(delete.this, "Fill all the field. ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Boolean regResult = db.deleteData(pod);
                                    if (regResult == true) {
                                        Toast.makeText(delete.this, " Delete success.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), ProductMenu.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(delete.this, "product Delete Failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });

      /*  delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String pod = name.getText().toString();

                if (pod.equals("") ) {
                    Toast.makeText(delete.this, "Fill all the field. ", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean regResult = db.deleteData(pod);
                    if (regResult == true) {
                        Toast.makeText(delete.this, " Delete success.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProductMenu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(delete.this, "product Delete Failed.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        */

    }
       }
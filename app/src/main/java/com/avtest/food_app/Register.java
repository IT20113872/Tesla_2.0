package com.avtest.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText name, email,phone, password, repass;
    Button register;

    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.pname);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        password = (EditText)findViewById(R.id.password);
        repass = (EditText)findViewById(R.id.repass);
        register = (Button) findViewById(R.id.register);



        db = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
              // ValidateEmailAddress(email);

                String user = name.getText().toString();
                String em = email.getText().toString();
                String ph = phone.getText().toString();
                String pass = password.getText().toString();
                String reps = repass.getText().toString();


                if(user.equals("") || em.equals("")|| ph.equals("") || pass.equals("") || reps.equals("") || ValidateEmailAddress(email)== false || ValidatePhoneAddress(phone)== false)

                {
                    Toast.makeText(Register.this, "Fill all the fields. ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(reps))
                    {
                        Boolean Result = db.checks(user);

                        if(Result == false)
                        {
                            Boolean regResult = db.insertData(user,em,ph,pass,reps);
                            if(regResult == true){
                                Toast.makeText(Register.this, "Registration success.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Register.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Register.this, "User already Exists.\n Please sing in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Register.this, "password not match.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private boolean ValidateEmailAddress(EditText email) {
        String emailInput = email.getText().toString();

        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {

            return true;
        } else {
            Toast.makeText(this, "Invalid Email Address!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
        private boolean ValidatePhoneAddress(EditText phone){
            String phoneInput = phone.getText().toString();

            if (!phoneInput.isEmpty() && Patterns.PHONE.matcher(phoneInput).matches()) {
                return true;
            } else {
                Toast.makeText(this, "Invalid Phone Number!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

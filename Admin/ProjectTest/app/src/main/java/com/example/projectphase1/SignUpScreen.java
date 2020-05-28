package com.example.projectphase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpScreen extends AppCompatActivity {

    private EditText fullName;
    private EditText email;
    private EditText number;
    private EditText userName;
    private EditText password;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        //firebase database
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin");

        //credential variables
        fullName =findViewById(R.id.et_fullName);
        email=findViewById(R.id.et_email);
        number =findViewById(R.id.et_number);
        userName =findViewById(R.id.et_userName);
        password=findViewById(R.id.et_password);

    }


    public void call_login1(View view)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Admin");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(userName.getText().toString())) {
                    show_toast("Admin exists. Login Now!");
                }
                else {
                    ClassAdmin classAdmin = new ClassAdmin();
                    classAdmin.setAdmin_userName(userName.getText().toString());
                    classAdmin.setAdmin_Pass(password.getText().toString());
                    classAdmin.setAdmin_Name(fullName.getText().toString());
                    classAdmin.setAdmin_email(email.getText().toString());
                    classAdmin.setAdmin_mobileNum(number.getText().toString());

                    databaseReference.child(userName.getText().toString()).setValue(classAdmin);

                    Toast.makeText(SignUpScreen.this,"ACCOUNT CREATED SUCCESSFULLY!!LOGIN NOW",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

    }

    public void call_signin(View view)
    {
        Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
        startActivity(intent);
    }

    public void show_toast(String string)
    {
        Toast toast = Toast.makeText(SignUpScreen.this, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 20);
        toast.show();
    }
}
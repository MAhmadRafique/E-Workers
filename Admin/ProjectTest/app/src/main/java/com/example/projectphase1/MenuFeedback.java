package com.example.projectphase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuFeedback extends AppCompatActivity {


    DatabaseReference databaseReference;
    EditText text;
    TextView name,address,phone,email,speciality;
    Button search,back;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_feedback);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("WorkerProfile");
        text = findViewById(R.id.id_searchWorker);
        name = findViewById(R.id.id_name);
        address = findViewById(R.id.id_address);
        phone = findViewById(R.id.id_phoneNum);
        email = findViewById(R.id.id_email);
        speciality = findViewById(R.id.id_speciality);
        search = findViewById(R.id.id_btnSearch);
        back = findViewById(R.id.id_btnGoBack);
        linearLayout = findViewById(R.id.linearLayout_search);


        linearLayout.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
//        name.setVisibility(View.GONE);
//        address.setVisibility(View.GONE);
//        email.setVisibility(View.GONE);
//        phone.setVisibility(View.GONE);
//        speciality.setVisibility(View.GONE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getdata();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
                back.setVisibility(View.GONE);

                email.setText("");
                name.setText("");
                phone.setText("");
                address.setText("");
                speciality.setText("");

                text.setVisibility(View.VISIBLE);
                search.setVisibility(View.VISIBLE);
            }
        });


    }

    public void call_submit(View view)
    {
        if(TextUtils.isEmpty(text.getText()))
        {
            show_toast("Please enter your feedback.");
        }
        else
        {
           ClassFeedbackFB classFeedbackFB=new ClassFeedbackFB(getDefaults("username",MenuFeedback.this),text.getText().toString());
           String id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(classFeedbackFB);

            show_toast("Feedback successfully submitted!");
            startActivity(new Intent(MenuFeedback.this,HomePageTabScreen.class));
        }

    }



    public void getdata()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try{
                    email.setText(dataSnapshot.child(text.getText().toString()).child("profile_email").getValue()+"");
                    address.setText(dataSnapshot.child(text.getText().toString()).child("profile_adress").getValue()+"");
                    name.setText(dataSnapshot.child(text.getText().toString()).child("profile_name").getValue()+"");
                    int integer = Integer.parseInt(dataSnapshot.child(text.getText().toString()).child("speciality").getValue()+"");
                    phone.setText(dataSnapshot.child(text.getText().toString()).child("profile_phone").getValue()+"");

                    if (integer == 0) {
                        speciality.setText("Electricain");
                    } else if (integer == 2) {
                        speciality.setText("Plumber");
                    } else if (integer == 3) {
                        speciality.setText("Cleaner");
                    } else if (integer == 4) {
                        speciality.setText("Painter");
                    } else if (integer == 5) {
                        speciality.setText("Technician");
                    } else {

                    }

                    linearLayout.setVisibility(View.VISIBLE);
                    back.setVisibility(View.VISIBLE);


                    text.setVisibility(View.GONE);
                    search.setVisibility(View.GONE);


                }

                catch(Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void show_toast(String string)
    {
        Toast toast = Toast.makeText(MenuFeedback.this, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 20);
        toast.show();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
package com.example.projectphase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.projectphase1.AdapterTasks.getDefaults;

public class ClassMembership extends AppCompatActivity {
    Button btn_BuyMembership;
    TextView tv_Status;
    String user_name;

    DatabaseReference dbReference;
    ClassProfile classProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        btn_BuyMembership = findViewById(R.id.membership_btn_BuyMembership);
        tv_Status = findViewById(R.id.membership_tv_status);

        user_name = getDefaults("username", getApplicationContext());
        dbReference = FirebaseDatabase.getInstance().getReference().child("Profile");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classProfile = new ClassProfile(dataSnapshot.child(user_name).child("profile_username").getValue() + "",
                        dataSnapshot.child(user_name).child("profile_name").getValue() + "",
                        dataSnapshot.child(user_name).child("profile_email").getValue() + "",
                        dataSnapshot.child(user_name).child("profile_phone").getValue() + "",
                        dataSnapshot.child(user_name).child("profile_adress").getValue() + "",
                        Integer.parseInt(dataSnapshot.child(user_name).child("wallet_value").getValue() + ""),
                        Integer.parseInt(dataSnapshot.child(user_name).child("membership_status").getValue() + ""));
                if (classProfile.getMembership_status() == 0) {
                    tv_Status.setText("Be a part of our membership program in Rs. 1000/- and enjoy 10 percent discount on each job.");
                    btn_BuyMembership.setVisibility(View.VISIBLE);
                } else
                    tv_Status.setText("Congratulations..!!\nYou are already part of our membership program.\nYou will get 10 percent discount on each job.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn_BuyMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (classProfile.getWallet_value() < 1000)
                    Toast.makeText(getApplicationContext(), "You have Insufficient Balance..!!", Toast.LENGTH_SHORT).show();
                else {
                    classProfile.setWallet_value(classProfile.getWallet_value() - 1000);
                    classProfile.setMembership_status(1);
                    dbReference.child(user_name).setValue(classProfile);
                    btn_BuyMembership.setVisibility(View.GONE);
                    tv_Status.setText("Congratulations..!!\nYou are already part of our membership program.\nYou will get 10 percent discount on each job.");
                }
            }
        });
    }
}

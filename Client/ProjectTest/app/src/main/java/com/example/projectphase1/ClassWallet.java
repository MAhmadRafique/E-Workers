package com.example.projectphase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.content.Intent;
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

public class ClassWallet extends AppCompatActivity {
    Button btn_Deposit;
    Button btn_Confirm;
    EditText et_BalanceInput;
    TextView tv_Balance;
    String user_name;

    DatabaseReference dbReference;
    ClassProfile classProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_wallet);

        btn_Deposit = findViewById(R.id.wallet_btn_deposit);
        btn_Confirm = findViewById(R.id.wallet_btn_confirm);
        et_BalanceInput = findViewById(R.id.wallet_balance_input);
        tv_Balance = findViewById(R.id.tv_currentBalance_Value);

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
                tv_Balance.setText(dataSnapshot.child(user_name).child("wallet_value").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_Deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_BalanceInput.setVisibility(View.VISIBLE);
                btn_Deposit.setVisibility(View.GONE);
                btn_Confirm.setVisibility(View.VISIBLE);
            }
        });

        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int addBalance = Integer.parseInt(et_BalanceInput.getText().toString());
                    addBalance += Integer.parseInt(tv_Balance.getText().toString());
                    tv_Balance.setText(addBalance + "");
                    classProfile.setWallet_value(addBalance);
                    dbReference.child(user_name).setValue(classProfile);
                    et_BalanceInput.setText("");
                    et_BalanceInput.setVisibility(View.GONE);
                    btn_Deposit.setVisibility(View.VISIBLE);
                    btn_Confirm.setVisibility(View.GONE);
                } catch (Exception e) {
                    et_BalanceInput.setText("");
                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

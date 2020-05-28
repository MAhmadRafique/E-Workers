package com.example.projectphase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminAdd extends AppCompatActivity {

    private EditText name;
    private EditText usernName;
    private EditText email;
    private EditText address;
    private EditText phoneNum;
    private Spinner speciality;
    DatabaseReference databaseReference_workerProfile;
    DatabaseReference databaseReference_worker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);

        //firebase database
        databaseReference_worker = FirebaseDatabase.getInstance().getReference().child("Workers");
        databaseReference_workerProfile = FirebaseDatabase.getInstance().getReference().child("WorkerProfile");

        name = findViewById(R.id.add_fullName);
        usernName = findViewById(R.id.add_userName);
        email = findViewById(R.id.add_email);
        address = findViewById(R.id.add_address);
        phoneNum = findViewById(R.id.add_pNum);
        speciality = findViewById(R.id.add_speciality);

    }

    public void cancelClicked(View view){
        this.finish();
    }

    public void addClicked(View view) {
        databaseReference_workerProfile = FirebaseDatabase.getInstance().getReference().child("WorkerProfile");
        databaseReference_workerProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(usernName.getText().toString())) {
                    //show_toast("Admin exists. Login Now!");
                }
                else {
                    ClassWorkerProfile workerProfile = new ClassWorkerProfile();
                    workerProfile.setProfile_username(usernName.getText().toString());
                    workerProfile.setProfile_name(name.getText().toString());
                    workerProfile.setProfile_adress(address.getText().toString());
                    workerProfile.setProfile_phone(phoneNum.getText().toString());
                    workerProfile.setProfile_email(email.getText().toString());

                    if (speciality.getSelectedItem().toString().equalsIgnoreCase("Electrician")) {
                        workerProfile.setSpeciality(0+"");
                    } else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Plumber")) {
                        workerProfile.setSpeciality(2+"");
                    } else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Cleaner")) {
                        workerProfile.setSpeciality(3+"");
                    } else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Painter")) {
                        workerProfile.setSpeciality(4+"");
                    } else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Technician")) {
                        workerProfile.setSpeciality(5+"");
                    }  else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Car Washer")) {
                        workerProfile.setSpeciality(6+"");
                    }
                    else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Gardener")) {
                        workerProfile.setSpeciality(7+"");
                    }
                    else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Welder")) {
                        workerProfile.setSpeciality(9+"");
                    }
                    else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Architect")) {
                        workerProfile.setSpeciality(12+"");
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Speciality Not selected", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    workerProfile.setConfirmedStatus("0");
                    databaseReference_workerProfile.child(usernName.getText().toString()).setValue(workerProfile);

                    ClassWorker worker = new ClassWorker();
                    worker.setUser_UserName(usernName.getText().toString());
                    worker.setUser_Name(name.getText().toString());
                    worker.setUser_Password("1");
                    worker.setUser_Email(email.getText().toString());
                    if (speciality.getSelectedItem().toString().equalsIgnoreCase("Electrician")) {
                        worker.setSpeciality(0+"");
                    } else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Plumber")) {
                        worker.setSpeciality(2+"");
                    } else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Cleaner")) {
                        worker.setSpeciality(3+"");
                    } else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Painter")) {
                        worker.setSpeciality(4+"");
                    } else if (speciality.getSelectedItem().toString().equalsIgnoreCase("Technician")) {
                        worker.setSpeciality(5+"");
                    } else {
                        Toast.makeText(getApplicationContext(), "Speciality Not selected", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    databaseReference_worker.child(usernName.getText().toString()).setValue(worker);

                    Toast.makeText(AdminAdd.this,"Worker "+name.getText().toString()+" added successfully!",Toast.LENGTH_LONG).show();
                    callFinish();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });


    }

    public void callFinish(){
        this.finish();
    }

    public void show_toast(String string) {
        Toast toast = Toast.makeText(AdminAdd.this, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 20);
        toast.show();
    }

}

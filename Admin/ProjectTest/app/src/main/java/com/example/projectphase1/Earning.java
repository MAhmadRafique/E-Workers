package com.example.projectphase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Earning extends AppCompatActivity {


    private List<AcceptedTasks> myList;
    private DatabaseReference databaseReference;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning);
        textView = findViewById(R.id.textView7);

        myList = new ArrayList<AcceptedTasks>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("acceptedTasks");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {

                        //  Toast.makeText(getContext(),""+data.child("myTask_date").getValue(),Toast.LENGTH_LONG).show();
                        myList.add(new AcceptedTasks(data.child("classAcceptedTask_TaskId").getValue()+""
                                ,data.child("classAcceptedTask_username").getValue()+""
                                ,data.child("classAcceptedTask_Job_Name").getValue()+""
                                ,Integer.parseInt(data.child("classAcceptedTask_task_job_id").getValue()+"")
                                ,Double.parseDouble(data.child("classAcceptedTask_TaskBill").getValue()+"")
                                ,data.child("classAcceptedTask_TaskDate").getValue()+""
                                ,data.child("classAcceptedTask_TaskLocation").getValue()+""
                                ,data.child("classAcceptedTask_TaakDescription").getValue()+""
                                ,data.child("classAcceptedTask_TaskImage").getValue()+""
                                , data.child("classAcceptedTask_workerId").getValue() + ""));


                }
                Toast.makeText(getApplicationContext(),myList.size()+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void monthEar_Clicked(View view){
        double earning=0;

        for(int i=0;i<myList.size();i++){
            earning+=myList.get(i).getClassAcceptedTask_TaskBill();
        }

        textView.setText(earning+"");
    }

    public void todaysEar_Clicked(View view){
        double earning=0;

        for(int i=0;i<myList.size();i++){
            earning+=myList.get(i).getClassAcceptedTask_TaskBill();
        }

        textView.setText(earning+"");
    }
}

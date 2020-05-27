package com.example.projectphase1.ui.main;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projectphase1.AcceptedTasks;
import com.example.projectphase1.AdapterMyTasks;
import com.example.projectphase1.ClassMyTask;
import com.example.projectphase1.ClassMyTasksFB;
import com.example.projectphase1.HomePageTabScreen;
import com.example.projectphase1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kotlin.jvm.internal.LocalVariableReferencesKt;

public class FragmentMyTask extends Fragment {
    View view;
    String u="usama";
    private AdapterMyTasks recyclerViewAdopter;
    private RecyclerView myRecyclerView;
    private List<AcceptedTasks> myList;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=  inflater.inflate(R.layout.fragment_fragment_my_task,container,false);
        myRecyclerView = view.findViewById(R.id.recycleView_myTask);
        recyclerViewAdopter = new AdapterMyTasks(getContext(),myList);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdopter);
        return  view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myList = new ArrayList<AcceptedTasks>();

        u=getDefaults("username",this.getContext());

        databaseReference= FirebaseDatabase.getInstance().getReference().child("acceptedTasks");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    if(data.child("classAcceptedTask_username").getValue().equals(u))
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
                }

                recyclerViewAdopter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}

package com.example.projectphase1.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.projectphase1.AdapterWorker;
import com.example.projectphase1.ClassWorkerProfile;
import com.example.projectphase1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentWorker extends Fragment {

    private  View view;
    private AdapterWorker adapterWorker;
    private RecyclerView myRecyclerView;
    private List<ClassWorkerProfile> myList;
    private DatabaseReference databaseReference;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=  inflater.inflate(R.layout.fragment_fragment_my_task,container,false);
        myRecyclerView = view.findViewById(R.id.recycleView_myTask);
        progressBar=view.findViewById(R.id.progressBar2);
        adapterWorker = new AdapterWorker(getContext(),myList,progressBar);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapterWorker);
        return  view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myList = new ArrayList<ClassWorkerProfile>();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("WorkerProfile");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    //  Toast.makeText(getContext(),""+data.child("myTask_date").getValue(),Toast.LENGTH_LONG).show();
                    myList.add(new ClassWorkerProfile(data.child("speciality").getValue()+""
                            ,data.child("profile_name").getValue()+""
                            ,data.child("profile_email").getValue()+""
                            ,data.child("confirmedStatus").getValue()+""
                            , data.child("profile_username").getValue()+""
                            , data.child("profile_adress").getValue()+""
                            , data.child("profile_phone").getValue()+""));
                }

                adapterWorker.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

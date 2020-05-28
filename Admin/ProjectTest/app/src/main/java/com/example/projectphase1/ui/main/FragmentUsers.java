package com.example.projectphase1.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectphase1.AdapterUsers;
import com.example.projectphase1.ClassUsers;
import com.example.projectphase1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentUsers extends Fragment {

    private  View view;
    private AdapterUsers adapterUsers;
    private RecyclerView myRecyclerView;
    private List<ClassUsers> myList;
    private DatabaseReference databaseReference;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=  inflater.inflate(R.layout.fragment_fragment_task,container,false);
        myRecyclerView = view.findViewById(R.id.recycleView_task);
        progressBar=view.findViewById(R.id.progressBar);
        adapterUsers = new AdapterUsers(getContext(),myList,progressBar);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapterUsers);
        return  view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myList = new ArrayList<ClassUsers>();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {

                        //  Toast.makeText(getContext(),""+data.child("myTask_date").getValue(),Toast.LENGTH_LONG).show();
                        myList.add(new ClassUsers(data.child("membership_status").getValue()+""
                                ,data.child("user_Name").getValue()+""
                                ,data.child("user_Email").getValue()+""
                                ,data.child("user_Password").getValue()+""
                                , data.child("user_UserName").getValue()+""
                                , data.child("wallet_value").getValue()+""));
                }

                adapterUsers.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}

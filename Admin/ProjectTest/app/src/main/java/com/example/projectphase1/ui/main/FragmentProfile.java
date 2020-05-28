package com.example.projectphase1.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectphase1.ClassAdmin;
import com.example.projectphase1.ClassProfile;
import com.example.projectphase1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FragmentProfile extends Fragment {

    TextView profile__name;
    TextView profile__number;
    TextView username_heading;
    TextView email_heading;
    ImageView imageView;
    String username;
    TextView textViewn;
    TextView em;
    TextView tv;
    ClassAdmin admin;
    View view;
    DatabaseReference databaseReference;
    //https://www.wallpaperup.com/uploads/wallpapers/2014/09/24/455833/4816bfd02322b6bfa75cf5a86655a68e-700.jpg
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_fragment_profile, container, false);

        imageView=view.findViewById(R.id.profile_back);
        em=view.findViewById(R.id.profile_tv_email);
        email_heading=view.findViewById(R.id.profile_tv_useremail_heading);
        username_heading=view.findViewById(R.id.profile_tv_username_heading);
        tv=view.findViewById(R.id.profile_tv_username);


        profile__name=view.findViewById(R.id.profile_tv_name);
        profile__number=view.findViewById(R.id.profile_tv_phone);
        textViewn=view.findViewById(R.id.jn);

        getdata();
     //   Toast.makeText(getContext(),"view",Toast.LENGTH_SHORT).show();
        return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getdata();

    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
    public void getdata()
    {
        username=getDefaults("username",this.getContext());
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                admin =new ClassAdmin(dataSnapshot.child(username).child("admin_Name").getValue()+"",
                        dataSnapshot.child(username).child("admin_email").getValue()+"",
                        dataSnapshot.child(username).child("admin_mobileNum").getValue()+"",
                        dataSnapshot.child(username).child("admin_userName").getValue()+"",
                        dataSnapshot.child(username).child("admin_Pass").getValue()+"");

                em.setText(admin.getAdmin_email());
                tv.setText(admin.getAdmin_userName());
                profile__number.setText(admin.getAdmin_mobileNum());
                profile__name.setText(admin.getAdmin_Name());
                textViewn.setText(profile__name.getText().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

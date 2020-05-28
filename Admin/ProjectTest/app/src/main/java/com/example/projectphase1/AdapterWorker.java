package com.example.projectphase1;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdapterWorker extends RecyclerView.Adapter<AdapterWorker.MyViewHolder> {


    Context mcontext;
    List<ClassWorkerProfile> workerList;
    EditText text;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("WorkerProfile");
    DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference().child("Workers");
    EditText editText;
    Dialog dialog;
    ProgressBar progressBar;

    public AdapterWorker(Context mcontext, List<ClassWorkerProfile> classWorkerProfileList, ProgressBar progressBar) {
        this.mcontext = mcontext;
        this.workerList = classWorkerProfileList;
        this.progressBar=progressBar;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_worker,parent,false);
        final MyViewHolder myViewHolder=new MyViewHolder(view);

        dialog=new Dialog(mcontext);
        dialog.setContentView(R.layout.dialog_worker);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView fullName=(TextView) dialog.findViewById(R.id.wd_name);
                final TextView userName=(TextView) dialog.findViewById(R.id.wd_uName);
                TextView email=(TextView) dialog.findViewById(R.id.wd_email);
                TextView pNumber=(TextView) dialog.findViewById(R.id.wd_pNum);
                TextView address=(TextView) dialog.findViewById(R.id.wd_address);
                TextView speciality=(TextView) dialog.findViewById(R.id.wd_speciality);

                fullName.setText(""+ workerList.get(myViewHolder.getAdapterPosition()).getProfile_name());
                userName.setText(""+ workerList.get(myViewHolder.getAdapterPosition()).getProfile_username());
                email.setText(""+ workerList.get(myViewHolder.getAdapterPosition()).getProfile_email());
                pNumber.setText(""+ workerList.get(myViewHolder.getAdapterPosition()).getProfile_phone());
                address.setText(""+ workerList.get(myViewHolder.getAdapterPosition()).getProfile_adress());

                int special = Integer.parseInt(workerList.get(myViewHolder.getAdapterPosition()).getSpeciality());

                if (special == 0) {
                    speciality.setText("Electricain");
                } else if (special == 2) {
                    speciality.setText("Plumber");
                } else if (special == 3) {
                    speciality.setText("Cleaner");
                } else if (special == 4) {
                    speciality.setText("Painter");
                } else if (special == 5) {
                    speciality.setText("Technician");
                } else {

                }


                //Picasso.with(mcontext).load(usersList.get(myViewHolder.getAdapterPosition()).getJobPhoto()).into( rImage);
                dialog.show();

                final Button dialogueButton=(Button) dialog.findViewById(R.id.dialog_task_btn);
                final Button removeBtn=(Button) dialog.findViewById(R.id.dw_removeBtn);

                dialogueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dialog.hide();
                    }
                });

                removeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseReference.child(workerList.get(myViewHolder.getAdapterPosition()).getProfile_username()).removeValue();
                        databaseReference2.child(workerList.get(myViewHolder.getAdapterPosition()).getProfile_username()).removeValue();
                        Toast.makeText(mcontext,"Worker "+workerList.get(myViewHolder.getAdapterPosition()).getProfile_username()+" has been removed.",Toast.LENGTH_LONG).show();
                        dialog.hide();
                    }
                });
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.userName.setText(workerList.get(position).getProfile_name());
        //Picasso.with(mcontext).load(usersList.get(position).getJobPhoto()).into(  holder.userPhoto);
        progressBar.setVisibility(View.GONE);
    }

    public String getDate()
    {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }
    @Override
    public int getItemCount() {
        return workerList.size();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout linearLayout;
        private TextView userName;
        private ImageView userPhoto;

        public MyViewHolder( View itemView) {
            super(itemView);
            linearLayout =(LinearLayout)itemView.findViewById(R.id.ll_itemWorker);
            userName = (TextView)itemView.findViewById(R.id.tv_workerName);
            userPhoto = (ImageView) itemView.findViewById(R.id.iv_workerPhoto);
        }
    }

}

package com.example.projectphase1;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
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
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class AdapterTasks extends RecyclerView.Adapter<AdapterTasks.MyViewHolder3> {
    Context mcontext;
    List<AcceptedTasks> myTasksClassList;
    Dialog dialog;

    public AdapterTasks(Context mcontext, List<AcceptedTasks> jobsClassList) {
        this.mcontext = mcontext;
        this.myTasksClassList = jobsClassList;

    }

    @NonNull
    @Override
    public AdapterTasks.MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_worker_task, parent, false);
        final AdapterTasks.MyViewHolder3 myViewHolder = new AdapterTasks.MyViewHolder3(view);

        dialog = new Dialog(mcontext);
        dialog.setContentView(R.layout.dialog_task);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        myViewHolder.linearLayout_Job_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView task_dialogue_ID = (TextView) dialog.findViewById(R.id.dialogue_myTask_taskid);
                TextView task_dialogue_Jobname = (TextView) dialog.findViewById(R.id.mytask_dialogue_name);
                TextView task_dialogue_job_id = (TextView) dialog.findViewById(R.id.dialogue_myTask_jobid);
                ImageView task_dialogue_image = (ImageView) dialog.findViewById(R.id.mytask_dialogue_image);
                TextView task_dialogue_location = (TextView) dialog.findViewById(R.id.dialogue_myTask_Location);
                TextView task_dialogue_bill = (TextView) dialog.findViewById(R.id.dialogue_myTask_bill);
                TextView task_dialogue_date = (TextView) dialog.findViewById(R.id.dialogue_myTask_date);
                TextView task_dialogue_UID = (TextView) dialog.findViewById(R.id.dialogue_myTask_description);
                TextView task_dialogue_WorkerId = dialog.findViewById(R.id.dialogue_myTask_WorkerId);
                TextView task_dialogue_username = dialog.findViewById(R.id.dialogue_myTask_username);

                task_dialogue_bill.setText("" + myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_TaskBill());
                task_dialogue_date.setText("" + myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_TaskDate());
                task_dialogue_ID.setText("" + myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_TaskId());
                task_dialogue_job_id.setText("" + myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_task_job_id());
                task_dialogue_Jobname.setText("" + myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_Job_Name());
                task_dialogue_location.setText("" + myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_TaskLocation());
                task_dialogue_UID.setText("" + myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_TaakDescription());
                task_dialogue_WorkerId.setText("" + myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_workerId());
                task_dialogue_username.setText("" + myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_username());

                Picasso.with(mcontext).load(myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassAcceptedTask_TaskImage()).into(task_dialogue_image);

                dialog.show();


                Button dialogueButton = (Button) dialog.findViewById(R.id.dialog_mytask_btn);
                dialogueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });

            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTasks.MyViewHolder3 holder, int position) {
        holder.task_name.setText(myTasksClassList.get(position).getClassAcceptedTask_Job_Name());
        Picasso.with(mcontext).load(myTasksClassList.get(position).getClassAcceptedTask_TaskImage()).into(holder.task_image);
    }

    @Override
    public int getItemCount() {
        return myTasksClassList.size();
    }

    public static class MyViewHolder3 extends RecyclerView.ViewHolder {
        LinearLayout linearLayout_Job_item;
        private TextView task_name;
        private ImageView task_image;

        public MyViewHolder3(View itemView) {
            super(itemView);


            linearLayout_Job_item = (LinearLayout) itemView.findViewById(R.id.task_item_id);
            task_name = (TextView) itemView.findViewById(R.id.task_item_name);
            task_image = (ImageView) itemView.findViewById(R.id.task_item_image);
        }
    }

}

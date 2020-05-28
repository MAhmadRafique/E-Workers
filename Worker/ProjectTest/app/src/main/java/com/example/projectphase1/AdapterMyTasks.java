package com.example.projectphase1;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.List;
import static com.example.projectphase1.ui.main.FragmentMyTask.getDefaults;

public class AdapterMyTasks extends RecyclerView.Adapter<AdapterMyTasks.MyViewHolder2> {


    Context mcontext;
    List<ClassMyTask> myTasksClassList;
    Dialog dialog;

    public AdapterMyTasks(Context mcontext, List<ClassMyTask> jobsClassList) {
        this.mcontext = mcontext;
        this.myTasksClassList = jobsClassList;

    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_my_task,parent,false);
        final MyViewHolder2 myViewHolder=new MyViewHolder2(view);

        dialog=new Dialog(mcontext);
        dialog.setContentView(R.layout.dialog_mytask);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        myViewHolder.linearLayout_Job_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView task_dialogue_ID=(TextView) dialog.findViewById(R.id.dialogue_myTask_taskid);
                TextView task_dialogue_Jobname=(TextView) dialog.findViewById(R.id.mytask_dialogue_name);
                TextView task_dialogue_job_id=(TextView) dialog.findViewById(R.id.dialogue_myTask_jobid);
                ImageView task_dialogue_image=(ImageView)dialog.findViewById(R.id.mytask_dialogue_image);
                TextView task_dialogue_location=(TextView) dialog.findViewById(R.id.dialogue_myTask_Location);
                TextView task_dialogue_bill=(TextView) dialog.findViewById(R.id.dialogue_myTask_bill);
                TextView task_dialogue_date=(TextView) dialog.findViewById(R.id.dialogue_myTask_date);
                TextView task_dialogue_UID=(TextView) dialog.findViewById(R.id.dialogue_myTask_description);

                task_dialogue_bill.setText(""+myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskBill());
                task_dialogue_date.setText(""+myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskDate());
                task_dialogue_ID.setText(""+myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskId());
                task_dialogue_job_id.setText(""+myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_task_job_id());
                task_dialogue_Jobname.setText(""+myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_Job_Name());
                task_dialogue_location.setText(""+myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskLocation());
                task_dialogue_UID.setText(""+myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaakDescription());

                Picasso.with(mcontext).load(myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskImage()).into( task_dialogue_image);


                dialog.show();


                Button dialogueButton = (Button) dialog.findViewById(R.id.dialog_mytask_btn);
                Button acceptButton = (Button) dialog.findViewById(R.id.dialog_mytask_accept);
                dialogueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });
                acceptButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("acceptedTasks");
                            String id = dbr.push().getKey();
                            AcceptedTasks acceptedTasks = new AcceptedTasks(myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskId()
                                    , myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_username()
                                    , myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_Job_Name()
                                    , myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_task_job_id()
                                    , myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskBill()
                                    , myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskDate()
                                    , myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskLocation()
                                    , myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaakDescription()
                                    , myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskImage()
                                    , getDefaults("username", mcontext));

                            dbr.child(myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskId()).setValue(acceptedTasks);

                            DatabaseReference dbr2 = FirebaseDatabase.getInstance().getReference().child("requestedTasks");
                            dbr2.child(myTasksClassList.get(myViewHolder.getAdapterPosition()).getClassMyTask_TaskId()).removeValue();

                            dialog.hide();
//                            Toast.makeText(mcontext, "the request for task " + rName.getText() + " has sent", Toast.LENGTH_SHORT).show();
//                            dialog.hide();
//                            text.getText().clear();
//                            editText.getText().clear();
//                            show_notifiication();
                            //   displayNotify("Your Task has been Register. Worker will be at your place with in 24 Hours");

                    }
                });
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        holder.task_name.setText(myTasksClassList.get(position).getClassMyTask_Job_Name());
        Picasso.with(mcontext).load(myTasksClassList.get(position).getClassMyTask_TaskImage()).into(holder.task_image);
    }

    @Override
    public int getItemCount() {
        return myTasksClassList.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder
    {
        private LinearLayout linearLayout_Job_item;
        private TextView task_name;
        private ImageView task_image;
        public MyViewHolder2( View itemView) {
            super(itemView);


            linearLayout_Job_item=(LinearLayout)itemView.findViewById(R.id.mytask_item_id);
            task_name= (TextView)itemView.findViewById(R.id.task_item_name);
            task_image= (ImageView) itemView.findViewById(R.id.task_item_image);
        }
    }
}

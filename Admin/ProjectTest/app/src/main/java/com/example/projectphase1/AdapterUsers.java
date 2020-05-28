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

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder> {


    Context mcontext;
    List<ClassUsers> usersList;
    EditText text;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
    EditText editText;
    Dialog dialog;
    ProgressBar progressBar;
    private final String CHANNEL_ID = "Personal Notification";
    private final int NOTIFICATION_ID = 1;

    public AdapterUsers(Context mcontext, List<ClassUsers> classUsersList, ProgressBar progressBar) {
        this.mcontext = mcontext;
        this.usersList = classUsersList;
        this.progressBar = progressBar;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_user,parent,false);
        final MyViewHolder myViewHolder=new MyViewHolder(view);

        dialog=new Dialog(mcontext);
        dialog.setContentView(R.layout.dialog_users);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView fullName=(TextView) dialog.findViewById(R.id.ud_name);
                TextView userName=(TextView) dialog.findViewById(R.id.ud_uName);
                TextView email=(TextView) dialog.findViewById(R.id.ud_email);
                TextView wallet=(TextView) dialog.findViewById(R.id.ud_wallet);

                fullName.setText(""+ usersList.get(myViewHolder.getAdapterPosition()).getName());
                userName.setText(""+ usersList.get(myViewHolder.getAdapterPosition()).getUserName());
                email.setText(""+ usersList.get(myViewHolder.getAdapterPosition()).getEmail());
                wallet.setText(""+ usersList.get(myViewHolder.getAdapterPosition()).getWalletValue());

                //Picasso.with(mcontext).load(usersList.get(myViewHolder.getAdapterPosition()).getJobPhoto()).into( rImage);
                dialog.show();

                final Button dialogueButton=(Button) dialog.findViewById(R.id.dialog_task_btn);
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.userName.setText(usersList.get(position).getName());
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
        return usersList.size();
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
            linearLayout =(LinearLayout)itemView.findViewById(R.id.ll_itemUser);
            userName = (TextView)itemView.findViewById(R.id.iv_userName);
            userPhoto = (ImageView) itemView.findViewById(R.id.iv_userPhoto);
        }
    }

    public boolean isConnectedToInternet()
    {
        boolean have_WIFI = false;
        boolean have_MData = false;

        ConnectivityManager connectivityManager =(ConnectivityManager) mcontext.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for(NetworkInfo info:networkInfos)
        {
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
                if(info.isConnected())
                    have_WIFI=true;
            if(info.getTypeName().equalsIgnoreCase("MOBILE"))
                if(info.isConnected())
                    have_MData=true;
        }
        return have_MData||have_WIFI;
    }

    public void displayNotify(String s)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Personal Notification";
            String description = "NOtification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) mcontext.getSystemService(mcontext.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder b = new NotificationCompat.Builder(mcontext, CHANNEL_ID);
        b.setContentTitle("E-Worker");
        b.setSmallIcon(R.drawable.app_icon);
        b.setContentText(s);
        b.setSound(alarmSound);
        b.setPriority(NotificationCompat.PRIORITY_MAX
        );

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(mcontext);
        notificationManagerCompat.notify(NOTIFICATION_ID, b.build());
    }

    public void show_notifiication()
    {
        AlarmManager alarmManager = (AlarmManager)mcontext.getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(mcontext, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(mcontext, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
    }

}

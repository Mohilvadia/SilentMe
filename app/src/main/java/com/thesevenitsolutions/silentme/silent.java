package com.thesevenitsolutions.silentme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class silent extends AppCompatActivity    {
    private static final int PICK_CONTACT =10 ;
    TextView settime,time,time1,settime2,addphone;
    Button btnsilence;
    private Context ctx=this;
    final Calendar c = Calendar.getInstance();
    private int mHour = c.get(Calendar.HOUR_OF_DAY);
    private int mMinute = c.get(Calendar.MINUTE);
    Date date = null;
    Date date1 = null;
    static    long timeInMs;
    static long timeInMs1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silent);
        setpermission();
        allocatememory();
        setevent();
    }

    private void setpermission() {
        NotificationManager notificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);
        }
    }

    public void setevent() {

        settime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ctx,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                time.setText(hourOfDay +":"+minute);
                                Toast.makeText(ctx,time.getText().toString(),Toast.LENGTH_SHORT).show();
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        settime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(ctx,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay1, int minute1) {

                                time1.setText(hourOfDay1 +":"+ minute1);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog1.show();
            }
        });
        btnsilence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    try {

                        date = sdf.parse(time.getText().toString());
                        date1 = sdf.parse(time1.getText().toString());

                    } catch (ParseException e) {

                        e.printStackTrace();
                    }
                    try {

                        date1 = sdf.parse(time1.getText().toString());

                    } catch (ParseException e) {

                        e.printStackTrace();
                    }
                    if(date!=null) {
                        timeInMs = date.getTime() ;
                    }
                    if(date!=null) {
                        timeInMs1 = date1.getTime();
                    }
                    if (timeInMs==0 || timeInMs1==0){
                        Toast.makeText(ctx,"please add Both time Then Proceed",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        startService(new Intent(getApplicationContext(), MyService.class));
                    }
                }



        });

        addphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);

            }
        });
    }


    public void allocatememory() {
        settime=findViewById(R.id.settime);
        settime2=findViewById(R.id.settime2);
        time=findViewById(R.id.time);
        time1=findViewById(R.id.time1);
        btnsilence=findViewById(R.id.silence);
        addphone=findViewById(R.id.addphone);
    }
    public static void getcurrenttime(){
        Calendar cal=Calendar.getInstance();
        int h=cal.get(Calendar.HOUR);
        int m=cal.get(Calendar.MINUTE);
        String time=(h+":"+m);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date= null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long val=date.getTime();

    }

}

package com.mobitechs.ashAppointment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobitechs.ashAppointment.appointment.R;

public class Appointment_Details extends BaseActivity implements View.OnClickListener {

    boolean doubleBackToExitPressedOnce = false;

    String  Appointmentid ;
    String  Full_Name ;
    String  Phone_no ;
    String  Appointment_dates;
    String  Appointment_time ;
    String  CountryName ;
    String  city ;
    String  Hotel_name;
    String  HotelMobileNo;
    String  Appointment_Type;
    String  Appointment_Status;
    String  BookedThrough;
    String  AppointmentDate ;

    TextView txtFull_Name;
    TextView txtPhone_no;
    TextView txtAppointment_dates;
    TextView txtAppointment_time;
    TextView txtCountryName;
    TextView txtcity;
    TextView txtHotel_name;
    TextView txtHotelMobileNo;
    TextView txtStatus;
    TextView txtBookedThrough;

    RelativeLayout part1RelativeLayout;
    RelativeLayout part2RelativeLayout;
    RelativeLayout part3RelativeLayout;

    int countForm1 = 0;
    int countForm2 = 0;
    int countForm3 = 0;

    CardView Details1CardView;
    CardView Details2CardView;
    CardView Details3CardView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_details);

        part1RelativeLayout = (RelativeLayout) findViewById(R.id.part3RelativeLayout);
        part2RelativeLayout = (RelativeLayout) findViewById(R.id.part1RelativeLayout);
        part3RelativeLayout = (RelativeLayout) findViewById(R.id.part2RelativeLayout);
        Details1CardView = (CardView) findViewById(R.id.otherDetailsCardView);
        Details2CardView = (CardView) findViewById(R.id.personalDetailsCardView);
        Details3CardView = (CardView) findViewById(R.id.schoolDetailsCardView);


        part1RelativeLayout.setOnClickListener(this);
        part2RelativeLayout.setOnClickListener(this);
        part3RelativeLayout.setOnClickListener(this);

//        Details1CardView.setVisibility(View.GONE);
//        Details2CardView.setVisibility(View.GONE);
//        Details3CardView.setVisibility(View.GONE);

        txtFull_Name = (TextView) findViewById(R.id.rollNo);
        txtPhone_no = (TextView) findViewById(R.id.GrNo);
        txtAppointment_dates = (TextView) findViewById(R.id.date);
        txtAppointment_time = (TextView) findViewById(R.id.time);
        txtCountryName = (TextView) findViewById(R.id.country);
        txtcity = (TextView) findViewById(R.id.city);
        txtHotel_name = (TextView) findViewById(R.id.schoolName);
        txtHotelMobileNo = (TextView) findViewById(R.id.schoolAddress);
        txtStatus = (TextView) findViewById(R.id.status);
        txtBookedThrough = (TextView) findViewById(R.id.bookThrough);

        txtPhone_no.setOnClickListener(this);
        txtHotelMobileNo.setOnClickListener(this);

        Intent intent = getIntent();
        if (null != intent) {
            Appointmentid = intent.getStringExtra("Appointmentid");
            Full_Name = intent.getStringExtra("Full_Name");
            Phone_no = intent.getStringExtra("Phone_no");
            Appointment_dates = intent.getStringExtra("Appointment_dates");
            Appointment_time = intent.getStringExtra("Appointment_time");
            CountryName = intent.getStringExtra("CountryName");
            city = intent.getStringExtra("city");
            Hotel_name = intent.getStringExtra("Hotel_name");
            HotelMobileNo = intent.getStringExtra("HotelMobileNo");
            Appointment_Type = intent.getStringExtra("Appointment_Type");
            Appointment_Status = intent.getStringExtra("Appointmentstatus");
            BookedThrough = intent.getStringExtra("AppointmentBookedThrough");
            AppointmentDate = intent.getStringExtra("AppointmentDate");
        }
        txtFull_Name.setText(Full_Name);
        txtPhone_no.setText(Phone_no);
        txtAppointment_dates.setText(Appointment_dates);
        txtAppointment_time.setText(Appointment_time);
        txtCountryName.setText(CountryName);
        txtcity.setText(city);
        txtHotelMobileNo.setText(HotelMobileNo);
        txtHotel_name.setText(Hotel_name);
        txtStatus.setText(Appointment_Status);
        txtBookedThrough.setText(BookedThrough);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            this.finish();
//            return;
//        }else {
//
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);

        Appointment_Details.this.finish();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.GrNo){
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + Phone_no));
            startActivity(callIntent);
        }
        else if(view.getId() == R.id.schoolAddress){
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + HotelMobileNo));
            startActivity(callIntent);
        }

        if (view.getId() == R.id.part3RelativeLayout) {

            if (countForm1 == 0) {
                Details1CardView.setVisibility(View.VISIBLE);
                countForm1 = 1;
            } else {
                Details1CardView.setVisibility(View.GONE);
                countForm1 = 0;
            }
        }
        else if (view.getId() == R.id.part1RelativeLayout) {

            if (countForm2 == 0) {
                Details2CardView.setVisibility(View.VISIBLE);
                countForm2 = 1;
            } else {
                Details2CardView.setVisibility(View.GONE);
                countForm2 = 0;
            }
        }
        else if (view.getId() == R.id.part2RelativeLayout) {

            if (countForm3 == 0) {
                Details3CardView.setVisibility(View.VISIBLE);
                countForm3 = 1;
            } else {
                Details3CardView.setVisibility(View.GONE);
                countForm3 = 0;
            }
        }
    }
}


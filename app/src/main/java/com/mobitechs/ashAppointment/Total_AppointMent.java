package com.mobitechs.ashAppointment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mobitechs.ashAppointment.adapter.Appointment_Adapter;
import com.mobitechs.ashAppointment.appointment.R;
import com.mobitechs.ashAppointment.connectivity.GetAllAppointment;
import com.mobitechs.ashAppointment.model.Appointment_items;
import com.mobitechs.ashAppointment.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Total_AppointMent extends BaseActivity implements View.OnClickListener {

    private ProgressDialog progressDialog = null;

    public List<Appointment_items> appointmentItems = new ArrayList<Appointment_items>();
    RecyclerView recyclerView;
    RecyclerView.Adapter reviewAdapter;
    LinearLayoutManager linearLayoutManager;

    String userId;
    String deptType;
    int month;
    int year;
    int day;

    private int current_page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_appointment_details);

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> typeOfUser = sessionManager.getUserDetails();
        userId = typeOfUser.get(SessionManager.KEY_USERID);
        deptType = typeOfUser.get(SessionManager.KEY_DEPTTYPE);

        Intent intent = getIntent();
        if (null != intent) {
            month = Integer.parseInt(intent.getStringExtra("month"));
            year = Integer.parseInt(intent.getStringExtra("year"));
            day = Integer.parseInt(intent.getStringExtra("day"));
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.smoothScrollToPosition(0);
        reviewAdapter = new Appointment_Adapter(appointmentItems);
        recyclerView.setAdapter(reviewAdapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait.");
        progressDialog.show();
        getList();

    }

    private void getList() {
        try {
            GetAllAppointment new_FetchList = new GetAllAppointment(this);
            new_FetchList.showAppointmentList(appointmentItems, recyclerView, reviewAdapter,  userId, deptType, day, month, year, progressDialog);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

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

        Total_AppointMent.this.finish();

    }

}

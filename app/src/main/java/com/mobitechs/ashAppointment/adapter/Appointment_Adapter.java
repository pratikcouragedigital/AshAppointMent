package com.mobitechs.ashAppointment.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobitechs.ashAppointment.Appointment_Details;
import com.mobitechs.ashAppointment.appointment.R;
import com.mobitechs.ashAppointment.model.Appointment_items;

import java.util.List;

public class Appointment_Adapter extends RecyclerView.Adapter<Appointment_Adapter.ViewHolder>{

    List<Appointment_items> listItems;
    View v;
    RecyclerView.ViewHolder viewHolder;


    public Appointment_Adapter(List<Appointment_items> items) {
        this.listItems = items;

    }

    @Override
    public Appointment_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.total_appointment_details_items, viewGroup, false);
        viewHolder = new Appointment_Adapter.ViewHolder(v);
        return (Appointment_Adapter.ViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(Appointment_Adapter.ViewHolder viewHolder, int position) {
        Appointment_items itemOflist = listItems.get(position);
        viewHolder.bindNotificationDetailsList(itemOflist );
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView txtaptPersonName;
        public TextView txtcountry;
        public TextView txtcity;
        public TextView txtcontactNo;

        public View cardView;

        Appointment_items listItems = new Appointment_items();

        public ViewHolder(View itemView) {
            super(itemView);

            txtaptPersonName = (TextView) itemView.findViewById(R.id.aptPersonName);
            txtcountry = (TextView) itemView.findViewById(R.id.country);
            txtcity = (TextView) itemView.findViewById(R.id.city);
            txtcontactNo = (TextView) itemView.findViewById(R.id.contactNo);
            cardView = itemView;
            cardView.setOnClickListener(this);
            txtcontactNo.setOnClickListener(this);
        }

        public void bindNotificationDetailsList(Appointment_items listItems) {
            this.listItems = listItems;

            txtaptPersonName.setText(listItems.getFull_Name());
            txtcountry.setText(listItems.getCountryName());
            txtcity.setText(listItems.getcity());
            txtcontactNo.setText(listItems.getPhone_no());
        }

        @Override
        public void onClick(View v) {
            if (this.listItems != null) {
                Intent petFullInformation = new Intent(v.getContext(), Appointment_Details.class);

                petFullInformation.putExtra("Appointmentid", listItems.getAppointmentid());
                petFullInformation.putExtra("Full_Name", listItems.getFull_Name());
                petFullInformation.putExtra("Phone_no", listItems.getPhone_no());
                petFullInformation.putExtra("Appointment_dates", listItems.getAppointment_dates());
                petFullInformation.putExtra("Appointment_time", listItems.getAppointment_time());
                petFullInformation.putExtra("CountryName", listItems.getCountryName());
                petFullInformation.putExtra("city", listItems.getcity());
                petFullInformation.putExtra("Hotel_name", listItems.getHotelName());
                petFullInformation.putExtra("HotelMobileNo", listItems.getHotelMobileNo());
                petFullInformation.putExtra("Appointment_Type", listItems.getAppointment_Type());
                petFullInformation.putExtra("Appointmentstatus", listItems.getAppointmentstatus());
                petFullInformation.putExtra("AppointmentBookedThrough", listItems.getBookedThrough());
                petFullInformation.putExtra("AppointmentDate", listItems.getPresentDate());
                petFullInformation.putExtra("AppointmentDate", listItems.getPresentDate());
                petFullInformation.putExtra("AppointmentDate", listItems.getPresentDate());
                petFullInformation.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                v.getContext().startActivity(petFullInformation);
            }
            else if(v.getId() == R.id.contactNo){
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + listItems.getPhone_no()));
                v.getContext().startActivity(callIntent);
            }
        }
    }
}

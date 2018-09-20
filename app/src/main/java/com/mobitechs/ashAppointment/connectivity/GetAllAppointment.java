package com.mobitechs.ashAppointment.connectivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.mobitechs.ashAppointment.Total_AppointMent;
import com.mobitechs.ashAppointment.model.Appointment_items;
import com.mobitechs.ashAppointment.webService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GetAllAppointment {

    private static Context context;
    private static String ResponseResult;
    private static String standard;
    private static String webMethName;
    private static String schoolId;
    private static int currentPage;
    private static ProgressDialog progressDialogBox;

    private static RecyclerView.Adapter adapterForAsyncTask;
    private static RecyclerView recyclerViewForAsyncTask;
    private static List<Appointment_items> ItemsArrayForAsyncTask;
    int selectedMonth;
    int selectedYear;
    int selectedDay;
    String userId;
    String deptType;

    public GetAllAppointment(Total_AppointMent total_AppointMent) {
        context = total_AppointMent;
    }


    public void showAppointmentList(List<Appointment_items> appointmentItems, RecyclerView recyclerView, RecyclerView.Adapter reviewAdapter, String idOfUser,String typeOfDept,int day, int month, int year, ProgressDialog progressDialog) {
        userId = idOfUser;
        deptType = typeOfDept;
        selectedMonth = month;
        selectedYear = year;
        selectedDay = day;
        progressDialogBox = progressDialog;
        adapterForAsyncTask = reviewAdapter;
        recyclerViewForAsyncTask = recyclerView;
        ItemsArrayForAsyncTask = appointmentItems;

        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }

    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            webMethName = "A_ViewAppointmentDateWise";
            ResponseResult = WebService.showDateWise_TotalAppoinment(userId, deptType,selectedDay, selectedMonth, selectedYear, webMethName);
            return null;
        }

        @Override
        protected void onPostExecute(Void res) {
            if (ResponseResult.equals("Details Not Found")) {
                progressDialogBox.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Result");
                builder.setMessage("Attendance Details Not Available For this Month.");
                AlertDialog alert1 = builder.create();
                alert1.show();
            } else if (ResponseResult.equals("No Network Found")) {
                progressDialogBox.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Result");
                builder.setMessage("There is Some Network Issues Please Try Again Later.");
                AlertDialog alert1 = builder.create();
                alert1.show();
            } else {
                progressDialogBox.dismiss();
                ItemsArrayForAsyncTask.clear();
                try {
                    JSONArray jsonArray = new JSONArray(ResponseResult);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            Appointment_items attendanceItems = new Appointment_items();
                            //attendanceItems.setAppointment_Type(obj.getString("Appointment_Type"));
                            //attendanceItems.setEmail(obj.getString("Email"));
                            //attendanceItems.setpurpose(obj.getString("purpose"));
                            //attendanceItems.setMessage(obj.getString("Message"));
                            //attendanceItems.setLocation(obj.getString("Location"));
                            //attendanceItems.setCampaignCode(obj.getString("CampaignCode"));

                            attendanceItems.setBookedThrough(obj.getString("AppointmentBookedThrough"));
                            attendanceItems.setAppointmentstatus(obj.getString("Appointmentstatus"));
                            attendanceItems.setAppointmentid(obj.getString("Appointmentid"));
                            attendanceItems.setFull_Name(obj.getString("Full_Name"));
                            attendanceItems.setPhone_no(obj.getString("Phone_no"));
                            attendanceItems.setAppointment_dates(obj.getString("Appointment_dates"));
                            attendanceItems.setAppointment_time(obj.getString("Appointment_time"));
                            attendanceItems.setCountryName(obj.getString("CountryName"));
                            attendanceItems.setcity(obj.getString("city"));
                            attendanceItems.setHotelName(obj.getString("Hotel_name"));
                            attendanceItems.setHotelMobileNo(obj.getString("HotelMobileNo"));
                            attendanceItems.setAppointment_Type(obj.getString("Appointment_Type"));
                            attendanceItems.setPresentDate(obj.getString("AppointmentDate"));

                            ItemsArrayForAsyncTask.add(attendanceItems);
                            adapterForAsyncTask.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
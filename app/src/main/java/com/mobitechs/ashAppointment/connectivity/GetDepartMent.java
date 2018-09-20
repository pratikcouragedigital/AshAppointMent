package com.mobitechs.ashAppointment.connectivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import com.mobitechs.ashAppointment.Login;
import com.mobitechs.ashAppointment.adapter.AdapterForSpinner;
import com.mobitechs.ashAppointment.webService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetDepartMent {

    private static Context context;
    private static AdapterForSpinner SpinnerAdapter;
    private static List<String> allNameList = new ArrayList<String>();
    private static List<String> allIdList = new ArrayList<String>();
    private static ProgressDialog spinnerDialogBox;

    String userSelectedStandard;
    String userSelectedDivision;
    String schoolId;
    private static String method = "A_DepartmentList";
    private static String loginResponseResult ;


    public GetDepartMent(Login login) {
        context =login;
    }

    public void FetchDept(List<String> spinnerItemNameList, List<String> spinnerItemIdList, AdapterForSpinner spinnerAdapter, ProgressDialog progressDialogBox) {
        allNameList = spinnerItemNameList;
        allIdList =spinnerItemIdList;
        SpinnerAdapter = spinnerAdapter;
        spinnerDialogBox = progressDialogBox;
        AsyncCallWS task = new AsyncCallWS();
        task.execute();

    }

    public class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //String method = "A_DepartmentList";
            loginResponseResult = WebService.GetDepartmentList(method);
            return null;
        }

        @Override
        protected void onPostExecute(Void res) {
            if (loginResponseResult.equals("Invalid Department")) {
                spinnerDialogBox.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Result");
                builder.setMessage(loginResponseResult);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface alert, int which) {
                        // TODO Auto-generated method stub
                        //Do something
                        alert.dismiss();
                    }
                });
                AlertDialog alert1 = builder.create();
                alert1.show();
            }
            else if (loginResponseResult.equals("No Network Found")) {
                spinnerDialogBox.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Result");
                builder.setMessage("Unable To Fetch Department. Please Try Again Later.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface alert, int which) {
                        // TODO Auto-generated method stub
                        //Do something
                        alert.dismiss();
                    }
                });
                AlertDialog alert1 = builder.create();
                alert1.show();
            }
            else {
                spinnerDialogBox.dismiss();
                allIdList.add("0");
                try {
                    JSONArray jArr = new JSONArray(loginResponseResult);
                    for (int count = 0; count < jArr.length(); count++) {
                        JSONObject obj = jArr.getJSONObject(count);
                        allNameList.add(obj.getString("DepartmentName"));
                        allIdList.add(obj.getString("DepartmentId"));
                        SpinnerAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

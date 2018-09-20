package com.mobitechs.ashAppointment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobitechs.ashAppointment.adapter.AdapterForSpinner;
import com.mobitechs.ashAppointment.appointment.R;
import com.mobitechs.ashAppointment.connectivity.GetDepartMent;
import com.mobitechs.ashAppointment.internetConnectivity.NetworkChangeReceiver;
import com.mobitechs.ashAppointment.sessionManager.SessionManager;
import com.mobitechs.ashAppointment.webService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login extends Activity implements View.OnClickListener {

    private long TIME = 5000;
    private EditText editTextUserId;
    private EditText editTextPassword;
    private Button btnSignIn;
    private ProgressDialog progressDialog;

    private String userId;
    private String userPassword;
    private String method;
    public String loginResponseResult;

    SessionManager sessionManager;
    public String saveUserId;
    public String saveName;
    public String saveEmail;
    public String saaveMobileNo;
    public String DeptType;

    Spinner department;
    String spinnerItemName;
    String spinnerItemId;
    private String[] spinnerArrayList;
    private AdapterForSpinner spinnerAdapter;
    private ProgressDialog spinnerDialogBox;
    private List<String> spinnerItemIdList = new ArrayList<String>();
    private List<String> spinnerItemNameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editTextUserId = (EditText) findViewById(R.id.txtLoginUserId);
        editTextPassword = (EditText) findViewById(R.id.txtLoginpassword);
        department = (Spinner) findViewById(R.id.department);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(this);

        getStandarddDetails();
    }

    private void getStandarddDetails() {
        spinnerArrayList = new String[]{
                "Department"
        };
        spinnerItemNameList = new ArrayList<>(Arrays.asList(spinnerArrayList));
        spinnerAdapter = new AdapterForSpinner(this, R.layout.spinneritem, spinnerItemNameList);
        getListOfStandard();
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(spinnerAdapter);
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    spinnerItemName = parent.getItemAtPosition(position).toString();
                    spinnerItemId = spinnerItemIdList.get(position);
                    String sbd= "ah";

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void getListOfStandard() {
        spinnerDialogBox = ProgressDialog.show(this, "", "Fetching Department Please Wait...", true);
        GetDepartMent getDepartMent = new GetDepartMent(this);
        getDepartMent.FetchDept(spinnerItemNameList, spinnerItemIdList, spinnerAdapter, spinnerDialogBox);

//        spinnerDialogBox = ProgressDialog.show(this, "", "Fetching Please Wait...", true);
//        AsyncCallWS task = new AsyncCallWS();
//        task.execute();
    }

    @Override
    public void onClick(final View v) {

        if (v.getId() == R.id.btnSignIn) {

            userId = editTextUserId.getText().toString();
            userPassword = editTextPassword.getText().toString();

            if (editTextUserId.getText().toString().isEmpty()) {
                Toast.makeText(Login.this, "Please Enter Your User Id.", Toast.LENGTH_LONG).show();
            }
            else if (editTextPassword.getText().toString().isEmpty()) {
                Toast.makeText(Login.this, "Please Enter Password.", Toast.LENGTH_LONG).show();
            }
            else if (spinnerItemName == null || spinnerItemName.isEmpty()) {
                Toast.makeText(this, "Please select Standard.", Toast.LENGTH_LONG).show();
            }
            else {
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Logging In.");
                progressDialog.show();
                LoginAsyncCallWS task = new LoginAsyncCallWS();
                task.execute();
            }
        }
    }

    public class LoginAsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            method = "A_loginDetailTailorApp";
            loginResponseResult = WebService.CreateLogin(userId, userPassword,spinnerItemId, method);
            return null;
        }

        @Override
        protected void onPostExecute(Void res) {
            if (loginResponseResult.equals("Err:Invalid UserName & Password")) {
                progressDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
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
            } else if (loginResponseResult.equals("No Network Found")) {
                progressDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Result");
                builder.setMessage("Unable To Login. Please Try Again Later.");
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
            } else {
                progressDialog.dismiss();
                sessionManager = new SessionManager(Login.this);
                try {
                    JSONArray jArr = new JSONArray(loginResponseResult);
                    for (int count = 0; count < jArr.length(); count++) {
                        JSONObject obj = jArr.getJSONObject(count);
                        saveName = obj.getString("Name");
                        saveUserId = obj.getString("UserId");
                        saveEmail = obj.getString("EmailId");
                        saaveMobileNo = obj.getString("MobileNo");
                        DeptType = obj.getString("Type");

                        sessionManager.createUserLoginSession(saveUserId, saveName,saveEmail,saaveMobileNo,DeptType);
                        editTextUserId.setText("");
                        editTextPassword.setText("");
                        Intent gotoHome = new Intent(Login.this, MainActivity.class);
                        startActivity(gotoHome);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }



    @Override
    protected void onPause() {
        super.onPause();

        PackageManager pm = Login.this.getPackageManager();
        ComponentName component = new ComponentName(Login.this, NetworkChangeReceiver.class);
        pm.setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

    }

    @Override
    protected void onResume() {
        super.onResume();

        PackageManager pm = Login.this.getPackageManager();
        ComponentName component = new ComponentName(Login.this, NetworkChangeReceiver.class);
        pm.setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.GET_ACTIVITIES);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}

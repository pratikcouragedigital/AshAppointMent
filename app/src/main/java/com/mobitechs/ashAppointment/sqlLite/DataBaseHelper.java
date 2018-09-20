package com.mobitechs.ashAppointment.sqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StudentCares";

    // user details
    public static final String TABLE_USERDETAILS = "UserDetails";
    public static final String USERDETAILS_ID = "Id";
    public static final String USERDETAILS_USERID = "userId";
    public static final String USERDETAILS_USERTYPE= "Usertype";
    public static final String USERDETAILS_NAME = "Name";
    public static final String USERDETAILS_MOBILENO = "MobileNo";
    public static final String USERDETAILS_EMAILID = "Emailid";
    public static final String USERDETAILS_DOB = "Dob";
    public static final String USERDETAILS_ADDRESS = "Address";
    public static final String USERDETAILS_GRNO = "GrNo";
    public static final String USERDETAILS_BLOODGROUP = "BloodGroup";
    public static final String USERDETAILS_SWIPECARDNO = "SwipeCardNo";
    public static final String USERDETAILS_ROLLNO = "RolNo";

    public static final String USERDETAILS_IMAAGE = "Image";
    public static final String USERDETAILS_SCHOOL_ID = "School_Code";
    public static final String USERDETAILS_SCHOOL_LOGO = "School_Logo";
    public static final String USERDETAILS_SCHOOL_NAME = "School_Name";
    public static final String USERDETAILS_SCHOOL_WEBSITE = "school_Website";
    public static final String USERDETAILS_SCHOOL_PHONENO = "School_Phone_No";
    public static final String USERDETAILS_SCHOOL_ADDRESS = "School_Address";
    public static final String USERDETAILS_SCHOOL_EAMIL = "School_Email";

    // homework details
    public static final String TABLE_HOMEWORKDETAILS = "HomeworkDetails";
    public static final String HOMEWORKDETAILS_ID = "Id";
    public static final String HOMEWORK_USERID = "userId";
    public static final String HOMEWORK_LISTID = "listId";
    public static final String HOMEWORK_STANDARD = "standard";
    public static final String HOMEWORK_DIVISION = "division";
    public static final String HOMEWORK_SUBJECT_NAME = "subject_Name";
    public static final String HOMEWORK_TEACHER_NAME = "teacher_Name";
    public static final String HOMEWORK_SUBMISSION_DATE = "submission_Date";
    public static final String HOMEWORK_ADDED_DATE = "addedDate";
    public static final String HOMEWORK_DESCRIPTION = "homework";
    public static final String HOMEWORK_TITLE = "homeworkTitle";
    public static final String HOMEWORK_IMAGE = "homeworkImage";

    //Standard Details
    public static final String TABLE_STANDARD = "StandardDetails";
    public static final String STANDARD_STANDARDID = "Id"; //primary key
    public static final String STANDARD_ID = "StandardId"; //server table primary key
    public static final String STANDARD_NAME = "Standard_Name";

    //Division Details
    public static final String TABLE_DIVISION = "DivisionDetails";
    public static final String DIVISION_DIVISIONID = "Id";  //primary key
    public static final String DIVISION_ID = "DivisionId";  //server table primary key
    public static final String DIVISION_NAME = "Division_Name";

    //Subject Details
    public static final String TABLE_SUBJECT = "SubjectDetails";
    public static final String SUBJECT_SUBJECTID = "Id"; //primary key
    public static final String SUBJECT_ID = "SubjectId";  //server table primary key
    public static final String SUBJECT_NAME = "Subject_Name";

    //Notice Details
    public static final String TABLE_NOTICE = "NoticeDetails";
    public static final String NOTICE_NOTICEID = "Id"; //primary key
    public static final String LIST_ID = "NoticeId";  //server table primary key
    public static final String NOTICE_TITLE = "Title";
    public static final String NOTICE_DESCRIPTION = "Description";
    public static final String NOTICE_ADDEDDATE = "AddedDate";
    public static final String NOTICE_ADDEDBYNAME = "AddedByName";
    public static final String NOTICE_IMAGE = "Image";

    //Attendace Details
    public static final String TABLE_ATTENDANCE = "AttendanceDetails";
    public static final String ID = "Id"; //primary key
    public static final String ATTENDANCE_ID = "attendance_id";  //server table primary key
    public static final String ATTENDANCE_STUDENT_ID = "student_id";
    public static final String ATTENDANCE_SWIPECARDNO = "swipCard_no";
    public static final String ATTENDANCE_SCHOOL_ID = "school_id";
    public static final String ATTENDANCE_MACHINE_ID = "machine_id";
    public static final String ATTENDANCE_MACHINE_NO = "machine_no";
    public static final String ATTENDANCE_TYPE = "type";
    public static final String ATTENDANCE_DATE = "date";
    public static final String ATTENDANCE_INTIME = "in_time";
    public static final String ATTENDANCE_OUTTIME = "out_time";
    public static final String ATTENDANCE_STATUS = "status";
    public static final String ATTENDANCE_SMSTREK = "trk_sms";
    public static final String ATTENDANCE_PRESENT_DATE = "presentDate";
    public static final String ATTENDANCE_PRESENT_MONTH = "presentMonth";
    public static final String ATTENDANCE_PRESENT_YEAR = "presentYear";



    private HashMap hp;

    String CREATE_TABLE_USERDETAILS = "CREATE TABLE " + TABLE_USERDETAILS + "("+ USERDETAILS_ID + " INTEGER PRIMARY KEY," + USERDETAILS_USERID +" TEXT," + USERDETAILS_USERTYPE + " TEXT," + USERDETAILS_NAME + " TEXT," +USERDETAILS_MOBILENO +" TEXT," +USERDETAILS_EMAILID + " TEXT,"+USERDETAILS_DOB + " TEXT,"+USERDETAILS_ADDRESS + " TEXT," +USERDETAILS_GRNO + " TEXT,"+USERDETAILS_BLOODGROUP + " TEXT," +USERDETAILS_SWIPECARDNO + " TEXT," +USERDETAILS_ROLLNO + " TEXT,"   +USERDETAILS_IMAAGE + " TEXT," +USERDETAILS_SCHOOL_ID + " TEXT,"+USERDETAILS_SCHOOL_LOGO + " TEXT," +USERDETAILS_SCHOOL_NAME + " TEXT," +USERDETAILS_SCHOOL_WEBSITE + " TEXT,"+USERDETAILS_SCHOOL_PHONENO + " TEXT," +USERDETAILS_SCHOOL_ADDRESS + " TEXT," +USERDETAILS_SCHOOL_EAMIL + " TEXT" +")";
    String CREATE_TABLE_HOMEWORKDETAILS ="CREATE TABLE " + TABLE_HOMEWORKDETAILS + "("+ HOMEWORKDETAILS_ID + " INTEGER PRIMARY KEY,"+ HOMEWORK_USERID +" TEXT,"+ HOMEWORK_LISTID +" TEXT UNIQUE,"+ HOMEWORK_STANDARD +" TEXT,"+ HOMEWORK_DIVISION +" TEXT,"+ HOMEWORK_SUBJECT_NAME +" TEXT,"+ HOMEWORK_TEACHER_NAME + " TEXT," + HOMEWORK_SUBMISSION_DATE + " TEXT,"+HOMEWORK_ADDED_DATE +" TEXT," +HOMEWORK_DESCRIPTION + " TEXT," +HOMEWORK_TITLE + " TEXT," +HOMEWORK_IMAGE + " TEXT" +")";
    String CREATE_TABLE_STANDARD ="CREATE TABLE " + TABLE_STANDARD + "("+ STANDARD_STANDARDID + " INTEGER PRIMARY KEY,"+ STANDARD_NAME +" TEXT,"+ STANDARD_ID +" TEXT"+ ")";
    String CREATE_TABLE_DIVISION ="CREATE TABLE " + TABLE_DIVISION + "("+ DIVISION_DIVISIONID + " INTEGER PRIMARY KEY,"+STANDARD_ID +" TEXT,"+ DIVISION_NAME +" TEXT,"+ DIVISION_ID +" TEXT"+ ")";
    String CREATE_TABLE_SUBJECT ="CREATE TABLE " + TABLE_SUBJECT + "("+ SUBJECT_SUBJECTID + " INTEGER PRIMARY KEY,"+ STANDARD_ID +" TEXT,"+ DIVISION_ID +" TEXT,"+ SUBJECT_NAME +" TEXT,"+ SUBJECT_ID +" TEXT"+ ")";
    String CREATE_TABLE_NOTICE ="CREATE TABLE " + TABLE_NOTICE + "("+ NOTICE_NOTICEID + " INTEGER PRIMARY KEY,"+ LIST_ID +" TEXT,"+ NOTICE_TITLE +" TEXT ,"+ NOTICE_DESCRIPTION +" TEXT,"+ NOTICE_ADDEDDATE +" TEXT,"+ NOTICE_ADDEDBYNAME +" TEXT,"+ NOTICE_IMAGE + " TEXT" + ")";
    String CREATE_TABLE_ATTENDANCE ="CREATE TABLE " + TABLE_ATTENDANCE + "("+ ID + " INTEGER PRIMARY KEY,"+ ATTENDANCE_ID +" TEXT,"+ ATTENDANCE_STUDENT_ID +" TEXT ,"+ ATTENDANCE_SWIPECARDNO +" TEXT,"+ ATTENDANCE_SCHOOL_ID +" TEXT,"+ ATTENDANCE_MACHINE_ID +" TEXT,"+ ATTENDANCE_MACHINE_NO +" TEXT ,"+ ATTENDANCE_TYPE +" TEXT ,"+ ATTENDANCE_DATE +" TEXT ,"+ ATTENDANCE_INTIME +" TEXT ,"+ ATTENDANCE_OUTTIME +" TEXT ,"+ATTENDANCE_STATUS +" TEXT ,"+ ATTENDANCE_SMSTREK +" TEXT ,"+ ATTENDANCE_PRESENT_DATE +" TEXT ,"+ ATTENDANCE_PRESENT_MONTH +" TEXT ,"+ ATTENDANCE_PRESENT_YEAR + " TEXT" + ")";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERDETAILS);
        db.execSQL(CREATE_TABLE_HOMEWORKDETAILS);
        db.execSQL(CREATE_TABLE_STANDARD);
        db.execSQL(CREATE_TABLE_DIVISION);
        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_NOTICE);
        db.execSQL(CREATE_TABLE_ATTENDANCE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERDETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOMEWORKDETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STANDARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIVISION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
        onCreate(db);
    }

//---------------------------- All Standard details-------------------------------//
    public boolean insertAllStandard (String standardId, String standardName) {

        String selectQuery = "SELECT * FROM "+ TABLE_STANDARD +" WHERE "+ STANDARD_ID +" = "+ standardId +"";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(selectQuery, null);
        int count =  cursor.getCount();
        cursor.close();

        if(count == 0){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(STANDARD_ID, standardId);
            contentValues.put(STANDARD_NAME, standardName);
            db.insert(TABLE_STANDARD, null, contentValues);
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

    public JSONArray getAllStandardForSpinner() throws JSONException {

        String selectQuery = "SELECT  * FROM " + TABLE_STANDARD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectQuery, null);
        res.moveToFirst();
        JSONObject Root = new JSONObject();
        JSONArray detailsArray = new JSONArray();
        int i = 0;
        while(res.isAfterLast() == false){
            JSONObject details = new JSONObject();
            details.put(STANDARD_NAME, res.getString(res.getColumnIndex(STANDARD_NAME)));
            details.put(STANDARD_ID, res.getString(res.getColumnIndex(STANDARD_ID)));

            res.moveToNext();
            detailsArray.put(i, details);
            i++;
        }
        res.close();
        return detailsArray;
    }

//---------------------------- All Division details-------------------------------//

    public boolean insertAllDivision (String standardId, String divisionId, String divisionName) {

        String selectQuery = "SELECT * FROM "+ TABLE_DIVISION +" WHERE "+ STANDARD_ID +" = "+ standardId+" AND "+DIVISION_ID+" = "+ divisionId+"";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(selectQuery, null);
        int count =  cursor.getCount();
        cursor.close();

        if(count == 0){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(STANDARD_ID, standardId);
            contentValues.put(DIVISION_ID, divisionId);
            contentValues.put(DIVISION_NAME, divisionName);

            db.insert(TABLE_DIVISION, null, contentValues);
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

    public JSONArray getAllDivisionForSpinner(String standardId) throws JSONException {

        String selectQuery = "SELECT * FROM "+ TABLE_DIVISION +" WHERE "+ STANDARD_ID +" = "+ standardId +" ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectQuery, null);
        res.moveToFirst();
        JSONObject Root = new JSONObject();
        JSONArray detailsArray = new JSONArray();
        int i = 0;
        while(res.isAfterLast() == false){
            JSONObject details = new JSONObject();
            details.put(DIVISION_NAME, res.getString(res.getColumnIndex(DIVISION_NAME)));
            details.put(DIVISION_ID, res.getString(res.getColumnIndex(DIVISION_ID)));

            res.moveToNext();
            detailsArray.put(i, details);
            i++;
        }
        res.close();
        return detailsArray;
    }

//---------------------------- All Subject details-------------------------------//

    public boolean insertAllSubject (String standardId, String divisionId, String subjectId, String subjectName) {

        String selectQuery = "SELECT * FROM "+ TABLE_SUBJECT +" WHERE "+ STANDARD_ID +" = "+ standardId+" AND "+DIVISION_ID+" = "+ divisionId+" AND " + SUBJECT_ID +" = "+ subjectId +"";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(selectQuery, null);
        int count =  cursor.getCount();
        cursor.close();

        if(count == 0){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(STANDARD_ID, standardId);
            contentValues.put(DIVISION_ID, divisionId);
            contentValues.put(SUBJECT_ID, subjectId);
            contentValues.put(SUBJECT_NAME, subjectName);

            db.insert(TABLE_SUBJECT, null, contentValues);
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

    public JSONArray getAllSubjectForSpinner(String standardId, String divisionId) throws JSONException {

        String selectQuery = "SELECT * FROM "+ TABLE_SUBJECT +" WHERE "+ STANDARD_ID +" = "+ standardId +" AND "+ DIVISION_ID +" = "+ divisionId +" ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectQuery, null);
        res.moveToFirst();
        JSONObject Root = new JSONObject();
        JSONArray detailsArray = new JSONArray();
        int i = 0;
        while(res.isAfterLast() == false){
            JSONObject details = new JSONObject();
            details.put(SUBJECT_NAME, res.getString(res.getColumnIndex(SUBJECT_NAME)));
            details.put(SUBJECT_ID, res.getString(res.getColumnIndex(SUBJECT_ID)));

            res.moveToNext();
            detailsArray.put(i, details);
            i++;
        }
        res.close();
        return detailsArray;
    }

//---------------------------- User details-------------------------------//
    public boolean insertUserDetails (String Usertype, String userId, String Name, String MobileNo, String Emailid, String userDob, String userAddress, String userBloodGroup, String userGrNo, String userSwipeCard, String userRollNo, String Image, String School_Code, String School_Logo, String School_Name, String school_Website, String School_Phone_No, String School_Address, String School_Email ) {

        String selectQuery = "SELECT * FROM "+ TABLE_USERDETAILS +" WHERE "+ USERDETAILS_USERID +" = "+ userId+" ";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(selectQuery, null);
        int count =  cursor.getCount();
        cursor.close();

        if(count == 0){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USERDETAILS_USERID, userId);
            contentValues.put(USERDETAILS_USERTYPE, Usertype);
            contentValues.put(USERDETAILS_NAME, Name);
            contentValues.put(USERDETAILS_MOBILENO, MobileNo);
            contentValues.put(USERDETAILS_EMAILID, Emailid);
            contentValues.put(USERDETAILS_DOB, userDob);
            contentValues.put(USERDETAILS_ADDRESS, userAddress);
            contentValues.put(USERDETAILS_BLOODGROUP, userBloodGroup);
            contentValues.put(USERDETAILS_GRNO, userGrNo);
            contentValues.put(USERDETAILS_SWIPECARDNO, userSwipeCard);
            contentValues.put(USERDETAILS_ROLLNO, userRollNo);
            contentValues.put(USERDETAILS_IMAAGE, Image);
            contentValues.put(USERDETAILS_SCHOOL_ID, School_Code);
            contentValues.put(USERDETAILS_SCHOOL_LOGO, School_Logo);
            contentValues.put(USERDETAILS_SCHOOL_NAME, School_Name);
            contentValues.put(USERDETAILS_SCHOOL_WEBSITE, school_Website);
            contentValues.put(USERDETAILS_SCHOOL_PHONENO, School_Phone_No);
            contentValues.put(USERDETAILS_SCHOOL_ADDRESS, School_Address);
            contentValues.put(USERDETAILS_SCHOOL_EAMIL, School_Email);
            db.insert(TABLE_USERDETAILS, null, contentValues);
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

    public JSONArray getAllUserForSpinner() throws JSONException {
        //ArrayList<String> array_list = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + TABLE_USERDETAILS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectQuery, null);
        res.moveToFirst();
        JSONObject Root = new JSONObject();
        JSONArray userDetailsArray = new JSONArray();
        int i = 0;
        while(res.isAfterLast() == false){
            JSONObject userDetails = new JSONObject();
            userDetails.put(USERDETAILS_NAME, res.getString(res.getColumnIndex(USERDETAILS_NAME)));
            userDetails.put(USERDETAILS_USERID, res.getString(res.getColumnIndex(USERDETAILS_USERID)));

// this fr array list
//            array_list.add(res.getString(res.getColumnIndex(USERDETAILS_NAME)));
//            array_list.add(res.getString(res.getColumnIndex(USERDETAILS_USERID)));
            res.moveToNext();
            userDetailsArray.put(i, userDetails);
            i++;
        }
        res.close();
        return userDetailsArray;
    }

//---------------------------- homework details-------------------------------//

    public boolean insertHomeworkDetails (String userId, String listId, String standanrd, String division, String subject_Name, String teacher_Name, String submission_Date, String addedDate, String homework, String homeworkTitle, String homeworkImage) {

        String selectQuery = "SELECT * FROM "+ TABLE_HOMEWORKDETAILS +" WHERE "+ HOMEWORK_LISTID +" = "+ listId +" ";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(selectQuery, null);
        int count =  cursor.getCount();
        cursor.close();

        if(count == 0){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(HOMEWORK_USERID, userId);
            contentValues.put(HOMEWORK_LISTID, listId);
            contentValues.put(HOMEWORK_STANDARD, standanrd);
            contentValues.put(HOMEWORK_DIVISION, division);
            contentValues.put(HOMEWORK_SUBJECT_NAME, subject_Name);
            contentValues.put(HOMEWORK_TEACHER_NAME, teacher_Name);
            contentValues.put(HOMEWORK_SUBMISSION_DATE, submission_Date);
            contentValues.put(HOMEWORK_ADDED_DATE, addedDate);
            contentValues.put(HOMEWORK_DESCRIPTION, homework);
            contentValues.put(HOMEWORK_TITLE, homeworkTitle);
            contentValues.put(HOMEWORK_IMAGE, homeworkImage);
            db.insert(TABLE_HOMEWORKDETAILS, null, contentValues);
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

    public JSONArray getAllHomeworkList(String userId) throws JSONException {

        String selectQuery = "SELECT * FROM " + TABLE_HOMEWORKDETAILS + " WHERE " + HOMEWORK_USERID +"=" +userId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectQuery, null);
        res.moveToFirst();
        JSONObject Root = new JSONObject();
        JSONArray detailsArray = new JSONArray();
        int i = 0;
        while(res.isAfterLast() == false){
            JSONObject details = new JSONObject();
            details.put(HOMEWORK_SUBJECT_NAME, res.getString(res.getColumnIndex(HOMEWORK_SUBJECT_NAME)));
            details.put(HOMEWORK_TEACHER_NAME, res.getString(res.getColumnIndex(HOMEWORK_TEACHER_NAME)));
            details.put(HOMEWORK_SUBMISSION_DATE, res.getString(res.getColumnIndex(HOMEWORK_SUBMISSION_DATE)));
            details.put(HOMEWORK_ADDED_DATE, res.getString(res.getColumnIndex(HOMEWORK_ADDED_DATE)));
            details.put(HOMEWORK_DESCRIPTION, res.getString(res.getColumnIndex(HOMEWORK_DESCRIPTION)));
            details.put(HOMEWORK_TITLE, res.getString(res.getColumnIndex(HOMEWORK_TITLE)));
            details.put(HOMEWORK_IMAGE, res.getString(res.getColumnIndex(HOMEWORK_IMAGE)));
            details.put(HOMEWORK_STANDARD, res.getString(res.getColumnIndex(HOMEWORK_STANDARD)));
            details.put(HOMEWORK_DIVISION, res.getString(res.getColumnIndex(HOMEWORK_DIVISION)));

            res.moveToNext();
            detailsArray.put(i, details);
            i++;
        }
        res.close();
        return detailsArray;
    }

    public JSONArray getAllFilterWiseHomeworkList(String userId, String selectedValue, int count) throws JSONException {
        String selectQuery="";
        if(count == 1){
            selectQuery = "SELECT * FROM " + TABLE_HOMEWORKDETAILS + " WHERE " + HOMEWORK_USERID +" = " +userId+" AND "+HOMEWORK_SUBJECT_NAME +" = "+ selectedValue;
        }
        else if (count == 2){
            selectQuery = "SELECT * FROM " + TABLE_HOMEWORKDETAILS + " WHERE " + HOMEWORK_USERID +" = " +userId+" AND "+HOMEWORK_SUBMISSION_DATE +" = "+ selectedValue;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectQuery, null);
        res.moveToFirst();
        JSONObject Root = new JSONObject();
        JSONArray detailsArray = new JSONArray();
        int i = 0;
        while(res.isAfterLast() == false){
            JSONObject details = new JSONObject();
            details.put(HOMEWORK_SUBJECT_NAME, res.getString(res.getColumnIndex(HOMEWORK_SUBJECT_NAME)));
            details.put(HOMEWORK_TEACHER_NAME, res.getString(res.getColumnIndex(HOMEWORK_TEACHER_NAME)));
            details.put(HOMEWORK_SUBMISSION_DATE, res.getString(res.getColumnIndex(HOMEWORK_SUBMISSION_DATE)));
            details.put(HOMEWORK_ADDED_DATE, res.getString(res.getColumnIndex(HOMEWORK_ADDED_DATE)));
            details.put(HOMEWORK_DESCRIPTION, res.getString(res.getColumnIndex(HOMEWORK_DESCRIPTION)));
            details.put(HOMEWORK_TITLE, res.getString(res.getColumnIndex(HOMEWORK_TITLE)));
            details.put(HOMEWORK_IMAGE, res.getString(res.getColumnIndex(HOMEWORK_IMAGE)));
            details.put(HOMEWORK_STANDARD, res.getString(res.getColumnIndex(HOMEWORK_STANDARD)));
            details.put(HOMEWORK_DIVISION, res.getString(res.getColumnIndex(HOMEWORK_DIVISION)));

            res.moveToNext();
            detailsArray.put(i, details);
            i++;
        }
        res.close();
        return detailsArray;
    }

//---------------------------- Notice details-------------------------------//

    public boolean insertNoticeDetails (String listId, String title , String description, String addedDate, String addedByName, String homeworkImage) {

        String selectQuery = "SELECT * FROM "+ TABLE_NOTICE +" WHERE "+ LIST_ID +" = "+ listId +" ";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(selectQuery, null);
        int count =  cursor.getCount();
        cursor.close();

        if(count == 0){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(LIST_ID, listId);
            contentValues.put(NOTICE_TITLE, title);
            contentValues.put(NOTICE_DESCRIPTION, description);
            contentValues.put(NOTICE_ADDEDDATE, addedDate);
            contentValues.put(NOTICE_ADDEDBYNAME, addedByName);
            contentValues.put(HOMEWORK_IMAGE, homeworkImage);
            db.insert(TABLE_NOTICE, null, contentValues);
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

    public JSONArray getAllNoticeList() throws JSONException {

        String selectQuery = "SELECT * FROM " + TABLE_NOTICE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectQuery, null);
        res.moveToFirst();
        JSONObject Root = new JSONObject();
        JSONArray detailsArray = new JSONArray();
        int i = 0;

        while(res.isAfterLast() == false){
            JSONObject details = new JSONObject();
            details.put(LIST_ID, res.getString(res.getColumnIndex(LIST_ID)));
            details.put(NOTICE_TITLE, res.getString(res.getColumnIndex(NOTICE_TITLE)));
            details.put(NOTICE_DESCRIPTION, res.getString(res.getColumnIndex(NOTICE_DESCRIPTION)));
            details.put(NOTICE_ADDEDDATE, res.getString(res.getColumnIndex(NOTICE_ADDEDDATE)));
            details.put(NOTICE_ADDEDBYNAME, res.getString(res.getColumnIndex(NOTICE_ADDEDBYNAME)));
            details.put(HOMEWORK_IMAGE, res.getString(res.getColumnIndex(HOMEWORK_IMAGE)));

            res.moveToNext();
            detailsArray.put(i, details);
            i++;
        }
        res.close();
        return detailsArray;
    }


//---------------------------- Attendance details-------------------------------//
    public boolean insetAttendanceDetails(String attendance_id, String student_id, String swipCard_no, String school_id, String machine_id, String machine_no, String type, String date, String in_time, String out_time, String status, String trk_sms, String presentDate, String presentMonth, String presentYear) {

        String selectQuery = "SELECT * FROM "+ TABLE_ATTENDANCE +" WHERE "+ ATTENDANCE_DATE +" = "+ date +" AND "+ ATTENDANCE_SMSTREK +" = "+trk_sms+"";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(selectQuery, null);
        int count =  cursor.getCount();
        cursor.close();

        if(count == 0){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ATTENDANCE_ID, attendance_id);
            contentValues.put(ATTENDANCE_STUDENT_ID, student_id);
            contentValues.put(ATTENDANCE_SWIPECARDNO, swipCard_no);
            contentValues.put(ATTENDANCE_SCHOOL_ID, school_id);
            contentValues.put(ATTENDANCE_MACHINE_ID, machine_id);
            contentValues.put(ATTENDANCE_MACHINE_NO, machine_no);
            contentValues.put(ATTENDANCE_DATE, date);
            contentValues.put(ATTENDANCE_INTIME, in_time);
            contentValues.put(ATTENDANCE_OUTTIME, out_time);
            contentValues.put(ATTENDANCE_STATUS, status);
            contentValues.put(ATTENDANCE_TYPE, type);
            contentValues.put(ATTENDANCE_SMSTREK, trk_sms);
            contentValues.put(ATTENDANCE_PRESENT_DATE, presentDate);
            contentValues.put(ATTENDANCE_PRESENT_MONTH, presentMonth);
            contentValues.put(ATTENDANCE_PRESENT_YEAR, presentYear);

            db.insert(TABLE_ATTENDANCE, null, contentValues);
            db.close();
            return true;
        }
        else{
            return false;
        }

    }

    public boolean UpdateAttendanceDetailsForOutTime(String dateForOutTime, String outTime) {
        String selectQuery = "SELECT * FROM "+ TABLE_ATTENDANCE +" WHERE "+ ATTENDANCE_DATE +" = "+""+dateForOutTime+""  +" AND "+ATTENDANCE_SMSTREK+" = "+"1"+" ";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(selectQuery, null);
        int count =  cursor.getCount();
        cursor.close();

        if(count == 1){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ATTENDANCE_OUTTIME, outTime);

          //  for multiple where clause
            String[] whereClauseArgument = new String[1];
            whereClauseArgument[0] = dateForOutTime;
            whereClauseArgument[1] = "1";
            db.update(TABLE_ATTENDANCE, contentValues, ATTENDANCE_DATE +" =? AND "+ATTENDANCE_SMSTREK+" = ?",whereClauseArgument);

//            db.update(TABLE_ATTENDANCE, contentValues, ATTENDANCE_DATE + " = ?",new String[]{dateForOutTime});
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

//    public void UpdateAttendance() throws JSONException {
//        String selectQuery = "SELECT * FROM "+ TABLE_ATTENDANCE +" WHERE "+ ATTENDANCE_SMSTREK +" = 2";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery(selectQuery, null);
//        res.moveToFirst();
//        JSONObject Root = new JSONObject();
//        JSONArray detailsArray = new JSONArray();
//        int i = 0;
//        while(res.isAfterLast() == false){
//            JSONObject details = new JSONObject();
//            String date = (res.getString(res.getColumnIndex(ATTENDANCE_DATE)));
//            String outTime = (res.getString(res.getColumnIndex(ATTENDANCE_INTIME)));
//
//
//            String selectQuery2 = "SELECT * FROM "+ TABLE_ATTENDANCE +" WHERE "+ ATTENDANCE_DATE +" = "+date+" AND "+ATTENDANCE_SMSTREK+" =1 ";
//            db = this.getReadableDatabase();
//            Cursor cursor = db.rawQuery(selectQuery2, null);
//            int count =  cursor.getCount();
//            cursor.close();
//
//            if(count == 1) {
//                db = this.getWritableDatabase();
//                ContentValues contentValues = new ContentValues();
//                contentValues.put(ATTENDANCE_OUTTIME, outTime);
//
//                //  for multiple where clause
//                String[] whereClauseArgument = new String[1];
//                whereClauseArgument[0] = date;
//                whereClauseArgument[1] = "1";
//                db.update(TABLE_ATTENDANCE, contentValues, ATTENDANCE_DATE + " =? AND " + ATTENDANCE_SMSTREK + " = ?", whereClauseArgument);
//                db.close();
//            }
//        }
//        res.close();
//    }

    public JSONArray getAttenadanceDetailsMonthWise(String userId, String month, String year) throws JSONException {

        String selectQuery = "SELECT * FROM " + TABLE_ATTENDANCE + " WHERE " + ATTENDANCE_STUDENT_ID +" = " +userId+" AND "+ATTENDANCE_PRESENT_MONTH +" = "+ month+" AND "+ATTENDANCE_PRESENT_YEAR +" = "+ year;
        //String selectQuery = "SELECT * FROM  AttendanceDetails WHERE student_id = 120032 AND presentMonth = 2 AND presentYear = 2017";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectQuery, null);
        int count =  res.getCount();
        res.moveToFirst();
        JSONObject Root = new JSONObject();
        JSONArray detailsArray = new JSONArray();
        int i = 0;
        while(res.isAfterLast() == false){
            JSONObject details = new JSONObject();
            details.put(ATTENDANCE_ID, res.getString(res.getColumnIndex(ATTENDANCE_ID)));
            details.put(ATTENDANCE_STUDENT_ID, res.getString(res.getColumnIndex(ATTENDANCE_STUDENT_ID)));
            details.put(ATTENDANCE_SWIPECARDNO, res.getString(res.getColumnIndex(ATTENDANCE_SWIPECARDNO)));
            details.put(ATTENDANCE_SCHOOL_ID, res.getString(res.getColumnIndex(ATTENDANCE_SCHOOL_ID)));
            details.put(ATTENDANCE_MACHINE_ID, res.getString(res.getColumnIndex(ATTENDANCE_MACHINE_ID)));
            details.put(ATTENDANCE_MACHINE_NO, res.getString(res.getColumnIndex(ATTENDANCE_MACHINE_NO)));
            details.put(ATTENDANCE_TYPE, res.getString(res.getColumnIndex(ATTENDANCE_TYPE)));
            details.put(ATTENDANCE_DATE, res.getString(res.getColumnIndex(ATTENDANCE_DATE)));
            details.put(ATTENDANCE_INTIME, res.getString(res.getColumnIndex(ATTENDANCE_INTIME)));
            details.put(ATTENDANCE_OUTTIME, res.getString(res.getColumnIndex(ATTENDANCE_OUTTIME)));
            details.put(ATTENDANCE_STATUS, res.getString(res.getColumnIndex(ATTENDANCE_STATUS)));
            details.put(ATTENDANCE_SMSTREK, res.getString(res.getColumnIndex(ATTENDANCE_SMSTREK)));
            details.put(ATTENDANCE_PRESENT_DATE, res.getString(res.getColumnIndex(ATTENDANCE_PRESENT_DATE)));
            details.put(ATTENDANCE_PRESENT_MONTH, res.getString(res.getColumnIndex(ATTENDANCE_PRESENT_MONTH)));
            details.put(ATTENDANCE_PRESENT_YEAR, res.getString(res.getColumnIndex(ATTENDANCE_PRESENT_YEAR)));

            res.moveToNext();
            detailsArray.put(i, details);
            i++;
        }
        res.close();
        return detailsArray;
    }


}

package com.mobitechs.ashAppointment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobitechs.ashAppointment.adapter.DrawerAdapter;
import com.mobitechs.ashAppointment.appointment.R;
import com.mobitechs.ashAppointment.model.DrawerItems;
import com.mobitechs.ashAppointment.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;


public class BaseActivity extends AppCompatActivity {

    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    SessionManager sessionManager;
    TextView lblName;
    //TextView lbluserId;
    RecyclerView listItems;
    DrawerLayout drawer;
    FrameLayout frameLayout;
    LinearLayout linearLayout;
    DrawerAdapter drawerAdapter;
    String userType;

    public ArrayList<DrawerItems> itemArrayList;
    public ArrayList<DrawerItems> itemSelectedArrayListForNgo;

    public ArrayList<DrawerItems> itemArrayListForNgo;
    public ArrayList<DrawerItems> itemSelectedArrayList;

    @Override
    public void setContentView(int layoutResID) {
        drawer = (DrawerLayout) getLayoutInflater().inflate(R.layout.drawer, null);
        frameLayout = (FrameLayout) drawer.findViewById(R.id.contentFrame);
        linearLayout = (LinearLayout) drawer.findViewById(R.id.drawerlinearlayout);
        listItems = (RecyclerView) drawer.findViewById(R.id.drawerListItem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listItems.setLayoutManager(linearLayoutManager);

        SessionManager sessionManagerNgo = new SessionManager(BaseActivity.this);
        HashMap<String, String> typeOfUser = sessionManagerNgo.getUserDetails();
        userType = typeOfUser.get(SessionManager.KEY_USERID);

        final String[] tittle = new String[]{"Home", "My Appointment", "Logout"};
        final int[] icons = new int[]{R.drawable.home, R.drawable.news, R.drawable.logout};
        itemArrayList = new ArrayList<DrawerItems>();
        for (int i = 0; i < tittle.length; i++) {
            DrawerItems drawerItems = new DrawerItems();
            drawerItems.setTittle(tittle[i]);
            drawerItems.setIcons(icons[i]);
            itemArrayList.add(drawerItems);
        }
        final int[] selectedicons = new int[]{R.drawable.home_red, R.drawable.news_red, R.drawable.logout};
        itemSelectedArrayList = new ArrayList<DrawerItems>();
        for (int i = 0; i < tittle.length; i++) {
            DrawerItems drawerItems = new DrawerItems();
            drawerItems.setTittle(tittle[i]);
            drawerItems.setIcons(selectedicons[i]);
            itemSelectedArrayList.add(drawerItems);
        }
        drawerAdapter = new DrawerAdapter(itemArrayList, itemSelectedArrayList, drawer);
        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        getLayoutInflater().inflate(layoutResID, linearLayout, true);
        drawer.setClickable(true);
        drawerAdapter.notifyDataSetChanged();
        listItems.setAdapter(drawerAdapter);


        toolbar = (Toolbar) drawer.findViewById(R.id.app_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        super.setContentView(drawer);
        sessionManager = new SessionManager(getApplicationContext());
        lblName = (TextView) findViewById(R.id.lblName);
        //lbluserId = (TextView) findViewById(R.id.lblUserId);

        HashMap<String, String> user = sessionManager.getUserDetails();

        //String userId = user.get(sessionManager.KEY_USERID);
        String name = user.get(SessionManager.KEY_NAME);  // get Name
        lblName.setText(name);   // Show user data on activity
        //lbluserId.setText(userId);
        //changeAppFont();
    }

//    public void changeAppFont() {
//        setDefaultFont(this, "MONOSPACE", "fonts/Montserrat-Regular.ttf");
//
//    }
//
//    public static void setDefaultFont(Context context,
//                                      String staticTypefaceFieldName, String fontAssetName) {
//        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
//                fontAssetName);
//        replaceFont(staticTypefaceFieldName, regular);
//    }
//
//    public static void replaceFont(String staticTypefaceFieldName,
//                                   final Typeface newTypeface) {
//
//        if (Build.VERSION.SDK_INT > 16) {
//            try {
//                final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
//                staticField.setAccessible(true);
//                staticField.set(null, newTypeface);
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
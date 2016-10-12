package com.mathos.jnemcic.mathos;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tomato extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView backgroundImage;
    private CountDownTimer countDownTimer;
    private TextView timerTextView;
    private int tomatoCount = 0;
    private LinearLayout tomatoLinearLayout;
    private Button timerButtonStart;
    private Button timerButtonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomato_activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        backgroundImage = (ImageView) findViewById(R.id.background_image);
        if (Personalization.backgroundSelector == 0) {
            backgroundImage.setImageResource(R.drawable.wallpaper1);
        } else if (Personalization.backgroundSelector == R.drawable.wallpaper1) {
            backgroundImage.setImageResource(R.drawable.wallpaper1);
        } else if (Personalization.backgroundSelector == R.drawable.wallpaper) {
            backgroundImage.setImageResource(R.drawable.wallpaper);
        } else {
            backgroundImage.setImageResource(R.drawable.background);
        }

        tomatoLinearLayout = (LinearLayout) findViewById(R.id.tomato_linear_layout);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerButtonStart = (Button) findViewById(R.id.timer_button_start);
        timerButtonStop = (Button) findViewById(R.id.timer_button_stop);
        timerButtonStop.setVisibility(View.GONE);

        timerButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimer();
            }
        });

        timerButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                timerTextView.setText("");
                timerButtonStart.setVisibility(View.VISIBLE);
                timerButtonStop.setVisibility(View.GONE);
            }
        });
    }

    public void setTimer() {
        countDownTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timerTextView.setText(String.format("%d:%02d", minutes, seconds));
                timerButtonStart.setVisibility(View.GONE);
                timerButtonStop.setVisibility(View.VISIBLE);
            }
            public void onFinish() {
                timerButtonStart.setVisibility(View.VISIBLE);
                timerButtonStop.setVisibility(View.GONE);
                timerTextView.setText("");
                tomatoCount += 1;
                if (tomatoCount < 4) {
                    addTomato();
                } else {
                    tomatoCount = 0;
                    tomatoLinearLayout.removeAllViews();
                    addTomato();
                }
            }
        }.start();
    }

    public void addTomato() {
        ImageView myImage = new ImageView(Tomato.this);
        myImage.setImageResource(R.mipmap.tomato);
        tomatoLinearLayout.addView(myImage);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_courses) {
            Intent intent = new Intent(this, MyCourses.class);
            startActivity(intent);
        } else if (id == R.id.nav_schedule) {

        } else if (id == R.id.nav_navigation) {

        } else if (id == R.id.nav_tomato) {

        } else if (id == R.id.nav_manage_courses) {
            Intent intent = new Intent(this, ManageCourses.class);
            startActivity(intent);
        } else if (id == R.id.nav_personalization) {
            Intent intent = new Intent(this, Personalization.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.MenuItem;
//import android.widget.ImageView;
//
//public class Tomato extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//
//    private ImageView backgroundImage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.tomato_activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        backgroundImage = (ImageView) findViewById(R.id.background_image);
//        if (Personalization.backgroundSelector == 0) {
//            backgroundImage.setImageResource(R.drawable.wallpaper1);
//        } else if (Personalization.backgroundSelector == R.drawable.wallpaper1) {
//            backgroundImage.setImageResource(R.drawable.wallpaper1);
//        } else if (Personalization.backgroundSelector == R.drawable.wallpaper) {
//            backgroundImage.setImageResource(R.drawable.wallpaper);
//        } else {
//            backgroundImage.setImageResource(R.drawable.background);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_courses) {
//            Intent intent = new Intent(this, MyCourses.class);
//            startActivity(intent);
//        } else if (id == R.id.nav_schedule) {
//
//        } else if (id == R.id.nav_navigation) {
//
//        } else if (id == R.id.nav_tomato) {
//
//        } else if (id == R.id.nav_manage_courses) {
//            Intent intent = new Intent(this, ManageCourses.class);
//            startActivity(intent);
//        } else if (id == R.id.nav_personalization) {
//            Intent intent = new Intent(this, Personalization.class);
//            startActivity(intent);
//        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//}
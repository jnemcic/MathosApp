package com.mathos.jnemcic.mathos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageCourses extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_program_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView undergraduateButton = (TextView) findViewById(R.id.undergraduate_math_button);
        TextView educationButton = (TextView) findViewById(R.id.education_math_button);
        TextView finStatsButton = (TextView) findViewById(R.id.financial_statistics_button);
        TextView compScienceButton = (TextView) findViewById(R.id.computer_science_button);

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

        undergraduateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageCourses.this, UndergraduateStudyProgram.class);
                startActivity(intent);
            }
        });

        educationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageCourses.this, EducationStudyProgram.class);
                startActivity(intent);
            }
        });

        finStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageCourses.this, FinancialStatisticsProgram.class);
                startActivity(intent);
            }
        });

        compScienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageCourses.this, ComputerScienceProgram.class);
                startActivity(intent);
            }
        });
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
            Intent intent = new Intent(this, Tomato.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage_courses) {

        } else if (id == R.id.nav_personalization) {
            Intent intent = new Intent(this, Personalization.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

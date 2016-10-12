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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinancialStatisticsProgram extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static ListView listView;
    List<Course> listCourse = new ArrayList<>();
    Course course;
    public ManageCoursesAdapter adapter;

    List<String> allCourses =
            new ArrayList<String>(Arrays.asList(new String[]{
                    "Slučajni procesi","Statistika","Vjerojatnost","Financijska i aktuarska matematika","Grafovi",
                    "Uvod u teoriju integracije","Uvod u teoriju mjere","Makroekonomija","Metrički prostori",
                    "Grupiranje podataka i primjene","Linearno programiranje","Signali i sustavi","Složenost algoritama",
                    "Znanstveno računalstvo","Konveksne funkcije","Poduzetništvo","Primjene dinamičkih sustava","Teorija odlučivanja",
                    "WEB programiranje i primjene","Matematičke financije", "Analiza vremenskih nizova", "Matematički praktikum",
                    "Multivarijantna analiza","Analiza poslovanja poduzeća","Upravljanje kreditnim rizicima","Diplomski seminar",
                    "Financijska tržišta","Kriptografija i sigurnost sustava",	"Metode optimizacije","Osnove umjetne inteligencije",
                    "Uvod u algebarsku topologiju","Osnove vođenja projekata","Stručna praksa","Matematički modeli",
                    "Operacijska istraživanja","Računarski praktikum","Upravljanje tržišnim rizicima","Uvod u teoriju pouzdanosti"}));
    private ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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

        for (int i=0; i<allCourses.size(); ++i) {
            course = new Course(allCourses.get(i), R.drawable.c);
            listCourse.add(course);
        }

        listView = (ListView) findViewById(R.id.list);
        adapter = new ManageCoursesAdapter(this, listCourse);
        listView.setAdapter(adapter);
        setListViewHeight();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkBox);

                if (checkbox.isChecked()) {
                    MyCourses.listCourseMyCourses.remove(i);
                } else {
                    MyCourses.listCourseMyCourses.add(listCourse.get(i));
                    MyCourses.adapter.notifyDataSetChanged();
                }
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

    public static void setListViewHeight() {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}

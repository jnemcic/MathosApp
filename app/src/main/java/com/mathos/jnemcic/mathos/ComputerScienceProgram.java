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
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComputerScienceProgram extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static ListView listView;
    List<Course> listCourse = new ArrayList<>();
    Course course;
    public ManageCoursesAdapter adapter;

    List<String> allCourses =
            new ArrayList<String>(Arrays.asList(new String[]{
                    "Dizajniranje i modeliranje baze podataka", "Funkcionalno programiranje", "Statistički praktikum", "Grupiranje podataka",
                    "Matematička teorija računarstva", "Složenost algoritama", "Znanstveno računalstvo", "3D računalna grafika",
                    "Klijentsko web programiranje", "Paralelno programiranje", "Računalno jezikoslovlje", "Slučajni procesi",
                    "Vjerojatnost", "Financijska i aktuarska matematika", "Konkretna matematika", "Linearno programiranje",
                    "Matematička logika u verifikaciji softvera", "Osnove inteligentnih robotskih sustava", "Signali i sustavi",
                    "Konveksne funkcije", "Multimedijski sustavi", "Odabrana poglavlja matrične teorije", "Primjene dinamičkih sustava",
                    "Stručna praksa", "Teorija odlučivanja", "Algoritmi na grafovima", "Kriptografija i sigurnost sustava",
                    "Matematički praktikum", "Osnove umjetne inteligencije", "Softversko inženjerstvo", "Diplomski seminar",
                    "Arhitekture računala", "Metode optimizacije", "Multivarijantna analiza", "Napredni koncepti programiranja",
                    "Poslužiteljsko web programiranje", "Teorija pouzdanosti", "Ugrađeni sustavi", "Uvod u algebarsku topologiju",
                    "Analiza poslovanja poduzeća", "Izračunjiva geometrija", "Osnove vođenja projekata", "Financijska tržišta",
                    "Matematičke metode za klasifikaciju teksta", "Matematički aspekti izbornih sustava", "Matematički modeli",
                    "Numerička linearna algebra u kontroli linearnih sustava", "Operacijska istraživanja", "Računarski praktikum",
                    "Strojno učenje"}));

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

        int[] images={R.drawable.ego_graph,R.drawable.database,R.drawable.c,R.drawable.zupanije2,
                R.drawable.logo,R.drawable.c,R.drawable.intro,R.drawable.to,R.drawable.to};

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
//                    checkbox.setChecked(false);
                    MyCourses.listCourseMyCourses.remove(i);
                } else {
//                    checkbox.setChecked(true);
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

        } else if (id == R.id.nav_manage_courses) {
            Intent intent = new Intent(this, ManageCourses.class);
            startActivity(intent);

        } else if (id == R.id.nav_personalization) {

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
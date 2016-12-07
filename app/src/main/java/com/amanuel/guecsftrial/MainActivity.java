package com.amanuel.guecsftrial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{


    DrawerLayout drawerLayout;
    RelativeLayout drawerpane;
    ListView lvNav;
    List<NavItem> listNavItems;
    List<Fragment> listFragments;
   //List<Activity> listFragment;
    ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        drawerpane=(RelativeLayout)findViewById(R.id.drawer_pane);
        lvNav=(ListView) findViewById(R.id.nav_list);


        listNavItems = new ArrayList<NavItem>();

        listNavItems.add(new NavItem("Home",R.drawable.ic_home));
        listNavItems.add(new NavItem("Registration",R.drawable.ic_registration));
        listNavItems.add(new NavItem("My weekly schedules",R.drawable.ic_weeklyschedule));
        listNavItems.add(new NavItem("Feedback",R.drawable.ic_feedback));
        listNavItems.add(new NavItem("About GUECSF",R.drawable.about));
        listNavItems.add(new NavItem("Share",R.drawable.share));

        /*NavlistAdapter navlistAdapter = new NavlistAdapter(
                getApplicationContext(),R.layout.item_nav_list,listNavItems);
*/
        NavlistAdapter navlistAdapter = new NavlistAdapter(
                getApplicationContext(),R.layout.item_nav_list,listNavItems);
        lvNav.setAdapter(navlistAdapter);

       // lvNav.setOnItemClickListener(new SlideMenuClickListener());

        listFragments= new ArrayList<android.support.v4.app.Fragment>();

        listFragments.add(new Home());
        listFragments.add(new Registration());
        listFragments.add(new Weekely());
        listFragments.add (new feedback());
        listFragments.add(new About());
        listFragments.add(new Share());


        // Set the first fragmetn as default
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().
                replace(R.id.main_content,listFragments.get(0));

        setTitle(listNavItems.get(0).getTitle());
        lvNav.setItemChecked(0,true);
        drawerLayout.closeDrawer(drawerpane);

        // set listener for navigation items
        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                // replace the fragment with the selected one correspondigly
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.main_content,listFragments.get(position))
                        .commit();

                setTitle(listNavItems.get(position).getTitle());
                lvNav.setItemChecked(position,true);
                drawerLayout.closeDrawer(drawerpane);

            }
        });

        // listener for drawer layout
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,
                R.string.drawer_opened,R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();

                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu, menu);
       return true;
   }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent i = new Intent(this,Share.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);

        }
        }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}

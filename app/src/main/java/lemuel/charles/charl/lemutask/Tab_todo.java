package lemuel.charles.charl.lemutask;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Tab_todo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    private ViewPager mViewPager;
    CheckBox chkTimed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        chkTimed=(CheckBox) findViewById(R.id.todochk1);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent todo=new Intent(Tab_todo.this,todo_input.class);
                startActivity(todo);
            }
        });

//

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
/*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);

    }
*/


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.homez) {
            Intent ma=new Intent(this,MainActivity.class);
            startActivity(ma);

        }

        if (id == R.id.todo) {
            Intent t=new Intent(this,Tab_todo.class);
            startActivity(t);
        }
        if(id==R.id.clas)
        {
            Intent t=new Intent(this,Classes.class);
            startActivity(t);
        }
        if(id==R.id.hw)
        {
            Intent t=new Intent(this,assignment_main.class);
            startActivity(t);
        }

        if(id==R.id.project)
        {
            Intent t=new Intent(this,project_main.class);
            startActivity(t);
        }

        if(id==R.id.contacts)
        {

            Intent t=new Intent(this,Contacts.class);
            startActivity(t);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void sht(View v)
    {
        View parent=(View) v.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.txtid);
        TextView test=(TextView)parent.findViewById(R.id.txtTitleop);

       // test.setText(someDynamicString);
        test.setPaintFlags(test.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        test.setTextColor(getResources().getColor(R.color.colordone));

        Toast.makeText(this,"Well Done!",Toast.LENGTH_LONG).show();
        test.setEnabled(false);

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

    /**
     * A placeholder fragment containing a simple view.
     */
   //deleted fragmentplace holder here

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                   Tab_todo_timed ttd=new Tab_todo_timed();
                    return ttd;
                case 1:
                    Tab_todo_oportune tto=new Tab_todo_oportune();
                    return tto;
                case 2:
                    NextSevenDaysTsk nxt=new NextSevenDaysTsk();
                    return nxt;
                case 3:
                    next_4_weeks nt=new next_4_weeks();
                    return nt;

            }
            return null;
        }

        @Override
        public int getCount() {

            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "TODAY'S TASK";
                case 1:
                    return "TOMORROW'S TASK";
                case 2:
                    return "NEXT SEVEN DAYS";
                case 3:
                    return "NEXT 4 WEEKS";
            }
            return null;
        }


    }
}

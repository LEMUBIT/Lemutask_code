package lemuel.charles.charl.lemutask.Project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import lemuel.charles.charl.lemutask.Assignment.AssignmentMain;
import lemuel.charles.charl.lemutask.Contact.Contacts;
import lemuel.charles.charl.lemutask.Lecture.Classes;
import lemuel.charles.charl.lemutask.MainActivity;
import lemuel.charles.charl.lemutask.R;
import lemuel.charles.charl.lemutask.Tasks.TabTodo;

public class ProjectMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent t = new Intent(ProjectMain.this, Project.class);
                t.putExtra("edit", "no");//this means the intent call is not for edit
                startActivity(t);

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.homez) {
            Intent ma = new Intent(this, MainActivity.class);
            startActivity(ma);

        }

        if (id == R.id.todo) {
            Intent t = new Intent(this, TabTodo.class);
            startActivity(t);
        }
        if (id == R.id.clas) {
            Intent t = new Intent(this, Classes.class);
            startActivity(t);
        }
        if (id == R.id.hw) {
            Intent t = new Intent(this, AssignmentMain.class);
            startActivity(t);
        }

        if (id == R.id.project) {
            Intent t = new Intent(this, ProjectMain.class);
            startActivity(t);
        }

        if (id == R.id.contacts) {

            Intent t = new Intent(this, Contacts.class);
            startActivity(t);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}

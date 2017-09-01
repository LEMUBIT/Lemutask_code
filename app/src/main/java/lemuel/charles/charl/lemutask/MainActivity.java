package lemuel.charles.charl.lemutask;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int delete=1;


    /**This is the activity of the home page
     * Contains the floating button
     *
     *
     * **/


    //
    FloatingActionButton fabTodo,fabIdea,fabplus,fabAssignment;
    Animation fabOpen, fabClose,fabRotate, fabRotateAnti;
    boolean isOpen=false;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


///////////
        fabAssignment=(FloatingActionButton) findViewById(R.id.fabAssignment);
        fabTodo=(FloatingActionButton) findViewById(R.id.fabtodo);
        fabIdea=(FloatingActionButton) findViewById(R.id.fabidea);
        fabOpen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.floating_open);
        fabClose=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.floating_close);
        fabRotate=AnimationUtils.loadAnimation(getApplication(),R.anim.floating_rotate);
        fabRotateAnti=AnimationUtils.loadAnimation(getApplication(),R.anim.floating_rotate_anti);
        fabplus = (FloatingActionButton) findViewById(R.id.fab);

//////////
        //animate fab
        DisplayMetrics dmetric=new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(dmetric);
        fabplus.setTranslationX(-dmetric.heightPixels);

        DecelerateInterpolator interpolator=new DecelerateInterpolator();
        fabplus.animate().setInterpolator(interpolator)
                .setDuration(200)
                .setStartDelay(700)
                .translationXBy(dmetric.heightPixels)
                .start();



        ///



        fabplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen)
                {
                    fabTodo.startAnimation(fabClose);
                    fabIdea.startAnimation(fabClose);
                    fabAssignment.startAnimation(fabClose);
                    fabplus.startAnimation(fabRotateAnti);
                    fabAssignment.setClickable(false);
                    fabTodo.setClickable(false);
                    fabIdea.setClickable(false);
                    isOpen=false;

                }
                else
                {fabTodo.startAnimation(fabOpen);
                    fabAssignment.startAnimation(fabOpen);
                    fabIdea.startAnimation(fabOpen);
                    fabplus.startAnimation(fabRotate);
                    fabAssignment.setClickable(true);
                    fabTodo.setClickable(true);
                    fabIdea.setClickable(true);
                    isOpen=true;

                }


//// TODO: 30/04/2017 temporary snackbar test for call back and delete //.....1)
//                delete=1;
//                Snackbar b= Snackbar.make(view, "Replace with your own actions", Snackbar.LENGTH_LONG)
//                        .setAction("Action", new UndoDelete());
//
//                //this sets a call back so that if the user does not click undo it deletes
//                b.setCallback(new Snackbar.Callback() {
//                    @Override
//                    public void onDismissed(Snackbar snackbar, int event) {
//                        super.onDismissed(snackbar, event);
//                        if(delete==0)
//                            Toast.makeText(MainActivity.this, "Delete Undone", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                b.show();


            }
        });

        fabTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabTodo.startAnimation(fabClose);
                fabIdea.startAnimation(fabClose);
                fabAssignment.startAnimation(fabClose);
                fabplus.startAnimation(fabRotateAnti);
                fabAssignment.setClickable(false);
                fabTodo.setClickable(false);
                fabIdea.setClickable(false);
                isOpen=false;
                Intent inputtd=new Intent(MainActivity.this,todo_input.class);
                startActivity(inputtd);
            }
        });


        fabIdea.setOnClickListener(new View.OnClickListener() {// for goals
            @Override
            public void onClick(View v) {
                fabTodo.startAnimation(fabClose);
                fabIdea.startAnimation(fabClose);
                fabAssignment.startAnimation(fabClose);
                fabplus.startAnimation(fabRotateAnti);
                fabAssignment.setClickable(false);
                fabTodo.setClickable(false);
                fabIdea.setClickable(false);
                isOpen=false;
                Intent proj=new Intent(MainActivity.this,project.class);
                startActivity(proj);
            }
        });


        fabAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabTodo.startAnimation(fabClose);
                fabIdea.startAnimation(fabClose);
                fabAssignment.startAnimation(fabClose);
                fabplus.startAnimation(fabRotateAnti);
                fabAssignment.setClickable(false);
                fabTodo.setClickable(false);
                fabIdea.setClickable(false);
                isOpen=false;
                Intent inputhw=new Intent(MainActivity.this,assignment_add_input.class);
                startActivity(inputhw);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /////
        HomeFragment homef=new HomeFragment();
        FragmentManager fragmgr=getSupportFragmentManager();

        //(replaced, the replacer)
        fragmgr.beginTransaction().replace(R.id.content_main,homef,homef.getTag()).commit();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

        if (id == R.id.homez) {

            Intent ma=new Intent(this,MainActivity.class);
            startActivity(ma);

//            HomeFragment homef=new HomeFragment();
//            FragmentManager fragmgr=getSupportFragmentManager();
//
//            //(replaced, the replacer)
//            fragmgr.beginTransaction().replace(R.id.content_main,homef,homef.getTag()).commit();

        } //I folded the code block, expand to see the code

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
}

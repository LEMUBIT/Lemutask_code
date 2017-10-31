package lemuel.charles.charl.lemutask;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class Project_view extends AppCompatActivity {
    sql_helper_lemutask sqlht;
    String ititle, idetail, idate;
    String irole = "No team role";
    String itask = "No team task";
    String team;
    TextView title, detail, date, notify, role, task;
    SeekBar sk;
    String id;
    String day, month, year;
    String p;
    int ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_view_activity);

        //widgets
        title = (TextView) findViewById(R.id.pvtitle);
        detail = (TextView) findViewById(R.id.pvdetail);
        date = (TextView) findViewById(R.id.tvdeadline);
        notify = (TextView) findViewById(R.id.tvnotify);
        //
        role = (TextView) findViewById(R.id.pvrole);
        task = (TextView) findViewById(R.id.pvtask);
        //
        sk = (SeekBar) findViewById(R.id.pvseekbar);

        //percent=9;
        ///
        sqlht = new sql_helper_lemutask(this);
        id = getIntent().getStringExtra("projectid");
        Cursor pv_c = sqlht.getProjectview(id);

        while (pv_c.moveToNext()) {

            team = pv_c.getString(3);
            day = pv_c.getString(4);
            month = pv_c.getString(5);
            year = pv_c.getString(6);
            ititle = pv_c.getString(1);
            idetail = pv_c.getString(2);
            idate = day + "/" + month + "/" + year;
            sk.setProgress(Integer.parseInt(pv_c.getString(9)));

            irole = pv_c.getString(7);
            itask = pv_c.getString(8);

            title.setText("Title: " + ititle);
            notify.setText(pv_c.getString(9) + "%" + " complete");
            detail.setText("Detail: " + idetail);
            date.setText("Deadline: " + day + "/" + month + "/" + year);
            role.setText(irole);
            task.setText(itask);


        }
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //
                //  notify.setText(String.valueOf(progress)+"%"+" complete");
                //better to use integer than string for performance
                ip = progress;
                //p=String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //it is better here for performance
                sqlht.updateproject(id, String.valueOf(ip));
                notify.setText(String.valueOf(ip) + "%" + " complete");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.project_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                Intent i = new Intent(Project_view.this, project.class);
                //send it so that other activity does not need to query database
                i.putExtra("edit", "yes");
                i.putExtra("title", ititle);
                i.putExtra("detail", idetail);
                i.putExtra("date", idate);
                i.putExtra("day", day);
                i.putExtra("month", month);
                i.putExtra("year", year);
                i.putExtra("team", team);
                i.putExtra("role", irole);
                i.putExtra("task", itask);
                i.putExtra("id", id);
                startActivity(i);
                break;
            default:
                return super.onOptionsItemSelected(item);


        }
        return super.onOptionsItemSelected(item);

    }
}

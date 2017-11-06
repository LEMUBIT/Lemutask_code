package lemuel.charles.charl.lemutask.Project;
//////todo: this is the Project add activity

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import lemuel.charles.charl.lemutask.R;
import lemuel.charles.charl.lemutask.SQLite.SqlHelperLemutask;

public class Project extends AppCompatActivity {
    Calendar calender = Calendar.getInstance();
    Calendar calender_compare = Calendar.getInstance();//to compare time chosen
    TextView projdt;
    CheckBox ck;
    String team = "1";
    int pyear, pmonth, pday;
    //
    String editid = "";
    String editday = "";
    String editmonth = "";
    String edityear = "";

    //
    static final int sidproj = 12;
    EditText prodrole, projtask, ptitle, pdetail;
    SqlHelperLemutask sqlht;
    String is_edit = "noedit";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_add_activity);

        projdt = (TextView) findViewById(R.id.projDatetx);
        pyear = calender.get(Calendar.YEAR);
        pmonth = calender.get(Calendar.MONTH);//becasue January is 0
        pmonth += 1;
        pday = calender.get(Calendar.DAY_OF_MONTH);
        pday = pday + 1;//because it cannot be that day
        projdt.setText(pday + "/" + pmonth + "/" + pyear);

        sqlht = new SqlHelperLemutask(this);
        ptitle = (EditText) findViewById(R.id.projTitletx);
        pdetail = (EditText) findViewById(R.id.projDetailtx);
        prodrole = (EditText) findViewById(R.id.projRoletx);
        projtask = (EditText) findViewById(R.id.projTeamTasktx);
        ck = (CheckBox) findViewById(R.id.projChkbox);
        projdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(sidproj);
            }
        });


        ///////////////to check if the call was for edit or save

        try {
            is_edit = getIntent().getStringExtra("edit");

            if (is_edit == null)//it is if it is equal to null, not if content has null, this is where '==' is used
                is_edit = "noedit";


            if (is_edit.contentEquals("yes")) {
                ptitle.setText(getIntent().getStringExtra("title"));
                pdetail.setText(getIntent().getStringExtra("detail"));
                projdt.setText(getIntent().getStringExtra("date"));
                team = getIntent().getStringExtra("team");//original team status
                editid = getIntent().getStringExtra("id");
                editday = getIntent().getStringExtra("day");//todo::stopped here
                editmonth = getIntent().getStringExtra("month");
                edityear = getIntent().getStringExtra("year");
                projdt.setText(editday + "/" + editmonth + "/" + edityear);
                pday = Integer.parseInt(editday);
                pmonth = Integer.parseInt(editmonth);
                pyear = Integer.parseInt(edityear);

                if (team.contentEquals("1")) {

                    prodrole.setText(getIntent().getStringExtra("role"));
                    projtask.setText(getIntent().getStringExtra("task"));
                } else {
                    //  ck.setChecked(false);
                }

                team = "1";//after comparing, team should be set to 1 because the checkbox is already ticked signifying that a team can be added

            }
        } catch (Exception e) {

        }
        ///////////////
    }

    //
    private DatePickerDialog.OnDateSetListener datelistener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            pyear = year;
            pmonth = month + 1;//since month starts forom 0
            pday = dayOfMonth;
            projdt.setText(pday + "/" + pmonth + "/" + pyear);
            //  Toast.makeText(TodoInput.this,"This is the year "+pyear+" and the month "+pmonth+" and the date "+pdate,Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == sidproj) {
            return new DatePickerDialog(this, datelistener1, pyear, pmonth - 1, pday);
            //pmonth -1 so that when dialog is shown it is not +1 month ahead, pmonth us already incremented for user

        } else {
            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu_todo_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save:
                if (!is_edit.contentEquals("yes") && team.contentEquals("1"))//if it is not for editing then do this,... im not using no edit because it is like getting the extra is putting a null value in
                    addProjTeam();
                if (!is_edit.contentEquals("yes") && team.contentEquals("0"))//
                    addProjnoTeam();
                if (is_edit.contentEquals("yes") && team.contentEquals("1"))//
                    editp();
                if (is_edit.contentEquals("yes") && team.contentEquals("0"))//
                    editp_norole();
                break;
            case R.id.cancel:
                finish();
                Toast.makeText(Project.this, "Canceled", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);


        }
        return super.onOptionsItemSelected(item);

    }

    public void onProjChekclicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (!checked) {
            team = "0";
            prodrole.setEnabled(false);
            projtask.setEnabled(false);
        } else {
            team = "1";
            prodrole.setEnabled(true);
            projtask.setEnabled(true);
        }

    }


    public void editp() {
        boolean isins = sqlht.editproject(editid, ptitle.getText().toString(), pdetail.getText().toString(), "1", String.valueOf(pday), String.valueOf(pmonth), String.valueOf(pyear), prodrole.getText().toString(), projtask.getText().toString());
        if (isins) {
            Toast.makeText(Project.this, "Success", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(Project.this, "Not Saved", Toast.LENGTH_SHORT).show();

        }


    }

    //
    public void editp_norole() {
        boolean isins = sqlht.editproject(editid, ptitle.getText().toString(), pdetail.getText().toString(), "0", String.valueOf(pday), String.valueOf(pmonth), String.valueOf(pyear));
        if (isins) {
            Toast.makeText(Project.this, "Got it!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(Project.this, "Not Saved", Toast.LENGTH_SHORT).show();

        }


    }

    //
    public boolean addProjTeam() {
        calender.set(Calendar.DAY_OF_MONTH, pday);
        calender.set(Calendar.MONTH, pmonth - 1);//for the computer to understand because month starts form zero and I incremented it before
        calender.set(Calendar.YEAR, pyear);

        if (calender.getTimeInMillis() >= calender_compare.getTimeInMillis()) {
            boolean isins = sqlht.insertPRJdata(ptitle.getText().toString(), pdetail.getText().toString(), "1", prodrole.getText().toString(), projtask.getText().toString(), String.valueOf(pday), String.valueOf(pmonth), String.valueOf(pyear));
            if (isins) {
                Toast.makeText(Project.this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(Project.this, "Not Saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Time has already passed :(", Toast.LENGTH_LONG).show();
        }


        return true;
    }

    public boolean addProjnoTeam() {
        calender.set(Calendar.DAY_OF_MONTH, pday);
        calender.set(Calendar.MONTH, pmonth - 1);//for the computer to understand because month starts form zero and I incremented it before
        calender.set(Calendar.YEAR, pyear);

        if (calender.getTimeInMillis() >= calender_compare.getTimeInMillis()) {
            boolean isins = sqlht.insertPRJdata(ptitle.getText().toString(), pdetail.getText().toString(), "0", String.valueOf(pday), String.valueOf(pmonth), String.valueOf(pyear));
            if (isins) {
                Toast.makeText(Project.this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(Project.this, "Not Saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Time has already passed :(", Toast.LENGTH_LONG).show();
        }


        return true;
    }
}

package lemuel.charles.charl.lemutask;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
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

public class assignment_add_input extends AppCompatActivity implements to_do_time_picker.myOnCompleteListener {
    Calendar calender = Calendar.getInstance();
    Calendar calender_compare = Calendar.getInstance();//to compare time chosen
    TextView time, date;
    String istimed = "1";
    EditText wktitle, wkdetail;
    int pyear, pmonth, pday;
    CheckBox chk;
    sql_helper_lemutask sqlht;
    static final int sidhw = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_add_input);
        time = (TextView) findViewById(R.id.assignment_time_tx);
        date = (TextView) findViewById(R.id.assignment_date_tx);
        chk = (CheckBox) findViewById(R.id.assignment_checkbox_tx);
        wktitle = (EditText) findViewById(R.id.assignment_title_tx);
        wkdetail = (EditText) findViewById(R.id.assignment_detail_tx);
        sqlht = new sql_helper_lemutask(this);
        /////////////
        pyear = calender.get(Calendar.YEAR);
        pmonth = calender.get(Calendar.MONTH);//becasue January is 0
        pmonth += 1;
        pday = calender.get(Calendar.DAY_OF_MONTH);
        pday = pday + 1;//because it cannot be that day
        date.setText(pday + "/" + pmonth + "/" + pyear);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(sidhw);


            }
        });

    }

    private DatePickerDialog.OnDateSetListener datelistener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            pyear = year;
            pmonth = month + 1;//since month starts forom 0
            pday = dayOfMonth;
            date.setText(pday + "/" + pmonth + "/" + pyear);

        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == sidhw) {
            return new DatePickerDialog(this, datelistener1, pyear, pmonth - 1, pday);
            //pmonth -1 so that when dialog is shown it is not +1 month ahead, pmonth us already incremented for user

        } else {
            return null;
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (!checked) {
            istimed = "0";
            time.setEnabled(false);
            date.setEnabled(false);
        } else {
            istimed = "1";
            time.setEnabled(true);
            date.setEnabled(true);
        }

    }


    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new to_do_time_picker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


    @Override
    public void onComplete(String time, String orgtime) {
        this.time.setText(time);//use "this" because argument has a variable named time also
    }

    /////////////////////////////////////
    private DatePickerDialog.OnDateSetListener datelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            pyear = year;
            pmonth = month + 1;//since month starts forom 0
            pday = dayOfMonth;
            date.setText(pday + "/" + pmonth + "/" + pyear);

        }
    };


    /////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu_todo_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                if (istimed.equals("1"))//when comparing string do not use ==
                    adddata();
                if (istimed.equals("0"))
                    adddatatime();
                finish();
                break;
            case R.id.cancel:
                finish();
                Toast.makeText(assignment_add_input.this, "Canceled", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);


        }
        return super.onOptionsItemSelected(item);

    }

    //////////////////


    //////////////////////
    public boolean adddata() {


        int hour, hour2, minute, minute2;
        String getfnum = time.getText().toString();

        if (getfnum.length() == 7) {
            // x:xx AM  --character index starting from 0 which is x, second character : is 1 and second x is 2
            String fromt = String.valueOf(time.getText().toString().charAt(0));
            String fromtm = String.valueOf(time.getText().toString().charAt(2));
            String fromtm2 = String.valueOf(time.getText().toString().charAt(3));
            String ampm = String.valueOf(time.getText().toString().charAt(5));

            hour = Integer.parseInt(fromt);
            if (ampm.equals("P")) {
                hour = hour + 12;
            }
            minute = Integer.parseInt(fromtm);
            minute = minute * 10;
            minute2 = Integer.parseInt(fromtm2);
            minute = minute + minute2;
        } else {
            String fromt = String.valueOf(time.getText().toString().charAt(0));
            String fromt2 = String.valueOf(time.getText().toString().charAt(1));
            String fromtm = String.valueOf(time.getText().toString().charAt(3));
            String fromtm2 = String.valueOf(time.getText().toString().charAt(4));
            String ampm = String.valueOf(time.getText().toString().charAt(6));

            hour = Integer.parseInt(fromt);
            hour = hour * 10;
            hour2 = Integer.parseInt(fromt2);
            hour = hour + hour2;

            if (ampm.equals("P")) {
                hour = hour + 12;
            }
            minute = Integer.parseInt(fromtm);
            minute = minute * 10;
            minute2 = Integer.parseInt(fromtm2);
            minute = minute + minute2;
        }


        //setting calendar for pending intent
        calender.set(Calendar.DAY_OF_MONTH, pday);
        calender.set(Calendar.MONTH, pmonth - 1);//for the computer to understand because month starts
        // form zero and I incremented it before
        calender.set(Calendar.YEAR, pyear);
        calender.set(Calendar.HOUR_OF_DAY, hour);
        calender.set(Calendar.MINUTE, minute);

        if (calender.getTimeInMillis() >= calender_compare.getTimeInMillis()) {


            boolean isins = sqlht.insertAssignmentdata(wktitle.getText().toString(),
                    wkdetail.getText().toString(), istimed, time.getText().toString(),
                    String.valueOf(pday), String.valueOf(pmonth), String.valueOf(pyear));

            if (isins) {
                Toast.makeText(assignment_add_input.this, "Success" + hour + ":" + minute, Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(assignment_add_input.this, "Not Saved", Toast.LENGTH_SHORT).show();


            return true;
        } else {
            Toast.makeText(this, "Time has already passed :(", Toast.LENGTH_LONG).show();
            return false;
        }


    }
/////////////////////////

    public boolean adddatatime() {

        boolean isins = sqlht.insertAssignmentdata(wktitle.getText().toString(), wkdetail.getText().toString(), istimed);
        if (isins) {
            Toast.makeText(assignment_add_input.this, "Success saved", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(assignment_add_input.this, "Not Saved data", Toast.LENGTH_SHORT).show();


        return true;

    }


}

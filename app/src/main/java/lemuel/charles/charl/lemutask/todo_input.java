package lemuel.charles.charl.lemutask;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class todo_input extends AppCompatActivity implements to_do_time_picker.myOnCompleteListener,
        to_do_time_picker_to.myOnCompleteListener {

    //initialization

    //todo: check different times in reminder because it does not respond well sometimes
TextView from,to;//textview for the From time and the To time

    EditText description;
    AutoCompleteTextView title;
    sql_helper_lemutask sqlht;
    boolean checked=true;//for check box
    boolean test;//to test if insert was successful
    public String istimed = "1";

    /////////////datepicker

    TextView datebtn;
    int pyear, pmonth, pday;
    static final int sid = 11;
    ////////

    Calendar calender = Calendar.getInstance();
    Calendar calender_compare = Calendar.getInstance();
    PendingIntent pendingalarm;
    Intent alarmint;
    AlarmManager amgr;

    //
    String[] savedtitle = new String[10];//make a list
    int savedcounter = 0;
    //end of initialization


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_input);

        sqlht = new sql_helper_lemutask(this);

        //*****
        from = (TextView) findViewById(R.id.button);
        to = (TextView) findViewById(R.id.button2);
        title = (AutoCompleteTextView) findViewById(R.id.editText);
        description = (EditText) findViewById(R.id.editText2);

        amgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmint = new Intent(todo_input.this, Todo_alarm.class);


        //?????????????? cursor for text
        Cursor savedtitlec = sqlht.getsavedtasks();
        if (savedtitlec.moveToNext()) {
            savedtitlec.getString(0);
            savedcounter = 1;
        }


        //*****

//
        datebtn = (TextView) findViewById(R.id.textView11);
        showdiag();
        pyear = calender.get(Calendar.YEAR);
        pmonth = calender.get(Calendar.MONTH);//becasue January is 0
        pmonth += 1;
        pday = calender.get(Calendar.DAY_OF_MONTH);
        datebtn.setText(pday + "/" + pmonth + "/" + pyear);
        //

    }

    //////////////
    private DatePickerDialog.OnDateSetListener datelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            pyear = year;
            pmonth = month + 1;//since month starts forom 0
            pday = dayOfMonth;
            datebtn.setText(pday + "/" + pmonth + "/" + pyear);
            //  Toast.makeText(todo_input.this,"This is the year "+pyear+" and the month "+pmonth+" and the date "+pdate,Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == sid) {
            return new DatePickerDialog(this, datelistener, pyear, pmonth-1, pday);//pmonth-1 to give computer real month digit

        } else {
            return null;
        }
    }

    public void showdiag() {


        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(sid);


            }
        });
    }

/////////////

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
                if(checked)
                    test = adddata();
                if(!checked)
                    test=adddatanotime();
                if (test)
                    finish();
                break;
            case R.id.cancel:
                finish();
                Toast.makeText(todo_input.this, "Not Saved", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);


        }
        return super.onOptionsItemSelected(item);

    }

    public boolean adddata() {


        int hour, hour2, minute, minute2;
        String getfnum = from.getText().toString();

        if (getfnum.length() == 7) {
            // x:xx AM  --starting from 0
            String fromt = String.valueOf(from.getText().toString().charAt(0));
            String fromtm = String.valueOf(from.getText().toString().charAt(2));
            String fromtm2 = String.valueOf(from.getText().toString().charAt(3));
            String ampm = String.valueOf(from.getText().toString().charAt(5));

            hour = Integer.parseInt(fromt);
            if (ampm.equals("P")) {
                hour = hour + 12;
            }
            minute = Integer.parseInt(fromtm);
            minute = minute * 10;
            minute2 = Integer.parseInt(fromtm2);
            minute = minute + minute2;
        } else {
            String fromt = String.valueOf(from.getText().toString().charAt(0));
            String fromt2 = String.valueOf(from.getText().toString().charAt(1));
            String fromtm = String.valueOf(from.getText().toString().charAt(3));
            String fromtm2 = String.valueOf(from.getText().toString().charAt(4));
            String ampm = String.valueOf(from.getText().toString().charAt(6));

            hour = Integer.parseInt(fromt);
            hour = hour * 10;
            hour2 = Integer.parseInt(fromt2);
            hour = hour + hour2;

            if (ampm.equals("P") && hour != 12)//because 12PM does not need added 12 hours, in 24 hour format it is still 12PM
            {
                hour = hour + 12;
            }
            minute = Integer.parseInt(fromtm);
            minute = minute * 10;
            minute2 = Integer.parseInt(fromtm2);
            minute = minute + minute2;
        }


        //setting calendar for pending intent
        calender.set(Calendar.DAY_OF_MONTH, pday);
        calender.set(Calendar.MONTH, pmonth - 1);//for the computer to understand because month starts form zero and I incremented it before
        calender.set(Calendar.YEAR, pyear);
        calender.set(Calendar.HOUR_OF_DAY, hour);
        calender.set(Calendar.MINUTE, minute);

        if (calender.getTimeInMillis() >= calender_compare.getTimeInMillis()) {
//pending intent
            alarmint.putExtra("taskTitle", title.getText().toString());//************unused yet
            alarmint.putExtra("taskDescription", description.getText().toString());

            pendingalarm = PendingIntent.getBroadcast(todo_input.this, 0, alarmint, PendingIntent.FLAG_UPDATE_CURRENT);
            boolean isins = sqlht.insertTODOdata(title.getText().toString(), description.getText().toString(), from.getText().toString(), to.getText().toString(), istimed, String.valueOf(pday), String.valueOf(pmonth), String.valueOf(pyear));
            if (isins) {
                Toast.makeText(todo_input.this, "Success" + hour + ":" + minute, Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(todo_input.this, "Not Saved", Toast.LENGTH_SHORT).show();


            title.setText("");
            description.setText("");


            //alarm manager
            amgr.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), pendingalarm);
            return true;
        } else {
            Toast.makeText(this, "Time has already passed :(", Toast.LENGTH_LONG).show();
            return false;
        }


    }

    //////////////////////////////////////////
    public boolean adddatanotime()
    {




        //setting calendar for pending intent
        calender.set(Calendar.DAY_OF_MONTH, pday);
        calender.set(Calendar.MONTH, pmonth - 1);//for the computer to understand because month starts form zero and I incremented it before
        calender.set(Calendar.YEAR, pyear);
calender.set(Calendar.HOUR_OF_DAY,23);

        if (calender.getTimeInMillis() >= calender_compare.getTimeInMillis()) {


            boolean isins = sqlht.insertTODOdata(title.getText().toString(), description.getText().toString(), "--:--", " ", istimed, String.valueOf(pday), String.valueOf(pmonth), String.valueOf(pyear));
            if (isins) {
                Toast.makeText(todo_input.this, "Success", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(todo_input.this, "Not Saved", Toast.LENGTH_SHORT).show();


            title.setText("");
            description.setText("");


                      return true;
        }
        else {
            Toast.makeText(this, "Time has already passed :(", Toast.LENGTH_LONG).show();
            return false;
        }

    }


    //////////////////////////////////////////////

    //
    @Override
    public void onCompleteto(String time, String time2) {
        to.setText(time);
    }

    //implements onComplete form to_do_time_picker
    @Override
    public void onComplete(String time, String time2) {
        from.setText(time);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        checked = ((CheckBox) view).isChecked();

        if (!checked) {
            istimed = "0";
            from.setEnabled(false);
            to.setEnabled(false);
            checked=false;
        } else {
            istimed = "1";
            checked=true;
            from.setEnabled(true);
            to.setEnabled(true);
        }

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new to_do_time_picker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showTimePickerDialogto(View v) {
        DialogFragment newFragment = new to_do_time_picker_to();
        newFragment.show(getSupportFragmentManager(), "timePickerto");
    }

}

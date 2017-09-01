package lemuel.charles.charl.lemutask;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class classes_add extends AppCompatActivity implements to_do_time_picker.myOnCompleteListener,
        to_do_time_picker_to.myOnCompleteListener{
TextView from,to,classtitle,code,credit,venue;
    AutoCompleteTextView lecturer;
    Spinner day;
    String org_24_time="8";//original time in 24 hours to store in database
    sql_helper_lemutask sqlht;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classes_add_activity);

        from=(TextView) findViewById(R.id.startclass);
        to=(TextView) findViewById(R.id.endclass);
        classtitle=(TextView) findViewById(R.id.classTitleinput);
        code=(TextView) findViewById(R.id.codeInput);
        credit=(TextView) findViewById(R.id.creditUnitInput);
        venue=(TextView) findViewById(R.id.venuInput);
        day=(Spinner) findViewById(R.id.spinner);
        lecturer=(AutoCompleteTextView) findViewById(R.id.lecturer_inputtx);
        sqlht=new sql_helper_lemutask(this);
//Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Days, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    //to show time picker dialog for the starting and ending time of the classes
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new to_do_time_picker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showTimePickerDialogto(View v) {
        DialogFragment newFragment = new to_do_time_picker_to();
        newFragment.show(getSupportFragmentManager(), "timePickerto");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.menu_todo_add,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.save:
                boolean test=adddata();
                if(test)
                    finish();
                break;
            case R.id.cancel:
                finish();
                Toast.makeText(classes_add.this,"Not Saved",Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);


        }
        return super.onOptionsItemSelected(item);

    }


    public boolean adddata()
    {




            boolean isins = sqlht.insertCLASSdata(classtitle.getText().toString(),code.getText().toString(),credit.getText().toString(),venue.getText().toString(),lecturer.getText().toString(),from.getText().toString(),to.getText().toString(),org_24_time,day.getSelectedItem().toString());
            if(isins)
            {
                Toast.makeText(classes_add.this,"Success"+day.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(classes_add.this,"Not Saved",Toast.LENGTH_SHORT).show();


return true;


    }



    @Override
    public void onComplete(String time, String orgtime) {
        from.setText(time);
        org_24_time=orgtime;
    }

    @Override
    public void onCompleteto(String time, String orgtime) {
to.setText(time);
    }


}

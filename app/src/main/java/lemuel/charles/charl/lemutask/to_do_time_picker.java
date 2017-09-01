package lemuel.charles.charl.lemutask;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by charles on 03/02/2017.
 */

public class to_do_time_picker extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
public int hour,minute;


    public static  interface myOnCompleteListener
    {
        public abstract void onComplete(String time, String orgtime);
    }

    private myOnCompleteListener mlistener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            this.mlistener=(myOnCompleteListener)context;
        }
        catch (final ClassCastException e)
        {
            throw new ClassCastException(context.toString() + "Must implement myOnCompleteListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker


        final Calendar c = Calendar.getInstance();
         hour = c.get(Calendar.HOUR_OF_DAY);
         minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));


    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String thehour=String.valueOf(hourOfDay);
        String the_org_hour=thehour;
        String theminute=String.valueOf(minute);
        String AP_M="AM";
        //convert from 24-hour to 12-hour
        if(hourOfDay>12)
        {
            thehour=String.valueOf(hourOfDay-12);
            AP_M="PM";
        }
        if(hourOfDay==12)
        {

            AP_M="PM";
        }

        if(minute<10)
        {
            theminute="0"+String.valueOf(minute);
        }

        String time=thehour+":" +theminute+" "+AP_M;
        this.mlistener.onComplete(time,the_org_hour);
    }
}

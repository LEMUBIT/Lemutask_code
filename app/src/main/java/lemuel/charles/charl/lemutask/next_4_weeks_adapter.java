package lemuel.charles.charl.lemutask;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by charles on 04/07/2017.
 */

public class next_4_weeks_adapter extends CursorAdapter {

    public next_4_weeks_adapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.next_text, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title=(TextView) view.findViewById(R.id.txtTitleop);
        TextView task_date=(TextView) view.findViewById(R.id.task_date);
        TextView tid=(TextView) view.findViewById(R.id.txtid);
        TextView time=(TextView) view.findViewById(R.id.textView4);
        TextView timend=(TextView) view.findViewById(R.id.textView10);

        CheckBox chk=(CheckBox) view.findViewById(R.id.todochk1);

        String puttime=cursor.getString(cursor.getColumnIndexOrThrow("STIME"));
        String endtime=cursor.getString(cursor.getColumnIndex("ETIME"));
        String puttitle=cursor.getString(cursor.getColumnIndexOrThrow("TITLE"));
        String putdescription = cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPTION"));
        String putid=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String puisdone=cursor.getString(cursor.getColumnIndexOrThrow("ISDONE"));
        String taskD=cursor.getString(cursor.getColumnIndexOrThrow("TDATE"));


        if(puisdone.contentEquals("1"))
        {

            //    descr.setText(putdescription);
            time.setText(puttime);
            timend.setText(endtime);
            task_date.setText(taskD);
            chk.setChecked(true);
            title.setText(puttitle);
            title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);



            //   descr.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else
        {
            time.setText(puttime);
            task_date.setText(taskD);
            timend.setText(endtime);
            //   descr.setText(putdescription);
            title.setText(puttitle);
        }



        tid.setText(putid);
    }
}

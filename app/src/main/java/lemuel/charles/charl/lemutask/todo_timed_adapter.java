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
 * Created by charles on 03/02/2017.
 */


//todo_timed adapter here is for 'today's task fragment'

public class todo_timed_adapter extends CursorAdapter {
    View parent1;

    public todo_timed_adapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        parent1 = parent;

        return LayoutInflater.from(context).inflate(R.layout.today_text, parent, false);

    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = (TextView) view.findViewById(R.id.txtTitleop);
//        TextView descr=(TextView) view.findViewById(R.id.txtbody);
        CheckBox chk = (CheckBox) view.findViewById(R.id.todochk1);
        TextView time = (TextView) view.findViewById(R.id.textView4);
        TextView timend = (TextView) view.findViewById(R.id.textView10);
        TextView tid = (TextView) view.findViewById(R.id.txtid);
        //  TextView txdate=(TextView) view.findViewById(R.id.dateop);


        String puttitle = cursor.getString(cursor.getColumnIndexOrThrow("TITLE"));
        //     String putdescription = cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPTION"));
        String puttime = cursor.getString(cursor.getColumnIndexOrThrow("STIME"));
        String endtime = cursor.getString(cursor.getColumnIndex("ETIME"));
        String putid = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String puisdone = cursor.getString(cursor.getColumnIndexOrThrow("ISDONE"));
        String day = cursor.getString(cursor.getColumnIndexOrThrow("DAY"));
        String month = cursor.getString(cursor.getColumnIndexOrThrow("MONTH"));
        String year = cursor.getString(cursor.getColumnIndexOrThrow("YEAR"));

        String date = day + "/" + month + "/" + year;
        if (puisdone.contentEquals("1")) {
            time.setText(puttime);
            timend.setText(endtime);
            //       descr.setText(putdescription);
            //     txdate.setText(date);
            chk.setChecked(true);
            title.setText(puttitle);
            title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            // txdate.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            time.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            timend.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //     descr.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            //  txdate.setText(date);
            time.setText(puttime);
            timend.setText(endtime);
            //    descr.setText(putdescription);
            title.setText(puttitle);
        }


        tid.setText(putid);

    }


}

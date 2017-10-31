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
 * Created by charles on 11/02/2017.
 */

public class todo_oportune_adapter extends CursorAdapter {

    public todo_oportune_adapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.today_text, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = (TextView) view.findViewById(R.id.txtTitleop);
        //  TextView descr=(TextView) view.findViewById(R.id.txtbody);
        TextView tid = (TextView) view.findViewById(R.id.txtid);
        TextView time = (TextView) view.findViewById(R.id.textView4);
        TextView timend = (TextView) view.findViewById(R.id.textView10);

        CheckBox chk = (CheckBox) view.findViewById(R.id.todochk1);

        String puttime = cursor.getString(cursor.getColumnIndexOrThrow("STIME"));
        String endtime = cursor.getString(cursor.getColumnIndex("ETIME"));
        String puttitle = cursor.getString(cursor.getColumnIndexOrThrow("TITLE"));
        String putdescription = cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPTION"));
        String putid = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String puisdone = cursor.getString(cursor.getColumnIndexOrThrow("ISDONE"));


        if (puisdone.contentEquals("1")) {

            //    descr.setText(putdescription);
            time.setText(puttime);
            timend.setText(endtime);
            chk.setChecked(true);
            title.setText(puttitle);
            title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            //   descr.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            time.setText(puttime);
            timend.setText(endtime);
            //   descr.setText(putdescription);
            title.setText(puttitle);
        }


        tid.setText(putid);


    }
}

package lemuel.charles.charl.lemutask.Assignment;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

import lemuel.charles.charl.lemutask.R;

/**
 * Created by charles on 10/03/2017.
 */

public class AssignmentAdapter extends CursorAdapter {

    public AssignmentAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.assignment_text, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView worktitle = (TextView) view.findViewById(R.id.homeworktitle);
        CheckBox ckid = (CheckBox) view.findViewById(R.id.homework_checkBox);

        TextView workid = (TextView) view.findViewById(R.id.homworkid);


        String puttitle = cursor.getString(cursor.getColumnIndexOrThrow("TITLE"));
        String putid = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String puisdone = cursor.getString(cursor.getColumnIndexOrThrow("ISDONE"));
        String day = cursor.getString(cursor.getColumnIndexOrThrow("DAY"));
        String month = cursor.getString(cursor.getColumnIndexOrThrow("MONTH"));
        String year = cursor.getString(cursor.getColumnIndexOrThrow("YEAR"));
        String date = day + "/" + month + "/" + year;

        if (puisdone.contentEquals("1")) {


            // due.setText(date);
            // worktitle.setText(puttitle);
            ckid.setChecked(true);
            worktitle.setText("done");
            worktitle.setPaintFlags(worktitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            // due.setPaintFlags(due.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            //  due.setText(date);
            worktitle.setText(puttitle);
        }


        workid.setText(putid);

    }

}


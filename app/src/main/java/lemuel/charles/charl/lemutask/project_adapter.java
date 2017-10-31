package lemuel.charles.charl.lemutask;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by charles on 28/03/2017.
 */

public class project_adapter extends CursorAdapter {

    public project_adapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.project_text, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView prt = (TextView) view.findViewById(R.id.projtitle);
        TextView prper = (TextView) view.findViewById(R.id.projpercent);
        TextView prid = (TextView) view.findViewById(R.id.projid);

        String putid = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String puttitle = cursor.getString(cursor.getColumnIndexOrThrow("TITLE"));
        String percent = cursor.getString(cursor.getColumnIndexOrThrow("PERCENT"));


        prid.setText(putid);
        prt.setText(puttitle);
        prper.setText(percent + "% complete");

    }
}

package lemuel.charles.charl.lemutask;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by charles on 19/02/2017.
 */

public class classes_adapter extends CursorAdapter {

    public classes_adapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.classes_text_, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView classtitle=(TextView) view.findViewById(R.id.txclassTitlemain);
      //  TextView classbody=(TextView) view.findViewById(R.id.txclassbody);
        TextView classtime=(TextView) view.findViewById(R.id.txclassTIme);
        TextView classid=(TextView) view.findViewById(R.id.class_id);
        //////

        String class_id=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String ctitle=cursor.getString(cursor.getColumnIndexOrThrow("TITLE"));
        String code=cursor.getString(cursor.getColumnIndexOrThrow("CODE"));
        String credit=cursor.getString(cursor.getColumnIndexOrThrow("CREDIT"));
        String venue=cursor.getString(cursor.getColumnIndexOrThrow("VENUE"));
        String lecturer=cursor.getString(cursor.getColumnIndexOrThrow("LECTURERS"));
        String startime=cursor.getString(cursor.getColumnIndexOrThrow("SRTCLASSTIME"));
        String stoptime=cursor.getString(cursor.getColumnIndexOrThrow("STPCLASSTIME"));
        String orgtime=cursor.getString(cursor.getColumnIndexOrThrow("ORIGINALTIME"));
        String day=cursor.getString(cursor.getColumnIndexOrThrow("DAY_C"));


classid.setText(class_id);
        classtitle.setText(code);
      //  classbody.setText("Taught by "+"("+lecturer+")"+", credit unit is "+credit+" and holds at "+venue);
        classtime.setText(startime+" To "+stoptime);


    }
}

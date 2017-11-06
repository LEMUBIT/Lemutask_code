package lemuel.charles.charl.lemutask.Contact;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import lemuel.charles.charl.lemutask.R;

/**
 * Created by charles on 05/04/2017.
 */

public class ContactAdapter extends CursorAdapter {

    public ContactAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.contact_text, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView contact_name = (TextView) view.findViewById(R.id.contactname);
        TextView contact_id = (TextView) view.findViewById(R.id.contactid);

        String putcid = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String putcname = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
        String cnick = cursor.getString(cursor.getColumnIndexOrThrow("NICK"));

        contact_id.setText(putcid);

        contact_name.setText(putcname + " " + "(" + "'" + cnick + "'" + ")");
    }
}

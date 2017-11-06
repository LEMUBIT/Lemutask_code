package lemuel.charles.charl.lemutask.Contact;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import lemuel.charles.charl.lemutask.R;
import lemuel.charles.charl.lemutask.SQLite.SqlHelperLemutask;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactsFragment extends Fragment {
    SqlHelperLemutask sqlht;
    View view;
    public ListView lvItems;

    public ContactsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contacts, container, false);
        sqlht = new SqlHelperLemutask(view.getContext());
        lvItems = (ListView) view.findViewById(R.id.contactsList);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Cursor contactcursor = sqlht.getContact();
        if (contactcursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {


            Context c = view.getContext();
            ContactAdapter todoAdapter = new ContactAdapter(c, contactcursor, true);
            lvItems.setAdapter(todoAdapter);

        }

    }
}

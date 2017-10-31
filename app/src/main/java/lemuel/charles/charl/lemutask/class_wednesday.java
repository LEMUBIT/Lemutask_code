package lemuel.charles.charl.lemutask;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by charles on 19/02/2017.
 */

public class class_wednesday extends Fragment {
    View view;
    sql_helper_lemutask sqlht;
    public ListView lvItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classes_wednesday_fragment, container, false);
        sqlht = new sql_helper_lemutask(getActivity());
        lvItems = (ListView) view.findViewById(R.id.wednesdaylist);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Query for items from the database and get a cursor back
        Cursor todoCursor = sqlht.getwednesdayclass();
        if (todoCursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {
            Context c = view.getContext();
            classes_adapter todoAdapter = new classes_adapter(c, todoCursor, true);
            lvItems.setAdapter(todoAdapter);

        }


    }
}

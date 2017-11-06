package lemuel.charles.charl.lemutask.Lecture;

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

import lemuel.charles.charl.lemutask.R;
import lemuel.charles.charl.lemutask.SQLite.SqlHelperLemutask;

/**
 * Created by charles on 20/02/2017.
 */

public class ClassSaturday extends Fragment {
    View view;
    SqlHelperLemutask sqlht;
    public ListView lvItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classes_saturday_fragment, container, false);
        sqlht = new SqlHelperLemutask(getActivity());
        lvItems = (ListView) view.findViewById(R.id.saturdaylist);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Query for items from the database and get a cursor back
        Cursor todoCursor = sqlht.getssaturdayclass();

        if (todoCursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {
            Context c = view.getContext();
            ClassesAdapter todoAdapter = new ClassesAdapter(c, todoCursor, true);
            lvItems.setAdapter(todoAdapter);

        }

    }
}

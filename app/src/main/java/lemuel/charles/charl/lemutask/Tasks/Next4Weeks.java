package lemuel.charles.charl.lemutask.Tasks;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import lemuel.charles.charl.lemutask.R;
import lemuel.charles.charl.lemutask.SQLite.SqlHelperLemutask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Next4Weeks extends Fragment {
    View rootView;
    public ListView next4list;

    public Next4Weeks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_next_4_weeks, container, false);
        next4list = (ListView) rootView.findViewById(R.id.next4list);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        SqlHelperLemutask sqlh = new SqlHelperLemutask(rootView.getContext());

        Cursor todoCursor = sqlh.getnext4weeks();
        if (todoCursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {


            Context c = rootView.getContext();
            Next4WeeksAdapter todoAdapter = new Next4WeeksAdapter(c, todoCursor, true);
            next4list.setAdapter(todoAdapter);
        }


    }
}

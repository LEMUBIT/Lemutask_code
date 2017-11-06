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
public class NextSevenDaysTsk extends Fragment {
    View rootView;
    public ListView lvItemsnxt;

    public NextSevenDaysTsk() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_next_seven_days_tsk, container, false);
        lvItemsnxt = (ListView) rootView.findViewById(R.id.nextsevenlist);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        SqlHelperLemutask sqlh = new SqlHelperLemutask(rootView.getContext());
        // SQLiteDatabase db = sqlh.getWritableDatabase();
        // Query for items from the database and get a cursor back
        Cursor todoCursor = sqlh.getnextsevendays();
        if (todoCursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {


            Context c = rootView.getContext();
            NextSevenDaysAdapter todoAdapter = new NextSevenDaysAdapter(c, todoCursor, true);
            lvItemsnxt.setAdapter(todoAdapter);
        }
    }
}

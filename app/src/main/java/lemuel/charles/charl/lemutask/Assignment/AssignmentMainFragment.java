package lemuel.charles.charl.lemutask.Assignment;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import lemuel.charles.charl.lemutask.R;
import lemuel.charles.charl.lemutask.SQLite.SqlHelperLemutask;

/**
 * A placeholder fragment containing a simple view.
 */
public class AssignmentMainFragment extends Fragment {
    View view;
    public ListView lvItems;
    SqlHelperLemutask sqlht;
    CheckBox hwchk;
    TextView hwtx, hwid;


    public AssignmentMainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.assignment_main_fragment, container, false);
        lvItems = (ListView) view.findViewById(R.id.assignment_list);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hwchk = (CheckBox) view.findViewById(R.id.homework_checkBox);
                hwtx = (TextView) view.findViewById(R.id.homeworktitle);
                hwid = (TextView) view.findViewById(R.id.homworkid);
                String idhw = hwid.getText().toString();


                boolean checked = hwchk.isChecked();
                SqlHelperLemutask sqlhck = new SqlHelperLemutask(view.getContext());
                if (!checked) {

                    hwchk.setChecked(true);
                    sqlhck.updateAssignment(idhw);
                    sqlhck.close();
                } else {
                    hwchk.setChecked(false);
                    sqlhck.updateAssignmentundone(idhw);
                    sqlhck.close();
                }


            }
        });


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        SqlHelperLemutask sqlh = new SqlHelperLemutask(view.getContext());
        // SQLiteDatabase db = sqlh.getWritableDatabase();
        // Query for items from the database and get a cursor back
        Cursor todoCursor = sqlh.getAssignment();
        if (todoCursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {


            Context c = view.getContext();
            AssignmentAdapter todoAdapter = new AssignmentAdapter(c, todoCursor, true);
            lvItems.setAdapter(todoAdapter);

        }


        sqlh.close();

    }
}

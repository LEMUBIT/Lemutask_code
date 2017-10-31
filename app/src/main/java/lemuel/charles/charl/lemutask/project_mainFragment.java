package lemuel.charles.charl.lemutask;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class project_mainFragment extends Fragment {
    View view;
    public ListView lvItems;
    TextView project_id;
    sql_helper_lemutask sqlht;


    public project_mainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project_main, container, false);
        lvItems = (ListView) view.findViewById(R.id.projlist);
        sqlht = new sql_helper_lemutask(view.getContext());

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                project_id = (TextView) view.findViewById(R.id.projid);
                String pid = project_id.getText().toString();

                Intent i = new Intent(getActivity(), Project_view.class);
                i.putExtra("projectid", pid);
                startActivity(i);
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();


        // SQLiteDatabase db = sqlh.getWritableDatabase();
        // Query for items from the database and get a cursor back
        Cursor projectcursor = sqlht.getProject();
        if (projectcursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {


            Context c = view.getContext();
            project_adapter todoAdapter = new project_adapter(c, projectcursor, true);
            lvItems.setAdapter(todoAdapter);

        }
    }
}

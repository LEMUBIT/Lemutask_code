package lemuel.charles.charl.lemutask;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by charles on 19/02/2017.
 */

public class class_monday extends Fragment {
    View view;
    sql_helper_lemutask sqlht;
    public ListView lvItems3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classes_monday_fragment, container, false);


        lvItems3 = (ListView) view.findViewById(R.id.mondaylist);
        lvItems3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.txclassTIme);

                title.setText("done");
            }
        });
        //register listview for context menu
        registerForContextMenu(lvItems3);
        sqlht = new sql_helper_lemutask(getActivity());
        return view;
    }


    //todo: seems widgets are preventing user from touching listview, when tested with class_text_testing, it works
    //todo:also clickable affects it, if its clickable then ...
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menulist:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                View targetView = (View) info.targetView;
                String viewId = (String) ((TextView) targetView.findViewById(R.id.class_id)).getText();
                int test = sqlht.deleteclass(viewId);
                Toast.makeText(getActivity(), "Id=" + viewId + "-" + test, Toast.LENGTH_SHORT).show();

                return true;
        }
        return super.onContextItemSelected(item);

    }


    @Override
    public void onResume() {
        super.onResume();

        // Query for items from the database and get a cursor back
        Cursor todoCursor = sqlht.getmondayclasss();

        if (todoCursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {

            //Log.i("Message","There is data "+ todoCursor.getColumnCount()+todoCursor.getColumnName(0)+todoCursor.getColumnIndexOrThrow("_id"));
// Setup cursor adapter using cursor from last step
            Context c = view.getContext();
            classes_adapter todoAdapter = new classes_adapter(c, todoCursor, true);
            lvItems3.setAdapter(todoAdapter);

        }
    }
}

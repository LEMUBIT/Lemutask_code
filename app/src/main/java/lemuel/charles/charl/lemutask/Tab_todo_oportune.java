package lemuel.charles.charl.lemutask;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by charles on 27/01/2017.
 */

public class Tab_todo_oportune extends Fragment {
    View rootView;
    public ListView lvItems2;
    sql_helper_lemutask sqlht;
    TextView txop;
    TextView opid;
    CheckBox chkop;
    //calendar to get the days date
    Calendar calender = Calendar.getInstance();
    int pyear, pmonth, pday;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab_todo_oportune, container, false);
        lvItems2 = (ListView) rootView.findViewById(R.id.oportunelst);
        registerForContextMenu(lvItems2);
        sqlht = new sql_helper_lemutask(getActivity());
//

        pyear = calender.get(Calendar.YEAR);
        pmonth = calender.get(Calendar.MONTH);//January is 0
        pday = calender.get(Calendar.DAY_OF_MONTH);
        //
        lvItems2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chkop = (CheckBox) view.findViewById(R.id.checkBoxop);
                txop = (TextView) view.findViewById(R.id.txtTitleop);
                opid = (TextView) view.findViewById(R.id.txtid);
                boolean checked = chkop.isChecked();

                if (!checked) {
                    chkop.setChecked(true);
                    sqlht.updatetodo(opid.getText().toString());
                    sqlht.close();
                } else {
                    chkop.setChecked(false);
                    sqlht.updatetodoundone(opid.getText().toString());
                    sqlht.close();
                }

            }
        });


        //set onclick listener for the widgets in the list views
     /*   lvItems2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txid= (TextView) view.findViewById(R.id.txtid);
                String txd=txid.getText().toString();
                TextView txtitle= (TextView) view.findViewById(R.id.txtTitle);
                txtitle.setText(txd);
                return true;
            }
        });*/
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        sql_helper_lemutask sqlh = new sql_helper_lemutask(rootView.getContext());
        // SQLiteDatabase db = sqlh.getWritableDatabase();
// Query for items from the database and get a cursor back
        Cursor todoCursor = sqlh.getnextdayTask(pday, pmonth, pyear);
        if (todoCursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {

            Log.i("Message", "There is data " + todoCursor.getColumnCount()
                    + todoCursor.getColumnName(0) + todoCursor.getColumnIndexOrThrow("_id"));
// Setup cursor adapter using cursor from last step
            Context c = rootView.getContext();
            todo_oportune_adapter todoAdapter = new todo_oportune_adapter(c, todoCursor, true);
            lvItems2.setAdapter(todoAdapter);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //  menu.add(Menu.NONE, R.id.menulist, Menu.NONE, "Menu A");
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menulist:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                View targetView = (View) info.targetView;
                String viewId = (String) ((TextView) targetView.findViewById(R.id.txtid)).getText();
                int test = sqlht.deletetask(viewId);
                Toast.makeText(getActivity(), "Id=" + viewId + "-" + test, Toast.LENGTH_SHORT).show();

                return true;
        }
        return super.onContextItemSelected(item);

    }


}

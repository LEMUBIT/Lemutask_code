package lemuel.charles.charl.lemutask;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.MenuItem;
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

public class Tab_todo_timed extends Fragment {
    View view;
    public ListView lvItems;
    sql_helper_lemutask sqlht;
    CheckBox chkTimed;
    TextView tid;
    TextView title;
    //calendar to get the days date
    Calendar calender = Calendar.getInstance();
    int pyear, pmonth, pday;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        pyear = calender.get(Calendar.YEAR);
        pmonth = calender.get(Calendar.MONTH);//becasue January is 0
        pmonth += 1;
        pday = calender.get(Calendar.DAY_OF_MONTH);


        view = inflater.inflate(R.layout.fragment_tab_todo_timed, container, false);
        title = (TextView) view.findViewById(R.id.txtTitleop);
        lvItems = (ListView) view.findViewById(R.id.timedlist);
        //  ImageView tv=(ImageView) view.findViewById(R.id.noteicimage);

        //register listview for context menu
        registerForContextMenu(lvItems);
        //lvItems.setEmptyView();//<<-used to set the empty view in listview after declaring it in XML
        sqlht = new sql_helper_lemutask(getActivity());
        //set onclick listener for the widgets in the list views


        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  TextView title= (TextView) view.findViewById(R.id.txtTitle);
                //todo: update database and set is done to 1
                //title.setText("done");

                chkTimed = (CheckBox) view.findViewById(R.id.todochk1);
                tid = (TextView) view.findViewById(R.id.txtid);

                boolean checked = chkTimed.isChecked();

                if (!checked) {
                    chkTimed.setChecked(true);
                    sqlht.updatetodo(tid.getText().toString());
                    sqlht.close();
                } else {
                    chkTimed.setChecked(false);
                    sqlht.updatetodoundone(tid.getText().toString());
                    sqlht.close();
                }


            }
        });


        //todo learn from code below how to access view form listview
      /*  lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txid= (TextView) view.findViewById(R.id.txtid);
                String txd=txid.getText().toString();
                TextView txtitle= (TextView) view.findViewById(R.id.txtTitle);
                txtitle.setText(txd);
                return true;
            }
        });*/
        return view;
    }


    public void checktodo(View v) {
        boolean checked = ((CheckBox) view).isChecked();


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

    @Override
    public void onResume() {
        super.onResume();

        sql_helper_lemutask sqlh = new sql_helper_lemutask(view.getContext());
        // SQLiteDatabase db = sqlh.getWritableDatabase();
// Query for items from the database and get a cursor back
        Cursor todoCursor = sqlh.getTODOdata(pday, pmonth, pyear);//pmonth is
        // incremented because in the sql helper class it is not incremented, unlike the function 'getcurrentdayTask()'
        if (todoCursor.getCount() == 0) {
            Log.i("Message", "No data");
            return;
        } else {

            Log.i("Message", "There is data " + todoCursor.getColumnCount() +
                    todoCursor.getColumnName(0) + todoCursor.getColumnIndexOrThrow("_id"));
// Setup cursor adapter using cursor from last step
            Context c = view.getContext();
            todo_timed_adapter todoAdapter = new todo_timed_adapter(c, todoCursor, true);
            lvItems.setAdapter(todoAdapter);

        }

    }
}

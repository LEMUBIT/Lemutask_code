package lemuel.charles.charl.lemutask;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class next_4_weeks extends Fragment {
    View rootView;
    public ListView next4list;

    public next_4_weeks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_next_4_weeks, container, false);
        next4list = (ListView) rootView.findViewById(R.id.next4list);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        sql_helper_lemutask sqlh=new sql_helper_lemutask(rootView.getContext());

        Cursor todoCursor = sqlh.getnext4weeks();
        if(todoCursor.getCount()==0)
        {
            Log.i("Message","No data");
            return;
        }
        else{


            Context c=rootView.getContext();
            next_4_weeks_adapter todoAdapter = new next_4_weeks_adapter(c, todoCursor,true);
            next4list.setAdapter(todoAdapter);
        }


    }
}

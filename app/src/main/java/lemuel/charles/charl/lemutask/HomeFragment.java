package lemuel.charles.charl.lemutask;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    View view;
    sql_helper_lemutask sqlht;
    Button today_tsk,tomorrow_tsk,homew,proj;
   // TextView today,tommorow,hw,proj;

    /**
     * LEMUBIT: This is the home fragment, which contains the summarized information
     *
     * **/


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //the sql_helper class object used to access the database objects
        sqlht=new sql_helper_lemutask(getActivity());

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        today_tsk=(Button) view.findViewById(R.id.today_tsk);
        tomorrow_tsk=(Button) view.findViewById(R.id.tommorow_tsk);
        homew=(Button) view.findViewById(R.id.due_hw_btn);
        proj=(Button) view.findViewById(R.id.project_btn);
        //************************************************Begining of animations

        //today task animation##############
        DisplayMetrics dmetric=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dmetric);
        today_tsk.setTranslationY(dmetric.heightPixels);

        DecelerateInterpolator interpolator=new DecelerateInterpolator();
        today_tsk.animate().setInterpolator(interpolator)
                .setDuration(300)
                .setStartDelay(300)
                .translationYBy(-dmetric.heightPixels)
                .start();
        //end today task animation##############

        //tomorrow task animation##############

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dmetric);
        tomorrow_tsk.setTranslationY(dmetric.heightPixels);
        tomorrow_tsk.animate().setInterpolator(interpolator)
                .setDuration(300)
                .setStartDelay(350)
                .translationYBy(-dmetric.heightPixels)
                .start();
        //end tomorrow task animation##############

        //project task animation##############

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dmetric);
        proj.setTranslationY(dmetric.heightPixels);
        proj.animate().setInterpolator(interpolator)
                .setDuration(400)
                .setStartDelay(400)
                .translationYBy(-dmetric.heightPixels)
                .start();
        //end project task animation##############

        //home work task animation##############

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dmetric);
        homew.setTranslationY(dmetric.heightPixels);
        homew.animate().setInterpolator(interpolator)
                .setDuration(400)
                .setStartDelay(450)
                .translationYBy(-dmetric.heightPixels)
                .start();
        //end homw work task animation##############
        //************************************************Ending  of animations
        return view;


    }


    @Override
    public void onResume() {
        // Activity activity=(Activity) getContext();//can use this if not using getActivity().getwin.... instead it will be activity.getwin...()
        super.onResume();
        //

        int day_oftask = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month_oftask = Calendar.getInstance().get(Calendar.MONTH);
        int year_oftask = Calendar.getInstance().get(Calendar.YEAR);

        //day of month working
        //month starts from 0//pmonth starts from 1
        //year working


        Cursor todoCursor = sqlht.getcurrentdayTask(day_oftask,month_oftask,year_oftask);
        int x=todoCursor.getCount();
//the x gets the number of tasks not done for the day


        //if there is no task
        if(x==0)
            today_tsk.setText("NO TASK");

        //if there is only one task, say TASK instead of TASKS
        if(x==1)
            today_tsk.setText(String.valueOf(x)+" TASK");

        //if there is only one task, say TASKS
        if(x>1)
            today_tsk.setText(String.valueOf(x)+" TASKS");
////////////////////////////////////////////////////////////////////////

        Cursor todoCursor2=sqlht.getnextdayTask(day_oftask,month_oftask,year_oftask);
        int tx=todoCursor2.getCount();
        if(tx==0)
            tomorrow_tsk.setText("NO TASK");

        if(tx==1)
            tomorrow_tsk.setText(String.valueOf(tx)+" TASK");

        if(tx>1)
            tomorrow_tsk.setText(String.valueOf(tx)+" TASKS");

////////////////////////////////////////////////////////////////////////////
        Cursor todoCursor3=sqlht.getundoneassignment();
        int txx=todoCursor3.getCount();
        if(txx==0)
            homew.setText("NONE");


        if(txx>0)
            homew.setText(String.valueOf(txx)+" UNDONE");
///////////////////////////////////////////////////////////

        Cursor todoCursor4=sqlht.getundoneproject();
        int txxx=todoCursor4.getCount();
        if(txxx==0)
            proj.setText("NONE");

        if(txxx>0)
            proj.setText(String.valueOf(txxx)+" UNDONE");


    }
}

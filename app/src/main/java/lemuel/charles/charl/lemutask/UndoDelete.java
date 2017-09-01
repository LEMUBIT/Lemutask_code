package lemuel.charles.charl.lemutask;

import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by charles on 30/04/2017.
 */

public class UndoDelete  implements View.OnClickListener{



    @Override
    public void onClick(View v) {


        //if undo is clicked then delete should be 0 meaning do no delete
        MainActivity.delete=0;

        Random r=new Random();

        if(r.nextInt(10)==5)
            Toast.makeText(v.getContext(), "Oops already deleted....Joking!", Toast.LENGTH_SHORT).show();

        if(r.nextInt(20)==10)
            Toast.makeText(v.getContext(), "That was close!", Toast.LENGTH_SHORT).show();

        if(r.nextInt(20)==15)
            Toast.makeText(v.getContext(), "Be careful!", Toast.LENGTH_SHORT).show();

    }
}

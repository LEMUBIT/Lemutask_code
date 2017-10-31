package lemuel.charles.charl.lemutask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by charles on 08/02/2017.
 */

public class Todo_alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Hey", "Receiver");

        Bundle b = intent.getExtras();
        String title = b.getString("taskTitle");
        String description = b.getString("taskDescription");
        if (title != null) {
            Log.i("Broad", title);
        } else {
            Log.i("Broad", "its a null");
        }
        //intent to ringtone service
        Intent service_intent = new Intent(context, ringservice.class);
        service_intent.putExtra("taskTitle", title);
        service_intent.putExtra("taskDescription", description);

        //start ringtone
        context.startService(service_intent);

    }
}

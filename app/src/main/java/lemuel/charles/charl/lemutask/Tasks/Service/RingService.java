package lemuel.charles.charl.lemutask.Tasks.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import lemuel.charles.charl.lemutask.R;
import lemuel.charles.charl.lemutask.SQLite.SqlHelperLemutask;
import lemuel.charles.charl.lemutask.Tasks.TabTodo;

/**
 * Created by charles on 08/02/2017.
 */
public class RingService extends Service {

    int numMessages;
    public String title, description;
    private NotificationManager notificatn;
    SqlHelperLemutask sqlht;
    Cursor idGetter;

    //The unique code here is to give the notifications a unique ID if not they will be overriding each time the user
    //sets a new task, so I used the ID of the latest taks before the new task and incremented it by one
    // (which will be equal to the main ID of the new task already) and used it as the id of the notification of the new task
    int uniqueCode = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */

    @Override
    public void onCreate() {
        notificatn = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        sqlht = new SqlHelperLemutask(getApplicationContext());
        // Display a notification about us starting.  We put an icon in the status bar.

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        //instance of media player
        idGetter = sqlht.getlatestId();
        String maxId_str = "";
        if (idGetter != null && idGetter.moveToFirst()) {
            maxId_str = idGetter.getString(idGetter.getColumnIndexOrThrow("_id"));
            uniqueCode = Integer.valueOf(maxId_str);
            uniqueCode = uniqueCode + 1;
        }
        Bundle b = intent.getExtras();
        title = b.getString("taskTitle");
        description = b.getString("taskDescription");

        Log.i("max=", maxId_str);
        Log.i("ring", title);
        showNotification();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        notificatn.cancel(0);

        // Tell the user we stopped.
        Toast.makeText(this, "On destroy called", Toast.LENGTH_SHORT).show();
    }

    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = title;
        CharSequence decr = description;
//Log.i("Notification",title);
        //the application to open
        Intent task = new Intent(this.getApplicationContext(), TabTodo.class);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, uniqueCode,
                task, 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker("Lemutask To_do")  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("Hey dear, you have a task: " + "'" + text + "'")  // the label of the entry
                .setContentText(decr)  // the contents of the entry
                .setContentIntent(contentIntent)// The intent to send when the entry is clicked
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .build();
        numMessages = 0;

        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        String[] events = new String[6];
// Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Event tracker details:");

// Moves events into the expanded layout
        for (int i = 0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }


        // Send the notification.
        notificatn.notify(uniqueCode, notification);
    }

}

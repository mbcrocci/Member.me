package pt.isec.gps1718_g15.memberme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;


public class AlarmReceiver extends BroadcastReceiver {

    Evento evento;

    @Override
    public void onReceive(Context context, Intent intent) {
        evento = (Evento) intent.getExtras().get("evento");

        generateNotification(context, (int) System.currentTimeMillis());
    }

    private  void generateNotification(Context context, int id) {

        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent notification = PendingIntent.getActivity(context, id, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentTitle("Member.Me");
        builder.setTicker(evento.getName());
        builder.setContentText(evento.toString());

        builder.setContentIntent(notification);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, builder.build());
    }
}

package pt.isec.gps1718_g15.memberme;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;


public class popupwindow extends Activity {

    TimePicker timePicker;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        Button btnAddConfirmar = findViewById(R.id.btnAddConfirmar);

        btnAddConfirmar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText vName = findViewById(R.id.edTitulo);
                    String name = vName.getText().toString();

                    TimePicker startTimePciker = findViewById(R.id.startTimePickerAdd);
                    TimePicker endTimePicker = findViewById(R.id.endTimePickerAdd);
                    DatePicker datePicker = findViewById(R.id.datePickerAdd);

                    CheckBox despertador = findViewById(R.id.checkboxDespertadorAdd);
                    CheckBox naoMeChateies = findViewById(R.id.checkboxNaoMeChateiesAdd);
                    CheckBox repetir = findViewById(R.id.checkboxnRepetirAdd);


                    Evento evento = new Evento(
                            name,
                            startTimePciker,
                            endTimePicker,
                            datePicker,
                            despertador.isChecked(),
                            naoMeChateies.isChecked(),
                            repetir.isChecked()
                    );

                    ArrayList<Evento> listaEventos = getIntent().getExtras().getParcelableArrayList("lista-eventos");

                    listaEventos.add(evento);

                    Log.i("PopupWindowsConfirm", evento.toString());

                    MainActivity.saveListaEventoToDisk(listaEventos, popupwindow.this);


                    // Criar a notificacao
                    if ( despertador.isChecked() ) {
                        Intent alarmIntent = new Intent(popupwindow.this, AlarmReceiver.class);
                        alarmIntent.putExtra("evento", evento);

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                popupwindow.this, 0, alarmIntent, 0);

                        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());

                        if (evento.getMinStart() - 30 > 0) {
                            calendar.set(Calendar.HOUR_OF_DAY, evento.getHourStart());
                            calendar.set(Calendar.MINUTE, evento.getMinStart() - 30);
                        } else {
                            calendar.set(Calendar.HOUR_OF_DAY, evento.getHourStart() - 1);
                            calendar.set(Calendar.MINUTE, 30 - (evento.getMinStart() - 30));
                        }
                        calendar.set(Calendar.DAY_OF_MONTH, evento.getDayStart());
                        calendar.set(Calendar.MONTH, evento.getMonthStart());
                        calendar.set(Calendar.YEAR, evento.getYearStart());

                        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    }

                    Intent intent = new Intent(popupwindow.this, MainActivity.class).
                            putParcelableArrayListExtra("lista_eventos", listaEventos);

                    startActivity(intent);

                }
            }
        );




    }

    public void cancelEvent(View v)
    {
        startActivity(new Intent(popupwindow.this, MainActivity.class));
    }
}


package pt.isec.gps1718_g15.memberme;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;


public class popupwindow extends Activity {

    Boolean editing;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        editing = getIntent().getExtras().getBoolean("Editing");

        if ( editing ) {
            try {
                pos = (int) getIntent().getExtras().get("pos");
                Evento evento = MainActivity.readListaEventoFromDisk(this).get(pos);

                setTitle("Modificar Evento");

                EditText vName = findViewById(R.id.edTitulo);
                vName.setText(evento.getName());

                TimePicker startTimePicker = findViewById(R.id.startTimePickerAdd);
                startTimePicker.setCurrentHour( evento.getHourStart() );
                startTimePicker.setCurrentMinute( evento.getMinStart() );

                TimePicker endTimePicker = findViewById(R.id.endTimePickerAdd);
                endTimePicker.setCurrentHour( evento.getHourEnd() );
                endTimePicker.setCurrentMinute( evento.getMinEnd() );

                DatePicker datePicker = findViewById(R.id.datePickerAdd);
                datePicker.updateDate(evento.getYearStart(), evento.getMonthStart()-1, evento.getDayStart());

                CheckBox despertador = findViewById(R.id.checkboxDespertadorAdd);
                despertador.setChecked( evento.getDespertador() );

                CheckBox naoMeChateies = findViewById(R.id.checkboxNaoMeChateiesAdd);
                naoMeChateies.setChecked( evento.getNaoMeChateies() );

                CheckBox repetir = findViewById(R.id.checkboxnRepetirAdd);
                repetir.setChecked( evento.getRepetirEvento() );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelEvent(View v) {
        startActivity(new Intent(popupwindow.this, MainActivity.class));
    }

    public void confimarEvento(View v) {
        EditText vName = findViewById(R.id.edTitulo);
        String name = vName.getText().toString();

        TimePicker startTimePicker = findViewById(R.id.startTimePickerAdd);
        TimePicker endTimePicker = findViewById(R.id.endTimePickerAdd);
        DatePicker datePicker = findViewById(R.id.datePickerAdd);

        CheckBox despertador = findViewById(R.id.checkboxDespertadorAdd);
        CheckBox naoMeChateies = findViewById(R.id.checkboxNaoMeChateiesAdd);
        CheckBox repetir = findViewById(R.id.checkboxnRepetirAdd);

        Evento evento;
        ArrayList<Evento> listaEventos = MainActivity.readListaEventoFromDisk(this);

        if ( !editing ) {
            evento = new Evento(
                    name,
                    startTimePicker,
                    endTimePicker,
                    datePicker,
                    despertador.isChecked(),
                    naoMeChateies.isChecked(),
                    repetir.isChecked()
            );
            listaEventos.add(evento);

        } else {
            evento = listaEventos.get(pos);
            if (evento != null) {
                evento.setValues(
                        name,
                        startTimePicker.getCurrentHour(), startTimePicker.getCurrentMinute(),
                        datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear(),
                        endTimePicker.getCurrentHour(), endTimePicker.getCurrentMinute(),
                        datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear(),
                        despertador.isChecked(),
                        naoMeChateies.isChecked(),
                        repetir.isChecked()
                );
            }
        }

        Log.i("PopupWindowsConfirm", evento.toString());

        MainActivity.saveListaEventoToDisk(listaEventos, this);

        // Criar a notificacao
        if ( despertador.isChecked() ) {
            Intent alarmIntent = new Intent(this, AlarmReceiver.class);
            alarmIntent.putExtra("evento", evento);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this, 0, alarmIntent, 0);

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

        Intent intent = new Intent(this, MainActivity.class).
                putParcelableArrayListExtra("lista_eventos", listaEventos);

        startActivity(intent);
        finish();
    }
}


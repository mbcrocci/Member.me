package pt.isec.gps1718_g15.memberme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.ArrayList;


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
                    // TODO(mbcrocci): Criar evento e guarda-lo na lista de eventos


                    View vName = findViewById(R.id.edTitulo);

                    String name = (String) vName.getContentDescription();
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

                    Log.i("PopupWindowsConfirm", "Evento " + name);

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


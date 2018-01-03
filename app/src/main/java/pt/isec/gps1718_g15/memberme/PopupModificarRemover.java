package pt.isec.gps1718_g15.memberme;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;

public class PopupModificarRemover extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_modificar_remover);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.75),(int)(height*.25));
    }

    public void modificarEvento(View v) {
        Intent intent = new Intent(this, popupwindow.class);
        intent.putExtra("Editing", true);

        try {
            int pos = (int) getIntent().getExtras().get("pos");
            intent.putExtra("pos", pos);

        } catch (Exception e) {
            e.printStackTrace();
        }

        startActivity(intent);
        finish();
    }

    public void removerEvento(View v) {
        ArrayList<Evento> listaEventos = MainActivity.readListaEventoFromDisk(this);

        try {
            int pos = (int) getIntent().getExtras().get("pos");
            listaEventos.remove(pos);

        } catch (Exception e) {

            final String name = (String) getIntent().getExtras().get("name");
            Evento evento = null;

            for (Evento ev : listaEventos) {
                if (ev.getName().equals(name))
                    evento = ev;
            }

            if (evento != null)
                listaEventos.remove(evento);
        }
        MainActivity.saveListaEventoToDisk(listaEventos, this);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

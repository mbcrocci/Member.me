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

        getWindow().setLayout((int)(width*.8),(int)(height*.7));
    }

    public void modificarEvento(View v) {

    }

    public void removerEvento(View v) {
        int pos = (int) getIntent().getExtras().get("pos");

        ArrayList<Evento> listaEventos = MainActivity.readListaEventoFromDisk(this);
        listaEventos.remove(pos);
        MainActivity.saveListaEventoToDisk(listaEventos, this);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

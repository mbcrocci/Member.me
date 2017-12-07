package pt.isec.gps1718_g15.memberme;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ArrayList<Evento> listaEventos;
    FileInputStream fInListaEventos;
    FileOutputStream fOutListaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEventos = criarEventosParaTeste();

        for (Evento e: listaEventos)
            Log.i("MainActivityOnCreate", "Name: " + e.getName()
                    + " Start Hour: " + e.getStartingHour()
                    + " End Hour: " + e.getEndingHour()
                    + " Day: " + e.getDayStart()
                    + " Month: " + e.getMonthStart()
                    + " Year: " + e.getYearStart()
            );


        //readListaEventoFromDisk();
    }

    private ArrayList<Evento> criarEventosParaTeste() {

        Evento e1 = new Evento("Evento 1", 10, 11,
                8, 13, 2017,
                8, 13, 2017,
                false, false, false);

        Evento e2 = new Evento("Evento 2", 8, 9,
                7, 13, 2017,
                7, 13, 2017,
                false, false, false);

        Evento e3 = new Evento("Evento 3", 12, 14,
                8, 13, 2017,
                8, 13, 2017,
                false, false, false);


        ArrayList<Evento> lista = new ArrayList<>();
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);

        for (Evento e: lista)
            Log.i("MainActivityCriarTestes", "Name: " + e.getName()
                    + " Start Hour: " + e.getStartingHour()
                    + " End Hour: " + e.getEndingHour()
                    + " Day: " + e.getDayStart()
                    + " Month: " + e.getMonthStart()
                    + " Year: " + e.getYearStart()
            );

        return lista;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //readListaEventoFromDisk();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //saveListaEventoToDisk();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //saveListaEventoToDisk();
    }

    private  void readListaEventoFromDisk() {
        try {
            fInListaEventos = openFileInput("listaEventos.data");
            ObjectInputStream objIn = new ObjectInputStream(fInListaEventos);

            listaEventos = (ArrayList<Evento>) objIn.readObject();

            objIn.close();
            fInListaEventos.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveListaEventoToDisk() {
        try {
            fOutListaEventos = openFileOutput("listaEventos.data", Context.MODE_PRIVATE);
            ObjectOutputStream objOut = new ObjectOutputStream(fOutListaEventos);

            objOut.writeObject(listaEventos);

            objOut.close();
            fOutListaEventos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater(); // para criar o menu
        mi.inflate(R.menu.menu_bar,menu);// ir buscar o menu do xml
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        if(item.getItemId() == R.id.next)
        {
            Intent intent = new Intent(this,calActivity.class)
                    .putExtra("evento1", listaEventos.get(0));
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}

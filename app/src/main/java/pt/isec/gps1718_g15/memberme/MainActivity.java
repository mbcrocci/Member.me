package pt.isec.gps1718_g15.memberme;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

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

    ListView lvListaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEventos = criarEventosParaTeste();
        //readListaEventoFromDisk();

        lvListaEventos = (ListView) findViewById(R.id.lvListaEventos);

        // Todo(mbcrocci): provavelmente seria melhor um CustomAdapter
        ArrayAdapter<Evento> arrayAdapter = new ArrayAdapter<Evento>(
                this,
                android.R.layout.simple_list_item_1,
                listaEventos
        );

        lvListaEventos.setAdapter(arrayAdapter);
    }

    private ArrayList<Evento> criarEventosParaTeste() {

        Evento e1 = new Evento("Evento 1", 10, 11,
                11, 12, 2017,
                11, 12, 2017,
                false, false, false);

        Evento e2 = new Evento("Evento 2", 12, 14,
                11, 12, 2017,
                11, 12, 2017,
                false, false, false);

        Evento e3 = new Evento("Evento 3", 13, 15,
                12, 12, 2017,
                12, 12, 2017,
                false, false, false);

        Evento e4 = new Evento("Evento 4", 16, 17,
                12, 12, 2017,
                12, 12, 2017,
                false, false,false);


        ArrayList<Evento> lista = new ArrayList<>();
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);
        lista.add(e4);

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

    public void addEvent(View v)
    {
        startActivity(new Intent(MainActivity.this,popupwindow.class));
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
                    .putParcelableArrayListExtra("lista_eventos", listaEventos);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}

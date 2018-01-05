package pt.isec.gps1718_g15.memberme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity {

    ArrayList<Evento> listaEventos;
    ListView lvListaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEventos = readListaEventoFromDisk(this);

        if (listaEventos == null) {
            listaEventos = criarEventosParaTeste();
            saveListaEventoToDisk(listaEventos, this);
        }


        lvListaEventos = (ListView) findViewById(R.id.lvListaEventos);

        // Todo(mbcrocci): provavelmente seria melhor um CustomAdapter
        ArrayAdapter<Evento> arrayAdapter = new ArrayAdapter<Evento>(
                this,
                android.R.layout.simple_list_item_1,
                listaEventos
        );

        lvListaEventos.setAdapter(arrayAdapter);
        lvListaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, PopupModificarRemover.class);
                intent.putExtra("pos", i);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Evento> criarEventosParaTeste() {

        Evento e1 = new Evento(
                "Evento 1",
                10, 0,
                18, 12, 2017,
                11, 0,
                18, 12, 2017,
                false, false, false);

        Evento e2 = new Evento(
                "Evento 2",
                13, 0,
                18, 12, 2017,
                15, 0,
                18, 12, 2017,
                false, false, false);

        Evento e3 = new Evento(
                "Evento 3",
                9, 30,
                19, 12, 2017,
                11, 30,
                18, 12, 2017,
                false, false, false);

        Evento e4 = new Evento(
                "Evento 4",
                12, 0,
                19, 12, 2017,
                14, 0,
                19, 12, 2017,
                false, false, false);


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
        listaEventos = readListaEventoFromDisk(this);
        ArrayAdapter<Evento> arrayAdapter = new ArrayAdapter<Evento>(
                this,
                android.R.layout.simple_list_item_1,
                listaEventos
        );

        lvListaEventos.setAdapter(arrayAdapter);
        lvListaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, PopupModificarRemover.class);
                intent.putExtra("pos", i);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveListaEventoToDisk(listaEventos, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveListaEventoToDisk(listaEventos, this);
    }

    public static ArrayList<Evento> readListaEventoFromDisk(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Evento>>() {}.getType();
        String gsonString = sharedPreferences.getString("listaEventos", "");

        return gson.fromJson(gsonString, type);
    }

    public static void saveListaEventoToDisk(ArrayList<Evento> listaEventos, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(listaEventos);
        editor.putString("listaEventos", json);
        editor.commit();
    }

    public void addEvent(View v)
    {
        Intent intent = new Intent(MainActivity.this, popupwindow.class)
                .putParcelableArrayListExtra("lista-eventos", listaEventos);

        startActivity(intent);
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

package pt.isec.gps1718_g15.memberme;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    List<Evento> listaEventos;
    FileInputStream fInListaEventos;
    FileOutputStream fOutListaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readListaEventoFromDisk();
    }

    @Override
    protected void onResume() {
        super.onResume();

        readListaEventoFromDisk();
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveListaEventoToDisk();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveListaEventoToDisk();
    }

    private  void readListaEventoFromDisk() {
        try {
            fInListaEventos = openFileInput("listaEventos.data");
            ObjectInputStream objIn = new ObjectInputStream(fInListaEventos);

            listaEventos = (List<Evento>) objIn.readObject();

            objIn.close();
            fInListaEventos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
}

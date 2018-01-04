package pt.isec.gps1718_g15.memberme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class calActivity extends Activity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener,
        WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    // Field copiados da biblioteca
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;

    private WeekView weekView;

    ArrayList<Evento> listaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        listaEventos = MainActivity.readListaEventoFromDisk(this);

        weekView = findViewById(R.id.weekView);
        weekView.setOnEventClickListener(this);
        weekView.setMonthChangeListener(this);
        weekView.setEventLongPressListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater(); // para criar o menu
        mi.inflate(R.menu.menu_bar_left,menu);// ir buscar o menu do xml
        return true;
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        if(item.getItemId() == R.id.previous)
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void addEvent(View v)
    {
        Intent intent = new Intent(calActivity.this, popupwindow.class)
                .putParcelableArrayListExtra("lista-eventos", listaEventos);

        startActivity(intent);
        
    }

    // Metodos copiados de https://github.com/alamkanak/Android-Week-View/blob/master/sample/src/main/java/com/alamkanak/weekview/sample/BaseActivity.java
    // Adaptados para o nosso codigo
    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<>();

        for (Evento evento : listaEventos)
            events.add(evento.toWeekViewEvent());

        for (WeekViewEvent event : events)
            Log.i("OnMonthChange", event.toString());

        return events;
    }

    // TODO(mbcrocci): Metodo que depois deixa modificar ou remover
    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        MainActivity.saveListaEventoToDisk(listaEventos, this);

        Intent intent = new Intent(this, PopupModificarRemover.class);
        intent.putExtra("name", event.getName());
        startActivity(intent);
    }

    // TODO(mbcrocci): Provavelmente faz o mesmo que onEventClick (rever SRS)
    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return weekView;
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }
    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }
}

package pt.isec.gps1718_g15.memberme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.List;

public class calActivity extends Activity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener {

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

        weekView = (WeekView) findViewById(R.id.weekView);
        weekView.setOnEventClickListener(this);
        weekView.setMonthChangeListener(this);
        weekView.setEventLongPressListener(this);

        weekView.goToToday();

        Bundle bdl = getIntent().getExtras();
        if (bdl != null) {
            listaEventos = bdl.getParcelableArrayList("lista_eventos");
        }

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


    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<>();

        for (Evento evento : listaEventos)
            events.add( evento.toWeekViewEventTeste() );

        return events;
    }

    // TODO(mbcrocci): Metodo que depois deixa modificar ou remover
    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }

    // TODO(mbcrocci): Provavelmente faz o mesmo que onEventClick (rever SRS)
    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }
}

package pt.isec.gps1718_g15.memberme;

import com.alamkanak.weekview.WeekViewEvent;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;


public class EventoToWeekEventTest {
    private Evento newEvento() {
        return new Evento(
                "Evento Teste 1",
                10, 30, 18, 12, 2017,
                11, 15, 18, 12, 2017,
                false, false, false
        );
    }

    private Evento newEventoFimPassado() {
        return new Evento(
                "Evento Teste 2",
                10, 30, 18, 12, 2017,
                9, 0, 17, 11, 2017,
                false, false, false
        );
    }

    @Test
    public void testToWeekViewEventId() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getId(), weekEvent.getId());
    }

    @Test
    public void testToWeekViewEventName() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getName(), weekEvent.getName());
    }

    @Test
    public void testToWeekViewEventHourStart() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getHourStart(), weekEvent.getStartTime().get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testToWeekViewEventMinStart() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getMinStart(), weekEvent.getStartTime().get(Calendar.MINUTE));
    }

    @Test
    public void testToWeekViewEventDayStart() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getDayStart(), weekEvent.getStartTime().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testToWeekViewEventMonthStart() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        /* -1 porque a class Calendar comeca com os meses em 0
         * O construtor da class WeekViewEvent converte mes para o correspondente no Calendar
         * se monthStart = 10 o mes correspondente em Calendar = 9
         */
        assertEquals(evento.getMonthStart() - 1, weekEvent.getStartTime().get(Calendar.MONTH));
    }

    @Test
    public void testToWeekViewEventYearStart() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getYearStart(), weekEvent.getStartTime().get(Calendar.YEAR));
    }

    @Test
    public void testToWeekViewEventHourEnd() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getHourEnd(), weekEvent.getEndTime().get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testToWeekViewEventMinEnd() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getMinEnd(), weekEvent.getEndTime().get(Calendar.MINUTE));
    }

    @Test
    public void testToWeekViewEventDayEnd() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getDayEnd(), weekEvent.getEndTime().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testToWeekViewEventMonthEnd() throws Exception {
        Evento evento = newEvento();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        /* -1 porque a class Calendar comeca com os meses em 0
         * O construtor da class WeekViewEvent converte mes para o correspondente no Calendar
         * se monthStart = 10 o mes correspondente em Calendar = 9
         */
        assertEquals(evento.getMonthEnd() - 1, weekEvent.getEndTime().get(Calendar.MONTH));
    }

    @Test
    public void testToWeekViewEventYearEnd() throws Exception {
        Evento evento = newEventoFimPassado();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getYearEnd(), weekEvent.getEndTime().get(Calendar.YEAR));
    }

    @Test
    public void testToWeekViewEventHourEndPast() throws Exception {
        Evento evento = newEventoFimPassado();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(
                evento.getHourStart()+1, weekEvent.getEndTime().get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testToWeekViewEventMinEndPast() throws Exception {
        Evento evento = newEventoFimPassado();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(0, weekEvent.getEndTime().get(Calendar.MINUTE));
    }

    @Test
    public void testToWeekViewEventDayEndPast() throws Exception {
        Evento evento = newEventoFimPassado();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getDayStart(), weekEvent.getEndTime().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testToWeekViewEventMonthEndPast() throws Exception {
        Evento evento = newEventoFimPassado();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        /* -1 porque a class Calendar comeca com os meses em 0
         * O construtor da class WeekViewEvent converte mes para o correspondente no Calendar
         * se monthStart = 10 o mes correspondente em Calendar = 9
         */
        assertEquals(evento.getMonthStart() - 1, weekEvent.getEndTime().get(Calendar.MONTH));
    }

    @Test
    public void testToWeekViewEventYearEndPast() throws Exception {
        Evento evento = newEventoFimPassado();
        WeekViewEvent weekEvent = evento.toWeekViewEvent();

        assertEquals(evento.getYearStart(), weekEvent.getEndTime().get(Calendar.YEAR));
    }
}
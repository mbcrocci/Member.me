package pt.isec.gps1718_g15.memberme;

import com.alamkanak.weekview.WeekViewEvent;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/*
 * Testa todos os campos da class Evento excepto o toWeekEvent que sera testado
 * no ficheiro EventoToWeekEventTest
 */

public class EventoTest {

    private Evento newEvento() {
        return new Evento(
                "Evento Teste 1",
                10, 30, 18, 12, 2017,
                11, 15, 18, 12, 2017,
                false, false, false
        );
    }

    @Test
    public void testGetId() throws Exception {
        Evento evento1 = newEvento();
        Evento evento2 = newEvento();

        assertEquals(evento1.getId(), evento2.getId()-1);
    }

    @Test
    public void testGetName() throws Exception {
        Evento evento = newEvento();
        assertEquals("Evento Teste 1", evento.getName());
    }

    @Test
    public void testGetHourStart() throws Exception {
        Evento evento = newEvento();
        assertEquals(10, evento.getHourStart());
    }

    @Test
    public void testGetMinStart() throws Exception {
        Evento evento = newEvento();
        assertEquals(30, evento.getMinStart());
    }

    @Test
    public void testGetDayStart() throws Exception {
        Evento evento = newEvento();
        assertEquals(18, evento.getDayStart());
    }

    @Test
    public void testGetMonthStart() throws Exception {
        Evento evento = newEvento();
        assertEquals(12, evento.getMonthStart());
    }

    @Test
    public void testGetYearStart() throws Exception {
        Evento evento = newEvento();
        assertEquals(2017, evento.getYearStart());
    }

    @Test
    public void testGetHourEnd() throws Exception {
        Evento evento = newEvento();
        assertEquals(11, evento.getHourEnd());
    }

    @Test
    public void testGetMinEnd() throws Exception {
        Evento evento = newEvento();
        assertEquals(15, evento.getMinEnd());
    }

    @Test
    public void testGetDayEnd() throws Exception {
        Evento evento = newEvento();
        assertEquals(18, evento.getDayEnd());
    }

    @Test
    public void testGetMonthEnd() throws Exception {
        Evento evento = newEvento();
        assertEquals(12, evento.getMonthEnd());
    }

    @Test
    public void testGetYearEnd() throws Exception {
        Evento evento = newEvento();
        assertEquals(2017, evento.getYearEnd());
    }

    @Test
    public void testToString() throws Exception {
        Evento evento = newEvento();
        // o expected deveria ser contruido apartir do evento criado
        assertEquals("Evento Teste 1 - 10:30 - 11:15 - 18/12/2017", evento.toString());
    }
}
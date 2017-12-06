package pt.isec.gps1718_g15.memberme;

import android.widget.DatePicker;

public class Evento {
    String name;
    int startingHour;
    int endingHour;
    DatePicker startDate;
    DatePicker endingDate;

    boolean despertador;
    boolean repetirEvento;
    boolean naoMeChateies;

    public Evento(
            String name, int startingHour, int endingHour,
            DatePicker startDate, DatePicker endingDate, boolean despertador,
            boolean repetirEvento, boolean naoMeChateies) {

        this.name = name;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        this.startDate = startDate;
        this.endingDate = endingDate;
        this.despertador = despertador;
        this.repetirEvento = repetirEvento;
        this.naoMeChateies = naoMeChateies;
    }
}

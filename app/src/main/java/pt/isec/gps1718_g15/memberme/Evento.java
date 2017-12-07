package pt.isec.gps1718_g15.memberme;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.DatePicker;

import com.alamkanak.weekview.WeekViewEvent;

import java.io.Serializable;
import java.util.Calendar;

public class Evento implements Parcelable {
    String name;
    int startingHour;
    int endingHour;
    DatePicker startDate;
    DatePicker endDate;

    boolean despertador;
    boolean repetirEvento;
    boolean naoMeChateies;

    int dayStart;
    int monthStart;
    int yearStart;
    int dayEnd;
    int monthEnd;
    int yearEnd;


    // Usar este construtor para teste
    public Evento(
            String name, int startingHour, int endingHour,
            int dayStart, int monthStart, int yearStart,
            int dayEnd, int monthEnd, int yearEnd,
            boolean despertador, boolean repetirEvento, boolean naoMeChateies) {

        this.name = name;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        this.dayStart = dayStart;
        this.monthStart = monthStart;
        this.yearStart = yearStart;

        this.dayEnd = dayEnd;
        this.monthEnd = monthEnd;
        this.yearEnd = yearEnd;

        this.despertador = despertador;
        this.repetirEvento = repetirEvento;
        this.naoMeChateies = naoMeChateies;

    }



    // Usar este construtor com o botao Add
    public Evento (
            String name, int startingHour, int endingHour,
            DatePicker startDate, DatePicker endDate,
            boolean despertador, boolean repetirEvento, boolean naoMeChateies) {

        this.name = name;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.despertador = despertador;
        this.repetirEvento = repetirEvento;
        this.naoMeChateies = naoMeChateies;
    }


    public String getName() {
        return name;
    }

    // Coverte Eveonto um WeekViewEvent para ser visualizado no calendario
    public WeekViewEvent toWeekViewEvent() {

        Calendar now = Calendar.getInstance();
        Calendar startTime = (Calendar) now.clone();

        startTime.set( Calendar.YEAR, startDate.getYear() );
        startTime.set( Calendar.MONTH, startDate.getMonth() );
        startTime.set( Calendar.DAY_OF_MONTH, startDate.getMonth() );

        Calendar endTime = (Calendar) startTime.clone();
        endTime.set( Calendar.YEAR, endDate.getYear() );
        endTime.set( Calendar.MONTH, endDate.getMonth() );
        endTime.set( Calendar.DAY_OF_MONTH, endDate.getDayOfMonth() );

        WeekViewEvent weekViewEvent = new WeekViewEvent();
        weekViewEvent.setName( getName() );
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);


        return weekViewEvent;
    }

    public WeekViewEvent toWeekViewEventTeste() {

        Calendar now = Calendar.getInstance();
        Calendar startTime = (Calendar) now.clone();

        startTime.set( Calendar.YEAR, dayStart );
        startTime.set( Calendar.MONTH, monthStart );
        startTime.set( Calendar.DAY_OF_MONTH, yearStart );

        Calendar endTime = (Calendar) startTime.clone();
        endTime.set( Calendar.YEAR, dayEnd );
        endTime.set( Calendar.MONTH, monthEnd );
        endTime.set( Calendar.DAY_OF_MONTH, yearEnd );

        WeekViewEvent weekViewEvent = new WeekViewEvent();
        weekViewEvent.setName( getName() );
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);


        return weekViewEvent;
    }

    // Metodos Gerados pelo Android Studio para a implementacao da interface Parcelable
    // Esta interface permite operacoes iguais a Serializable

    protected Evento(Parcel in) {
        name = in.readString();
        startingHour = in.readInt();
        endingHour = in.readInt();
        despertador = in.readByte() != 0;
        repetirEvento = in.readByte() != 0;
        naoMeChateies = in.readByte() != 0;
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(startingHour);
        parcel.writeInt(endingHour);
        parcel.writeByte((byte) (despertador ? 1 : 0));
        parcel.writeByte((byte) (repetirEvento ? 1 : 0));
        parcel.writeByte((byte) (naoMeChateies ? 1 : 0));
    }
}

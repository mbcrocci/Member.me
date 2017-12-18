package pt.isec.gps1718_g15.memberme;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.alamkanak.weekview.WeekViewEvent;

public class Evento implements Parcelable {
    private static int classID = 0;
    int id;
    String name;

    private int hourStart;
    private int minStart;
    private int dayStart;
    private int monthStart;
    private int yearStart;

    private int hourEnd;
    private int minEnd;
    private int dayEnd;
    private int monthEnd;
    private int yearEnd;

    private boolean despertador;
    private boolean repetirEvento;
    private boolean naoMeChateies;


    public Evento(
            String name, int hourStart, int minStart,
            int dayStart, int monthStart, int yearStart,
            int hourEnd, int minEnd,
            int dayEnd, int monthEnd, int yearEnd,
            boolean despertador, boolean repetirEvento, boolean naoMeChateies) {

        classID++;
        this.id = classID;
        this.name = name;

        this.hourStart = hourStart;
        this.minStart = minStart;
        this.dayStart = dayStart;
        this.monthStart = monthStart;
        this.yearStart = yearStart;

        this.hourEnd = hourEnd;
        this.minEnd = minEnd;
        this.dayEnd = dayEnd;
        this.monthEnd = monthEnd;
        this.yearEnd = yearEnd;

        this.despertador = despertador;
        this.repetirEvento = repetirEvento;
        this.naoMeChateies = naoMeChateies;

    }

    // Usar este construtor com o botao Add
    public Evento (
            String name, TimePicker startTime, TimePicker endTime,
            DatePicker date,
            boolean despertador, boolean repetirEvento, boolean naoMeChateies) {

        classID++;
        this.id = classID;
        this.name = name;

        this.hourStart = startTime.getCurrentHour();
        this.minStart = startTime.getCurrentMinute();
        this.dayStart = date.getDayOfMonth();
        this.monthStart = date.getMonth() + 1;
        this.yearStart = date.getYear();

        this.hourEnd = endTime.getCurrentHour();
        this.minEnd = endTime.getCurrentMinute();
        this.dayEnd = date.getDayOfMonth();
        this.monthEnd = date.getDayOfMonth() + 1;
        this.yearEnd = date.getDayOfMonth();

        this.despertador = despertador;
        this.repetirEvento = repetirEvento;
        this.naoMeChateies = naoMeChateies;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHourStart() {
        return hourStart;
    }

    public int getMinStart() {
        return minStart;
    }

    public int getDayStart() {
        return dayStart;
    }

    public int getMonthStart() {
        return monthStart;
    }

    public int getYearStart() {
        return yearStart;
    }

    public int getHourEnd() {
        return hourEnd;
    }

    public int getMinEnd() {
        return minEnd;
    }

    public int getDayEnd() {
        return dayEnd;
    }

    public int getMonthEnd() {
        return monthEnd;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    // Coverte Eveonto um WeekViewEvent para ser visualizado no calendario
    public WeekViewEvent toWeekViewEvent() {
       WeekViewEvent weekViewEvent = new WeekViewEvent(
               id, name,
               yearStart, monthStart, dayStart,
               hourStart, minStart,
               yearEnd, monthEnd, dayEnd,
               hourEnd, minEnd
       );
       weekViewEvent.setColor(Color.BLUE);

        return weekViewEvent;
    }

    // Metodos Gerados pelo Android Studio para a implementacao da interface Parcelable
    // Esta interface permite operacoes iguais a Serializable

    protected Evento(Parcel in) {
        id = in.readInt();

        name = in.readString();
        hourStart = in.readInt();
        minStart = in.readInt();
        dayStart = in.readInt();
        monthStart = in.readInt();
        yearStart = in.readInt();

        hourEnd = in.readInt();
        minEnd = in.readInt();
        dayEnd = in.readInt();
        monthEnd = in.readInt();
        yearEnd = in.readInt();

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
        parcel.writeInt(id);
        parcel.writeString(name);

        parcel.writeInt(hourStart);
        parcel.writeInt(minStart);
        parcel.writeInt(dayStart);
        parcel.writeInt(monthStart);
        parcel.writeInt(yearStart);

        parcel.writeInt(hourEnd);
        parcel.writeInt(minEnd);
        parcel.writeInt(dayEnd);
        parcel.writeInt(monthEnd);
        parcel.writeInt(yearEnd);

        parcel.writeByte((byte) (despertador ? 1 : 0));
        parcel.writeByte((byte) (repetirEvento ? 1 : 0));
        parcel.writeByte((byte) (naoMeChateies ? 1 : 0));
    }

    @Override
    public String toString() {
            return name + " - " + hourStart + ":" + minStart + " - "
                    + hourEnd + ":" + minEnd + " - "
                    + dayStart + "/" + monthStart + "/" + yearStart;
    }
}

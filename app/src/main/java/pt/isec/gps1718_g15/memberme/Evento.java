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

        if (yearEnd < yearStart)
            this.yearEnd = yearStart;
        else
            this.yearEnd = yearEnd;

        if (this.yearEnd == this.yearStart
                && monthEnd < this.monthStart)
            this.monthEnd = monthStart;
        else
            this.monthEnd = monthEnd;

        if (this.yearEnd == this.yearStart
                && this.monthEnd == this.monthStart
                && dayEnd < this.dayStart)
            this.dayEnd = this.dayStart;
        else
            this.dayEnd = dayEnd;

        if (hourEnd < hourStart)
            this.hourEnd = hourStart + 1;
        else
            this.hourEnd = hourEnd;

        if (this.hourStart == this.hourEnd && minEnd < minStart) {
            if (minStart + 30 >= 60)
                this.minEnd = (minStart + 30) - 60;
            else
                this.minEnd = minStart + 30;
        } else {
            this.minEnd = minEnd;
        }

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

        if (endTime.getCurrentHour() < startTime.getCurrentHour())
            this.hourEnd = hourStart + 1;
        else
            this.hourEnd = endTime.getCurrentHour();

        if (this.hourStart == this.hourEnd
                && endTime.getCurrentMinute() < this.minStart) {
            if (this.minStart + 30 >= 60)
                this.minEnd = (this.minStart + 30) - 60;
            else
                this.minEnd = this.minStart + 30;
        } else {
            this.minEnd = endTime.getCurrentMinute();
        }

        // Nao e preciso fazer check pq so e passado uma data
        this.dayEnd = date.getDayOfMonth();
        this.monthEnd = date.getMonth() + 1;
        this.yearEnd = date.getYear();

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

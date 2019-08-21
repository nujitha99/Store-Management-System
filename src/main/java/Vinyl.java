import java.util.Objects;

//subclass of the MusicItem
public class Vinyl extends MusicItem {

    private int speed; //data type is double
    private int diameter; //date type is double

    public Vinyl(int itemId, String title, String genre, Date date, String artist, int price, int speed, int diameter) {
        super(itemId, title, genre, date, artist, price);
        this.speed = speed;
        this.diameter = diameter;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vinyl vinyl = (Vinyl) o;
        return speed == vinyl.speed &&
                diameter == vinyl.diameter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speed, diameter);
    }

    @Override
    public String toString() {
        return "Vinyl{" +
                "speed=" + speed +
                ", diameter=" + diameter +
                ", itemId=" + itemId +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", date=" + date +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }
}

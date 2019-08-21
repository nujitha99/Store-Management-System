import java.util.Objects;

//subclass of the MusicItem
public class CD extends MusicItem {

    private int duration;

    public CD(int itemId, String title, String genre, Date date, String artist, int price, int duration) {
        super(itemId, title, genre, date, artist, price);
        this.duration = duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CD cd = (CD) o;
        return duration == cd.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duration);
    }

    @Override
    public String toString() {
        return "CD{" +
                "duration=" + duration +
                ", itemId=" + itemId +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", date=" + date +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }
}

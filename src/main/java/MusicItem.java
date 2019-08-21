import java.util.Objects;

//super class

public abstract class MusicItem implements Comparable<MusicItem> {
    protected int itemId;
    protected String title;
    protected String genre;
    protected Date date;
    protected String artist;
    protected double price;

    public MusicItem(int itemId, String title, String genre, Date date, String artist, int price) {
        this.itemId = itemId;
        this.title = title;
        this.genre = genre;
        this.date = date;
        this.artist = artist;
        this.price = price;

    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicItem musicItem = (MusicItem) o;
        return itemId == musicItem.itemId &&
                Double.compare(musicItem.price, price) == 0 &&
                Objects.equals(title, musicItem.title) &&
                Objects.equals(genre, musicItem.genre) &&
                Objects.equals(date, musicItem.date) &&
                Objects.equals(artist, musicItem.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, title, genre, date, artist, price);
    }

    @Override
    public int compareTo(MusicItem o) {
        //sort using title
        return title.compareTo(o.getTitle());

    }

    @Override
    public String toString() {
        return "MusicItem{" +
                "itemId=" + itemId +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", date=" + date +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }
}

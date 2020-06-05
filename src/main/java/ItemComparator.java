import java.util.Comparator;

public class ItemComparator implements Comparator<MusicItem> {

    @Override
    public int compare(MusicItem o1, MusicItem o2) {
        return o1.getItemId();
    }
}

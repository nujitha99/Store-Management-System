import java.util.concurrent.ExecutionException;

public interface StoreManager {
    void addItem(MusicItem item);

    void deleteItem(int id);
    //void deleteItem(MusicItem item);

    void printList() throws ExecutionException, InterruptedException;

    void sortItems() throws ExecutionException, InterruptedException;

    void buyItems() throws ExecutionException, InterruptedException;

    void generateReport() throws ExecutionException, InterruptedException;

    void testMethod();

}

import org.omg.SendingContext.RunTime;

public class StoreFullException extends RuntimeException {
    public StoreFullException(String store_is_full) {
        super();
    }
}

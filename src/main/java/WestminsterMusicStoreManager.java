import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class WestminsterMusicStoreManager implements StoreManager {

    private int currCount = 0;
    //firestore initializing
    private static Firestore db;
    private static final int count = 1000;
    public Double total;

    public WestminsterMusicStoreManager() {
        try {
            setupFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //implement methods in store manager


    @Override
    public void addItem(MusicItem item) {
        currCount++;
        db.collection("MusicItems").document(String.valueOf(item.getItemId())).set(item);
    }

    @Override
    public void deleteItem(int id) {
        try {
            firestoreDelete(id);
            currCount--;
            System.out.println("Number of free spaces left : " + (count - currCount));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printList() throws ExecutionException, InterruptedException {
        // Create a query against the collection.
        Query query = db.collection("MusicItems");
        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        System.out.println("ID   Title\n__________");

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            System.out.println(document.getId() + " -> " + document.getString("title"));
        }
    }

    @Override
    public void sortItems() throws ExecutionException, InterruptedException {
        // Create a reference to the MusicItems collection and query against the collection.
        Query query = db.collection("MusicItems").orderBy("title");
        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        System.out.println("ID   Title\n__________");
        //displays all the items in the store after sorting them out
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            System.out.println(document.getId() + " -> " + document.getString("title"));
        }
    }

    @Override
    public void buyItems() {
        System.out.println("The total amount is : " + total);
    }

    @Override
    public void generateReport() throws ExecutionException, InterruptedException {
        // Create a query against the collection.
        Query query = db.collection("BoughtItems");
        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        System.out.println("ID   Title\n__________");

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            System.out.println(document.getId() + " -> " + document.getString(document.getId()));
        }
    }

    //setting up the firebase connection
    private static void setupFirebase() throws IOException {
        InputStream serviceAccount = new FileInputStream("java-project-263b1-firebase-adminsdk-f3f30-a5df91161f.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }

    //method to delete items from the firebase
    public void firestoreDelete(int id) throws ExecutionException, InterruptedException {
        //refers the given document in the firestore for the given id
        DocumentReference docRef = db.collection("MusicItems").document(String.valueOf(id));
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            db.collection("MusicItems").document(String.valueOf(id)).delete();
            System.out.println("Item deleted successfully..!!");
        } else {
            System.out.println("No such document!");
        }
    }

    //check if such item exists in the firestore before buying
    public void checkData(int itemId) throws ExecutionException, InterruptedException {
        printList();
        //refers the given document in the firestore for the given id
        DocumentReference docRef = db.collection("MusicItems").document(String.valueOf(itemId));
        ApiFuture<DocumentSnapshot> ref = docRef.get();
        DocumentSnapshot document = ref.get();
        if (document.exists()) {
            Map<String, Object> BoughtItems = new HashMap<>();
            BoughtItems.put(document.getId(), document.getString("title"));
            db.collection("BoughtItems").document(String.valueOf(itemId)).set(BoughtItems);
            firestoreDelete(itemId);
            System.out.println("Item added to the cart...");
        } else {
            System.out.println("No such document!");
        }
    }

    public void searchItem(String title) throws ExecutionException, InterruptedException {
        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection("MusicItems").whereEqualTo("title", title).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            if (document.exists()) {
                System.out.println("Item ID -> " + document.getId());
                System.out.println("Title   -> " + document.getString("title"));
                System.out.println("Artist  -> " + document.getString("artist"));
                System.out.println("Genre   -> " + document.getString("genre"));
                System.out.println("Price   -> " + document.get("price"));
                break;
            } else {
                System.out.println("No such document!");
            }
        }
    }

}

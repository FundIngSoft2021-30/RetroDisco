package database;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.*;
import com.google.firebase.cloud.FirestoreClient;

public class ConexionFB {

    public Firestore iniciarFirebase(){

        try {
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("baseretrodiscos-firebase-adminsdk-a0lt9-038507a86f.json"))).build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FirestoreClient.getFirestore();
    }
}

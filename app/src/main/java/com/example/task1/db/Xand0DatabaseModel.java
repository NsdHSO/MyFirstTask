package com.example.task1.db;

import com.example.task1.modelController.Xand0Deals;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Xand0DatabaseModel {
    public static FirebaseDatabase mFireBaseDB;
    public static DatabaseReference mDatabaseReference;
    private static Xand0DatabaseModel xand0DatabaseModel;
    public static ArrayList<Xand0Deals> xand0Deals;
    private Xand0DatabaseModel(){};
    public static void openFbREference(String ref){
        if (xand0DatabaseModel == null){
            xand0DatabaseModel = new Xand0DatabaseModel();
            mFireBaseDB = FirebaseDatabase.getInstance();
            xand0Deals = new ArrayList<>();

        }
        mDatabaseReference = mFireBaseDB.getReference().child(ref);
    }
}

package com.example.justjava;

import static com.mongodb.client.model.Filters.eq;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DatabaseDisplay2 extends AppCompatActivity {


//    @SuppressLint("AuthLeak")
//    MongoClientURI uri = new MongoClientURI("mongodb://asko:asko@cluster0.1jpcp.mongodb.net/mcc?retryWrites=true&w=majority");
//    MongoClient mongoClient = new MongoClient(uri);
//    MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());
//    MongoCollection<Document> orders= db.getCollection("coffeOrders");

//    ConnectionString connectionString = new ConnectionString("mongodb+srv://asko:<password>@cluster0.1jpcp.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
//    MongoClientSettings settings = MongoClientSettings.builder()
//            .applyConnectionString(connectionString)
//            .build();
//    MongoClient mongoClient = MongoClients.create(settings);
//    MongoDatabase database = mongoClient.getDatabase("test");
//    @SuppressLint("AuthLeak")
    String uri = "mongodb+srv://asko:asko@cluster0.1jpcp.mongodb.net/mcc?retryWrites=true&w=majority";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_display2);
//        MongoCursor<Document> cursor = orders.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next().toJson());
//            }
//        } finally {
//            cursor.close();
//        }
        databaseHelper dbHelper = new databaseHelper(DatabaseDisplay2.this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
//        String[] projection = {
//                BaseColumns._ID,
//                databaseHelper.FeedEntry.name,
//                databaseHelper.FeedEntry.quantity,
//                databaseHelper.FeedEntry.whippedCreme,
//                databaseHelper.FeedEntry.chocolate,
//                databaseHelper.FeedEntry.total,
//        };

        String projection = "SELECT * FROM "+databaseHelper.FeedEntry.TABLE_NAME;

// Filter results WHERE "title" = 'My Title'
//        String selection = databaseHelper.FeedEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                databaseHelper.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.rawQuery(projection,null);
//                databaseHelper.FeedEntry.TABLE_NAME,   // The table to query
//                projection        // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                sortOrder               // The sort order
//        );
        LayoutInflater layoutInflator = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout insertPoint = (LinearLayout) findViewById(R.id.linearLayout1);
        List views = new ArrayList();

        List<Order> allOrders = new ArrayList<>();
        while(cursor.moveToNext()) {
            int quantity = cursor.getInt(
                    cursor.getColumnIndexOrThrow(databaseHelper.FeedEntry.quantity));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(databaseHelper.FeedEntry.name));
            boolean chocolate = cursor.getInt(
                    cursor.getColumnIndexOrThrow(databaseHelper.FeedEntry.chocolate)) == 1 ? true:false;
            boolean whippedCreme = cursor.getInt(
                    cursor.getColumnIndexOrThrow(databaseHelper.FeedEntry.whippedCreme)) == 1 ? true:false;
            int total = cursor.getInt(
                    cursor.getColumnIndexOrThrow(databaseHelper.FeedEntry.total));

            Order order = new Order(quantity,name,whippedCreme,chocolate,total);

            View view = layoutInflator.inflate(R.layout.activity_database_display2, null);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(order.toString());

            views.add(view);

            allOrders.add(order);
        }
        cursor.close();
//        String[] info = new String[2];
//        try{
//            MongoClient mongoClient = MongoClients.create(uri);
//            MongoDatabase database = mongoClient.getDatabase("mcc");
//            MongoCollection<Document> collection = database.getCollection("coffeOrders");
////            Document doc = collection.find(eq("title", "Back to the Future")).first();
////            System.out.println(doc.toJson());
//            long orders = collection.countDocuments();
//            info[0] = String.valueOf(orders);
//        }
//        catch(Exception e){
//            info[0] = String.valueOf(e);
//        }
//
//
//        info[1] = "info 2";
//
//        LayoutInflater layoutInflator = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LinearLayout insertPoint = (LinearLayout) findViewById(R.id.linearLayout1);
//        List views = new ArrayList();
//
//        for(int i=0; i < info.length; i++){
//            View view = layoutInflator.inflate(R.layout.activity_database_display2, null);
//            TextView textView = (TextView) view.findViewById(R.id.textView1);
//            textView.setText(info[i]);
//
//            views.add(view);
//        }

        for(int i = 0; i < views.size(); i++) {
            insertPoint.addView((View) views.get(i));
        }
    }


}
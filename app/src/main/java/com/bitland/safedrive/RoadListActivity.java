/**Activity that makes use of a recycler view to display the list of roads in the detected location.
 * The database object which is the entry point into the fire base real time database is also defined in this class.
 * A reference to the specif part of the data base is also defined here. In OnCreate() we then make use of the
 * addValueEventListener method and a ValueEventListener object in order to retrieve data from our fire base real time DB.*/
package com.bitland.safedrive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoadListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    //Firebase DB object, entry point for accessing DB
    private FirebaseDatabase mRoadDatabase;
    //Reference to a specific part of the database
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_list);
        ButterKnife.bind(this);

        //main access point of our DB
        mRoadDatabase = FirebaseDatabase.getInstance();
        //use access point to get a ref to a particular part of the DB
        mDatabaseReference = mRoadDatabase.getReference().child("locations");

        //Intent to retrieve location name from intent that was used to start this activity from MainActivity
        Intent intent = getIntent();
        final String location = intent.getStringExtra(MainActivity.LOCATION_NAME);

        //Retrieve data from the fire base real time database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                database_loop:
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Location location1 = snapshot.getValue(Location.class);
                    String locationName = location1.getName();
                    if (location.equals(locationName)){
                        displayRoadList(location1);
                        break database_loop;
                    }
                    else {
                        Toast.makeText(RoadListActivity.this, "Location not found", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void displayRoadList(com.bitland.safedrive.Location location){
        ArrayList<Road> roadList = location.getRoads();

//        String name;
//        for(Road road : roadList){
//            name = road.getName();
//            Log.d(RoadListActivity.class.getSimpleName(), name);
//        }

        RoadAdapter adapter = new RoadAdapter(this, roadList);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
    }
}

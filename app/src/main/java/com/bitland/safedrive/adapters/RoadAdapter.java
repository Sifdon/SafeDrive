/**
 * This class is the custom adapter for the Road object. It maps the Road data or values to tbe views defined in the road_item_list
 * layout file. A custom adapter is compulsory in recycler views and has two major functions i.e mapping data to fields in layout
 * and making the data suited from the views. This is done using the helper methods defined in the Adapter and ViewHolder classes
 * below.*/
package com.bitland.safedrive.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitland.safedrive.R;
import com.bitland.safedrive.model.Road;
import com.bitland.safedrive.ui.RoadInfoActivity;

import java.util.ArrayList;

public class RoadAdapter extends RecyclerView.Adapter<RoadAdapter.RoadViewHolder> {

    public static final String ROAD_NAME = "road name" ;
    public static final String TRAFFIC_LIGHTS = "traffic lights" ;
    public static final String TRAFFIC_VOLUME =  "traffic volume";
    public static final String CARNAGE_COUNT = "carnage count" ;
    public static final String ROAD_BLOCK =  "road block";

    //Array list to hold the list of roads for a particular location
    private ArrayList<Road> mRoads;

    private Context mContext;

    /**The constructor enables us to create the adapter in the displaying activity (RoadListActivity)
     * and set its data.*/
    public RoadAdapter(Context context, ArrayList<Road> roads){
        mContext = context;
        mRoads = roads;
    }

    /**Method called when a new ViewHolder is created.
     * These are re-used and are created as and when needed. */
    @Override
    public RoadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.road_list_item, parent, false);
        RoadViewHolder viewHolder = new RoadViewHolder(view);
        return viewHolder;
    }

    /**Method is the bridge between the adapter and the bindRoad() method in the ViewHolder class.
     * @param holder is a ViewHolder object used to access the bindRoad() method.
     * @param position is used to access an mRoads array list object.*/
    @Override
    public void onBindViewHolder(RoadViewHolder holder, int position) {
        holder.bindRoad(mRoads.get(position));

    }

    //Method returns the number of items in the mRoads array list.
    @Override
    public int getItemCount() {
        return mRoads.size();
    }

    public class RoadViewHolder extends RecyclerView.ViewHolder
       implements View.OnClickListener{

        private String mRoadName;
        private String mTrafficLightStatus;
        private String mTrafficVolume;
        private String mCarnageCount;
        private String mRoadBlockCount;

        //Declare member variables for each view defined in the road__list_item layout file
        public TextView roadNameTextView;

        /**Constructor to initialize the member variables declared above.
         * It is called when the view holder is created */
        public RoadViewHolder(View itemView) {
            super(itemView);

            roadNameTextView = itemView.findViewById(R.id.roadNameTextView);

            itemView.setOnClickListener(this);
        }

        /**Method to set or bind the views.
         * Inside the method, set the views.*/
        public void bindRoad(Road road){
            roadNameTextView.setText(road.getName());

            this.mRoadName = roadNameTextView.getText().toString();
            this.mTrafficLightStatus = road.getTrafficLightsStatus();
            this.mTrafficVolume = road.getTrafficVolume() + "";
            this.mCarnageCount = road.getCarnageCount() + "";
            this.mRoadBlockCount = road.getRoadBlockCount() + "";
        }

        @Override
        public void onClick(View view) {
//            String message = String.format("The %s is %s is %s is %s", mTrafficLightStatus,
//                    mTrafficVolume,mCarnageCount,mRoadBlockCount);
//            Log.d(RoadAdapter.class.getSimpleName(), message);

            startRoadInfoActivity();

        }

        private void startRoadInfoActivity(){
            Intent intent = new Intent(mContext, RoadInfoActivity.class);
            intent.putExtra(ROAD_NAME,mRoadName);
            intent.putExtra(TRAFFIC_LIGHTS,mTrafficLightStatus);
            intent.putExtra(TRAFFIC_VOLUME,mTrafficVolume);
            intent.putExtra(CARNAGE_COUNT,mCarnageCount);
            intent.putExtra(ROAD_BLOCK,mRoadBlockCount);
            mContext.startActivity(intent);

        }
    }
}

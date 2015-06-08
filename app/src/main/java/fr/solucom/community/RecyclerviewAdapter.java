package fr.solucom.community;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wasier on 28/05/2015.
 */

//This adaptator is used to adapt activities card to a recycleview
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ActivityViewHolder>{

    List<ActivityInfo> activityInfos;

    public RecyclerviewAdapter(List<ActivityInfo> activityInfos){
        this.activityInfos = activityInfos;
    }

    //is to check data size
    @Override
    public int getItemCount() {
        return activityInfos.size();
    }


    @Override
    //used to update the recycle view
    public void onBindViewHolder(ActivityViewHolder activityViewHolder, int i) {
        activityViewHolder.title.setText(activityInfos.get(i).title);
        activityViewHolder.pictureId.setImageResource(activityInfos.get(i).pictureId);
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.activitycard_layout, viewGroup, false);
        ActivityViewHolder Avh = new ActivityViewHolder(v);
        return Avh;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        ImageView pictureId;

        ActivityViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardview);
            title = (TextView)itemView.findViewById(R.id.activity_title);
            pictureId = (ImageView)itemView.findViewById(R.id.activity_picture);
        }
    }
}

package asquero.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anmol on 10-Apr-18.
 */

public class UpcomingListAdapter extends RecyclerView.Adapter<UpcomingListAdapter.ViewHolder> {

    private List<UpcomingList>list;
    private Context context;

    //For storing the checked items
    private ArrayList<UpcomingList> chkUpcoming;


    private ArrayList<String> itemsChkd = new ArrayList<>();

    public UpcomingListAdapter(List<UpcomingList> listUpcoming, Upcoming upcoming) {
        this.list = listUpcoming;
        this.context = upcoming;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upcoming_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        chkUpcoming = new ArrayList<>();
        final UpcomingList listItem = list.get(position);

        String cc = listItem.getContestCode();
        String cn = listItem.getContestName();
        String cs = listItem.getStartDate();
        String ce = listItem.getEndDate();
        String csrc = listItem.getContestSource();

        String url = listItem.getImageUrl();

        holder.contestCode.setText(""+cc);
        holder.contestName.setText(cn);
        holder.endDate.setText(""+(cs));
        holder.startDate.setText(""+(ce));
        holder.contestSourceImg.setImageResource(listItem.getContestSourceImg());
        holder.aic.setText(listItem.getAIC());
        holder.progressBar.setVisibility(View.VISIBLE);
        holder.contestSource.setText("Source: "+csrc);

        try {

            Picasso.get().load(""+url).into(holder.imageView);
            holder.progressBar.setVisibility(View.INVISIBLE);
        }
        catch (Exception e){

            e.printStackTrace();
            //Picasso.get().load(url).placeholder(R.drawable.upcoming).error(R.drawable.ended).into(holder.imageView);
            holder.progressBar.setVisibility(View.INVISIBLE);
            holder.imageView.setImageResource(R.drawable.endedsmall);
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CheckBox chk = (CheckBox) view;

                //For checking the checkBox
                if (chk.isChecked()){

                    //storing the item
                    chkUpcoming.add(list.get(position));
                    Toast.makeText(view.getContext(), listItem.getContestCode(), Toast.LENGTH_SHORT).show();


                    itemsChkd.add(listItem.getContestCode());

                }else if (!chk.isChecked()){

                    //Storing the item
                    chkUpcoming.remove(list.get(position));
                    Toast.makeText(view.getContext(), listItem.getContestCode(), Toast.LENGTH_SHORT).show();


                    itemsChkd.remove(listItem.getContestCode());

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView contestCode;
        public TextView contestName;
        public TextView endDate;
        public TextView startDate;
        ImageView contestSourceImg;
        public ImageView imageView;
        TextView aic;
        public TextView contestSource;
        public ProgressBar progressBar;
        CheckBox chkBox;

        ItemClickListener itemClickListener;

        ViewHolder(View itemView) {
            super(itemView);
            contestCode = (TextView)itemView.findViewById(R.id.contestCode);
            contestName = (TextView)itemView.findViewById(R.id.contestName);
            endDate = (TextView)itemView.findViewById(R.id.startDateNum);
            startDate = (TextView)itemView.findViewById(R.id.endDateNum);
            contestSourceImg = (ImageView)itemView.findViewById(R.id.contestSourceImage);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarImage);
            aic = (TextView)itemView.findViewById(R.id.AICTextView);
            contestSource = (TextView)itemView.findViewById(R.id.contestSource);
            chkBox = (CheckBox) itemView.findViewById(R.id.checkBox);

            chkBox.setOnClickListener(this);
        }

        void setItemClickListener(ItemClickListener icl){
            this.itemClickListener = icl;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }
}


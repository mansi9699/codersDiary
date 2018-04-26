package asquero.com.myapplication;

import android.support.v7.widget.RecyclerView;
import android.content.Context;

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

public class LiveListAdapter extends RecyclerView.Adapter<LiveListAdapter.ViewHolder> {

    private static final String TAG = "myactivity";
    private List<LiveList>list;
    private Context context;

    //For storing the checked items
    private ArrayList<LiveList> chkLive;


    private ArrayList<String> itemsChkd = new ArrayList<>();

    //DotProgressBar dotProgressBar = (DotProgressBar) View.findViewById(R.id.dot_progress_bar);



    LiveListAdapter(List<LiveList> listLive, Live live) {
        this.list = listLive;
        this.context = live;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.live_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        chkLive = new ArrayList<>(list.size());
        final LiveList listItem = list.get(position);

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
        //String url = "https://edsurge.imgix.net/uploads/post/image/7747/Kids_coding-1456433921.jpg?auto=compress%2Cformat&w=2000&h=810&fit=crop";

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
                    chkLive.add(list.get(position));
                    Toast.makeText(view.getContext(), listItem.getContestCode(), Toast.LENGTH_SHORT).show();
                    
                    
                    itemsChkd.add(listItem.getContestCode());

                }else if (!chk.isChecked()){
                    
                    //Storing the item
                    chkLive.remove(list.get(position));
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
        public ImageView contestSourceImg;
        public ImageView imageView;
        public TextView aic;
        public TextView contestSource;
        public ProgressBar progressBar;
        public CheckBox chkBox;

        public ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            contestCode = (TextView) itemView.findViewById(R.id.contestCode);
            contestName = (TextView) itemView.findViewById(R.id.contestName);
            endDate = (TextView) itemView.findViewById(R.id.startDateNum);
            startDate = (TextView) itemView.findViewById(R.id.endDateNum);
            contestSourceImg = (ImageView) itemView.findViewById(R.id.contestSourceImage);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarImage);
            aic = (TextView) itemView.findViewById(R.id.AICTextView);
            contestSource = (TextView) itemView.findViewById(R.id.contestSource);
            chkBox = (CheckBox) itemView.findViewById(R.id.checkBox);

            chkBox.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener icl){
            this.itemClickListener = icl;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }
}

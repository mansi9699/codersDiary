package asquero.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.DetailViewHolder> {

    private static final String TAG = "myactivity";
    private List<DetailList> list;
    private Context context;

    //For storing the checked items
    private ArrayList<DetailList> chkDetails;

    private ArrayList<String> itemsChkd = new ArrayList<>();


    public DetailListAdapter(List<DetailList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.detail_list_layout, parent, false);
        return new DetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DetailViewHolder holder, final int position) {

        chkDetails = new ArrayList<>(list.size());
        final DetailList detailList = list.get(position);

        String cc = detailList.getContestCode();
        String cn = detailList.getContestName();
        String cs = detailList.getStartDate();
        String ce = detailList.getEndDate();
        String csrc = detailList.getContestSource();
        final int sci = detailList.getSrcCompanyImage();

        String url = detailList.getImageUrl();

        holder.contestCode.setText(cc);
        holder.contestName.setText(cn);
        holder.endDate.setText(cs);
        holder.startDate.setText(ce);
        holder.contestSourceImg.setImageResource(detailList.getContestSourceImg());
        holder.aic.setText(detailList.getAIC());
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

        holder.notificationBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.notificationBell.getDrawable().getConstantState() == holder.notificationBell.getResources().getDrawable(R.drawable.ic_notification_unchecked).getConstantState()){

                    //storing the item
                    holder.notificationBell.setImageResource(R.drawable.ic_turn_notifications_checked);
                    Toast.makeText(context, "Notification Checked", Toast.LENGTH_SHORT).show();


                    itemsChkd.add(detailList.getContestCode());

                }else if (holder.notificationBell.getDrawable().getConstantState() == holder.notificationBell.getResources().getDrawable(R.drawable.ic_turn_notifications_checked).getConstantState()){

                    //Storing the item
                    holder.notificationBell.setImageResource(R.drawable.ic_notification_unchecked);
                    Toast.makeText(context, detailList.getContestCode(), Toast.LENGTH_SHORT).show();


                    itemsChkd.remove(detailList.getContestCode());

                }

            }
        });

        /*holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageView nB = (ImageView) view;

                //For checking the checkBox
                if (nB.getDrawable().getConstantState() == nB.getResources().getDrawable(R.drawable.ic_notification_unchecked).getConstantState()){

                    //storing the item
                    nB.setImageResource(R.drawable.ic_turn_notifications_checked);
                    Toast.makeText(view.getContext(), "Notification Checked", Toast.LENGTH_SHORT).show();


                    itemsChkd.add(detailList.getContestCode());

                }else if (nB.getDrawable().getConstantState() == nB.getResources().getDrawable(R.drawable.ic_turn_notifications_checked).getConstantState()){

                    //Storing the item
                    nB.setImageResource(R.drawable.ic_notification_unchecked);
                    Toast.makeText(view.getContext(), detailList.getContestCode(), Toast.LENGTH_SHORT).show();


                    itemsChkd.remove(detailList.getContestCode());

                }
            }
        });*/

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++){
                    Intent intent = new Intent(context,ContestDetail.class);
                    intent.putExtra("ContestHostingCompany","Details for "+list.get(position).getContestName());
                    intent.putExtra("parentName","Details");
                    intent.putExtra("CompanyImage", sci);
                    context.startActivity(intent);
                    break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView contestCode;
        public TextView contestName;
        public TextView endDate;
        public TextView startDate;
        ImageView contestSourceImg;
        public ImageView imageView;
        TextView aic;
        public TextView contestSource;
        public ProgressBar progressBar;
        ImageButton notificationBell;

        RelativeLayout relativeLayout;

        ItemClickListener itemClickListener;

        DetailViewHolder(View itemView) {
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
            notificationBell = (ImageButton) itemView.findViewById(R.id.notificationBell);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.parent_layout1);

            notificationBell.setOnClickListener(this);
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

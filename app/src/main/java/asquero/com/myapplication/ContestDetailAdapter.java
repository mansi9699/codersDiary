package asquero.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContestDetailAdapter extends RecyclerView.Adapter<ContestDetailAdapter.ContestDetailViewAdapter>{

    private List<ContestDetailList> cdList;
    private Context context;

    public ContestDetailAdapter(List<ContestDetailList> cdList, Context context) {
        this.cdList = cdList;
        this.context = context;
    }

    @Override
    public ContestDetailViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contest_detail_list_layout,parent,false);
        return new ContestDetailViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(ContestDetailViewAdapter holder, int position) {

        ContestDetailList contestDetailList = cdList.get(position);

        holder.heading.setText(contestDetailList.getContestHeading());
        holder.details.setText(contestDetailList.getContestDetails());
        holder.svgImage.setImageResource(contestDetailList.getSvgImage());

    }

    @Override
    public int getItemCount() {
        return cdList.size();
    }

    public class ContestDetailViewAdapter extends RecyclerView.ViewHolder{

        public ImageView svgImage;
        public TextView heading, details;

        public ContestDetailViewAdapter(View itemView) {
            super(itemView);

            svgImage = (ImageView) itemView.findViewById(R.id.SvgImage);
            heading = (TextView) itemView.findViewById(R.id.heading);
            details = (TextView) itemView.findViewById(R.id.details);

        }
    }
}

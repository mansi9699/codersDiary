package asquero.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class HorizontalCompanyAdapter extends RecyclerView.Adapter<HorizontalCompanyAdapter.HorizontalViewHolder> {

    private List<HorizontalCompanyList> horizontalCompanyLists;
    private Context context;

    HorizontalCompanyAdapter(List<HorizontalCompanyList> companyLists, Context context) {
        this.horizontalCompanyLists = companyLists;
        this.context = context;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_recycler_view_content_layout,parent,false);
        return new HorizontalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, int position) {

        HorizontalCompanyList hCompanyLists = horizontalCompanyLists.get(position);

        holder.hTView.setText(hCompanyLists.getCompany());
        holder.hIView.setImageResource(hCompanyLists.getCompanyImage());
    }

    @Override
    public int getItemCount() {
        return horizontalCompanyLists.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder{

        private ImageView hIView;
        private TextView hTView;

        HorizontalViewHolder(View itemView) {
            super(itemView);

            hTView = (TextView) itemView.findViewById(R.id.hTextView);
            hIView = (ImageView) itemView.findViewById(R.id.himageView);
        }
    }
}

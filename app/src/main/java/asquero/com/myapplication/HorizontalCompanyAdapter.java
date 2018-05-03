package asquero.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    public void onBindViewHolder(HorizontalViewHolder holder, final int position) {

        HorizontalCompanyList hCompanyLists = horizontalCompanyLists.get(position);

        holder.hTView.setText(hCompanyLists.getCompany());
        holder.hIView.setImageResource(hCompanyLists.getCompanyImage());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",horizontalCompanyLists.get(0).getCompany());
                    intent.putExtra("CompanyImage", horizontalCompanyLists.get(0).getCompanyImage());
                    context.startActivity(intent);
                }
                else if (position == 1){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",horizontalCompanyLists.get(1).getCompany());
                    intent.putExtra("CompanyImage", horizontalCompanyLists.get(1).getCompanyImage());
                    context.startActivity(intent);
                }
                else if (position == 2){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",horizontalCompanyLists.get(2).getCompany());
                    intent.putExtra("CompanyImage", horizontalCompanyLists.get(2).getCompanyImage());
                    context.startActivity(intent);
                }
                else if (position == 3){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",horizontalCompanyLists.get(3).getCompany());
                    intent.putExtra("CompanyImage", horizontalCompanyLists.get(3).getCompanyImage());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalCompanyLists.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder{

        private ImageView hIView;
        private TextView hTView;

        private RelativeLayout relativeLayout;

        HorizontalViewHolder(View itemView) {
            super(itemView);

            hTView = (TextView) itemView.findViewById(R.id.hTextView);
            hIView = (ImageView) itemView.findViewById(R.id.himageView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.CompanyView);
        }
    }
}

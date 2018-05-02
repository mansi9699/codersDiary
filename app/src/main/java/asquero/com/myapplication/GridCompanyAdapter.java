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

public class GridCompanyAdapter extends RecyclerView.Adapter<GridCompanyAdapter.GridViewHolder>{

    private List<GridCompanyList> gridCompanyLists;
    private Context context;

    public GridCompanyAdapter(List<GridCompanyList> gridCompanyLists, Context context) {
        this.gridCompanyLists = gridCompanyLists;
        this.context = context;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_recycler_view_content_layout,parent,false);
        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, final int position) {

        GridCompanyList gCompanyLists = gridCompanyLists.get(position);

        String companyName = gCompanyLists.getCompany();
        int companyImage = gCompanyLists.getCompanyImage();

        holder.hTView.setText(companyName);
        holder.hIView.setImageResource(companyImage);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",gridCompanyLists.get(0).getCompany());
                    intent.putExtra("CompanyImage", gridCompanyLists.get(0).getCompanyImage());
                    context.startActivity(intent);
                }
                else if (position == 1){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",gridCompanyLists.get(1).getCompany());
                    intent.putExtra("CompanyImage", gridCompanyLists.get(1).getCompanyImage());
                    context.startActivity(intent);
                }
                else if (position == 2){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",gridCompanyLists.get(2).getCompany());
                    intent.putExtra("CompanyImage", gridCompanyLists.get(2).getCompanyImage());
                    context.startActivity(intent);
                }
                else if (position == 3){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",gridCompanyLists.get(3).getCompany());
                    intent.putExtra("CompanyImage", gridCompanyLists.get(3).getCompanyImage());
                    context.startActivity(intent);
                }
                else if (position == 4){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",gridCompanyLists.get(4).getCompany());
                    intent.putExtra("CompanyImage", gridCompanyLists.get(4).getCompanyImage());
                    context.startActivity(intent);
                }
                else if (position == 5){
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("CompanyName",gridCompanyLists.get(5).getCompany());
                    intent.putExtra("CompanyImage", gridCompanyLists.get(5).getCompanyImage());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return gridCompanyLists.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder{

        private ImageView hIView;
        private TextView hTView;
        private RelativeLayout relativeLayout;

        GridViewHolder(View itemView) {
            super(itemView);

            hTView = (TextView) itemView.findViewById(R.id.hTextView);
            hIView = (ImageView) itemView.findViewById(R.id.himageView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.CompanyView);
        }
    }
}

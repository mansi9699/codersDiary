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

/**
 * Created by Anmol on 05-Apr-18.
 */

class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private List<EventList> listEvent;
    private Context context;

    public EventListAdapter(List<EventList> listEvent,Context context) {
        this.listEvent = listEvent;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        EventList listItem = listEvent.get(position);

        holder.event.setText(listItem.getEvent());
        holder.imageView.setImageResource(listItem.getImage());
        holder.itemsView = listItem.getItems();

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent(context, Live.class);
                    intent.putExtra("subs",holder.itemsView);
                    context.startActivity(intent);
                }
                else if (position == 1){
                    Intent intent = new Intent(context,Upcoming.class);
                    intent.putExtra("subs",holder.itemsView);
                    context.startActivity(intent);
                }
                else if (position == 2){
                    Intent intent = new Intent(context, Ended.class);
                    intent.putExtra("subs",holder.itemsView);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView event;
        public ImageView imageView;
        public String[]itemsView;
        public RelativeLayout parent_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            event = (TextView)itemView.findViewById(R.id.eventTextView);
            imageView = (ImageView)itemView.findViewById(R.id.eventImageView);
            parent_layout = (RelativeLayout)itemView.findViewById(R.id.parent_layout);
        }
    }
}

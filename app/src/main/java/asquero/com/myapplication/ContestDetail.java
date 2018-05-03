package asquero.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContestDetail extends AppCompatActivity {

    Bundle extras;

    RelativeLayout rl;

    private String contestCompany, parentActivity;
    private int contestImage;

    private List<ContestDetailList> contestDetailLists;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter ContestDetailAdapter;

    private ImageView contestHostingCompanyImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contest_detail_layout);

        contestHostingCompanyImage = (ImageView)findViewById(R.id.ContestHostingCompanyImage);

        rl = (RelativeLayout)findViewById(R.id.contest);

        recyclerView = (RecyclerView)findViewById(R.id.contestDetailRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        extras = getIntent().getExtras();

        if (extras!=null){
        contestCompany = extras.getString("ContestHostingCompany");
        parentActivity = extras.getString("parentName");
        contestImage = extras.getInt("CompanyImage");
        }

        getSupportActionBar().setTitle(contestCompany);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contestHostingCompanyImage.setImageResource(contestImage);

        contestDetailLists = new ArrayList<>();

        /*if (parentActivity.equals("Live")||parentActivity.equals("Upcoming"))
        {
            rl.setVisibility(View.GONE);
        }*/

        contestDetailLists.add(new ContestDetailList("About","Details Go Here",R.drawable.app_icon));
        contestDetailLists.add(new ContestDetailList("Start","Details Go Here",R.drawable.app_icon));
        contestDetailLists.add(new ContestDetailList("End","Details Go Here",R.drawable.app_icon));
        contestDetailLists.add(new ContestDetailList("Duration","Details Go Here",R.drawable.app_icon));
        contestDetailLists.add(new ContestDetailList("Prizes","Details Go Here",R.drawable.app_icon));
        contestDetailLists.add(new ContestDetailList("Rules","Details Go Here",R.drawable.app_icon));
        contestDetailLists.add(new ContestDetailList("Programming Languages","Details Go Here",R.drawable.app_icon));
        contestDetailLists.add(new ContestDetailList("Eligibility Criteria","Details Go Here",R.drawable.app_icon));
        contestDetailLists.add(new ContestDetailList("For Any Queries And More Information","Details Go Here",R.drawable.app_icon));

        ContestDetailAdapter = new ContestDetailAdapter(contestDetailLists, ContestDetail.this);
        recyclerView.setAdapter(ContestDetailAdapter);


    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        return getParentActivityIntentImpl();
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        return getParentActivityIntentImpl();
    }

    private Intent getParentActivityIntentImpl() {
        Intent i = null;
        if (parentActivity.equals("Live"))
            {
                i = new Intent(this,Live.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
            }
        if (parentActivity.equals("Upcoming"))
            {
                i = new Intent(this, Upcoming.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
            }
        if (parentActivity.equals("Details"))
        {
            i = new Intent(this, DetailActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            finish();
        }
        /*if (parentActivity.equals("Live"))
        {
            i = new Intent(this, Live.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            finish();
        }*/
        return i;
    }
}

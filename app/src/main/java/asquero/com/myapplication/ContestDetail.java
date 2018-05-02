package asquero.com.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ContestDetail extends AppCompatActivity {

    Bundle extras;

    private String contestCompany;
    private TextView tView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contest_detail_layout);

        tView = (TextView) findViewById(R.id.textCompanyHostingContest);

        if (extras!=null)
        extras = getIntent().getExtras();
        contestCompany = extras.getString("ContestHostingCompany");

        getSupportActionBar().setTitle(contestCompany);

        tView.setText(contestCompany);

    }
}

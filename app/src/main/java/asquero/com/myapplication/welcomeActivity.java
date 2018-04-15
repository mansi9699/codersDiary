package asquero.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class welcomeActivity extends AppCompatActivity {
    private RelativeLayout R1,R2;

    private Animation animationUTD, animationDTU;

    private ProgressBar pBar;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        R1 = (RelativeLayout)findViewById(R.id.R1);
        R2 = (RelativeLayout)findViewById(R.id.R2);

        pBar = (ProgressBar)findViewById(R.id.progressBar);

        animationUTD = AnimationUtils.loadAnimation(this,R.anim.up_to_down);
        R1.setAnimation(animationUTD);

        animationDTU = AnimationUtils.loadAnimation(this,R.anim.down_to_up);
        R2.setAnimation(animationDTU);

        //for (i = 1 ; i <= 100 ;i++) {
           //pBar.setProgress(i);
            //if (i==100) {
                Thread splashThread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(5000);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                splashThread.start();
            //}
        //}

    }
}

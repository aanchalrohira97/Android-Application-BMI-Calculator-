package aanchal.example.com.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SpActivity extends AppCompatActivity {
    ImageView iml;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sp);


        iml=(ImageView)findViewById(R.id.iml);

        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.a1);

        iml.startAnimation(animation);



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i=new Intent(SpActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }).start();
    }
}

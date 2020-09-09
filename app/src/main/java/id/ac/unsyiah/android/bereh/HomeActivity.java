package id.ac.unsyiah.android.bereh;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.synnapps.carouselview.ImageListener;

import android.support.v4.view.ViewPager;
import java.util.Timer;
import java.util.TimerTask;


public class HomeActivity extends AppCompatActivity {

    ViewPager viewPager;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

    }



    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    }else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void koleksi(View view) {
        Intent intent = new Intent(HomeActivity.this, KoleksiActivity.class);
        startActivity(intent);
    }

    public void Akun(View view) {
        Intent intent = new Intent(HomeActivity.this, AkunActivity.class);
        startActivity(intent);
    }

    public void Arsip(View view) {
        Intent intent = new Intent(HomeActivity.this, ArsipActivity.class);
        startActivity(intent);
    }



}

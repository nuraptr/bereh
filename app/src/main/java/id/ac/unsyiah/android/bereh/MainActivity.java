package id.ac.unsyiah.android.bereh;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import id.ac.unsyiah.android.bereh.Session.SharedPreference;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;

    private Button PindahActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        actionBar = getSupportActionBar();
        actionBar.hide();

        //Memanggil id button dari layout activity_main
        PindahActivity = (Button) findViewById(R.id.button);

        PindahActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Membuat intent untuk berpindah activity dari MainActivity ke SecondActivity
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                //Menjalankan Activity
                startActivity(intent);
                //Menutup MainActivity
                MainActivity.this.finish();
            }
        });



    }

}

package id.ac.unsyiah.android.bereh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.unsyiah.android.bereh.Session.SharedPreference;

public class AkunActivity extends AppCompatActivity {

    Button logout;
    SharedPreference session;
    ActionBar actionBar;
    TextView namaUser;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        logout = findViewById(R.id.logout);
        session = new SharedPreference(AkunActivity.this);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //session = new SharedPreference(AkunActivity.this);
                session.logoutUser();
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });

        namaUser = findViewById(R.id.namaUser);
        namaUser.setText(session.getNama());



    }
}

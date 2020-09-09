package id.ac.unsyiah.android.bereh;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.unsyiah.android.bereh.POJO.DataUserPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.UserPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.Value;


import id.ac.unsyiah.android.bereh.RetrofitHelper.RetrofitHelper;
import id.ac.unsyiah.android.bereh.Session.SharedPreference;
import id.ac.unsyiah.android.bereh.Util.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    ActionBar actionBar;

    EditText nama, username, password;
    TextView btnRegister;
    SharedPreference session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView login = (TextView) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });

        actionBar = getSupportActionBar();
        actionBar.hide();

        session = new SharedPreference(this);
        nama = findViewById(R.id.rNama);
        username = findViewById(R.id.rUsername);
        password = findViewById(R.id.rPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();

            }
        });
    }

    public void performRegistration(){
        if (username.getText().toString().equals("") || nama.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "Tidak Boleh Ada Data yang Kosong", Toast.LENGTH_SHORT).show();
        }else{

            Retrofit retrofit = new RetrofitHelper().retrofitBuilder();
            final WebService webService = retrofit.create(WebService.class);

            Toast.makeText(RegisterActivity.this, "bisa", Toast.LENGTH_SHORT).show();

            Call<Value> call = webService.tambahUser(
                    nama.getText().toString(),
                    username.getText().toString(),
                    password.getText().toString());

            call.enqueue(new Callback<Value>() {

                @Override
                public void onResponse(Call<Value> call, Response<Value> response) {
                    Call<Value> login = webService.loginUser(
                            username.getText().toString(),
                            password.getText().toString()
                    );
                    login.enqueue(new Callback<Value>() {
                        @Override
                        public void onResponse(Call<Value> call, Response<Value> response) {
                            if((response.body().kode)==1){
                                Toast.makeText(RegisterActivity.this, "Berhasil Daftar", Toast.LENGTH_SHORT).show();
                                getUserNamePreference();
                                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            }}

                        @Override
                        public void onFailure(Call<Value> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<Value> call, Throwable t) {

                }
            });
        }


    }

    private void getUserNamePreference() {

        Retrofit retrofit2 = RetrofitHelper.retrofitBuilder();
        WebService webservice2 = retrofit2.create(WebService.class);

        Call<UserPOJO> call = webservice2.dataUser(
                username.getText().toString(),
                password.getText().toString()
        );
        call.enqueue(new Callback<UserPOJO>() {
            @Override
            public void onResponse(Call<UserPOJO> call, Response<UserPOJO> response) {

                session = new SharedPreference(RegisterActivity.this);
                session.LoginSession();
                session.setID(response.body().id);
                session.setUserName(response.body().username);
                session.setNama(response.body().nama);

//                dataUser = new DataUser();
//                dataUser.setNama(response.body().nama);
            }

            @Override
            public void onFailure(Call<UserPOJO> call, Throwable t) {

            }
        });

    }
}

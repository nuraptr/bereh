package id.ac.unsyiah.android.bereh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.unsyiah.android.bereh.POJObaru.UserPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.Value;
import id.ac.unsyiah.android.bereh.RetrofitHelper.RetrofitHelper;
import id.ac.unsyiah.android.bereh.Session.SharedPreference;
import id.ac.unsyiah.android.bereh.Util.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    ActionBar actionBar;

    EditText username, password;
    SharedPreference session;
    TextView btnLogin;
//    public DataUser dataUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        session = new SharedPreference(this);
//
//        if(session.isLoggedIn()){
//            Intent ia = new Intent(getApplicationContext(),MainActivity.class);
//            ia.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(ia);
//            finish();
//        }

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        final TextView signup = (TextView) findViewById(R.id.btnRegister);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });


        actionBar = getSupportActionBar();
        actionBar.hide();


        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(v.getContext());
                pd.setMessage("Please wait ...");
                pd.show();

                //Toast.makeText(LoginActivity.this, "bisa", Toast.LENGTH_SHORT).show();

                Retrofit retrofit2 = RetrofitHelper.retrofitBuilder();
                WebService webService = retrofit2.create(WebService.class);

                retrofit2.Call<Value> call = webService.loginUser(
                        username.getText().toString(),
                        password.getText().toString()
                );

                //Toast.makeText(LoginActivity.this, "bisaaa", Toast.LENGTH_SHORT).show();

                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        Toast.makeText(LoginActivity.this, response.body().pesan, Toast.LENGTH_SHORT).show();

                        if((response.body().kode)==1){
                            pd.cancel();

                            getUserNamePreference();
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            finish();
                        }else {
                            pd.cancel();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                    }
                });
            }
        });

    }


    private void getUserNamePreference() {

        Retrofit retrofit2 = RetrofitHelper.retrofitBuilder();
        WebService webservice = retrofit2.create(WebService.class);

        Call<UserPOJO> call = webservice.dataUser(
                username.getText().toString(),
                password.getText().toString()
        );
        call.enqueue(new Callback<UserPOJO>() {
            @Override
            public void onResponse(Call<UserPOJO> call, Response<UserPOJO> response) {

                session = new SharedPreference(LoginActivity.this);
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

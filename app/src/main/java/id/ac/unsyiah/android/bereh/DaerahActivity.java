package id.ac.unsyiah.android.bereh;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.unsyiah.android.bereh.POJObaru.ReseppPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.Value;
import id.ac.unsyiah.android.bereh.RetrofitHelper.RetrofitHelper;
import id.ac.unsyiah.android.bereh.Util.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DaerahActivity extends AppCompatActivity {

    ActionBar actionBar;
    RecyclerView recyclerView;
    private List<ReseppPOJO> results = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daerah);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.rv);


        Retrofit retrofit = new RetrofitHelper().retrofitBuilder();
        WebService webService = retrofit.create(WebService.class);

        String id = getIntent().getStringExtra("id");

        Call<Value> call = webService.cariResep(id);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                if ((response.body().kode)== 1){
                    results = response.body().getHasil();
                    updateDisplay();
                }
                else{
                    Toast.makeText(DaerahActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    protected void updateDisplay(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;

        recyclerView.setLayoutManager(rvLiLayoutManager);

        DaerahAdapter adapter = new DaerahAdapter(DaerahActivity.this, results);

        recyclerView.setAdapter(adapter);
    }

}

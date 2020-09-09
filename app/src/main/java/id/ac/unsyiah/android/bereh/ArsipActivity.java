package id.ac.unsyiah.android.bereh;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import id.ac.unsyiah.android.bereh.POJObaru.ArsipPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.Value;
import id.ac.unsyiah.android.bereh.RetrofitHelper.RetrofitHelper;
import id.ac.unsyiah.android.bereh.Session.SharedPreference;
import id.ac.unsyiah.android.bereh.Util.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ArsipActivity extends AppCompatActivity {
    ListView listView;
    private List<ArsipPOJO> arsipPOJOList;
    ActionBar actionBar;
    SharedPreference session;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arsip);

//        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.activity_arsip, arsipList);
//        ListView listview =(ListView) findViewById(R.id.listArsip);
//        listview.setAdapter(adapter);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listArsip);

        loadArsip();
    }

    public void loadArsip(){
        Retrofit retrofit2 = RetrofitHelper.retrofitBuilder();
        WebService webService = retrofit2.create(WebService.class);

        session = new SharedPreference(ArsipActivity.this);
        id_user = session.getID();

        Toast.makeText(this, id_user, Toast.LENGTH_SHORT).show();

        Call<Value> call = webService.daftarArsip(id_user);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Toast.makeText(ArsipActivity.this, "bisa", Toast.LENGTH_SHORT).show();

                List<ArsipPOJO> arsipList = response.body().getHasilArsip();
                arsipPOJOList = arsipList;

                String[] items = new String[arsipPOJOList.size()];

                for (int i = 0; i < arsipPOJOList.size(); i++) {
                    items[i] = arsipPOJOList.get(i).nama;
                }
                //Membuat Array Adapter for listview
                ArrayAdapter adapter = new ArrayAdapter<String>(ArsipActivity.this, R.layout.activity_listview, items);

                //setting adapter untuk listview
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    public void dataArsip(View view) {
        Toast.makeText(ArsipActivity.this, "bisa", Toast.LENGTH_SHORT).show();
    }
}

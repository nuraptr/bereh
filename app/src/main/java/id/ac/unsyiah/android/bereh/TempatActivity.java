package id.ac.unsyiah.android.bereh;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.unsyiah.android.bereh.POJObaru.TempatPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.Value;
import id.ac.unsyiah.android.bereh.RetrofitHelper.RetrofitHelper;
import id.ac.unsyiah.android.bereh.Util.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TempatActivity extends AppCompatActivity {
    ListView listView;
    private List<TempatPOJO> tempatPOJOList;
    //private TempatPOJO tempatPOJO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat);

//        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.activity_tempat, tempatList);
//        ListView listview =(ListView) findViewById(R.id.listTempat);
//        listview.setAdapter(adapter);


        listView = (ListView) findViewById(R.id.listTempat);

        loadTempat();
    }

    public void loadTempat(){
        Retrofit retrofit2 = RetrofitHelper.retrofitBuilder();
        WebService webService = retrofit2.create(WebService.class);

        String id = getIntent().getStringExtra("id");
        //Toast.makeText(this, "bisa" + id, Toast.LENGTH_SHORT).show();

        Call<Value> call = webService.cariTempat(id);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                //Toast.makeText(TempatActivity.this, "bisa", Toast.LENGTH_SHORT).show();
                //final ArrayList<List<TempatPOJO>> list = new ArrayList<>();

//                tempatList = response.body().getTempat();
//
//                for(int i = 0; i < tempatList.size() ; i++){
//                    tempatPOJO = tempatList.get(i);
//                    list.add(tempatPOJO.getTempat());
//
//                }
//                ListAdapter adapter = new ArrayAdapter(TempatActivity.this,R.layout.activity_tempat, list);
//                listView.setAdapter(adapter);

                List<TempatPOJO> tempatList = response.body().getTempat();
                tempatPOJOList = tempatList;

                String[] items = new String[tempatPOJOList.size()];

                for (int i = 0; i < tempatPOJOList.size(); i++) {
                    items[i] = tempatPOJOList.get(i).tempat;
                }
                //Membuat Array Adapter for listview
                ArrayAdapter adapter = new ArrayAdapter<String>(TempatActivity.this, R.layout.activity_listview, items);

                //setting adapter untuk listview
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }
}

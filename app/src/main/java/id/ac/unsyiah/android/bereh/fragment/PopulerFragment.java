package id.ac.unsyiah.android.bereh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.unsyiah.android.bereh.DaerahActivity;
import id.ac.unsyiah.android.bereh.GridAdapterPopuler;
import id.ac.unsyiah.android.bereh.POJObaru.PopulerPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.Value;
import id.ac.unsyiah.android.bereh.R;
import id.ac.unsyiah.android.bereh.ResepActivity;
import id.ac.unsyiah.android.bereh.RetrofitHelper.RetrofitHelper;
import id.ac.unsyiah.android.bereh.Util.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopulerFragment extends Fragment {


//    public PopulerFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_populer, container, false);
//    }

    private GridView gridView;
    private List<PopulerPOJO> results = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_populer, container, false);

        gridView = (GridView)v.findViewById(R.id.quotesGridView);

        GridAdapterPopuler adapter = new GridAdapterPopuler(getActivity(), results);

        gridView.setAdapter(adapter);

        loadMakanan();
        return v;
    }
    private void loadMakanan(){
        Retrofit retrofit2 = RetrofitHelper.retrofitBuilder();
        WebService webService = retrofit2.create(WebService.class);

        Call<Value> call = webService.daftarPopuler();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                //String valuee = response.body().getKode();
                if ((response.body().kode)== 1){
                    results = response.body().getResultt();
                    GridAdapterPopuler gridAdapter = new GridAdapterPopuler(getActivity(), results);
                    gridView.setAdapter(gridAdapter);

//                             gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                                    Intent intent = new Intent(getActivity(), DaerahActivity.class);
//                                    startActivity(intent);
//                                }
//                            });
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });

    }

}

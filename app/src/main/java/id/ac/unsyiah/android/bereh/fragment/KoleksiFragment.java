package id.ac.unsyiah.android.bereh.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import id.ac.unsyiah.android.bereh.DaerahActivity;
import id.ac.unsyiah.android.bereh.GridAdapter;
import id.ac.unsyiah.android.bereh.POJObaru.MakananPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.Value;
import id.ac.unsyiah.android.bereh.R;
import id.ac.unsyiah.android.bereh.RetrofitHelper.RetrofitHelper;
import id.ac.unsyiah.android.bereh.Util.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoleksiFragment extends Fragment {


//    public KoleksiFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_koleksi, container, false);
//    }


    ///versi kedua
//    GridView gridView;
//    ArrayList<String> mItems;
//
//    String letterList[] = {"MJsdc" , "MJsdc", "MJsdc", "MJsdc", "MJsdc", "MJsdc", "MJsdc", "MJsdc"};
//    int letterIcon[] = {R.drawable.slide1, R.drawable.slide1,R.drawable.slide1,R.drawable.slide1,R.drawable.slide1,R.drawable.slide1,R.drawable.slide1,R.drawable.slide1 };
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_koleksi, container, false);
//
//        gridView = (GridView)v.findViewById(R.id.quotesGridView);
//
//        GridAdapter adapter = new GridAdapter(getActivity(), letterIcon, letterList);
//
//        gridView.setAdapter(adapter);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Memilih: " +letterList[position], Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(getActivity(), DaerahActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        //setupAdapter();
//        return v;
//    }

        private GridView gridView;
        private List<MakananPOJO> results = new ArrayList<>();

            @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_koleksi, container, false);

            gridView = (GridView)v.findViewById(R.id.quotesGridView);

            GridAdapter adapter = new GridAdapter(getActivity(), results);

            gridView.setAdapter(adapter);

//            if (isOnline()==false){
//                Toast.makeText(getActivity(), "Tidak ada Koneksi", Toast.LENGTH_SHORT).show();
//            }
//            Toast.makeText(getActivity(), "Terhubung", Toast.LENGTH_SHORT).show();

            loadMakanan();
            return v;
        }
         private void loadMakanan(){
             Retrofit retrofit2 = RetrofitHelper.retrofitBuilder();
             WebService webService = retrofit2.create(WebService.class);

             Call<Value> call = webService.daftarMakanan();
                 call.enqueue(new Callback<Value>() {
                     @Override
                     public void onResponse(Call<Value> call, Response<Value> response) {
                         //String valuee = response.body().getKode();
                         if((response.body().kode)== 1){
                             results = response.body().getResult();
                             GridAdapter gridAdapter = new GridAdapter(getActivity(), results);
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

//    protected boolean isOnline(){ //nyambung internet
//        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//            return true;
//        }else{
//            return false;
//        }
//    }

}

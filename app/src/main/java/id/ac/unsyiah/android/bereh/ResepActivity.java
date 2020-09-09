package id.ac.unsyiah.android.bereh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.ac.unsyiah.android.bereh.POJObaru.ReseppPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.Value;
import id.ac.unsyiah.android.bereh.RetrofitHelper.RetrofitHelper;
import id.ac.unsyiah.android.bereh.Session.SharedPreference;
import id.ac.unsyiah.android.bereh.Util.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResepActivity extends AppCompatActivity {

    ActionBar actionBar;
    Button tempat, arsip;
    Context context;
    SharedPreference session;
    String id_user;
    Float rating;
    ImageView imageView;

    private static Button button_sbm;
    private static TextView text_v;
    private static RatingBar rating_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        getIncomingIntent();
        final String id = getIntent().getStringExtra("id");
        tempat = findViewById(R.id.tempat);
        tempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResepActivity.this, TempatActivity.class);
                intent.putExtra("id", id);
                Toast.makeText(ResepActivity.this, id, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        arsip = findViewById(R.id.arsip);
        arsip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new RetrofitHelper().retrofitBuilder();
                WebService webService = retrofit.create(WebService.class);

                String id_resep = getIntent().getStringExtra("id");

                session = new SharedPreference(ResepActivity.this);
                id_user = session.getID();

//                Toast.makeText(ResepActivity.this, "tes " + id_resep + id_user, Toast.LENGTH_SHORT).show();

                Call<Value> call = webService.simpanArsip(id_user, id_resep );
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
//                        if ((response.body().kode)== 1){
                            Toast.makeText(ResepActivity.this,response.body().pesan , Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(ResepActivity.this, "gagal", Toast.LENGTH_SHORT).show();
//                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                    }
                });

                //Toast.makeText(ResepActivity.this, "Disimpan ke arsip.", Toast.LENGTH_SHORT).show();
            }
        });

        listenerForRatingBar();
        onButtonClickListener();



    }

    private void getIncomingIntent(){
        //Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("nama")){
            //Log.d(TAG, "getIncomingIntent: found intent extras.");
            String id = getIntent().getStringExtra("id");
            String id_makanan = getIntent().getStringExtra("id_makanan");
            String nama = getIntent().getStringExtra("nama");
            String waktu = getIntent().getStringExtra("waktu");
            String bahan = getIntent().getStringExtra("bahan");
            String cara = getIntent().getStringExtra("cara");
            String rating = getIntent().getStringExtra("rating");

            //Toast.makeText(context, "rating" +rating, Toast.LENGTH_SHORT).show();

            setResep(nama, waktu, bahan, cara, rating);


            imageView = findViewById(R.id.gambar);

            if(id_makanan.equals("3")){
                imageView.setImageResource(R.drawable.slide2);
            }else if(id_makanan.equals("4")){
                imageView.setImageResource(R.drawable.slide3);
            }else if(id_makanan.equals("5")){
                imageView.setImageResource(R.drawable.slide1);
            }else if(id_makanan.equals("6")){
                imageView.setImageResource(R.drawable.keumamah);
            }else if(id_makanan.equals("7")){
                imageView.setImageResource(R.drawable.timphan);
            }



        }
    }

    public void setResep(String nama, String waktu, String bahan, String cara, String rating){

        TextView name = findViewById(R.id.nama);
        name.setText(nama);

        TextView waktuu = findViewById(R.id.waktu);
        waktuu.setText(waktu);

        TextView bahann = findViewById(R.id.bahan);
        bahann.setText(bahan);

        TextView caraa = findViewById(R.id.cara);
        caraa.setText(cara);

        if (rating == "5"){
            ImageView star = findViewById(R.id.ratingHasil);
            star.setImageResource(R.drawable.rating5);
        }else if(rating == "4"){
            ImageView star = findViewById(R.id.ratingHasil);
            star.setImageResource(R.drawable.rating4);
        }else if(rating == "3") {
            ImageView star = findViewById(R.id.ratingHasil);
            star.setImageResource(R.drawable.rating3);
        }else if(rating == "2") {
            ImageView star = findViewById(R.id.ratingHasil);
            star.setImageResource(R.drawable.rating2);
        }else if(rating == "1") {
            ImageView star = findViewById(R.id.ratingHasil);
            star.setImageResource(R.drawable.rating1);
        }else {
            ImageView star = findViewById(R.id.ratingHasil);
            star.setImageResource(R.drawable.rating0);
        }




    }

    public void listenerForRatingBar() {
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
        text_v = (TextView) findViewById(R.id.beriRating);

        rating_b.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        text_v.setText(String.valueOf(v));
                    }
                }
        );
    }

    public void onButtonClickListener() {
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
        button_sbm = (Button) findViewById(R.id.buttonSubmit);


        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(ResepActivity.this, String.valueOf(rating_b.getRating()), Toast.LENGTH_SHORT).show();

                        rating = rating_b.getRating();

                        Retrofit retrofit = new RetrofitHelper().retrofitBuilder();
                        WebService webService = retrofit.create(WebService.class);

                        String id_resep = getIntent().getStringExtra("id");

//                Toast.makeText(ResepActivity.this, "tes " + id_resep + id_user, Toast.LENGTH_SHORT).show();

                        Call<Value> call = webService.beriRating( id_resep, rating );
                        call.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
//                        if ((response.body().kode)== 1){
                                Toast.makeText(ResepActivity.this,response.body().pesan , Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(ResepActivity.this, "gagal", Toast.LENGTH_SHORT).show();
//                        }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {

                            }
                        });
                    }
                }
        );
    }




}

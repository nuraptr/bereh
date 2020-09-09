package id.ac.unsyiah.android.bereh.RetrofitHelper;

import id.ac.unsyiah.android.bereh.Util.Konstanta;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Asus on 6/5/2018.
 */

public class RetrofitHelper {
    public static Retrofit retrofitBuilder(){

        return new Retrofit.Builder()
                .baseUrl(Konstanta.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

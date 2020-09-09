package id.ac.unsyiah.android.bereh.Util;

import java.util.List;

import id.ac.unsyiah.android.bereh.POJO.DataUserPOJO;
import id.ac.unsyiah.android.bereh.POJO.ResponsePOJO;

import id.ac.unsyiah.android.bereh.POJObaru.TempatPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.UserPOJO;
import id.ac.unsyiah.android.bereh.POJObaru.Value;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Asus on 6/5/2018.
 */

public interface WebService {
    @FormUrlEncoded
    @POST(Konstanta.TAMBAH_USER)
    public Call<Value> tambahUser(
            @Field("nama") String nama,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(Konstanta.LOGIN_USER)
    public Call<Value> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(Konstanta.TAKE_DATA_USER)
    public Call<UserPOJO> dataUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET(Konstanta.TAKE_DATA_MAKANAN)
    Call<Value> daftarMakanan();


    @FormUrlEncoded
    @POST(Konstanta.TAKE_DATA_RESEP)
    public Call<Value> cariResep(
            @Field("id") String id
    );

//    @GET(Konstanta.TAKE_DATA_RESEP)
//    Call<Value> daftarResep();

    @GET(Konstanta.TAKE_DATA_POPULER)
    Call<Value> daftarPopuler();

    @FormUrlEncoded
    @POST(Konstanta.TAKE_DATA_TEMPAT)
    public Call<Value> cariTempat(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST(Konstanta.ARSIP)
    public Call<Value> simpanArsip(
            @Field("id_user") String id_user,
            @Field("id_resep") String id_resep
    );

    @FormUrlEncoded
    @POST(Konstanta.TAKE_DATA_ARSIP)
    Call<Value> daftarArsip(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST(Konstanta.RATING)
    Call<Value> beriRating(
            @Field("id_resep") String id_resep,
            @Field("jumlah") float jumlah
    );

}
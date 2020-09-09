package id.ac.unsyiah.android.bereh.Session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Asus on 6/5/2018.
 */

public class SharedPreference {
    SharedPreferences pref;
    Context context;
    SharedPreferences.Editor editor;
    public static final String REFERENCES = "MyReferences";

    //int PRIVATE_MODE = 0;

    public SharedPreference(Context context){
        this.context = context;
        pref = context.getSharedPreferences(REFERENCES, context.MODE_PRIVATE);
        editor = pref.edit();

    }
    public void LoginSession(){

        editor.putBoolean("IsLogin", true);
        editor.commit();
    }

    public void setUserName(String username){
        editor.putString("Username", username);
        editor.commit();
    }

    public void setID(String id){
        editor.putString("ID", id);
        editor.commit();
    }
    public void setNama(String nama){
        editor.putString("Nama", nama);
        editor.commit();
    }


    public void logoutUser(){
        editor.clear();
        editor.commit();

    }
    public boolean isLoggedIn(){
        return pref.getBoolean("IsLogin", false);
    }

    public String getUsername(){
        String username = pref.getString("Username","");

        return username;

    }
    public String getNama(){
        String nama = pref.getString("Nama","");

        return nama;

    }
    public String getID(){
        String id = pref.getString("ID","");

        return id;

    }

}

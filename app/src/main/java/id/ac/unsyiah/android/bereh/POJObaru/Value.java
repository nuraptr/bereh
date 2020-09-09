package id.ac.unsyiah.android.bereh.POJObaru;

import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by Asus on 6/22/2018.
 */

public class Value {
    public int kode;
    public String pesan;
    List<MakananPOJO> respone;
    List<ReseppPOJO> hasil;
    List<PopulerPOJO> result;
    List<UserPOJO> user;
    List<TempatPOJO> tempat;
    List<ArsipPOJO> hasilArsip;

    public List<ReseppPOJO> getHasil() {
        return hasil;
    }
    //    public String getKode() {
//        return kode;
//    }

//    public String getPesan() {
//        return pesan;
//    }

    public List<MakananPOJO> getResult() {
        return respone;
    }

    public List<PopulerPOJO> getResultt() {
        return result;
    }

    public List<UserPOJO> getUser() {
        return user;
    }

    public List<TempatPOJO> getTempat() {
        return tempat;
    }

    public List<ArsipPOJO> getHasilArsip() {
        return hasilArsip;
    }
}

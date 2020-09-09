package id.ac.unsyiah.android.bereh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.ac.unsyiah.android.bereh.fragment.KoleksiFragment;
import id.ac.unsyiah.android.bereh.fragment.PopulerFragment;

public class PageAdapter extends FragmentPagerAdapter{

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                KoleksiFragment tabKoleksi = new KoleksiFragment();
                return tabKoleksi;

            case 1:
                PopulerFragment tabPopuler = new PopulerFragment();
                return tabPopuler;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

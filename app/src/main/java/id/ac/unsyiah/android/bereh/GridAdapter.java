package id.ac.unsyiah.android.bereh;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.unsyiah.android.bereh.POJObaru.MakananPOJO;

/**
 * Created by Asus on 6/22/2018.
 */

public class GridAdapter extends BaseAdapter {

//    private int icons[];
//    private String letters[];
//    private Context context;
//    private LayoutInflater inflater;
//
//    public GridAdapter(Context context, int[] icons, String[] letters) {
//        this.icons = icons;
//        this.letters = letters;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return letters.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return letters[position];
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View gridView = convertView;
//
//        if(convertView == null){
//
//            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            gridView = inflater.inflate(R.layout.custom_layout_grid,null);
//        }
//
//        ImageView icon = (ImageView) gridView.findViewById(R.id.imageView);
//        TextView letter = (TextView) gridView.findViewById(R.id.textView);
//
//        icon.setImageResource(icons[position]);
//        letter.setText(letters[position]);
//
//        return gridView;
//    }

    private Context context;
    private List<MakananPOJO> makanan;
    private LayoutInflater inflater;

    public GridAdapter(Context context, List<MakananPOJO> makanan) {
        this.context = context;
        this.makanan = makanan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if(convertView == null){

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.custom_layout_grid,null);
        }



        ImageView icon = (ImageView) gridView.findViewById(R.id.imageView);
        TextView letter = (TextView) gridView.findViewById(R.id.textView);

        final MakananPOJO result = makanan.get(position);

//        icon.setImageResource(R.drawable.slide3);
        letter.setText(result.getNama());
        if((result.getNama()).equals("Kuah Plik U")){
            icon.setImageResource(R.drawable.slide2);
        }else if((result.getNama()).equals("Kuah Sie Kameng")) {
            icon.setImageResource(R.drawable.slide3);
        }else if((result.getNama()).equals("Mie Aceh")){
            icon.setImageResource(R.drawable.slide1);
        }else if((result.getNama()).equals("Keumamah")) {
            icon.setImageResource(R.drawable.keumamah);
        }else if((result.getNama()).equals("Timphan")) {
            icon.setImageResource(R.drawable.timphan);
        }

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(context, result.getIdMakanan(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, DaerahActivity.class);
                intent.putExtra("id", result.getIdMakanan());
//                intent.putExtra("nama", result.getNama());
//                intent.putExtra("bahan", result.getBahan());
//                intent.putExtra("cara", result.getCara());
//                intent.putExtra("waktu", result.getWaktu());
//                intent.putExtra("rating", result.getRating());
                context.startActivity(intent);
            }
        });



        return gridView;
    }


    @Override
    public int getCount() {
        return makanan.size();
    }

    @Override
    public Object getItem(int position) {
        return makanan;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


}

package id.ac.unsyiah.android.bereh;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.ac.unsyiah.android.bereh.POJObaru.PopulerPOJO;

public class GridAdapterPopuler extends BaseAdapter {

    private Context context;
    private List<PopulerPOJO> makanan;
    private LayoutInflater inflater;

    public GridAdapterPopuler(Context context, List<PopulerPOJO> makanan) {
        this.context = context;
        this.makanan = makanan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if(convertView == null){

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.custom_layout_grid_populer,null);
        }



        ImageView icon = (ImageView) gridView.findViewById(R.id.imageView);
        TextView letter = (TextView) gridView.findViewById(R.id.textView);
        TextView rating = (TextView) gridView.findViewById(R.id.jumlahRating);

        final PopulerPOJO result = makanan.get(position);

//        icon.setImageResource(R.drawable.slide3);
        letter.setText(result.getNama());
        rating.setText(result.getRating());

        if((result.getIdMakanan()).equals("3")){
            icon.setImageResource(R.drawable.slide2);
        }else if((result.getIdMakanan()).equals("4") ){
            icon.setImageResource(R.drawable.slide3);
        }else if((result.getIdMakanan()).equals("5")){
            icon.setImageResource(R.drawable.slide1);
        }else if((result.getIdMakanan()).equals("6")){
            icon.setImageResource(R.drawable.keumamah);
        }else if((result.getIdMakanan()).equals("7")){
            icon.setImageResource(R.drawable.timphan);
        }

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, result.getRating(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ResepActivity.class);
                intent.putExtra("id", result.getId());
                intent.putExtra("id_makanan", result.getIdMakanan());
                intent.putExtra("nama", result.getNama());
                intent.putExtra("bahan", result.getBahan());
                intent.putExtra("cara", result.getCara());
                intent.putExtra("waktu", result.getWaktu());
                intent.putExtra("rating", result.getRating());
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

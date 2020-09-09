package id.ac.unsyiah.android.bereh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.ac.unsyiah.android.bereh.POJObaru.ReseppPOJO;

import static android.content.ContentValues.TAG;

/**
 * Created by Asus on 6/12/2018.
 */

public class DaerahAdapter extends RecyclerView.Adapter<DaerahAdapter.ViewHolder> {

    private Context mContext;
    ImageView imageView;
    private List<ReseppPOJO> resep;
    DaerahAdapter(Context context, List<ReseppPOJO> list){
        mContext = context;
        resep = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.rv_daerah_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ReseppPOJO result = resep.get(position);

        ImageView image = holder.item_image;
        TextView name = holder.item_name;
        TextView tombol = holder.button;

        //image.setImageResource(R.drawable.slide2);
        name.setText(result.getNama());

//        imageView = imageView.findViewById(R.id.gambarDaerah);

        if((result.getIdMakanan()).equals("3")){
            image.setImageResource(R.drawable.slide2);
        }else if((result.getIdMakanan()).equals("4") ){
            image.setImageResource(R.drawable.slide3);
        }else if((result.getIdMakanan()).equals("5")){
            image.setImageResource(R.drawable.slide1);
        }else if((result.getIdMakanan()).equals("6")){
            image.setImageResource(R.drawable.keumamah);
        }else if((result.getIdMakanan()).equals("7")){
            image.setImageResource(R.drawable.timphan);
        }

        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + result.getNama());

                Toast.makeText(mContext, result.getNama(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, ResepActivity.class);
                intent.putExtra("id", result.getId());
                intent.putExtra("id_makanan", result.getIdMakanan());
                intent.putExtra("nama", result.getNama());
                intent.putExtra("bahan", result.getBahan());
                intent.putExtra("cara", result.getCara());
                intent.putExtra("waktu", result.getWaktu());
                intent.putExtra("rating", result.getRating());

                //Toast.makeText(mContext, result.getNama(), Toast.LENGTH_SHORT).show();
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return resep.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_image;
        TextView item_name, button;

        public ViewHolder(View itemView){
            super (itemView);

            item_image = itemView.findViewById(R.id.gambarDaerah);
            item_name = itemView.findViewById(R.id.item_name);
            button = itemView.findViewById(R.id.lihat);
        }
    }
}
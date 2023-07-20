package com.azhar.wisatapurwakarta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.wisatapurwakarta.R;
import com.azhar.wisatapurwakarta.model.ModelKomunitas;
import com.azhar.wisatapurwakarta.model.ModelKomunitas;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class KomunitasAdapter extends RecyclerView.Adapter<KomunitasAdapter.ViewHolder> {

    private List<ModelKomunitas> items;
    private KomunitasAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelKomunitas modelKomunitas);
    }

    public KomunitasAdapter(Context context, List<ModelKomunitas> items, KomunitasAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public KomunitasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_komunitas, parent, false);
        return new KomunitasAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KomunitasAdapter.ViewHolder holder, int position) {
        final ModelKomunitas data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarKomunitas())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgKomunitas);

        holder.tvKategori.setText(data.getKategoriKomunitas());
        holder.tvKomunitas.setText(data.getTxtNamaKomunitas());
        holder.cvKomunitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKomunitas;
        public TextView tvKategori;
        public CardView cvKomunitas;
        public ImageView imgKomunitas;

        public ViewHolder(View itemView) {
            super(itemView);
            cvKomunitas = itemView.findViewById(R.id.cvKomunitas);
            tvKomunitas = itemView.findViewById(R.id.tvKomunitas);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            imgKomunitas = itemView.findViewById(R.id.imgKomunitas);
        }
    }

}

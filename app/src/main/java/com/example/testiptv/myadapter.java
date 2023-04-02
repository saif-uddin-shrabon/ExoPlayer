package com.example.testiptv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {

    ArrayList<model> dataholder;

    public myadapter(ArrayList<model> dataholder){
        this.dataholder = dataholder;
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.cname.setText(dataholder.get(position).getCOLUMN_NAME());
        System.out.println("NameChannel: "+dataholder.get(position).getCOLUMN_NAME());
//        holder.clogo.setText(dataholder.get(position).getCOLUMN_LOGO());
//        holder.clink.setText(dataholder.get(position).getCOLUMN_LINK());
        System.out.println("URLCHANNEL: "+dataholder.get(position).getCOLUMN_LINK());
        String logoLInk = dataholder.get(position).getCOLUMN_LOGO();
        System.out.println("LOGO_link : "+logoLInk);
        Glide.with(holder.clogo.getContext()).load(dataholder.get(position).getCOLUMN_LOGO()).into(holder.clogo);

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView cname,clink;
        ImageView clogo;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            cname =(TextView) itemView.findViewById(R.id.cadName);
            clogo = (ImageView) itemView.findViewById(R.id.viewImage);
            clink = (TextView) itemView.findViewById(R.id.cadlink);

        }
    }
}


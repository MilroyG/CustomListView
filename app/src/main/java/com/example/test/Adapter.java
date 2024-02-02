package com.example.test;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private Context mContext;
    private List<Name> mData;


    public Adapter(Context mContext, List<Name> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v= inflater.inflate(R.layout.name, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.fname.setText(mData.get(position).getFname());
        holder.lname.setText(mData.get(position).getLname());
        holder.email.setText(mData.get(position).getEmail());


        //Glide LIBRARY TO LOADIMAGE.
        Glide.with(mContext)
                .load(mData.get(position).getImg())
                .into(holder.img);

    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView lname;
        TextView fname;
        TextView email;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        fname = itemView.findViewById(R.id.fname);
        lname = itemView.findViewById(R.id.lname);
        email = itemView.findViewById(R.id.email);
        img = itemView.findViewById(R.id.img);
        }


    }
}

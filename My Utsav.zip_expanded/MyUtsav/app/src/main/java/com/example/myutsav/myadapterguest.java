package com.example.myutsav;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapterguest extends RecyclerView.Adapter<myadapterguest.myviewholder> {
    ArrayList <guestdatamodel> dataholder;
    Context context;
    public myadapterguest(@NonNull ArrayList<guestdatamodel> dataholder,Context context) {
        this.dataholder = dataholder;
        this.context=context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guestsinglerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        final guestdatamodel obj=dataholder.get(position);
        holder.guestnamead.setText(dataholder.get(position).getGuestname());
        holder.guestnumberad.setText(dataholder.get(position).getGuestnumber());
        holder.guestaddressad.setText(dataholder.get(position).getGuestaddress());
        holder.gueststatusad.setText(dataholder.get(position).getGueststatus());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context,updateguest.class);
                i.putExtra("taskname",obj.getGuestname());
                i.putExtra("tasknumber",obj.getGuestnumber());
                i.putExtra("taskaddress",obj.getGuestaddress());
                i.putExtra("taskstatus",obj.getGueststatus());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context,updateguest.class);
                i.putExtra("taskname",obj.getGuestname());
                i.putExtra("tasknumber",obj.getGuestnumber());
                i.putExtra("taskaddress",obj.getGuestaddress());
                i.putExtra("taskstatus",obj.getGueststatus());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        TextView guestnamead,guestnumberad,gueststatusad,guestaddressad;
        LinearLayout edit,delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            guestnamead=itemView.findViewById(R.id.guestname);
           guestaddressad=itemView.findViewById(R.id.guestaddress);
            guestnumberad=itemView.findViewById(R.id.guestnumber);
            gueststatusad=itemView.findViewById(R.id.gueststatus);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}

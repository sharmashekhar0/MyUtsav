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

public class mybudgetadapter extends RecyclerView.Adapter<mybudgetadapter.myviewholder> {
    ArrayList <budgetdatamodel> dataholder;
    Context context;
    String strtotal,strgift,strdeco,strcat,strcarbus,strother,strcamp,strremain,strover;
    public mybudgetadapter(@NonNull ArrayList<budgetdatamodel> dataholder,Context context) {
        this.dataholder = dataholder;
        this.context=context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowbudget,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        final budgetdatamodel obj=dataholder.get(position);
        strtotal=dataholder.get(position).getStrtotal();
        strgift=dataholder.get(position).getStrgift();
        strdeco=dataholder.get(position).getStrdecoration();
        strcat=dataholder.get(position).getStrcatering();
        strcarbus=dataholder.get(position).getStrcarbus();
        strother=dataholder.get(position).getStrother();
        strcamp=dataholder.get(position).getStrcamping();
        holder.eventnametext.setText(dataholder.get(position).getStreventname());
        holder.total.setText(strtotal);
        holder.gift.setText(strgift);
        holder.decoration.setText(strdeco);
        holder.catering.setText(strcat);
        holder.carbus.setText(strcarbus);
        holder.other.setText(strother);
        holder.camping.setText(strcamp);
        long remain;
        remain = Long.parseLong(strtotal)-(Long.parseLong(strgift)+Long.parseLong(strdeco)+Long.parseLong(strcat)+Long.parseLong(strcarbus)+Long.parseLong(strother)+Long.parseLong(strcamp));
        if(remain>0)
        {
            holder.remaintxt.setText(String.valueOf(remain));
            holder.overtxt.setText("0");
        }
        else
        {
            remain=-(remain);
            holder.remaintxt.setText("0");
            holder.overtxt.setText(String.valueOf(remain));
        }
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context,updatebudget.class);
                i.putExtra("eventnamestring",obj.getStreventname());
                i.putExtra("total",obj.getStrtotal());
                i.putExtra("gift",obj.getStrgift());
                i.putExtra("decoration",obj.getStrdecoration());
                i.putExtra("catering",obj.getStrcatering());
                i.putExtra("carbus",obj.getStrcarbus());
                i.putExtra("other",obj.getStrother());
                i.putExtra("camping",obj.getStrcamping());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context,updatebudget.class);
                i.putExtra("eventnamestring",obj.getStreventname());
                i.putExtra("total",obj.getStrtotal());
                i.putExtra("gift",obj.getStrgift());
                i.putExtra("decoration",obj.getStrdecoration());
                i.putExtra("catering",obj.getStrcatering());
                i.putExtra("carbus",obj.getStrcarbus());
                i.putExtra("other",obj.getStrother());
                i.putExtra("camping",obj.getStrcamping());
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
        TextView total,gift,decoration,catering,carbus,other,camping,eventnametext,remaintxt,overtxt;
        LinearLayout edit,delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            total=itemView.findViewById(R.id.total);
            eventnametext=itemView.findViewById(R.id.eventname);
            gift=itemView.findViewById(R.id.giftbudget);
            remaintxt=itemView.findViewById(R.id.remain);
            overtxt=itemView.findViewById(R.id.overbudget);
            decoration=itemView.findViewById(R.id.decorationbudget);
            catering=itemView.findViewById(R.id.cateringbudget);
            carbus=itemView.findViewById(R.id.carbusbudget);
            other=itemView.findViewById(R.id.otherbudgettext);
            camping=itemView.findViewById(R.id.campingbudget);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}

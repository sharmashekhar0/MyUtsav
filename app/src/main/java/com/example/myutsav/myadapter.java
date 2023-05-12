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

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {
    @NonNull
    ArrayList <model> dataholder;
    Context context;

    public myadapter(@NonNull ArrayList<model> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context=context;
    }

    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        final model obj=dataholder.get(position);
      holder.name.setText(dataholder.get(position).getName());
      holder.note.setText(dataholder.get(position).getNote());
        holder.status.setText(dataholder.get(position).getStatus());
        holder.category.setText(dataholder.get(position).getCategory());
        holder.date.setText(dataholder.get(position).getDate());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context,updateevent.class);
                i.putExtra("taskname",obj.getName());
                i.putExtra("tasknote",obj.getNote());
                i.putExtra("taskstatus",obj.getStatus());
                i.putExtra("taskcat",obj.getCategory());
                i.putExtra("taskdate",obj.getDate());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context,updateevent.class);
                i.putExtra("taskname",obj.getName());
                i.putExtra("tasknote",obj.getNote());
                i.putExtra("taskstatus",obj.getStatus());
                i.putExtra("taskcat",obj.getCategory());
                i.putExtra("taskdate",obj.getDate());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {
    TextView name,note,status,date,category;
        LinearLayout edit,delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.eventname);
            note=itemView.findViewById(R.id.eventnote);
            status=itemView.findViewById(R.id.eventstatus);
            date=itemView.findViewById(R.id.eventdate);
            category=itemView.findViewById(R.id.eventcat);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}

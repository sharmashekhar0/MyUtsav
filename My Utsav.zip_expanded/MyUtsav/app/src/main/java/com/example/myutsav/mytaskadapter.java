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

public class mytaskadapter extends RecyclerView.Adapter<mytaskadapter.myviewholder> {
    @NonNull
    ArrayList <taskmodel> dataholder;
 Context context;
    public mytaskadapter(@NonNull ArrayList<taskmodel> dataholder,Context context) {
        this.dataholder = dataholder;
        this.context=context;
    }

    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowtask,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        final taskmodel obj=dataholder.get(position);
        holder.name.setText(dataholder.get(position).getName());
        holder.note.setText(dataholder.get(position).getNote());
        holder.status.setText(dataholder.get(position).getStatus());
        holder.category.setText(dataholder.get(position).getCategory());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context,updatetask.class);
                i.putExtra("taskname",obj.getName());
                i.putExtra("tasknote",obj.getNote());
                i.putExtra("taskstatus",obj.getStatus());
                i.putExtra("taskcat",obj.getCategory());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context,updatetask.class);
                i.putExtra("taskname",obj.getName());
                i.putExtra("tasknote",obj.getNote());
                i.putExtra("taskstatus",obj.getStatus());
                i.putExtra("taskcat",obj.getCategory());
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
        TextView name,note,status,category;
        LinearLayout edit,delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.eventname);
            note=itemView.findViewById(R.id.eventnote);
            status=itemView.findViewById(R.id.eventstatus);
            category=itemView.findViewById(R.id.eventcat);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}

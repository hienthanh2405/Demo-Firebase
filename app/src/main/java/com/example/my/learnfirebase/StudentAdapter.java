package com.example.my.learnfirebase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by My on 4/15/2018.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private Context context;
    private List<Student> items;

    public StudentAdapter(Context context, List<Student> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_student, parent, false));
    }

    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder holder, int position) {
        Student student = items.get(position);
        holder.tvName.setText(student.getName());
        holder.tvMssv.setText(student.getMssv());
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvMssv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMssv = itemView.findViewById(R.id.tv_mssv);
        }
    }
}

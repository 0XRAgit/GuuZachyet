package ru.wnfx.framework.ui.main.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.databinding.ItemMainBinding;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    ArrayList<Lesson> lessons;
    MainContract.View view;

    public MainAdapter( ArrayList<Lesson> lessons, MainContract.View view){
        this.lessons=new ArrayList<>(lessons);
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMainBinding binding =ItemMainBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.audi.setText(lessons.get(position).getAud());
        holder.binding.teacher.setText(lessons.get(position).getTeacher());
        holder.binding.lesson.setText(lessons.get(position).getName());
        lessons.get(position).setTime(lessons.get(position).getTime().replaceAll("-","\n-\n"));
        holder.binding.time.setText(lessons.get(position).getTime());

        holder.itemView.setOnClickListener(v->view.chooseDay(lessons.get(position)));
    }


    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        ItemMainBinding binding;

        public ViewHolder(@NonNull ItemMainBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

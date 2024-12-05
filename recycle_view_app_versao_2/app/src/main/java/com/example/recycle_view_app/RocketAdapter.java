package com.example.recycle_view_app;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RocketAdapter extends RecyclerView.Adapter<RocketAdapter.ViewHolder> {

    ArrayList<RocketModel> rocketModels;

    public RocketAdapter(ArrayList<RocketModel> rocketModels){
        this.rocketModels = rocketModels;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rocket_name;
        TextView launch_date;
        TextView launch_success;
        TextView payload;
        ImageView img_rocket;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rocket_name = itemView.findViewById(R.id.rocket_name);
            launch_date = itemView.findViewById(R.id.launch_date);
            launch_success = itemView.findViewById(R.id.launch_success);
            payload = itemView.findViewById(R.id.payload);
            img_rocket = itemView.findViewById(R.id.img_rocket);
        }
    }
    //Cria uma nova instância de ViewHolder para cada item da lista.
    @NonNull
    @Override
    public RocketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    // Exibe cada item da lista
    @Override
    public void onBindViewHolder(@NonNull RocketAdapter.ViewHolder holder, int position) {
        RocketModel rocketModel = rocketModels.get(position);
        holder.rocket_name.setText("Rocket: " + rocketModel.getRocketName());
        holder.launch_date.setText("Launch Date: " + rocketModel.getLaunchDate());

        if (rocketModel.isLaunchSuccess()){
            holder.launch_success.setText("Launch Succeeded");
        }else{
            holder.launch_success.setText("Launch Failed");
        }

        holder.payload.setText("Payload: " + rocketModel.getPayload());
        holder.img_rocket.setImageResource(rocketModel.getImageResource());
    }

    // Retorna o número total de itens
    @Override
    public int getItemCount() {
        return rocketModels.size();
    }
}

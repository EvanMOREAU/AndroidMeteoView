package net.juteau.felix.meteovilles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class VilleAdapter extends BaseAdapter {

    private final List<VilleEntitie> lve;
    private final Context context;

    public VilleAdapter(List<VilleEntitie> lve, Context context) {
        this.lve = lve;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lve.size();
    }

    @Override
    public Object getItem(int position) {
        return lve.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lve.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = null;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.ville_adapter_activity, parent, false);
        TextView textview_name = itemView.findViewById(R.id.textview_name);
        TextView textview_temp = itemView.findViewById(R.id.textview_temp);
        TextView textview_meteo = itemView.findViewById(R.id.textview_meteo);
        textview_name.setText("Nom : "+lve.get(position).getNom());
        textview_temp.setText("Prenom : "+lve.get(position).getTemp());
        textview_meteo.setText("Job : "+lve.get(position).getMeteo());
        return itemView;
    }
}

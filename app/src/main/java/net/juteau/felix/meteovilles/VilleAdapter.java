package net.juteau.felix.meteovilles;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

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
        ImageView imageview_weather_icon = itemView.findViewById(R.id.imageview_weather_icon);
        textview_name.setText(lve.get(position).getNom() + " • " + lve.get(position).getTemp() + " C°");
        String iconUrl = "https://openweathermap.org/img/wn/" + lve.get(position).getIconCode() + ".png";
        Glide.with(context).load(iconUrl).into(imageview_weather_icon);

        // Détecter un clic long pour modifier le nom de la ville
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Afficher un dialog pour modifier le nom
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Modifier la ville");

                // Ajouter un champ de texte pour saisir le nouveau nom
                final EditText input = new EditText(context);
                input.setText(lve.get(position).getNom());  // Initialiser avec le nom actuel
                builder.setView(input);

                builder.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = input.getText().toString();
                        lve.get(position).setNom(newName); // Mettre à jour le nom de la ville

                        // Mettre à jour la ListView et notifier l'adaptateur
                        notifyDataSetChanged(); // Cela va recharger la ListView avec le nouveau nom
                    }
                });

                builder.setNegativeButton("Annuler", null);

                builder.show();
                return true; // Retourner true pour indiquer que l'événement est consommé
            }
        });

        return itemView;
    }
}

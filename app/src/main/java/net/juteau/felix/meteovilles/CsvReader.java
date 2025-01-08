package net.juteau.felix.meteovilles;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<VilleEntitie> chargerVilles(Context context, String filename) {
        List<VilleEntitie> villes = new ArrayList<>();

        try (InputStream is = context.getAssets().open(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 1) {
                    String nom = fields[0].trim();
                    // Vérification des valeurs
                        VilleEntitie ville = new VilleEntitie(nom);
                        villes.add(ville);
                    }

            }

        } catch (IOException e) {
            Log.e("CsvReader", "Erreur lors de la lecture du fichier CSV", e);
        }
        return villes;
    }
}


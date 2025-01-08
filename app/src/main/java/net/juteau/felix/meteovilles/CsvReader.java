package net.juteau.felix.meteovilles;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<VilleEntitie> chargerVilles(Context context, String filename) {
        List<VilleEntitie> villes = new ArrayList<>();
        File file = new File(context.getFilesDir(), filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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

    public static boolean ajouterVille(Context context, String fileName, String nom) throws IOException {
        File file = new File(context.getFilesDir(), fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.newLine();
            bw.write(nom);
        }
        return true;
    }
}



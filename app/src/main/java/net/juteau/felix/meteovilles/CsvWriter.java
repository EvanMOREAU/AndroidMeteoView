package net.juteau.felix.meteovilles;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class CsvWriter {

    public static void ajouterVilleAuCsv(Context context, String filename, String nomVille) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(context.getFilesDir() + "/" + filename, true))) {
            // Ajouter la ville sous forme de nouvelle ligne dans le fichier CSV
            writer.write(nomVille + "\n"); // Ajouter la ville suivie d'un saut de ligne
        } catch (IOException e) {
            Log.e("CsvWriter", "Erreur lors de l'écriture dans le fichier CSV", e);
        }
    }
}



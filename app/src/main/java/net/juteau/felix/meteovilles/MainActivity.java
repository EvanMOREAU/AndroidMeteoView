package net.juteau.felix.meteovilles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCity;
    private ListView listViewWeather;
    private VilleAdapter villeAdapter;
    private List<VilleEntitie> villeList;
    private WeatherApiClient weatherApiClient = new WeatherApiClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCity = findViewById(R.id.editTextCity);
        Button buttonGetWeather = findViewById(R.id.buttonGetWeather);
        listViewWeather = findViewById(R.id.listViewWeather);
        villeList = new ArrayList<>();
        villeAdapter = new VilleAdapter(villeList, this);
        listViewWeather.setAdapter(villeAdapter);
        List<VilleEntitie> vL = CsvReader.chargerVilles(this, "villes.csv");
        for (VilleEntitie ville : vL) {
            getWeatherData(ville.getNom());
        }
        buttonGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editTextCity.getText().toString();
                getWeatherData(city);
                try {
                    CsvReader.ajouterVille(MainActivity.this, "villes.csv", city);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getWeatherData(String city) {
        weatherApiClient.getWeatherData(city, new WeatherApiClient.WeatherCallback() {
            @Override
            public void onSuccess(VilleEntitie villeEntitie) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        villeList.add(villeEntitie);
                        villeAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Handle error, possibly show a toast or dialog
                    }
                });
            }
        });
    }
}

package net.juteau.felix.meteovilles;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class WeatherApiClient {

    private static final String apiKey = "c82e76f4ba021ebd3632e8105d9bffed"; // Remplacez par votre clé API
    private OkHttpClient client = new OkHttpClient();

    public interface WeatherCallback {
        void onSuccess(VilleEntitie villeEntitie);
        void onFailure(String errorMessage);
    }

    public void getWeatherData(String city, WeatherCallback callback) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric&lang=fr";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Échec de la requête : " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        String cityName = jsonObject.getString("name");
                        String weatherInfo = jsonObject.getJSONArray("weather")
                                .getJSONObject(0).getString("description");
                        int temperature = jsonObject.getJSONObject("main")
                                .getInt("temp");

                        VilleEntitie villeEntitie = new VilleEntitie(cityName, temperature, weatherInfo);
                        callback.onSuccess(villeEntitie);
                    } catch (JSONException e) {
                        callback.onFailure("Erreur d'analyse des données : " + e.getMessage());
                    }
                } else {
                    callback.onFailure("Erreur de réponse du serveur : " + response.message());
                }
            }
        });
    }
}

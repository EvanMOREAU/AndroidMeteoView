package net.juteau.felix.meteovilles;

public class VilleEntitie {
    private String nom;
    private Integer temp;
    private String meteo;

    public VilleEntitie(String nom, Integer temp, String meteo) {
        this.nom = nom;
        this.temp = temp;
        this.meteo = meteo;
    }

    public VilleEntitie(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getMeteo() {
        return meteo;
    }

    public void setMeteo(String meteo) {
        this.meteo = meteo;
    }
}

package net.juteau.felix.meteovilles;

public class VilleEntitie {
    private String nom;
    private Integer temp;
    private String meteo;
    private String iconCode;


    public VilleEntitie(String nom, Integer temp, String meteo, String iconCode) {
        this.nom = nom;
        this.temp = temp;
        this.meteo = meteo;
        this.iconCode = iconCode;

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

    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }
}

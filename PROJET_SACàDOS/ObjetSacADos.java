public class ObjetSacADos {
    private int poids;
    private int volume;
    private int valeur;

    public ObjetSacADos(int poids, int volume, int valeur) {
        this.poids = poids;
        this.volume = volume;
        this.valeur = valeur;
    }

    public int getPoids() {
        return poids;
    }

    public int getVolume() {
        return volume;
    }

    public int getValeur() {
        return valeur;
    }
}
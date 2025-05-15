public class NoeudSacADos {
    int niveau;
    int poids;
    int volume;
    int valeur;
    int estimation;
    boolean[] objetsInclus;

    public NoeudSacADos(int n) {
        objetsInclus = new boolean[n];
    }
}
import java.util.Random;

public class GenerateurDonnees {
    public static ObjetSacADos[] genererObjets(int n) {
        ObjetSacADos[] objets = new ObjetSacADos[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int poids = 1 + rand.nextInt(10);
            int volume = 1 + rand.nextInt(5);
            int valeur = 10 + rand.nextInt(91);
            objets[i] = new ObjetSacADos(poids, volume, valeur);
        }
        return objets;
    }
}
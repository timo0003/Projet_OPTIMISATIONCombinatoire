import java.util.*;

public class SacADosBranchAndBound {
    public static int resoudre(ObjetSacADos[] objets, int poidsMax, int volumeMax, boolean[] solution) {
        int n = objets.length;
        PriorityQueue<NoeudSacADos> file = new PriorityQueue<>(Comparator.comparingInt(a -> -a.estimation));
        NoeudSacADos racine = new NoeudSacADos(n);
        racine.estimation = estimer(objets, 0, 0, 0, 0, poidsMax, volumeMax);
        file.add(racine);

        int meilleureValeur = 0;
        boolean[] meilleureSolution = new boolean[n];

        while (!file.isEmpty()) {
            NoeudSacADos noeud = file.poll();

            if (noeud.niveau == n) continue;

            int i = noeud.niveau;

            // Inclure
            if (noeud.poids + objets[i].getPoids() <= poidsMax &&
                noeud.volume + objets[i].getVolume() <= volumeMax) {
                NoeudSacADos gauche = new NoeudSacADos(n);
                gauche.niveau = i + 1;
                gauche.poids = noeud.poids + objets[i].getPoids();
                gauche.volume = noeud.volume + objets[i].getVolume();
                gauche.valeur = noeud.valeur + objets[i].getValeur();
                System.arraycopy(noeud.objetsInclus, 0, gauche.objetsInclus, 0, n);
                gauche.objetsInclus[i] = true;
                gauche.estimation = estimer(objets, gauche.niveau, gauche.poids, gauche.volume, gauche.valeur, poidsMax, volumeMax);
                if (gauche.valeur > meilleureValeur) {
                    meilleureValeur = gauche.valeur;
                    meilleureSolution = gauche.objetsInclus.clone();
                }
                file.add(gauche);
            }

            // Exclure
            NoeudSacADos droite = new NoeudSacADos(n);
            droite.niveau = i + 1;
            droite.poids = noeud.poids;
            droite.volume = noeud.volume;
            droite.valeur = noeud.valeur;
            System.arraycopy(noeud.objetsInclus, 0, droite.objetsInclus, 0, n);
            droite.estimation = estimer(objets, droite.niveau, droite.poids, droite.volume, droite.valeur, poidsMax, volumeMax);
            file.add(droite);
        }

        System.arraycopy(meilleureSolution, 0, solution, 0, n);
        return meilleureValeur;
    }

    private static int estimer(ObjetSacADos[] objets, int niveau, int poids, int volume, int valeur, int poidsMax, int volumeMax) {
        int estimation = valeur;
        for (int i = niveau; i < objets.length; i++) {
            if (poids + objets[i].getPoids() <= poidsMax && volume + objets[i].getVolume() <= volumeMax) {
                poids += objets[i].getPoids();
                volume += objets[i].getVolume();
                estimation += objets[i].getValeur();
            }
        }
        return estimation;
    }
}
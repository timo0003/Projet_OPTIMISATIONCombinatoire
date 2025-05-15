public class Main {
    public static void main(String[] args) {
        int n = 30;
        int poidsMax = 30;
        int volumeMax = 20;

        ObjetSacADos[] objets = GenerateurDonnees.genererObjets(n);

        boolean[] solutionDyn = new boolean[n];
        long startDyn = System.nanoTime();
        int valeurDyn = SacADosBiDimDyn.resoudre(objets, poidsMax, volumeMax, solutionDyn);
        long endDyn = System.nanoTime();

        boolean[] solutionBB = new boolean[n];
        long startBB = System.nanoTime();
        int valeurBB = SacADosBranchAndBound.resoudre(objets, poidsMax, volumeMax, solutionBB);
        long endBB = System.nanoTime();

        System.out.println("--- Résultats ---");
        System.out.println("Valeur optimale (Méthode de Programmation Dynamique - récurrence) : " + valeurDyn + ", Temps : " + (endDyn - startDyn) / 1e6 + " ms");
        System.out.println("Objets choisis :");
        for (int i = 0; i < n; i++) if (solutionDyn[i])
            System.out.println("  Objet " + i + " (v=" + objets[i].getValeur() + ", p=" + objets[i].getPoids() + ", vol=" + objets[i].getVolume() + ")");

        System.out.println("\nValeur optimale (Méthode Arborescente - avec minorant et majorant) : " + valeurBB + ", Temps : " + (endBB - startBB) / 1e6 + " ms");
        System.out.println("Objets choisis :");
        for (int i = 0; i < n; i++) if (solutionBB[i])
            System.out.println("  Objet " + i + " (v=" + objets[i].getValeur() + ", p=" + objets[i].getPoids() + ", vol=" + objets[i].getVolume() + ")");
    }
}

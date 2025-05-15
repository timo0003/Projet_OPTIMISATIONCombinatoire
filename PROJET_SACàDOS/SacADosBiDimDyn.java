public class SacADosBiDimDyn {
    public static int resoudre(ObjetSacADos[] objets, int poidsMax, int volumeMax, boolean[] solution) {
        int[][][] dp = new int[objets.length + 1][poidsMax + 1][volumeMax + 1];

        for (int i = 1; i <= objets.length; i++) {
            ObjetSacADos obj = objets[i - 1];
            for (int p = 0; p <= poidsMax; p++) {
                for (int v = 0; v <= volumeMax; v++) {
                    if (obj.getPoids() <= p && obj.getVolume() <= v) {
                        dp[i][p][v] = Math.max(dp[i - 1][p][v],
                            dp[i - 1][p - obj.getPoids()][v - obj.getVolume()] + obj.getValeur());
                    } else {
                        dp[i][p][v] = dp[i - 1][p][v];
                    }
                }
            }
        }

        int p = poidsMax, v = volumeMax;
        for (int i = objets.length; i > 0; i--) {
            if (dp[i][p][v] != dp[i - 1][p][v]) {
                solution[i - 1] = true;
                p -= objets[i - 1].getPoids();
                v -= objets[i - 1].getVolume();
            }
        }

        return dp[objets.length][poidsMax][volumeMax];
    }
}

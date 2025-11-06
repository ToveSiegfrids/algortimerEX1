public class DisjSets {

    private static int [] s;

    /**
     * Initiera en disjunkt mängd
     * */
public DisjSets(int numElements) {
    s = new int[numElements];
    for (int i = 0; i < s.length; i++)
        s[i] = -1;
}


public static int find(int x) {
    if( s[x] < 0 ) /* x är en rot, returnera den */
        return x;
    else return  s[x] = find( s[x] ); /* annars gå ett steg uppåt */
}

/* Antar att rot1 och rot2 är rötter */
public static void union(int rot1, int rot2)
{
    if (rot1 == rot2) return;

    if (s[rot2] < s[rot1]) { // root2 är "större" (mer negativt)
        s[rot1] = rot2;

    } else {
        if (s[rot1] == s[rot2])
            s[rot1]--; // öka storlek på root1:s träd
        s[rot2] = rot1;
    }}

    public boolean connected(int rotA, int rotB) {
        return find(rotA) == find(rotB);
    }

    public static boolean allDone() {
        // allt är klart om det finns exakt en rot
        int roots = 0;
        for (int i = 0; i < s.length; i++)
            if (s[i] < 0) roots++;
        return roots == 1;
    }
}


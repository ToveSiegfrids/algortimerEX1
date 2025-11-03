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
    else return find( s[x] ); /* annars gå ett steg uppåt */
}

/* Antar att rot1 och rot2 är rötter */
public static void union(int rot1, int rot2)
{
    if ( s[rot2] < s[rot1] ) /* rot2 är större */
    { s[rot2] += s[rot1]; /* Addera storekarna */
        s[rot1] = rot2; /* rot2 blir ny rot */
    }
    else /* rot1 är det större trädet */
    { s[rot1] += s[rot2];
        s[rot2] = rot1; /* rot1 blir ny rot */
    }
}

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


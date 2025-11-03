import java.util.ArrayList;

public class DisjSets {


/* Initiera en disjunkt mängd */


    private int [] s;

public void DisjSets(int numElements) {
    s = new int[numElements];
    for (int i = 0; i < s.length; i++)
        s[i] = -1;
}

public int find(int x)
{
    if( s[x] < 0 ) /* x är en rot, returnera den */
        return x;
    else return find( s[x] ); /* annars gå ett steg uppåt */
}

/* Antar att rot1 och rot2 är rötter */
public void union( int rot1, int rot2)
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
}

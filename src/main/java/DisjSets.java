public class DisjSets {

    public void DisjSet( int numElements )
    public void union( int rot1, int rot2 )
    public int find( int x )
    private int [] s;
}

/* Initiera en disjunkt mängd */
public void DisjSets(int numElements)
{ s = new int[numElements];
    for ( int i = 0; i < s.length; i++)
        s[i] = -1;
}

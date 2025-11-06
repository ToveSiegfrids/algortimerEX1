public class DisjSets {

    private int [] s;

    /**
     * Initiealize a disjunctive set
     * */
public DisjSets(int numElements) {
    s = new int[numElements];
    for (int i = 0; i < s.length; i++)
        s[i] = -1;
}

    /**
     * find() searhes the root of a cell
     * ("the mom of the cells set")
     * @param x
     * @return
     */
    public int find(int x) {
    if( s[x] < 0 ) //x is the root itself, return it
        return x;
    else return  s[x] = find( s[x] ); //else we keep searching the "parent" of xs root
}

    /**
     * union() smashes two disjunctive quantyies to one
     * depending on the size of the two sets
     * @param cell1
     * @param cell2
     */
    public void union(int cell1, int cell2) {
    //find roots for each set the cells are in
        int root1 = find(cell1);
        int root2 = find(cell2);

        if (root1 == root2) return; //cell1 and cell2 is in the same set (cant do a union!)

        if (s[root2] < s[root1]) { //if the size of the set cell2 is in is "bigger" (more negative)
            s[root1] = root2; //smash the set that cell1 is in, into the set cell2 is in

        } else {
            if (s[root1] == s[root2]) //if both sets (the one cell1 is in and the one cell2 is in) are the same size
                s[root1]--; //make cell1 set one element larger
            s[root2] = root1; //smash cell2s set into the set cell1 is in
     }}

    /**
     * connected() checks if two cells are connected (in the same set)
     * @param rotA
     * @param rotB
     * @return
     */
    public boolean connected(int rotA, int rotB) {
        return find(rotA) == find(rotB);
    }

    /**
     * alldone() checks if every cell belongs to the same set
     * @return
     */
    public boolean allDone() {
        // everything is done if we only have one root (for all cells)
        int roots = 0;
        for (int i = 0; i < s.length; i++) //for every cell
            if (s[i] < 0) roots++; //count how many is a root (negative value)
        return roots == 1;
    }
}


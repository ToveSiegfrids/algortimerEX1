import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * class that creats the maze and contains logic for it
 * additional actions (void methods for union and find) is handled over to DisSets class
 */
class MazeComponent extends JComponent {
    protected int width;
    protected int height;
    protected int cells;
    protected int cellWidth;
    protected int cellHeight;
    Random random;

    // Draw a maze of size w*h with c*c cells
    MazeComponent(int w, int h, int c) {
        super();
        cells = c;                // Number of cells
        cellWidth = w/cells;      // Width of a cell
        cellHeight = h/cells;     // Height of a cell
        width =  c*cellWidth;     // Calculate exact dimensions of the component
        height = c*cellHeight;
        setPreferredSize(new Dimension(width+1,height+1));  // Add 1 pixel for the border
        setBackground(Color.yellow);
        setOpaque(true);
        System.out.println("MazeComponent size = " + width + "x" + height);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.yellow);                    // Yellow background
        g.fillRect(0, 0, width, height);

        // Draw a grid of cells
        g.setColor(Color.blue);                 // Blue lines
        for (int i = 0; i<=cells; i++) {        // Draw horizontal grid lines
            g.drawLine (0, i*cellHeight, cells*cellWidth, i*cellHeight);
        }
        for (int j = 0; j<=cells; j++) {       // Draw verical grid lines
            g.drawLine (j*cellWidth, 0, j*cellWidth, cells*cellHeight);
        }

        // Mark entry and exit cells
        paintCell(0,0,Color.green, g);               // Mark entry cell
        drawWall(-1, 0, 2, g);                       // Open up entry cell
        paintCell(cells-1, cells-1,Color.pink, g);   // Mark exit cell
        drawWall(cells-1, cells-1, 2, g);            // Open up exit cell

        g.setColor(Color.yellow);                 // Use yellow lines to remove existing walls
        createMaze(cells, g);
    }

    /** creates maze and handles different situations with walls (exist or not)
     * @param cells
     * @param g
     */

    private void createMaze(int cells, Graphics g) {
        int total = cells * cells; //total amount of cells
        DisjSets uf = new DisjSets(total);
        Random random = new Random();

        while (!uf.allDone()) { //while we are having more than one set for all cells
            int randomCell = random.nextInt(total); //random number that gives a random cell in the maze
            int wall = random.nextInt(4); //random wall -  0=left, 1=up, 2=right, 3=down
            int neighborCell = -1; // the cell on the other side of wall

            switch (wall) {
                case 0: //we handle left wall
                    if (randomCell % cells != 0) //checks that we are not in col = 0
                        neighborCell = randomCell - 1; //we have a neighbour which has number cell-1
                    break;
                case 1: //handle up wall
                    if (randomCell >= cells) //checks that we are not in row = 0
                        neighborCell = randomCell - cells; //we have a neighbour which has number cell- cells (lenght of one row), iow one row over)
                    break;
                case 2: //handle right wall
                    if ((randomCell + 1) % cells != 0) //checks that we are not in the rightmost col
                        neighborCell = randomCell + 1; //we have a neighbor to the right
                    break;
                case 3: //handle down wall
                    if (randomCell < (cells - 1) * cells) //checks that we are not in the bottom row
                        neighborCell = randomCell + cells; //we have a neighbor under us
                    break;
            }

            // if the cell dont have any neighbor we skip
            if (neighborCell == -1) continue;

            //checks if the cells arent connected (in the same set)
            if (!uf.connected(randomCell, neighborCell)) {
                uf.union(randomCell, neighborCell); //we smash them together (union)

                //when we know the neighbor state we can delete the wall to the neighbor:

                //coordinates for cell
                int x = randomCell % cells;
                int y = randomCell / cells;

                //coordinates for the neihgbor
                int nx = neighborCell % cells;
                int ny = neighborCell / cells;

                //"deletes" wall (changes color to background color)
                g.setColor(Color.yellow);
                drawWall(x, y, wall, g); //draw wall in method drawwall() in background color
                int oppositeWall = (wall + 2) % 4; //opposite wall (wall needs to be deleted from both cell and neighbor)
                drawWall(nx, ny, oppositeWall, g); //paint neighbors wall also in background color
            }
        }
        System.out.println("vi har endast en mängd wohoo");

    }


    // Paints the interior of the cell at postion x,y with colour c
    private void paintCell(int x, int y, Color c, Graphics g) {
        int xpos = x*cellWidth;    // Position in pixel coordinates
        int ypos = y*cellHeight;
        g.setColor(c);
        g.fillRect(xpos+1, ypos+1, cellWidth-1, cellHeight-1);
    }


    // Draw the wall w in cell (x,y) (0=left, 1=up, 2=right, 3=down)
    private void drawWall(int x, int y, int w, Graphics g) {
        int xpos = x*cellWidth;    // Position in pixel coordinates
        int ypos = y*cellHeight;

        switch(w){
            case (0):       // Wall to the left
                g.drawLine(xpos, ypos+1, xpos, ypos+cellHeight-1);
                break;
            case (1):       // Wall at top
                g.drawLine(xpos+1, ypos, xpos+cellWidth-1, ypos);
                break;
            case (2):      // Wall to the right
                g.drawLine(xpos+cellWidth, ypos+1, xpos+cellWidth, ypos+cellHeight-1);
                break;
            case (3):      // Wall at bottom
                g.drawLine(xpos+1, ypos+cellHeight, xpos+cellWidth-1, ypos+cellHeight);
                break;
        }
    }
}


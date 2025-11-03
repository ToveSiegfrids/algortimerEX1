import javax.swing.*;
import java.awt.*;
import java.util.Random;


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
    }

    public void paintComponent(Graphics g) {
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

    private void createMaze (int cells, Graphics g) {
        initDisjointSets(cells*cells);  //unionfind-struktur skapas var varje cell är sin egen mängd
        // (om två celler har samma mängd-> det finns en vägg mellan dem
        random = new Random(); //slumpar fram val så det är en unik labyrint varje gång

        while(!DisjSets.allDone()){ //huvudloop som kör så länge det finns flera celler som ännu inte är ihopkopplade
            int x = random.nextInt(cells);
            int y= random.nextInt(cells);
            int wall= random.nextInt(4);
        //x och y slumpmässig cell i rutnätet, wall=vilken vägg ska tas bort, 0=vänst, 1=upp, 2=hög, 3=ner)

//            int cell1 = y* cells+x;
//            int cell2 =-1;   //union find är i 1d så konverteras rutnätet till ett enda tal
//
//            switch(wall){
//                case 0: if (x>0) cell2=y* cells+x-1; break;
//                case 1: if (y>0) cell2= (y-1)* cells+x; break;
//                case 2: if (x<cells-1) cell2=y* cells+x+1; break;
//                case 3: if (y<cells-1) cell2= (y+1)* cells+x; break;
//            }
//            if (cell2 ==-1) continue;
//            if (DisjSets.find(cell1) != DisjSets.find(cell2)){
//                DisjSets.union(cell1, cell2);
//                drawWall(x, y, wall, g);
//            }
//        }

        // This is what you write

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


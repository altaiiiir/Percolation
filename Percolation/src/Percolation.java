import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // declare instance variables
    private int [][] grid;
    private int [][] load;
    private int size;
    private int openSites = 0;
    private boolean percolates = false;
    private WeightedQuickUnionUF qu;

    // creates n-by-n matrix, with all sites initially blocked
    public Percolation(int n){
        // can't have a matrix smaller than 1 x 1
        if(n <= 0) throw new IllegalArgumentException();

        this.size = n;
        qu = new WeightedQuickUnionUF((this.size*this.size)+1);

        // create a new 1 indexed 2d array & its respective load 2d array
        this.grid = new int[this.size+1][this.size+1];
        this.load = new int[this.size+1][this.size+1];

        // Initialize all sites to be blocked on grid (0 = blocked 1 = open)
        // Initialize all sites to be empty on load (0 = empty 1 = full)
        for (int i = 1; i < this.grid.length; i++) {
            for (int j = 1; j < this.grid[i].length; j++){
                this.grid[i][j] = 0;
                this.load[i][j] = 0;
            }
        }
    }

    private int flattenGrid(int row, int col) {
        return this.size * (row - 1) + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(row<=0 || row>this.size || col<=0 || col>this.size) throw new IllegalArgumentException();
        this.grid[row][col] = 1;
        if(row+1 == 1) this.load[row][col] = 1;

        // top
        if(isOpen(row-1,col)) this.qu.union(flattenGrid(row,col), flattenGrid(row-1,col));
        // down
        if(isOpen(row+1,col)) this.qu.union(flattenGrid(row,col), flattenGrid(row+1,col));
        // left
        if(isOpen(row,col-1)) this.qu.union(flattenGrid(row,col), flattenGrid(row,col-1));
        // right
        if(isOpen(row,col+1)) this.qu.union(flattenGrid(row,col), flattenGrid(row,col+1));

        //if(this.qu.connected(flattenGrid(row,col), flattenGrid(row,col))

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row<=0 || row>size || col<=0 || col>size) throw new IllegalArgumentException(); this.openSites++;
        return this.grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(row<=0 || row>this.size || col<=0 || col>this.size) throw new IllegalArgumentException();
        return this.load[row][col] == 1;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return false;
    }

    public static void main(String[] args){
        Percolation perc = new Percolation(10);

        for (int i = 1; i < perc.grid.length; i++) {
            for (int j = 1; j < perc.grid[i].length; j++)
                System.out.print(perc.grid[i][j] + " ");
            System.out.println();
        }
    }
}

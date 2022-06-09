import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // declare instance variables
    private int [][] grid;
    private int size;
    private int openSites = 0;
    private boolean percolates = false;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF qu;
    private WeightedQuickUnionUF load;

    // creates n-by-n matrix, with all sites initially blocked
    public Percolation(int n){
        // can't have a matrix smaller than 1 x 1
        if(n <= 0) throw new IllegalArgumentException();

        // adding 2 extra for top and bottom of grid
        // also adding an extra one for loads top
        this.size = n;
        qu = new WeightedQuickUnionUF((this.size*this.size)+3);
        load = new WeightedQuickUnionUF((this.size*this.size)+2);

        bottom = (n*n) + + 2;
        top = (n*n) + 1;

        // create a new 1 indexed 2d array & its respective load 2d array
        this.grid = new int[this.size+1][this.size+1];

    }

    private int flattenGrid(int row, int col) {
        return this.size * (row - 1) + col;
    }
    private boolean inbounds(int row, int col) {
        if(row <= this.size && col <= this.size && row > 0 && col > 0) return true;
        else return false;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(row<=0 || row>this.size || col<=0 || col>this.size) throw new IllegalArgumentException();
        if (isOpen(row, col)) return;

        int currIndex = flattenGrid(row,col);

        // open current site
        this.grid[row][col] = 1;
        this.openSites++;

        // if its top row then connect to top pointer
        if(row == 1) {
            qu.union(top, currIndex);
            load.union(top, currIndex);
        }

        // if its bottom row then connect it to bottom pointer
        if(row == this.size+1) {
            qu.union(bottom, currIndex);
        }

        // top
        if(inbounds(row-1,col) && isOpen(row-1,col)) {
            this.qu.union(currIndex, flattenGrid(row-1,col));
            this.load.union(currIndex, flattenGrid(row-1,col));
        }
        // down
        if(inbounds(row+1,col) && isOpen(row+1,col)) {
            this.qu.union(currIndex, flattenGrid(row+1,col));
            this.load.union(currIndex, flattenGrid(row+1,col));
        }
        // left
        if(inbounds(row,col-1) && isOpen(row,col-1)) {
            this.qu.union(currIndex, flattenGrid(row,col-1));
            this.load.union(currIndex, flattenGrid(row,col-1));
        }
        // right
        if(inbounds(row,col+1) && isOpen(row,col+1)) {
            this.qu.union(currIndex, flattenGrid(row,col+1));
            this.load.union(currIndex, flattenGrid(row,col+1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row<=0 || row>size || col<=0 || col>size) throw new IllegalArgumentException();
        return this.grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(row<=0 || row>this.size || col<=0 || col>this.size) throw new IllegalArgumentException();
        return this.load.find(flattenGrid(row, col)) == this.load.find(bottom);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return this.qu.find(top) == this.qu.find(bottom);
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);

        Percolation percolation = new Percolation(n);
        int argCount = args.length;
        for (int i = 1; argCount >= 2; i += 2) {
            int row = Integer.parseInt(args[i]);
            int col = Integer.parseInt(args[i + 1]);
            StdOut.printf("Adding row: %d  col: %d %n", row, col);
            percolation.open(row, col);
            if (percolation.percolates()) {
                StdOut.printf("%nThe System percolates");
            }
            argCount -= 2;
        }
        if (!percolation.percolates()) {
            StdOut.print("Does not percolate");
        }
    }
}

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // declare instance variables
    int [][] grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        // can't have a grid smaller than 1 x 1
        if(n <= 0) throw new IllegalArgumentException();

        // create a new 1 indexed 2d array
        grid = new int[n+1][n+1];

        // Initialize all sites to be blocked

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){

        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){

        return 0;
    }

    // does the system percolate?
    public boolean percolates(){

        return false;
    }

    public static void main(String[] args){

    }
}

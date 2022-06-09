import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    // Instance Variables
    private int numOpened = 0;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n<=0 || trials<=0) throw new IllegalArgumentException();

        // Monty Carlos Sim
        Percolation perc = new Percolation(n);

        for(int i = 0; i < trials; i++) {
            // while(!perc.percolates()||
            for(int j = 0; j < trials; j++) {
                // choose and open a random (closed) site
                int randX, randY = 0;
                do {
                    randX = StdRandom.uniform(1, n + 1);
                    randY = StdRandom.uniform(1, n + 1);
                } while (perc.isOpen(randX, randY));
                perc.open(randX, randY);
                numOpened++;
            }
        }

    }

    // sample mean of percolation threshold
    public double mean(){

        return 0;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){

        return 0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){

        return 0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){

        return 0;
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percs = new PercolationStats(n, trials);

        System.out.print("mean");
        for (int i = 4; i < 26; i++) System.out.print(" ");
        System.out.println("= " + percs.numOpened);
        System.out.print("stddev");
        for (int i = 6; i < 26; i++) System.out.print(" ");
        System.out.println("= ");
        System.out.print("95% confidence interval");
        for (int i = 23; i < 26; i++) System.out.print(" ");
        System.out.println("= ");
    }
}

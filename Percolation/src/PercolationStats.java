import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    // Instance Variables
    private int trials = 0;
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n<=0 || trials<=0) throw new IllegalArgumentException();
        this.trials = trials;
        this.results = new double[this.trials];

        // Monty Carlos Sim
        for(int trial = 0; trial < this.trials; trial++) {
            Percolation perc = new Percolation(n);

            for(int j = 0; j < trials; j++) {
                // choose and open a random (closed) site
                int randRow, randCol = 0;
                while(!perc.percolates()) {
                    randRow = StdRandom.uniform(1, n + 1);
                    randCol = StdRandom.uniform(1, n + 1);
                    perc.open(randRow, randCol);
                }

                double open = perc.numberOfOpenSites();
                double result = open/(n*n);
                results[trial] = result;
            }
        }

    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return this.mean() - ((1.96 * this.stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return this.mean() + ((1.96 * this.stddev()) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args){
        int size = 0;
        int trialCount = 0;

        if (args.length >= 2) {
            size = Integer.parseInt(args[0]);
            trialCount = Integer.parseInt(args[1]);
        }

        PercolationStats ps = new PercolationStats(size, trialCount);

        String confidence = "[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]";

        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}

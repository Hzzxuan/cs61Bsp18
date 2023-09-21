package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private int TotalTime;
    private int [] ArrayX;
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        TotalTime =T ;
        ArrayX = new int[T];
        for(int i =0 ;i<TotalTime;i++){
            Percolation PercolationSample=pf.make(N);
            while(!PercolationSample.percolates()) {
                int row = StdRandom.uniform(0, N - 1);
                int col = StdRandom.uniform(0, N - 1);
                if (!PercolationSample.isOpen(row, col)) {
                    PercolationSample.open(row, col);
                }
            }
            ArrayX[i] = PercolationSample.numberOfOpenSites();
        }

    }   // perform T independent experiments on an N-by-N grid
    public double mean(){
        return StdStats.mean(ArrayX);
    }               // sample mean of percolation threshold
    public double stddev(){
        return StdStats.stddev(ArrayX);
    }                                         // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return (mean()-1.96*stddev()/Math.sqrt(TotalTime));
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean()+1.96*stddev()/Math.sqrt(TotalTime));
    }                               // high endpoint of 95% confidence interval

}

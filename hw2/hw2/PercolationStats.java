package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    int TotalTime;
    int [] ArrayX;
    public PercolationStats(int N, int T, PercolationFactory pf){
        TotalTime =T ;
        ArrayX = new int[N];
        for(int i =0 ;i<TotalTime;i++){
            Percolation PercolationSample=pf.make(N);
            int x = 0;
            while(!PercolationSample.percolates()){
                int row = StdRandom.uniform(0,N-1);
                int col = StdRandom.uniform(0,N-1);
                if(!PercolationSample.isOpen(row,col)){
                    PercolationSample.open(row,col);
                    x=x+1;
                }

            }
            ArrayX[i] = x;
        }

    }   // perform T independent experiments on an N-by-N grid
    public double mean(){
        return StdStats.mean(ArrayX);
    }               // sample mean of percolation threshold
    public double stddev(){
        return StdStats.stddev(ArrayX);
    }                                         // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return (StdStats.mean(ArrayX)-1.96*Math.sqrt(StdStats.stddev(ArrayX)/TotalTime));
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (StdStats.mean(ArrayX)+1.96*Math.sqrt(StdStats.stddev(ArrayX)/TotalTime));
    }                               // high endpoint of 95% confidence interval

}

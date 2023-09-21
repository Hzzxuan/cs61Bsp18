package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

public class Percolation{
    private boolean[][] grid;
    private int size;
    private int vertical;
    private WeightedQuickUnionUF union1;
    //row*vertical+col
    public Percolation(int N){
        grid = new boolean[N][N];
        for(int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                grid[i][j]= false;
            }
        }
        size = 0;
        vertical = N;
        union1 = new WeightedQuickUnionUF(N*N+1);
        UnionInit();
    }                // create N-by-N grid, with all sites initially blocked


    private void UnionInit(){
        for(int i=0;i<vertical ;i++){
            if (grid[0][i] == true){
                UnionUpdate(0,i);
            }
        }
    }

    private void UnionUpdate(int row,int col){
        if(row==0 && col==0){
            if(isOpen(row,col+1)){union1.union(UnionIndex(row, col),UnionIndex(row,col+1));}
            if(isOpen(row+1,col)){union1.union(UnionIndex(row, col),UnionIndex(row+1,col));}
        } else if (row==0 && col==vertical-1) {
            if(isOpen(row,col-1)){union1.union(UnionIndex(row, col),UnionIndex(row,col-1));}
            if(isOpen(row+1,col)){union1.union(UnionIndex(row, col),UnionIndex(row+1,col));}
        } else if (row==vertical-1 && col==0) {
            if(isOpen(row,col+1)){union1.union(UnionIndex(row, col),UnionIndex(row,col+1));}
            if(isOpen(row-1,col)){union1.union(UnionIndex(row, col),UnionIndex(row-1,col));}
        } else if (row==vertical-1 && col==vertical-1) {
            if(isOpen(row,col-1)){union1.union(UnionIndex(row, col),UnionIndex(row,col-1));}
            if(isOpen(row-1,col)){union1.union(UnionIndex(row, col),UnionIndex(row-1,col));}
        } else if (row==0) {
            if(isOpen(row,col-1)){union1.union(UnionIndex(row, col),UnionIndex(row,col-1));}
            if(isOpen(row,col+1)){union1.union(UnionIndex(row, col),UnionIndex(row,col+1));}
            if(isOpen(row+1,col)){union1.union(UnionIndex(row, col),UnionIndex(row+1,col));}
        } else if (row==vertical-1) {
            if(isOpen(row,col-1)){union1.union(UnionIndex(row, col),UnionIndex(row,col-1));}
            if(isOpen(row,col+1)){union1.union(UnionIndex(row, col),UnionIndex(row,col+1));}
            if(isOpen(row-1,col)){union1.union(UnionIndex(row, col),UnionIndex(row-1,col));}
        } else if (col==0) {
            if(isOpen(row,col+1)){union1.union(UnionIndex(row, col),UnionIndex(row,col+1));}
            if(isOpen(row-1,col)){union1.union(UnionIndex(row, col),UnionIndex(row-1,col));}
            if(isOpen(row+1,col)){union1.union(UnionIndex(row, col),UnionIndex(row+1,col));}
        } else if (col==vertical-1) {
            if(isOpen(row,col-1)){union1.union(UnionIndex(row, col),UnionIndex(row,col-1));}
            if(isOpen(row-1,col)){union1.union(UnionIndex(row, col),UnionIndex(row-1,col));}
            if(isOpen(row+1,col)){union1.union(UnionIndex(row, col),UnionIndex(row+1,col));}
        }else {
            if(isOpen(row,col+1)){union1.union(UnionIndex(row, col),UnionIndex(row,col+1));}
            if(isOpen(row,col-1)){union1.union(UnionIndex(row, col),UnionIndex(row,col-1));}
            if(isOpen(row-1,col)){union1.union(UnionIndex(row, col),UnionIndex(row-1,col));}
            if(isOpen(row+1,col)){union1.union(UnionIndex(row, col),UnionIndex(row+1,col));}
        }
    }
    public void open(int row, int col){
        if(row>=vertical ||col>=vertical ||row <0||col<0){
            throw new IllegalArgumentException("Argument Out of range");
        }
        grid[row][col] = true;
        size += 1;
        UnionUpdate(row,col);

    }       // open the site (row, col) if it is not open already
    private int UnionIndex(int row ,int col){
        return row*vertical+col;
    }

    public boolean isOpen(int row, int col){
        if(row>=vertical ||col>=vertical ||row <0||col<0){
            throw new IllegalArgumentException("Argument Out of range");
        }
        return grid[row][col];
    }  // is the site (row, col) open?
    public boolean isFull(int row, int col){
        if(row>=vertical ||col>=vertical ||row <0||col<0){
            throw new IllegalArgumentException("Argument Out of range");
        }
        if(UnionIndex(row,col)>=0&&UnionIndex(row,col)<=vertical-1&&isOpen(row,col)){
            return true;
        } else if (UnionIndex(row,col)>=0&&UnionIndex(row,col)<=vertical-1&&(!isOpen(row,col))) {
            return false;
        } else {
            for(int i=0;i<=vertical-1;i++){
                if(union1.connected(UnionIndex(row,col),i)){
                    return true;
                }
            }
        }
        return false;
    }  // is the site (row, col) full?
    public int numberOfOpenSites(){
        return size;
    }           // number of open sites
    public boolean percolates() {
        for(int j=0;j<vertical;j++){
            for(int i=0;i<vertical;i++){
                if(union1.connected(UnionIndex(vertical-1,j),UnionIndex(0,i))){
                    return true;
                }
            }
        }
        return false;
    }             // does the system percolate?
    public static void main(String[] args){
        Percolation x =new Percolation(4);
        x.open(0,0);
        x.open(1,0);
        x.open(2,0);
        boolean y = x.percolates();
        System.out.println(y);
        x.open(3,0);
        boolean z = x.percolates();
        System.out.println(z);
    }   // use for unit testing (not required)
}

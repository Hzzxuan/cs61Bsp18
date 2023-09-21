package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

public class Percolation{
    private boolean[][] grid;
    private int size;
    private int vertical;
    private int topSite;
    private int bottomSite;
    private WeightedQuickUnionUF union1;
    private WeightedQuickUnionUF union2;
    //row*vertical+col
    public Percolation(int N){
        if(N<=0){
            throw new IllegalArgumentException();
        }
        topSite = N*N;
        bottomSite =N*N+1;
        grid = new boolean[N][N];
        for(int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                grid[i][j]= false;
            }
        }
        size = 0;
        vertical = N;
        union1 = new WeightedQuickUnionUF(N*N+2);
        for (int i = 0; i < N; i++) {
            union1.union(topSite, UnionIndex(0, i));
        }
        for (int i = 0; i < N; i++) {
            union1.union(bottomSite, UnionIndex(N - 1, i));
        }
        union2 = new WeightedQuickUnionUF((N*N+1));
        for (int i = 0; i < N; i++) {
            union2.union(topSite, UnionIndex(0, i));
        }
    }                // create N-by-N grid, with all sites initially blocked



    private void UnionUpdate(int row,int col){
        UnionUpdateHelper(row,col,row+1,col);
        UnionUpdateHelper(row,col,row-1,col);
        UnionUpdateHelper(row,col,row,col+1);
        UnionUpdateHelper(row,col,row,col-1);
    }

    private void UnionUpdateHelper(int row,int col,int NewRow,int Newcol){
        if (NewRow < 0 || NewRow >= vertical || Newcol < 0 || Newcol >= vertical) {
            return;
        }
        else {
            if(isOpen(NewRow,Newcol)){
                union1.union(UnionIndex(row, col),UnionIndex(NewRow, Newcol));
                union2.union(UnionIndex(row, col),UnionIndex(NewRow, Newcol));
            }
        }
    }
    public void open(int row, int col){
        if(row>=vertical ||col>=vertical ||row <0||col<0){
            throw new IndexOutOfBoundsException("Argument Out of bounds");
        }
        if(!isOpen(row,col)){
            grid[row][col] = true;
            size += 1;
        }
        UnionUpdate(row,col);

    }       // open the site (row, col) if it is not open already
    private int UnionIndex(int row ,int col){
        return row*vertical+col;
    }

    public boolean isOpen(int row, int col){
        if(row>=vertical ||col>=vertical ||row <0||col<0){
            throw new IndexOutOfBoundsException("Argument Out of range");
        }
        return grid[row][col];
    }  // is the site (row, col) open?
    public boolean isFull(int row, int col){
        if(row>=vertical ||col>=vertical ||row <0||col<0){
            throw new IndexOutOfBoundsException("Argument Out of range");
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return union2.connected(UnionIndex(row, col), topSite);
    }  // is the site (row, col) full?
    public int numberOfOpenSites(){
        return size;
    }           // number of open sites
    public boolean percolates() {
        if (size == 0) {
            return false;
        }
        if(union1.connected(topSite,bottomSite)){
            return true;
        }
        return false;
    }             // does the system percolate?
    public static void main(String[] args){

    }   // use for unit testing (not required)
}

package hw3.hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        ArrayList<LinkedList<Oomage>> Table = new ArrayList<LinkedList<Oomage>>();
        for(int i =0;i<M;i++){
            Table.add(new LinkedList<Oomage>());
        }
        int N = oomages.size();
        for(int i =0 ;i<N;i++){
            int bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            Table.get(bucketNum).add(oomages.get(i));
        }
        for (int i=0;i<M;i++){
            if(Table.get(i).size()<(N/50) || Table.get(i).size()>(N/2.5)){
                return false;
            }
        }
        return true;
    }
}

public class OffByN implements CharacterComparator{
    private static int SplitNum;
    public OffByN(int N){
        SplitNum=N;
    }
    public boolean equalChars (char a,char b){
        if(Math.abs(a-b)==SplitNum){
            return true;
        }
        else{
            return false;
        }
    }
}

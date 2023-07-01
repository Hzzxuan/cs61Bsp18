public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> Dq = new ArrayDeque<>();
        for(int i=0;i<word.length();i++){
            Dq.addLast(word.charAt(i));
        }
        return Dq;
    }

    public boolean isPalindrome(String word){

        Deque<Character> Dq = wordToDeque(word);
        char x;
        String output = "";
        for(int i=0;i<word.length();i=i+1){
            x =Dq.removeLast();
            output = output + x;
        }
        return output.equals(word);
    }

    public boolean isPalindromeRec(String word){
        Deque<Character> Dq = wordToDeque(word);
        return isPalindromeRec_helper(Dq);

    }
    private boolean isPalindromeRec_helper(Deque Dq){
        //ABDBA
        if(Dq.size()==0||Dq.size()==1){
            return true;
        }
        if(Dq.removeLast()==Dq.removeFirst()){
            return true & (isPalindromeRec_helper(Dq));
        }
        return false;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> Dq = wordToDeque(word);
        return isPalindrome_helper(Dq,cc);
    }
    private boolean isPalindrome_helper(Deque<Character> Dq,CharacterComparator offByOne){
        if(Dq.size()==0||Dq.size()==1){
            return true;
        }

        if(offByOne.equalChars(Dq.removeLast(),Dq.removeFirst())){
            return true & (isPalindrome_helper(Dq,offByOne));
        }
        return false;


    }


}


public class PrintNumbersRecursive {
    public static int print1ToN(int n){
        //Base case
        if(n == 0){
            return 0;
        }
        //Hypothesis
        print1ToN(n - 1);
        //Induction
        System.out.print(n + " ");
        return n;
    }
    public static int printNTo1(int n){
        //Base case
        if(n == 0) return 0;
        //Induction
        System.out.print(n + " ");
        //Hypothesis
        printNTo1(n - 1);
        return n;
    }
    public static void main(String[] args) {
        print1ToN(5);
        System.out.println();
        printNTo1(5);
        System.out.println();
    }
}
  

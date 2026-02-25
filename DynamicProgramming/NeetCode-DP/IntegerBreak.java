public class IntegerBreak {
    public int integerBreak(int n){
        int add = 0, mul = 1;
        for(int i = 1; i < n; i++){
            add += i;
            mul *= i;
        }
        if(add == mul) return 
    }
}

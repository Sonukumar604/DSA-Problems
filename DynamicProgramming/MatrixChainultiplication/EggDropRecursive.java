public class EggDropRecursive {
    public static int eggDrop(int e, int f){
        if( f== 0 || f == 1 ) return f;
        if( e == 1) return f;
        int min = Integer.MAX_VALUE;
        for(int k = 1; k <= f; k++){
            int temp = 1 + Math.max(eggDrop(e-1, k-1), eggDrop(e, f-k));
            min = Math.min(min, temp);
        }
        return min;
    }
    public static void main(String[] args) {
        int egg = 3, floors = 6;
        System.out.println("Minimal trials in worst case: " + eggDrop(egg, floors));
    }
}

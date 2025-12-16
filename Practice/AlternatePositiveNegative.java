import java.util.ArrayList;
import java.util.Arrays;

public class AlternatePositiveNegative {

    public void rearrange(ArrayList<Integer> arr){
        int n = arr.size();
        ArrayList<Integer> temp = new ArrayList<>(arr);
        int posIndex = 0; // Start placing positive numbers at even indices
        int negIndex = 1; // Start placing negative numbers at odd indices

        for(int num : temp){
            if(num >= 0){
                arr.set(posIndex, num);
                posIndex += 2;
            }else{
                arr.set(negIndex, num);
                negIndex += 2;
            }
        }
    }
    //Optimal Approach: Using Partitioning (Similar to QuickSort)
    public void rearrangeOptimal(ArrayList<Integer> arr) {
        int n = arr.size();
        int[] temp = new int[n];
        int posIndex = 0;
        int negIndex = 1;

        for (int i = 0; i < n; i++) {
            if (arr.get(i) >= 0) {
                temp[posIndex] = arr.get(i);
                posIndex += 2;
            } else {
                temp[negIndex] = arr.get(i);
                negIndex += 2;
            }
        }

        for (int i = 0; i < n; i++) {
            arr.set(i, temp[i]);
        }
    }
    //Better Approach: Using Extra Space
    public void rearrangeBetter(ArrayList<Integer> arr) {
        int n = arr.size();
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();

        for (int num : arr) {
            if (num >= 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        int posIndex = 0, negIndex = 0, index = 0;

        while (posIndex < positive.size() && negIndex < negative.size()) {
            arr.set(index++, positive.get(posIndex++));
            arr.set(index++, negative.get(negIndex++));
        }

        while (posIndex < positive.size()) {
            arr.set(index++, positive.get(posIndex++));
        }

        while (negIndex < negative.size()) {
            arr.set(index++, negative.get(negIndex++));
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(
            1, -2, 3, -4, 5, -6, 7, -8
        ));
        AlternatePositiveNegative obj = new AlternatePositiveNegative();
        obj.rearrange(arr);
        System.out.println(arr);
    }
}
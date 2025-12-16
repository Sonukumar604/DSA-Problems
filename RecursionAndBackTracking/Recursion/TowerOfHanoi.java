public class TowerOfHanoi{
    public static int solveHanoi(int n, char source, char helper, char destination){
 
        // Base Case: If there is only one disk, move it directly. This is 1 move.
        if(n == 1){
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return 1;
        }
        // Hypothesis: Assume solveHanoi(n-1, ...) correctly moves n-1 disks
        // and returns the total count of moves required for it.

        // Induction Step: Use the hypothesis to solve for n disks.
        // 1. Move n-1 disks from source to helper peg.
        int count1 = solveHanoi(n-1, source, destination, helper);

        // 2. Move the nth disk from source to destination peg.
        System.out.println("Move disk " + n + " from " + source + " to " + destination);

        // 3. Move the n-1 disks from helper to d
        // estination peg.
        int count2 = solveHanoi(n-1, helper, source, destination);

        // Total moves is the sum of moves from all three steps.
        return count1 + 1 + count2;
    }
    public static void main(String[] args) {
        int numDisks = 3;
        System.out.println("Solving Tower of Hanoi for " + numDisks + " disks.");
        int totalMoves = solveHanoi(numDisks, 'A', 'B', 'C');
        System.out.println("\nTotal moves required: " + totalMoves);

    }
}
 
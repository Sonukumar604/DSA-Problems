public class singleNonDuplicate {
    public static int singleNonDuplicate(int[] arr){
        int low = 0;
        int high = arr.length - 1;
        while(low <= high){
            int mid =  low + (high - low) / 2;
            if((mid == 0 || arr[mid] != arr[mid - 1]) && (mid == arr.length - 1 || arr[mid] != arr[mid + 1])){
                return arr[mid];
            }
            if(mid % 2 == 0){
                if(arr[mid] == arr[mid + 1]){
                    low = mid + 2;
                }else{
                    high = mid - 1;
                }
            }else{
                if(arr[mid] == arr[mid - 1]){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {1,1,2,3,3,4,4,8,8};
        System.out.println(singleNonDuplicate(arr));
    }
}

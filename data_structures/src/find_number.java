/*
Returns index of search number in array

**** find number ****

 */
class FindNumber {
    public static void main(String[] args) {
        FindNumber fn = new FindNumber();
        int[] array1 = {5,-4,0,22,91,112,13,23,44};
        int target = 44;

        System.out.println("**** find number ****");
        System.out.print("array1: ");
        for (int i = 0; i < array1.length; i++) {
            System.out.print(" " + array1[i]);
        }
        System.out.println("\ntarget: " + target);

        fn.searchInArray(array1,44);
    }

    // search method
    public int searchInArray(int[] intArray,int valueToSearch) {
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == valueToSearch) {
                System.out.println("index location " + i);
                return i;
            }
        }
        System.out.println("not found....");
        return -1;
    }
}

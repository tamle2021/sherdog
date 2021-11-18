/*
Find the missing number within an array of numbers

**** missing number ****
 */
import java.util.Arrays;

class MissingNumber {
  public static void main(String[] args) {
    MissingNumber mn = new MissingNumber();
    int array1[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,112};

    System.out.println("**** missing number ****");
    mn.begin(array1);
  }

  public void begin(int[] array1) {
    int sum1 = 0;
    int sum2 = 0;
    int arrayLength = array1.length + 1;
    for (int i: array1) {
      sum1 += i;
    }
    sum2 = arrayLength * (arrayLength + 1) / 2;
    int difference = sum2 - sum1;
    System.out.println("array " + Arrays.toString(array1) + "\n" + "does not have number  " + difference);
  }
}

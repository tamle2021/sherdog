/*

**** sum of digits for integer string ****

example: 416 => 4 + 1 + 6 = 11
 */

class SumOfDigits {
    public static void main(String[] args) {
        SumOfDigits q1 = new SumOfDigits();
        var result = q1.recursiveProcess(213);
        System.out.println("**** sum of digits for integer string ****\n");
        System.out.println((int) result);
    }

    public int recursiveProcess(int n) {
        if (n == 0 || n < 0) {
            return 0;
        }

        return n % 10 + recursiveProcess(n / 10);
    }


}

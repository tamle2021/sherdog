/*
Recursion is the process of defining a problem or the solution to a problem in terms of a simpler version of itself.

**** factorial of number using recursion ****
 */

class FactorialRecursion {
    public static void main(String[] args) {
        FactorialRecursion fc = new FactorialRecursion();
        int num = 6;
        var result = fc.begin(num);
        System.out.println("**** factorial of number using recursion ****");
        System.out.println(num + " factorial = " + result);
    }

    public int begin(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * begin(n - 1);
    }
}

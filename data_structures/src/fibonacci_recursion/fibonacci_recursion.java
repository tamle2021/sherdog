/*
In mathematics, the Fibonacci numbers, commonly denoted Fn, form a sequence, called the Fibonacci sequence, such that each number is the
sum of the two preceding ones, starting from 0 and 1.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...

****  fibonacci number using recursion ****
 */
package fibonacci_recursion;

class FibonacciRecursion {
    public static void main(String[] args) {
        FibonacciRecursion fr = new FibonacciRecursion();
        int fibNum = 6;
        int result = fr.recurse(fibNum);
        System.out.println("**** fibonacci number using recursion ****");
        System.out.print("fibonacci " + fibNum + " = " + result);
    }

    public int recurse(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0 || n == 1) {
            return n;
        }
        return recurse(n - 1) + recurse(n - 2);
    }

}

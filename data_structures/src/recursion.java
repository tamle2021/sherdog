/*
Recursion is the process of defining a problem or the solution to a problem in terms of a simpler version of itself.

 */

class Main {
    public static void main(String[] args) {
        Main recursion = new Main();
        var rec = recursion.factorial(6);
        System.out.println(rec);
    }

    public int factorial(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}

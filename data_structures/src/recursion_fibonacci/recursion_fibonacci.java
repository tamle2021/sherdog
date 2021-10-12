package recursion_fibonacci;

class RecursionFibonacci {
    public static void main(String[] args) {
        RecursionFibonacci rf = new RecursionFibonacci();
        int rec2 = rf.fibonacci(4);
        System.out.print((int) rec2);
    }

    public int fibonacci(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}

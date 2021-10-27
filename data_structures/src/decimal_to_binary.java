/*


 **** convert decimal number to binary format using recursion ****
 */
class DecimalToBinary {
    public static void main(String[] args) {
        DecimalToBinary dtb = new DecimalToBinary();
        int decimalNum = 7;
        int result = dtb.recurse(decimalNum);
        System.out.println("**** convert decimal number to binary format using recursion ****");
        System.out.println(decimalNum + " converted to binary = " + result);

    }

    public int recurse(int n) {
        if (n == 0) {
            return 0;
        }
        return n % 2 + (10 * recurse(n / 2));
    }


}

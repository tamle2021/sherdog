/*


 **** greatest common divisor ****
 */

class GreatestCommonDivisor {
    public static void main(String[] args) {
        GreatestCommonDivisor gcd = new GreatestCommonDivisor();
        int a = 17;
        int b = 51;
        int result = gcd.recurse(17, 51);
        System.out.println("**** greatest common divisor ****");
        System.out.println("gcd of  " + a + " and " + b + " = " + (int) result);
    }

    public int recurse(int a, int b) {
        if (a < 0 || b < 0) {
            return -1;
        }
        if (b == 0) {
            return a;
        }
        return recurse(b, a % b);
    }
}

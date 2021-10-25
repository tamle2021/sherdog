/*


 **** base number to exponent power ****
 */

class ExponentPower {
    public static void main(String[] args) {
        ExponentPower ep = new ExponentPower();
        int base = 3;
        int exponent = 3;
        var result = ep.recurse(base,exponent);
        System.out.println("**** base number to exponent power ****");
        System.out.println(base + " to the power of " + exponent + " = " + (int) result);

    }

    public int recurse(int base, int exp) {
        if (exp < 0) {
            return -1;
        }
        if (exp == 0 || exp == 1) {
            return base;
        }

        return base * recurse(base, exp - 1);
    }
}

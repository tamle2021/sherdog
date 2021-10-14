

/**

       This program tests the BankAccount class.

    */

    public class BankAccountTester {
       /** Tests the methods of the BankAccount class.
          @param args not used
       **/

      public static void main(String[] args) {
         BankAccount harrysAccount = new BankAccount(5000);
         harrysAccount.deposit(500);
         harrysAccount.withdraw(2000);
         harrysAccount.addInterest(1);
          System.out.printf("%.2f\n",harrysAccount.getBalance());
          System.out.println("Expected: 1504.90");

          SqlConnection sqlConnect = new SqlConnection();
          sqlConnect.connect();

      }

   }

/*
Circular linked list is linked list where all nodes are connected to form a circle and there is not a null at the end.


**** circular singly linked list ****
 */
package circular_singly_ll;

class MainCircularSinglyLinkedList {
  public static void main(String[] args) {
    int testValue = 11;
    System.out.println("**** circular singly linked list ****");
    CircularSinglyLinkedList csll = new CircularSinglyLinkedList();
    csll.createCSLL(5);
    csll.insertCSLL(4, 0);
    csll.insertCSLL(6, 1);
    csll.insertCSLL(7, 8);
    csll.insertCSLL(testValue,2);
    csll.insertCSLL(-44,5);
    csll.traverseCSLL();
    // searching node before deletion
    System.out.print("searching node " + testValue + " before deletion => ");
    csll.searchNode(testValue);
    System.out.println("");

    csll.deleteCSLL();
    csll.traverseCSLL();

    // searching node after deletion
    System.out.print("searching node " + testValue + " after deletion => ");
    csll.searchNode(11);

  }
}

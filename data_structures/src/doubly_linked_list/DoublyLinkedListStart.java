/*
Doubly-linked list is a type of linked list in which each node consists of 3 components which are address of previous node,
data, and address of next node.


**** doubly-linked list ****
 */
package doubly_linked_list;

class DoublyLinkedListStart {
  public static void main(String[] args) {
    System.out.println("**** doubly-linked list ****");
    DoublyLinkedList dll = new DoublyLinkedList();
    dll.createList(1);
    dll.insertList(6, 0);
    dll.insertList(-4, 1);
    dll.insertList(25, 2);
    dll.insertList(52,3);
    dll.insertList(122,4);
    dll.insertList(4, 7);
    dll.traverseList();
    //  delete at location 4
    System.out.println("deleting node at location 4: ");
    dll.deleteNode(4);
    dll.traverseList();
    // reverse traverse
    System.out.println("reverse traverse: ");
    dll.reverseTraverseList();
    dll.deleteList();
    dll.traverseList();
  }
}

package doubly_linked_list;

class DoublyLinkedListMain {
  public static void main(String[] args) {
    DoublyLinkedList dll = new DoublyLinkedList();
    dll.createDLL(1);
    dll.insertDLL(6, 0);
    dll.insertDLL(-4, 1);
    dll.insertDLL(25, 2);
    dll.insertDLL(4, 7);
    dll.traverseDLL();
    dll.deleteDLL();
    dll.traverseDLL();
  }
}

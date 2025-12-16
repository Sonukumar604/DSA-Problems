public class ReversedLinkedList {
    class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    Node head;
    public void insert(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
    }
    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public void reverseIterative(){
        Node current = head;
        Node prev = null;
        while(current != null){
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        head = prev;
    }
    public static void main(String[] args) {
        ReversedLinkedList Rll = new ReversedLinkedList();
        Rll.insert(10);
        Rll.insert(20);
        Rll.insert(30);
        Rll.insert(40);
        Rll.insert(50);
        Rll.display();
        Rll.reverseIterative();
        System.out.println("Reversed Linked List (Iterative): ");
        Rll.display();


    }
}

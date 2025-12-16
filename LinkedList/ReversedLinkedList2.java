public class ReversedLinkedList2 {
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
    public Node reverseRecursive(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    public static void main(String[] args) {
        ReversedLinkedList2 rll = new ReversedLinkedList2();
        rll.insert(10);
        rll.insert(20);
        rll.insert(30);
        rll.insert(40);
        rll.insert(50);
        rll.display();
        rll.head = rll.reverseRecursive(rll.head);
        System.out.println("Reversed Linked List(recursive): ");
        rll.display();

    }
}

public class CycleDetectionAndRemoval {
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static Node detectCycle(Node head){
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return slow;
            }
        }
        return null;
    }
    public static Node detectFirstNode(Node head){
        Node meet = detectCycle(head);
        if(meet == null){
            return null;
        }
        Node start = head;
        while(start != meet){
            start = start.next;
            meet = meet.next;
        }
        return start;
    }
    public static void removeCycle(Node head){
        Node startNode = detectFirstNode(head);
        if(startNode == null)return;
        Node temp = startNode;
        while(temp.next != startNode){
            temp = temp.next;
        }
        temp.next = null;
    }
    public static void printList(Node head){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next = head.next.next;
        Node cycleStart = detectFirstNode(head);
        if(cycleStart != null){
            System.out.println("Cycle detected at node: " + cycleStart.data);
        }else{
            System.out.println("No cycle found.");
        }
        removeCycle(head);
        if(detectCycle(head) == null){
            System.out.println("Cycle Successfully removed.");
        }
        System.out.println("Linked list after cycle removal: ");
        printList(head);
    }
}

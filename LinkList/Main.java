class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
    }

}
class LinkedList{
    int count = 0;
    Node head;
    public void append(int data){
        if(head == null){
            head = new Node(data);
            count++;
            return;
        }
        Node current = head;
        while(current.next != null)
        current = current.next;

        current.next = new Node(data);
        count++;

    }
    public void prepend(int data){
        if(head == null){
            head = new Node(data);
            return;
        }
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        count++;
    }
    public void print(){
        System.out.print("\nList: ");
        Node current = head;
        while(current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
    }
    public void deleteK(int data){
        if(head == null)return;
        if(head.data == data)
        {
            head = head.next;
            return;
        }
        
        Node current = head;
        while(current.next != null){
            if(current.next.data == data){
                current.next = current.next.next;
            }
           current = current.next;
        }
    }
}
public class Main{
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.print();
        list.deleteK(5);
        list.print();
        
    }
}
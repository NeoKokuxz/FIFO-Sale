public class FIFO_LL {

    private class Node {
        private double price;
        private int amount;
        private Node next;

        //Default Constructor
        public Node(){
            price = 0;
            amount = 0;
            next = null;
        }

        //Constructor with input data
        public Node(int amount, double price){
            this.price = price;
            this.amount = amount;
            next = null;
        }

        //Default functions for Node

        //Get current amount in node
        public int getAmount(){
            return this.amount;
        }

        //Get current price in node
        public double getPrice(){
            return this.price;
        }

        //Set current price in node
        public void setPrice(double newPrice){
            this.price = newPrice;
        }

        //Set current amount in node
        public void setAmount(int newAmount){
            this.amount = newAmount;
        }

        //Get next node
        public Node getNext(){
            return this.next;
        }

        //Set next node
        public void setNext(Node next){
            this.next = next;
        }
    }

    //Use Node class in LinkedList
    private Node head;

    public FIFO_LL() {
        this.head = new Node();
    }

    /*
    Functions for FIFO Linked List:
    */

    /*
        printAmount()
        return: void
        parameter: none
        function: Start from head and move to next node if it's not null, and print the amount in each node.
     */
    public void printAmount(){
        Node pointer = head;

        System.out.println("Amount: ");
        while(pointer.getNext() != null){
            pointer = pointer.getNext();
            System.out.println(pointer.getAmount() + "\n" + pointer.getPrice());
        }
    }

    /*
        add()
        return: void
        parameter: int amount - amount of widgets to put in
                   double price - unit price of each widget
        function: create a new node in the list, and add to last position with amount and it's unit price to node
     */
    public void add(int amount, double price){
        //Create new node for incoming data
        Node node = new Node(amount, price);
        Node pointer = head;

        while(pointer.getNext() != null){
            pointer = pointer.getNext();
        }

        //Add data after head
        pointer.setNext(node);
    }

    /*
        pop()
        return: void
        parameter: none
        function: Move head to next available node.
                  When the previous node is not pointed by head, the garbage collection with collect it.
     */
    public void pop(){
        head = head.getNext();
    }

    /*
        adjustAmount();
        return: void
        parameter: int - new amount to adjust the old one
        function: Input new amount and pointer will find the first node and adjust the amount to new amount.

     */
    public void adjustAmount(int newAmount){
        Node pointer = head.getNext();
        pointer.setAmount( newAmount );
    }

    /*
        getAmount()
        return: int
        parameter: none
        function: Check the amount inside the first node of the linked list as integer.
     */
    public int getAmount (){
        if(head.getNext() != null){
            return head.getNext().getAmount();
        }
        else return 0;
    }

    /*
        getPrice()
        return: double - price in double format
        parameter: none
        function: if first node isn't null, get it's unit price for widget.
     */
    public double getPrice() {
        if(head.getNext() != null){
            return head.getNext().getPrice();
        } else return 0.0;
    }
    /*
        getLastAmount()
        return: int - left over amount of widgets in the last node.
        parameter: none
        function: return the number of widgets in the last node. If none, return 0.
     */
    public int getLastAmount(){
        Node pointer = head;
        if(head.getNext() == null){
            return 0;
        }
        while (head.getNext() != null){
            pointer = pointer.getNext();
            //System.out.println(pointer.getAmount());
        }
        return pointer.getAmount();
    }
    /*
        getLastPrice()
        return: double - price in double format
        parameter: none
        function: if last node isn't null, get it's unit price for widget.
     */
    public double getLastPrice() {
        Node pointer = head;
        if(head.getNext() == null){
            return 0.0;
        }
        while(head.getNext() != null){
            pointer = pointer.next;
        }
        return pointer.price;
    }
}

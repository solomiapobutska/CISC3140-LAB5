/**
 * SOLOMIYA POBUTSKA
 * Assignment# 2
 * Linked Lists
 * CISC 3130
 * Spring 2020
 **/

import java.util.*;
import java.io.*;
class Node {
    char col1;
    int col2;
    double price;
    Node next=null;
    public Node(char col1, int col2, double price) {
        this.col1=col1;
        this.col2=col2;
        this.price=price;
    }
    public Node(char col1, int col2) {
        this.col1=col1;
        this.col2=col2;
    }
}
public class LinkedList {
    Node head=null;
    Node tail=null;
    void addNode(char col1, int col2, double price) {
        Node newNode=new Node(col1, col2, price);
        if(head==null) {
            head=newNode;
            tail=newNode;
        }
        else {
            tail.next=newNode;
            tail=newNode;
        }
    }
    void addNode(char col1, int col2) {
        Node newNode=new Node(col1, col2);
        if(head==null) {
            head=newNode;
            tail=newNode;
        }
        else {
            tail.next=newNode;
            tail=newNode;
        }
    }

    void readFile() {

        try {
            Scanner master = new Scanner(new File("src/data.txt"));
            while(master.hasNextLine()) {
                String line=master.nextLine();
                String[] arr=line.split("\\s+");
                if(arr.length==3) {
                    char col1=arr[0].charAt(0);
                    int col2=Integer.valueOf(arr[1]);
                    double price=Double.parseDouble(arr[2]);
                    addNode(col1, col2, price);
                }
                else if(arr.length==2) {
                    char col1=arr[0].charAt(0);
                    int col2=Integer.valueOf(arr[1]);
                    addNode(col1, col2);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println( "file not found. Exiting the program.");
        }
    }
    void lookup() {
        Node curr=head, sale=head;
        int Customers=0;
        double Discount=0;
        while(curr!=null) {
            if(curr.col1=='R') {
                double totalPrice=curr.col2*curr.price;
                System.out.println(curr.col2 + " widgets received at " + curr.price + " each.");
                System.out.println("Total Received: $" + totalPrice);
            }
            else if(curr.col1=='P') {
                System.out.println("Next 2 customers will receive " + curr.col2 + "% discount.");
                Customers=2;
                Discount=curr.col2;
            }
            else {
                Node temp=sale;
                int Widgets=0;
                while(temp!=curr && Widgets<=curr.col2) {
                    if(temp.col1=='R') {
                        Widgets+=temp.col2;
                    }
                    temp=temp.next;
                }
                if(Widgets>=curr.col2) {
                    Widgets=curr.col2;
                }
                System.out.println(Widgets + " Widgets sold");
                double Price=0;
                temp=sale;
                while(temp!=curr && Widgets>0) {
                    if(temp.col1=='R') {
                        double newamount=temp.price*1.3;
                        if(temp.col2<=Widgets) {
                            System.out.println(temp.col2 + " at " + newamount + " each\tprice:$" + newamount*temp.col2);
                            Price+=newamount*temp.col2;
                            Widgets-=temp.col2;
                            temp.col2=0;
                            temp=temp.next;
                        }
                        else {
                            System.out.println(Widgets + " at " + newamount + " each\tprice:$" + newamount*Widgets);
                            Price+=newamount*Widgets;
                            temp.col2 -=Widgets;
                            Widgets=0;
                        }
                    }
                    else {
                        temp=temp.next;
                    }
                }
                sale=temp;
                System.out.println("\t\tTotal price:$" + Price);
                if(Customers>0) {
                    System.out.println("\t\tDiscount:$" + Price*(Discount/100));
                    Customers--;
                }
                System.out.println("\tTotal price after Dicount:$" + Price*(1-Discount/100));
            }
            curr=curr.next;
            System.out.println();
        }
    }
    public static void main(String[] args) {
        LinkedList l=new LinkedList();
        l.readFile();
        l.lookup();
    }
}
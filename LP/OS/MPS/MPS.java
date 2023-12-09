package MPS;
import java.util.*;
public class MPS {
    public static void setData(Process[] p, Block[] b) {
        for(int i=0; i<p.length; i++) {
            p[i] = new Process(i+1);
        }
        for(int j=0; j<b.length; j++) {
            b[j] = new Block(j+1);
        }
        System.out.println("Data stored successfully !!");
        System.out.println("Processes:");
        System.out.println("P-Id\tSize");
        System.out.println("------------");
        for(Process i : p) {
            System.out.println("P" + i.id + "\t" + i.size);
            System.out.println("------------");
        }
        System.out.println();
        System.out.println();
        System.out.println("Blocks:");
        System.out.println("B-Id\tSize");
        System.out.println("------------");
        for(Block j : b) {
            System.out.println("B" + j.id + "\t" + j.size);
            System.out.println("------------");
        }
        System.out.println();
    }
    public static void printResults(Process[] p) {
        System.out.println("PId\tSize\tBlock\tFragmentation");
        System.out.println("------------------------------------");
        for(Process i : p) {
            System.out.print("P"+i.id + "\t" + i.size + "\t");
            if(i.block == 0) {
                System.out.println("-\t-");
            } else {
                System.out.println("B"+i.block+"\t"+i.fragmentation);
            }
            System.out.println("------------------------------------");
        }
    }
    public static void reset(Process[] p, Block[] b) {
        for(Process i : p) {
            i.block = 0;
            i.fragmentation = 0;
        }
        for(Block j : b) {
            j.freeSpace = j.size;
        }
        for(int i=0; i<b.length; i++) {
            for(int j=i+1; j<b.length; j++) {
                if(b[i].id > b[j].id) {
                    Block temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
        
    }
    public static void firstFit(Process[] p, Block[] b) {

        for(int i=0; i<p.length; i++) {
            Process curr = p[i];
            for(int j=0; j<b.length; j++) {
                if(curr.size <= b[j].freeSpace) {
                    curr.block = b[j].id;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }
    public static void nextFit(Process[] p, Block[] b) {
        int n = b.length;
        int block = -1;
        for(int i=0; i<p.length; i++) {
            Process curr = p[i];
            for(int j=(block+1)%n; j!=block; j=(j+1)%n) {
                if(curr.size <= b[j].freeSpace) {
                    curr.block = b[j].id;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    block = j;
                    break;
                }
            }
        }
    }
    public static void bestFit(Process[] p, Block[] b) {
        for(int i=0; i<p.length; i++) {
            Process curr = p[i];
            sortBlocks(b);
            for(int j=0; j<b.length; j++) {
                if(curr.size <= b[j].freeSpace) {
                    curr.block = b[j].id;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }
    public static void worstFit(Process[] p, Block[] b) {
        for(int i=0; i<p.length; i++) {
            Process curr = p[i];
            sortBlocksReverse(b);
            for(int j=0; j<b.length; j++) {
                if(curr.size <= b[j].freeSpace) {
                    curr.block = b[j].id;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }
    public static void sortBlocks(Block[] b) {  // by free space
        for(int i=0; i<b.length; i++) {
            for(int j=i+1; j<b.length; j++) {
                if(b[i].freeSpace > b[j].freeSpace) {
                    Block temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }
    public static void sortBlocksReverse(Block[] b) {
        for(int i=0; i<b.length; i++) {
            for(int j=i+1; j<b.length; j++) {
                if(b[i].freeSpace < b[j].freeSpace) {
                    Block temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();
        System.out.print("Enter number of blocks : ");
        int m = sc.nextInt();
        Process[] p = new Process[n];
        Block[] b = new Block[m];
        setData(p,b);
        while(true) {
            System.out.println("Enter your choice\n\t1-first fit\n\t2-next fit\n\t3-best fit\n\t4-worst fit");
            int choice = sc.nextInt();
            reset(p, b);
            switch(choice) {
                case 1:
                    firstFit(p, b);
                    break;
                case 2:
                    nextFit(p, b);
                    break;
                case 3:
                    bestFit(p, b);
                    break;
                case 4:
                    worstFit(p, b);
                    break;
                default:
                    System.out.println("Wrong choice");
            }
            if(choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                printResults(p);
            }
        }
    }
}
class Block{
    int id;
    int size;
    int freeSpace;
    public Block(int id) {
        this.id = id;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of B"+id+" : ");
        size = sc.nextInt();
        freeSpace = size;
    }
}
class Process{
    int id;
    int size;
    int block;
    int fragmentation;
    public Process(int id) {
        this.id = id;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of P"+id+" : ");
        size = sc.nextInt();
    }
}
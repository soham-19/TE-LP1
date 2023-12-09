import java.util.Scanner;

public class MPS {

    static class Process {
        int id;
        int size;

        int b_id;
        int fragmentation;

        public Process(int i) {
            id = i;
        }

        public void input() {

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter size of P" + id + ": ");
            size = sc.nextInt();
        }
    }

    static class Block {
        int id;
        int size;
        int freeSpace;

        public Block(int i) {
            id = i;
        }

        public void input() {

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter size of B" + id + ": ");
            freeSpace = size = sc.nextInt();
        }
    }

    public static void setProcesses(Process[] p) {
        for (int i = 0; i < p.length; i++) {
            p[i] = new Process(i + 1);
            p[i].input();
        }
    }

    public static void setBlocks(Block[] b) {
        for (int i = 0; i < b.length; i++) {
            b[i] = new Block(i + 1);
            b[i].input();
        }
    }

    public static void print(Process[] processes, Block[] blocks) {

        System.out.println("P-ID\tSize");
        for (Process p : processes) {
            System.out.println("P" + p.id + "\t" + p.size);
        }

        System.out.println("\n");

        System.out.println("B-ID\tSize");
        for (Block b : blocks) {
            System.out.println("B" + b.id + "\t" + b.size);
        }
        System.out.println();
    }

    public static void printResults(Process[] processes) {

        System.out.println("Process\t\tSize\t\tAllocated Block\t\tFragmented Block");
        System.out.println("--------------------------------------------------------------------------");

        for (Process p : processes) {
            System.out.println(p.id + "\t\t" + p.size + "\t\t\tB" + p.b_id + "\t\t\t" + p.fragmentation);
            System.out.println("--------------------------------------------------------------------------");
        }
        System.out.println("\n");
    }

    public static void reset(Process[] processes, Block[] blocks) {
        for (Process p : processes) {
            p.b_id = 0;
            p.fragmentation = 0;
        }

        for (Block b : blocks) {
            b.freeSpace = b.size;
        }
    }

    public static void firstFit(Process[] p, Block[] b) {

        for (int i = 0; i < p.length; i++) {

            Process curr = p[i];

            for (int j = 0; j < b.length; j++) {

                if (curr.size <= b[j].freeSpace) {
                    curr.b_id = b[j].id;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }

    public static void nextFit(Process[] p, Block[] b) {

        int selectedBlock = -1;
        int nBlock = b.length, nProcess = p.length;

        for (int i = 0; i < nProcess; i++) {
            Process curr = p[i];

            for (int j = (selectedBlock + 1) % nBlock; j != selectedBlock; j = ((j + 1) % nBlock)) {

                if (curr.size <= b[j].freeSpace) {

                    curr.b_id = b[j].id;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    selectedBlock = j;
                    break;
                }
            }
        }
    }

    public static void sortBlocks(Block[] b) {
        for (int i = 0; i < b.length; i++) {
            Block temp;
            for (int j = i + 1; j < b.length; j++) {
                if (b[i].freeSpace > b[j].freeSpace) {
                    temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }

    public static void bestFit(Process[] p, Block[] b) {

        for (int i = 0; i < p.length; i++) {

            Process curr = p[i];

            sortBlocks(b);

            for (int j = 0; j < b.length; j++) {
                if (curr.size <= b[j].freeSpace) {
                    curr.b_id = b[j].id;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }

    public static void sortBlocksReverse(Block[] b) {

        for (int i = 0; i < b.length; i++) {
            Block temp;
            for (int j = i + 1; j < b.length; j++) {
                if (b[i].freeSpace < b[j].freeSpace) {
                    temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }

    public static void worstFit(Process[] p, Block[] b) {
        for (int i = 0; i < p.length; i++) {
            Process curr = p[i];
            sortBlocksReverse(b);

            for (int j = 0; j < b.length; j++) {
                if (curr.size <= b[j].freeSpace) {
                    curr.b_id = b[j].id;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int m; // no of processes
        int n; // no of blocks

        System.out.print("Enter number of processes : ");
        m = sc.nextInt();
        Process[] p = new Process[m];
        setProcesses(p);

        System.out.print("Enter number of blocks : ");
        n = sc.nextInt();
        Block[] b = new Block[n];
        setBlocks(b);

        System.out.println("\nData stored successfully\n");

        print(p, b);

        firstFit(p, b);
        printResults(p);
        reset(p, b);

        nextFit(p, b);
        printResults(p);
        reset(p, b);

        bestFit(p, b);
        printResults(p);
        reset(p, b);

        worstFit(p, b);
        printResults(p);
        reset(p, b);

    }
}
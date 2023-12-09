import java.util.ArrayList;
import java.util.Scanner;

public class PRS {

    static Scanner sc = new Scanner(System.in);

    public static void fifo(int[] arr) {


        int hit = 0, miss = 0, idx = 0, fSize;

        ArrayList<Integer> frame = new ArrayList<>();

        System.out.print("Enter frame size : ");
        fSize = sc.nextInt();

        for(int i : arr) {

            if(frame.size() < fSize) {

                if(!frame.contains(i)) {
                    miss++;
                    frame.add(i);
                } else {
                    hit++;
                }
            } else {

                if(!frame.contains(i)) {
                    miss++;
                    frame.set(idx, i);
                    idx = (idx+1)%fSize;
                } else {
                    hit++;
                }
            }
            System.out.println("Frame " + frame);
        }
        System.out.println("Total miss " + miss);
        System.out.println("Total hit " + hit);
    }

    public static void lru(int[] arr) {

        int hit = 0, miss = 0, fSize;

        ArrayList<Integer> frame = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();

        System.out.print("Enter size of frame : ");
        fSize = sc.nextInt();

        for(int i : arr) {

            System.out.println("i: " + i + " Frame : " + frame + "\tTrack : " + track);

            if(frame.size() < fSize) {

                if(frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    frame.add(i);
                    track.add(i);
                }
            } else {
                if(frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    int leastUsed = track.get(0);
                    int idx = frame.indexOf(leastUsed);
                    frame.set(idx, i);
                    track.remove(0);
                    track.add(i);
                }
            }
        }

        System.out.println("Final Frame : " + frame);
        System.out.println("Final Track : " + track);
        System.out.println("Hits : " + hit);
        System.out.println("Miss : " + miss);
    }
    public static void mru(int[] arr) {

        int hit = 0, miss = 0, fSize;

        ArrayList<Integer> frame = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();

        System.out.print("Enter size of frame : ");
        fSize = sc.nextInt();

        for(int i : arr) {

            System.out.println("i: " + i + " Frame : " + frame + "\tTrack : " + track);

            if(frame.size() < fSize) {

                if(frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    frame.add(i);
                    track.add(i);
                }
            } else {
                if(frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    int leastUsed = track.get(fSize-1);
                    int idx = frame.indexOf(leastUsed);
                    frame.set(idx, i);
                    track.remove(fSize-1);
                    track.add(i);
                }
            }
        }

        System.out.println("Final Frame : " + frame);
        System.out.println("Final Track : " + track);
        System.out.println("Hits : " + hit);
        System.out.println("Miss : " + miss);
    }

    public static void main(String[] args) {
        int arr[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };
        fifo(arr);
        lru(arr);
        mru(arr);
    }
}
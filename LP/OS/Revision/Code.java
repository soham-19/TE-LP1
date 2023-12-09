import java.util.*;

public class Code {

    static Scanner sc = new Scanner(System.in);

    public static void fifo(int[] arr) {

        int hit = 0, miss = 0, idx = 0, fSize;

        System.out.print("Enter frame size : ");
        fSize = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();

        for (int i : arr) {

            if (frame.size() < fSize) {

                if (frame.contains(i)) {
                    hit++;
                } else {
                    miss++;
                    frame.add(i);
                }
            } else {

                if (frame.contains(i)) {
                    hit++;
                } else {
                    miss++;
                    frame.set(idx, i);
                    idx = (idx + 1) % fSize;
                }
            }

            System.out.println("i:" + i + " Frame : " + frame);
        }
        System.out.println("Miss : " + miss);
        System.out.println("Hit : " + hit);
    }

    public static void lru(int[] arr) {

        int miss = 0, hit = 0, fSize;

        System.out.print("Enter frame size : ");
        fSize = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();

        for (int i : arr) {

            if (frame.size() < fSize) {

                if (frame.contains(i)) {
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

                if (frame.contains(i)) {
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

            System.out.println("i:" + i + " Frame : " + frame + "\tTrack : " + track);
        }
        System.out.println("Miss : " + miss);
        System.out.println("Hit : " + hit);
    }

    public static void mru(int[] arr) {

        int hit = 0, miss = 0, fSize;

        System.out.print("Enter the frame size : ");
        fSize = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();

        for (int i : arr) {

            if (frame.size() < fSize) {
                if (frame.contains(i)) {
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
                if (frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    int mostRecentlyUsed = track.get(track.size() - 1);
                    int idx = frame.indexOf(mostRecentlyUsed);
                    frame.set(idx, i);
                    track.remove(track.size() - 1);
                    track.add(i);
                }
            }
            System.out.println("i:" + i + " Frame : " + frame + "\tTrack : " + track);
        }
        System.out.println("Miss : " + miss);
        System.out.println("Hit : " + hit);
    }

    public static void main(String[] args) {

        int arr[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };
        fifo(arr);
        lru(arr);
        mru(arr);
    }
}
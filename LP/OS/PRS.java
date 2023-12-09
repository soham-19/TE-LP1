import java.util.*;

public class PRS {
    static Scanner sc = new Scanner(System.in);

    public static void fifo(int[] arr) {
        int miss = 0;
        int hit = 0;
        int idx = 0;
        System.out.print("Enter the frame size ");
        int fSize = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {

            if (frame.size() < fSize) {

                if (!frame.contains(arr[i])) {
                    miss++;
                    frame.add(arr[i]);
                } else {
                    hit++;
                }
            } else {
                if (frame.contains(arr[i])) {
                    hit++;
                } else {
                    miss++;
                    frame.set(idx, arr[i]);
                    idx = (idx+1)%fSize;
                }
            }

            System.out.println("status: " + frame);
        }

        System.out.println("Final Frame : " + frame);
        System.out.println("Page Miss : " + miss);
        System.out.println("Hit : " + hit);
    }

    public static void lru(int[] arr) {

        int miss = 0, hit = 0, fSize;

        ArrayList<Integer> frame = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();

        System.out.print("Enter the frame size : ");
        fSize = sc.nextInt();
        for(int i : arr) {

            System.out.println(i + "  Frame : " + frame + "\tTrack : " + track);

            if(frame.size() < fSize) {

                if(frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    track.add(i);
                    frame.add(i);
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

    public static void main(String[] args) {
        int arr[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1 };
        lru(arr);
    }
}
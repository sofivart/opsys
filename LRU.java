import java.util.*;

class LRU
{
    static int pageFaults(int pages[], int capacity)
    {
        int fault = 1;
        LinkedList<Integer> list = new LinkedList<>();

        for(int i = 0; i < capacity; i++) {
            if(!list.contains(pages[i])) {
                list.add(pages[i]);
                fault++;
            }
        }

        for(int i = capacity; i < pages.length; i++) {
            if(!list.contains(pages[i])) {
                list.remove();
                list.add(pages[i]);
                fault++;
            }
        }
        return fault;
    }
    // Driver Method to test your algorithm with a simple example
    public static void main(String args[])
    {
        /*
         * This is an array that holds the reference string for all
         * page requests.
         */
        int pages[] = {5, 1, 0, 3, 2, 3, 0, 4, 2, 3, 0, 3, 5, 2};

        // This is the number of available page frames
        int memoryCapacity = 3;

        int faults = pageFaults(pages, memoryCapacity);
        System.out.println(faults);
    }
}

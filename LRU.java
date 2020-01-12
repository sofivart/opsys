import java.util.*;

/*
    5, 1, 0, 3, 2, 3, 0, 4, 2, 3, 0, 3, 5, 2
    To perform the LRU method in these set of numbers for three slots capacity we get the following method.
    F 5
    F 5 1
    F 5 1 0
    F 1 0 3
    F 0 3 2
    hit 0 2 3
    hit 2 3 0
    F 3 0 4
    F 0 4 2
    F 4 2 3
    F 2 3 0
    hit 2 0 3
    F 0 3 5
    F 3 5 2
    fault: 11
    So, we check if the next page is already allocated in the memory. If the list contains the pages[i] then we have a hit.
    Else, if the list doesn't contain the pages[i], we add them into the list and increase the fault. This applies to the first
    3 pages, until the counter reaches the capacity. Then, we do another for loop from i=3 until the last page and check again if the list
    contains the pages. If it doesn't, we remove it from the list and then increase the fault. In the end, we simply return the fault.

 */

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

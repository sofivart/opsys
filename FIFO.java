class FIFO
{
    // Method to find page faults using indexes
    static int pageFaults(int pages[], int capacity)
    {
        /*
         * Put your code in here!
         */
        int[] memory = new int[capacity];
        boolean found;
        for (int i = 0; i<capacity; i++){
            memory[i] = -1;
        }
        int hit = 0;
        int lastChange = 0;
        for(int i=0; i< pages.length; i++){
            found = false;
            for(int j = 0; j<capacity; j++){
                if(memory[j] == -1){
                    memory[j] = pages[i];
                    lastChange++;
                    found = true;
                    break;
                }
                if(memory[j] == pages[i]){
                    hit++;
                    found = true;
                    break;
                }
            }
            if(!found){
                memory[lastChange] = pages[i];
                lastChange++;
            }
            if(lastChange == capacity){
                lastChange = 0;
            }

        }
        int faults = pages.length - hit;
        return faults;

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

class FIFO
{
    // Method to find page faults using indexes
    static int pageFaults(int pages[], int capacity)
    {

        int[] memory = new int[capacity];       //we create an array of capacity length to store the pages last used
        boolean found;      //if there is a hit or the memory still has empty space, it becomes true
        for (int i = 0; i<capacity; i++){       //initialization of memory array with value -1, witch means no pages are stored
            memory[i] = -1;
        }
        int hit = 0;        //number of hits
        int lastChange = 0;     //the place of the memory array that the next page will be stored if it is not a hit
        for(int i=0; i< pages.length; i++){
            found = false;      //initialization of found boolean with value false
            for(int j = 0; j<capacity; j++){        //parsing through the memory array
                if(memory[j] == -1){        //if memory cell is empty
                    memory[j] = pages[i];       //it immediately stores the new page
                    lastChange++;       //declares the next memory place to be changed
                    found = true;       //set true so as to not enter the if outside of the loop that pushes the page into memory array
                    break;      //breaks the loop
                }
                if(memory[j] == pages[i]){      //if the memory value equals the page to be loaded
                    hit++;      //there is a hit
                    found = true;       //page i is found in the memory table
                    break;      //breaks the loop
                }
            }
            if(!found){     //when the loop parsing through the memory array is finishes, if boolean found is still false, page i has to be loaded into memory
                memory[lastChange] = pages[i];      //loads page i to memory place of lastChange
                lastChange++;       //moves to the next place to be changed
            }
            if(lastChange == capacity){     //if the lastChanged value equals capacity, which means me are out od bounds of memory array
                lastChange = 0;     //we set the lastChanged value back to 0
            }

        }
        int faults = pages.length - hit;        //faults are the pages array length minus the hits found
        return faults;      //returns faults

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

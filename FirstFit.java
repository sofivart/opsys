import java.util.ArrayList;

public class FirstFit
{
    /*
     * Method to allocate memory to blocks according to the first fit
     * algorithm. It should return an ArrayList of Integers, where the
     * index is the process ID (zero-indexed) and the value is the block
     * number (also zero-indexed).
     */
    static ArrayList<Integer> firstFit(int sizeOfBlocks[], int sizeOfProcesses[])
    {

        int m=sizeOfBlocks.length;//number of blocks
        int n=sizeOfProcesses.length;//number of processes
        ArrayList<Integer> array = new ArrayList<Integer>(n);//Stores block id of the block allocated to a process
        for(int i=0;i<n;i++){
            //for each process: check if it can be assigned to the current block
            int flag=0;//flag=1-> process can be assigned to a block or flag=1->process can't be assigned to a block
            for(int j=0;j<m;j++){
                if(sizeOfBlocks[j]>=sizeOfProcesses[i]){ //if yes
                    flag=1;
                    array.add(j);//// allocate block j to process
                    sizeOfBlocks[j] -= sizeOfProcesses[i]; //reduce available memory in this block
                    break;//In the first fit, the partition is allocated which is first sufficient from the top of Main Memory.
                }

            }
            if(flag==0){array.add(-255);}
        }
        return array;

    }

    // Method to print the memory allocation
    public static void printMemoryAllocation(ArrayList<Integer> memAllocation) {
        System.out.println("Process No.\tBlock No.");
        System.out.println("===========\t=========");
        for (int i = 0; i < memAllocation.size(); i++)
        {
            System.out.print("\t " + i + "\t\t\t");
            // if a process has been allocated position -255, it means that it
            // has not been actually allocated
            if (memAllocation.get(i) != -255)
                System.out.print(memAllocation.get(i));
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }

    // Driver Method to test your algorithm with a simple example
    public static void main(String[] args)
    {
        /* There are 5 available blocks in this example. The block ID
         * is the array index and the available block size is the value.
         * So we have the following blocks and sizes:
         *
         *   BlockID    Size
         *   =======    ====
         *      0        200
         *      1        500
         *      2        100
         *      3        300
         *      4        600
         *
         */
        int sizeOfBlocks[] = {200, 500, 100, 300, 600};
        /* And there are 4 processes. The process ID is the array
         * index. So we have these processes and sizes:
         *
         *   ProcessID     Size
         *   =========     ====
         *       0          214
         *       1          415
         *       2          112
         *       3          425
         */
        int sizeOfProcesses[] = {214, 415, 112, 425};

        ArrayList<Integer> memAlloc = firstFit(sizeOfBlocks, sizeOfProcesses);
        printMemoryAllocation(memAlloc);
    }
}

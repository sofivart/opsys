import java.util.ArrayList;

public class FCFS
{
    // Method that calculates the waiting time for all processes
    static int[] calcWaitingTime(int burstTime[], int quantum)
    {
        int n = burstTime.length;       //number of processes
        int [] time=new int[n];     //an array with length n, which indicates when each process begins, that is the waiting time
        time[0] = 0;        //process 0 begins at time 0

        for(int i = 1; i<n; i++){       //for the rest of the processes
            time[i]= burstTime[i-1] + time[i-1] ;       //the waiting time equals the burst and waiting times of the previous process
        }
        for (int i=0; i<n; i++){        //for every process, we reduce the waiting time by the index of the process, which is the arriving time of the process
            time[i]-=i;
        }
        return time;        //returns array with waiting times
    }

    // Method that calculates turn around time for all processes
    static int[] calcTurnAroundTime(int burstTime[], int waitingTime[])
    {
        int n = burstTime.length;       //number of processes
        int [] time=new int[n];     //an array with length n, that contains the turnaround times
        for(int i = 0; i<n; i++){       //loop for all processes
            time[i]= burstTime[i] + waitingTime[i]  ;       //turnaround time equals the sum of burst and waiting times of the process
        }
        return time;        //returns array with turnaround times


    }

    // Method that prints the results and calculates the average waiting and
    // turnaround times
    static void printAvgTimes(int burstTime[], int quantum)
    {
        int n = burstTime.length;                       //n equals the number of processes
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        // Find waiting time of all processes
        int[] waitingTime = calcWaitingTime(burstTime, quantum);

        // Find turn around time for all processes
        int[] turnAroundTime = calcTurnAroundTime(burstTime, waitingTime);

        // Display processes along with all details
        System.out.println("Process " + " Burst Time " +
                " Waiting Time " + " Turnaround Time");
        System.out.println("=======  ==========  ============  ===============");
        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
            System.out.println(i + "\t\t\t" + burstTime[i] +"\t\t\t " +
                    waitingTime[i] +"\t\t\t\t " + turnAroundTime[i]);
        }

        System.out.println("\nAverage waiting time = " +
                (float)totalWaitingTime / (float)n);
        System.out.println("Average turnaround time = " +
                (float)totalTurnAroundTime / (float)n);
    }

    // Driver Method to test your algorithm with a simple example
    public static void main(String[] args)
    {
        // Burst time of processes. The array index is the process ID.
        int burstTime[] = {5, 15, 4, 3};

        // Time quantum
        int quantum = 3;

        printAvgTimes(burstTime, quantum);
    }
}

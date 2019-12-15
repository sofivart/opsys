package CPU;

public class RoundRobin
{
    // Method that calculates the waiting time for all processes
    static int[] calcWaitingTime(int burstTime[], int quantum) {
        int n = burstTime.length;
        int[] waitingtime = new int[n];
        for (int i = 0; i < n; i++) {
            waitingtime[i] = 0;
        }
        boolean done = true;
        int[] bt = new int[n];
        bt = burstTime.clone();
        do {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        if (bt[j] != 0) {
                            if (bt[i] >= quantum) {
                                waitingtime[j] += quantum;
                            } else {
                                waitingtime[j] += bt[i];
                            }
                        }
                    }
                }
                if (bt[i] >= quantum) {
                    bt[i] -= quantum;
                } else {
                    bt[i] = 0;
                }
            }
            done = true;
            for (int i = 0; i < n; i++) {
                if (bt[i] != 0) {
                    done = false;
                    break;
                }
            }
        }while (!done);
        return waitingtime;
    }

    // Method that calculates turn around time for all processes
    static int[] calcTurnAroundTime(int burstTime[], int waitingTime[])
    {
        /*
         * Put your code here!
         */
        int n = burstTime.length;
        int[] tat = new int[n];
        for(int i = 0; i<n; i++){
            tat[i] = burstTime[i]+ waitingTime[i];
        }
        return tat;

    }

    // Method that prints the results and calculates the average waiting and
    // turnaround times
    static void printAvgTimes(int burstTime[], int quantum)
    {
        int n = burstTime.length;
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
            System.out.println(i + "\t\t\t" + burstTime[i] +"\t\t\t" +
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

package task12;

import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Integer.parseInt;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        int numThreads = parseInt(args[0]);
        String hashCode = args[1];

        ArrayList<PasswordRunnable> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++){
            PasswordRunnable tmp = new PasswordRunnable(i, hashCode, numThreads);
            threads.add(tmp);
            tmp.start();
        }
    }
}

package task12;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class PasswordRunnable extends Thread {

    private int[] curPass;
    private final String hashcode;
    private final int numThreads;
    public final int alpaLen = 26;
    public final int variants = (int)Math.pow(alpaLen, 7);

    private boolean isComplete = true;
    private static final char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private static final Object lock = new Object();

    public PasswordRunnable(int thNum, String hashcode, int numThreads){
        this.hashcode = hashcode;
        this.numThreads = numThreads;
        int curVariant = variants / numThreads * thNum;
        int[] start = new int[]{0, 0, 0, 0, 0, 0, 0};
        int j = curVariant;
        int i = 6;
        while (j > 1) {
            start[i] = j % alpaLen;
            j = j / alpaLen;
            i --;
        }
        this.curPass = start;
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    private static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }

    private String Generate(){
        String pass = "" + alpha[this.curPass[0]] + "" + alpha[this.curPass[1]] + "" + alpha[this.curPass[2]] + ""
                + "" + alpha[this.curPass[3]] + "" + alpha[this.curPass[4]] + "" + alpha[this.curPass[5]] + "" + alpha[this.curPass[6]];

        if (this.curPass[6] == alpaLen - 1) {
            for (int j = 6; j > 0; j--) {
                if (this.curPass[j] == alpaLen - 1) {
                    this.curPass[j] = 0;
                    this.curPass[j - 1] += 1;
                }
            }
        }
        else this.curPass[6] += 1;
        return pass;
    }

    private boolean check(String genHash){
        return Objects.equals(genHash, this.hashcode);
    }

    public void run() {
        int n = variants / this.numThreads;
            while (n > 0) {
                if (isComplete){
                    synchronized (lock) {
                        String generated;
                        generated = Generate();
                        System.out.println(generated);
                        if (check(hashPassword(generated))) {
                                System.out.println("Пароль:" + generated);
                                isComplete = false;
                        }
                    }
                }
                else break;
                n--;
            }
        }
    }



import java.io.IOException;

public class Cut {

    private String ofile = null;

    public static void main(String[] args) throws Exception {

        if (args[2].contains("-o")) {
            ofile = args[3];
        }

        if (args[0].equals("cut") && args[1].equals("-c") && args[args.length - 1].contains("-")) {

        } else {
            if (args[0].equals("cut") && args[1].equals("-w") && args[args.length - 1].contains("-")) {

            } else throw new IOException();
        }

    }

    public static void toCut(int n, int k, boolean flag) {
        if (flag == false) {

        } else {

        }
    }
}

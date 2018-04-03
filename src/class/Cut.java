import java.io.IOException;

public class Cut {

    public static void main(String[] args) throws Exception{
        if (args[0].equals("cut") && args[1].equals("-c") && args[args.length - 1].contains("-")){

        } else {
            if (args[0].equals("cut") && args[1].equals("-w") && args[args.length - 1].contains("-")){

            }else throw new IOException();
        }

    }

    public static void toCut(int n, boolean flag, String outName){

    }

    public static void toCut(int n, int k, String outName){

    }
}

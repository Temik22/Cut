public class CutParser {

    private static String input = "", output = "";
    private static int n, k;
    private static boolean words = false, chars = false;

    public CutParser(String[] args) throws IllegalArgumentException{

        StringBuilder check = new StringBuilder(args[0]);
        for (int i = 1; i < args.length; i++)
            check.append(' ').append(args[i]);

        if (check.toString().matches("cut (-c|-w) (-o \\w+ )?(\\w+)? (\\d*-\\d*)")) {


            for (int i = 1; i < args.length; i++) {
                switch (args[i]) {
                    case ("-c"):
                        chars = true;
                        break;
                    case ("-w"):
                        words = true;
                        break;
                    case ("-o"): {
                        output = args[i + 1];
                        break;
                    }
                    default:
                        input = args[args.length - 2];
                }
            }

            String[] range = args[args.length - 1].split("-");

            if (range[0].equals("")) {
                n = 0;
                k = Integer.parseInt(range[1]) - 1;
            } else {
                if (range.length == 1) {
                    k = -1;
                    n = Integer.parseInt(range[0]) - 1;
                } else {
                    n = Integer.parseInt(range[0]) - 1;
                    k = Integer.parseInt(range[1]) - 1;
                }
            }

        } else {
            throw new IllegalArgumentException("Wrong command line");
        }
    }

    public static String getInput() {
        return input;
    }

    public static String getOutput() {
        return output;
    }

    public static int getN() {
        return n;
    }

    public static int getK() {
        return k;
    }

    public static boolean isWords() {
        return words;
    }

    public static boolean isChars() {
        return chars;
    }
}
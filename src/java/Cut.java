import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Cut {

    private static String input = "", output = "";
    private static int n, k;
    private static boolean words = false, chars = false;

    public static void main(String[] args) throws Exception {

        if (args[0].equals("cut") && (args[1].equals("-w") || args[1].equals("-c")) && args[args.length - 1].contains("-")) {

            for (int i = 1; i < args.length; i++) {
                switch (args[i]) {
                    case ("-c"):
                        chars = true;
                    case ("-w"):
                        words = true;
                    case ("-o"): {
                        output = args[i + 1];
                        String temp = args[i + 2];
                        if (!temp.equals(args[args.length - 1])) input = temp;
                    }
                    default:
                }
            }

            String[] range = args[args.length - 1].split("-");

            if (range[0].equals("")) {
                n = 0;
                k = Integer.parseInt(range[1]);
            } else {
                if (range[1].equals("")) {
                    k = -1;
                    n = Integer.parseInt(range[0]);
                } else {
                    n = Integer.parseInt(range[0]);
                    k = Integer.parseInt(range[1]);
                }
            }

        } else {
            throw new IOException("Wrong command line");
        }

        if (words == true && chars == true) throw new IOException("Command line error");

        String text = new String(Files.readAllBytes(Paths.get("src/files" + input + ".txt")));
        Cut.toCut(text);
    }

    public static List<String> toCut(String text) {

        List<String> answerFile = new ArrayList<>();
        StringBuilder answerLine = new StringBuilder();

        if (chars == true) {

            for (String lines : text.split("\n")) {

                int length = lines.length();
                if (k == -1) k = length;

                if (n != 0) {
                    if (k >= length) {
                        int begin = n;
                        length -= n;
                        while (length != 0) {
                            answerLine.append(lines.charAt(begin));
                            begin++;
                            length--;
                        }
                    } else {
                        length = k - n;
                        int begin = n;
                        while (length != 0) {
                            answerLine.append(lines.charAt(begin));
                            begin++;
                            length--;
                        }
                    }
                } else {
                    if (k >= length) {
                        answerLine.append(lines);
                    } else {
                        length = k;
                        int begin = n;
                        while (length != 0) {
                            answerLine.append(lines.charAt(begin));
                            begin++;
                            length--;
                        }
                    }
                }
                answerFile.add(answerLine.toString());
                answerLine.delete(0, answerLine.length());
            }

        } else {

            List<String> lineBuilder = new ArrayList<>();

            for (String lines : text.split("\n")) {

                String[] word = lines.split(" ");
                int length = word.length;
                if (k == -1) k = length;

                if (n != 0) {
                    if (k >= length) {
                        int begin = n;
                        length -= n;
                        while (length != 0) {
                            lineBuilder.add(word[begin]);
                            begin++;
                            length--;
                        }
                    } else {
                        length = k - n;
                        int begin = n;
                        while (length != 0) {
                            lineBuilder.add(word[begin]);
                            begin++;
                            length--;
                        }
                    }
                } else {
                    if (k >= length) {
                        for (int i = 0; i < length; i++) lineBuilder.add(word[i]);
                    } else {
                        length = k;
                        int begin = n;
                        while (length != 0) {
                            lineBuilder.add(word[begin]);
                            begin++;
                            length--;
                        }
                    }
                }

                answerLine.append(lineBuilder.get(0));
                for (int i = 1; i < lineBuilder.size(); i++) answerLine.append(" " + lineBuilder.get(i));
                answerFile.add(answerLine.toString());
                answerLine.delete(0, answerLine.length());
            }
        }
        return answerFile;
    }
}

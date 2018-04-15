import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cut {

    private static String input = "", output = "";
    private static int n, k;
    private static boolean words = false, chars = false;

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if (command.matches("cut (-c|-w) (-o \\w+ )?(\\w+)? (\\d*-\\d*)")) {

            args = command.split(" ");

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

        String text = new String(Files.readAllBytes(Paths.get("src/files/" + input + ".txt")));
        List<String> cutter = Cut.toCut(text);


        if (output == "") {
            for (String line : cutter) {
                System.out.println(line);
            }
        } else {
            FileWriter writer = new FileWriter("src/files/" + output + ".txt");
            String out = cutter.toString().replaceAll("\\[|\\]", "").replace(", ","\n");
            writer.write(out);
            writer.close();
        }
    }

    public static List<String> toCut(String text) {

        List<String> answerFile = new ArrayList<>();
        StringBuilder answerLine = new StringBuilder();

        if (chars && !words) {

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
                        length = k - n + 1;
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
                        length = k + 1;
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
                        length = k - n + 1;
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
                        length = k + 1;
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
                lineBuilder.clear();
            }
        }
        return answerFile;
    }

}

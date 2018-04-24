import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Cut {


    public static void main(String[] args) throws Exception {

        CutParser command = new CutParser(args);



        String text = new String(Files.readAllBytes(Paths.get("src/files/" + CutParser.getInput() + ".txt")));
        List<String> cutter = Cut.toCut(text);


        if (CutParser.getOutput() == "") {
            for (String line : cutter) {
                System.out.println(line);
            }
        } else {
            FileWriter writer = new FileWriter("src/files/" + CutParser.getOutput() + ".txt");
            String out = cutter.toString().replaceAll("\\[|\\]", "").replace(", ","\n");
            writer.write(out);
            writer.close();
        }
    }

    public static List<String> toCut(String text) {

        List<String> answerFile = new ArrayList<>();
        StringBuilder answerLine = new StringBuilder();
        int k = CutParser.getK();
        int n = CutParser.getN();

        if (CutParser.isChars() && !CutParser.isWords()) {

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

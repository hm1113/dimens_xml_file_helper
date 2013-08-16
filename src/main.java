import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 */

/**
 * @author heming.liu
 */
public class main {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        String inputPath = "/Users/heming.liu/Documents/funzio/android_client/dr/android-ui-lib/AndroidUILib/res/values/dimens.xml";
        String outputPath = "/Users/heming.liu/Documents/funzio/android_client/dr/android-ui-lib/AndroidUILib/res/values/dimensÑnew.xml";

        generateDimensXML(inputPath, outputPath, 1.8f);
    }

    private static void generateDimensXML(final String inputPath, final String outputPath, final float factor) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));

            String line = null;

            while ((line = reader.readLine()) != null) {
                int begin = line.indexOf("\">") + 2;
                int end = line.indexOf("</") - 2;
                if (begin > 0 && end > 0) {
                    String original = line.substring(begin, end);
                    String tmp = "";
                    if (original.substring(0, 1).equals("-")) {
                        tmp = "-";
                        original = original.substring(1);
                    }

                    float result = Float.parseFloat(original) * factor;

                    java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
                    String text = df.format(result);

                    // String text = String.format((Float.toString(result)), "%.1f");
                    writer.write(line.substring(0, begin) + tmp + text + line.substring(end));
                } else {
                    writer.write(line);
                }
                writer.newLine();

            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

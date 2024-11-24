import org.apache.poi.xwpf.usermodel.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DocxFootnotesConverter {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java DocxFootnotesConverter <input.docx> <output.docx>");
            return;
        }
        System.out.println("input: " + args[0]);
        System.out.println("output: " + args[1]);

        try (FileInputStream fis = new FileInputStream(args[0]);
             XWPFDocument document = new XWPFDocument(fis);
             FileOutputStream fos = new FileOutputStream(args[1])) {

            List<XWPFFootnote> footnotes = document.getFootnotes();
            for (XWPFFootnote footnote : footnotes) {
                System.out.println("footnote: " + footnote);
                for (XWPFParagraph paragraph : footnote.getParagraphs()) {
                    StringBuilder newText = new StringBuilder("((");
                    for (XWPFRun run : paragraph.getRuns()) {
                        newText.append(run.getText(0));
                    }
                    newText.append("))");

                    paragraph.getRuns().clear(); // Clear old content
                    XWPFRun newRun = paragraph.createRun();
                    newRun.setText(newText.toString());
                }
            }


            document.write(fos);
            System.out.println("Document saved to: " + args[1]);

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage()); // Use System.err for errors
        }
    }
}

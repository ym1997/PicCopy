import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TestPicCopyforChar {
    public static void main(String[] args) throws FileNotFoundException {
        PicCopyForChar picCopyForChar=null;
        JFileChooser jFileChooser = new JFileChooser();
        int i = jFileChooser.showOpenDialog(null);
        if (i == 0) {
            File selectedFile = jFileChooser.getSelectedFile();
            picCopyForChar = new PicCopyForChar(selectedFile.getAbsolutePath());
            //System.out.println(selectedFile.getAbsoluteFile());
            int j = jFileChooser.showOpenDialog(null);
            if (j == 0) {
                picCopyForChar.uploadPic(jFileChooser.getSelectedFile().getPath());
            }
            picCopyForChar.close();
        }


        }
    }


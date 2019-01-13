import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TestPicCopyforStream {
    public static void main(String[] args) throws FileNotFoundException {
        PicCopyForStream picCopyForStream=null;
        JFileChooser jFileChooser = new JFileChooser();
        int i = jFileChooser.showOpenDialog(null);
        if (i == 0) {
            File selectedFile = jFileChooser.getSelectedFile();
            picCopyForStream = new PicCopyForStream(selectedFile.getAbsolutePath());
            System.out.println("--"+selectedFile.getAbsolutePath());
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //System.out.println(selectedFile.getAbsoluteFile());
            int j = jFileChooser.showOpenDialog(null);
            if (j == 0) {
                picCopyForStream.uploadPic(jFileChooser.getSelectedFile().getPath());
                System.out.println("---"+jFileChooser.getSelectedFile().getPath());
            }
           // picCopyForStream.close();
        }


    }
}


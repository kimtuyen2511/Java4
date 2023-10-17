package qldaotao.utis;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XImage {

    public static Image getApplcon() {
        URL url = XImage.class.getResource("/img/next.png");
        return new ImageIcon(url).getImage().getScaledInstance(111, 93,
                Image.SCALE_SMOOTH);
    }

    public static void save(File src) {

        File newFile = new File("Img", src.getName());
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdir();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(newFile.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon read(String fileName) {
        File path = new File("Img", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}

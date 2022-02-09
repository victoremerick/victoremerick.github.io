package app7food.view.images;

import app7food.view.icones.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;

public class GetImage {

    private GetImage() {
    }

    public static ImageIcon create(String name) {
        URL is = GetImage.class.getResource(name.toLowerCase() + ".png");
        if (is != null) {
            ImageIcon ii = new ImageIcon(is);
            return ii;
        } else {
            return null;
        }
    }
}

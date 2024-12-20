/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #11
 * 1 - 5026231069 - Muhammad Zaky Al Khair
 * 2 - 5026231068 - Nailah Adlina
 * 3 - 5026231173 - Naura Salsabila
 */

package ConnectFour;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public enum Seed {   // to save as "Seed.java"
    CROSS("X", "kucingegypt.png"),   // displayName, imageFilename
    NOUGHT("O", "egypt.png"),
    NO_SEED(" ", null);

    // Private variables
    private String displayName;
    private Image img = null;

    // Constructor (must be private)
    private Seed(String name, String imageFilename) {
        this.displayName = name;

        if (imageFilename != null) {
            URL imgURL = getClass().getClassLoader().getResource(imageFilename);
            ImageIcon icon = null;
            if (imgURL != null) {
                icon = new ImageIcon(imgURL);
                this.img = icon.getImage(); // Ensure the gif will be properly animated
            } else {
                System.err.println("Couldn't find file " + imageFilename);
            }
        }
    }

    public String getDisplayName() {
        return displayName;
    }
  
    public Image getImage() {
        return img;
    }
}

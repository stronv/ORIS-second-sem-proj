package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Hole extends SuperObject{
    public Hole(){
        name = "Hole";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/hole.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}

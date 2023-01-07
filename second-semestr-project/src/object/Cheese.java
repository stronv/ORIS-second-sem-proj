package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Cheese extends SuperObject{
    public Cheese(){
        name = "Cheese";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cheese_.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

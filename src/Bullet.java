import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    public Vector2D position;
    public Vector2D velocity;
    public BufferedImage image;

    public Bullet() {

        position = new Vector2D();
        velocity = new Vector2D();

    }

    public void run() {
        position.addUp(velocity);
    }



    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int)position.x, (int)position.y, 20,20,null );

    }


}


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Star {


    public Vector2D position;

    public BufferedImage image;
    public Vector2D velocity;

    public Star() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
    }


    public void run() {
        this.position.addUp(velocity);
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int)this.position.x, (int)this.position.y, 5, 5, null);
    }
}
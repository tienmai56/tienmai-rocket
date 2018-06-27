import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {

        BufferedImage backBuffered;
        BufferedImage bulletImage;
        Graphics graphics;

        int countStar = 0;
        int countBull = 0;
        int countPlayerBull =0;

        List<Star> stars;
        List<Bullet> bullets;
        List<Bullet> playerBullet;

        Background background;

        public Player player = new Player();
        public Enemy enemy = new Enemy();

        private Random random = new Random();


        public GameCanvas() {
            this.setSize(1024, 600);

            this.setupBackBuffered();

            this.setupCharacter();

            this.setVisible(true);
        }

        private void setupBackBuffered() {
            this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_4BYTE_ABGR);
            this.graphics = this.backBuffered.getGraphics();
        }

        private void setupCharacter() {
            this.background = new Background();
            this.stars = new ArrayList<>();
            this.bullets = new ArrayList<>();
            this.playerBullet = new ArrayList<>();
            this.setupPlayer();
            this.setupEnemy();
        }

        private void setupPlayer() {
            this.player.position.set(100, 200);
        }

        private void setupEnemy() {
            this.enemy.position.set(800, 400);
            this.enemy.image = this.loadImage("resources/images/circle.png");

        }



        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(this.backBuffered, 0, 0, null);
        }

        public void renderAll() {
            this.background.render(this.graphics);
            this.stars.forEach(star -> star.render(graphics));

            this.player.render(this.graphics);
            this.playerBullet.forEach(bullet -> bullet.render(graphics));
            this.enemy.render(this.graphics);
            this.bullets.forEach(bullet -> bullet.render(graphics));

            this.repaint();
        }

        public void runAll() {
            this.createStar();
            this.stars.forEach(star -> star.run());
            this.runEnemy();
            creatBullet();
            this.bullets.forEach(bullet -> bullet.run());
            this.player.run();
            this.creatPlayerBullet();
            this.playerBullet.forEach(bullet -> bullet.run());
        }


    public void creatPlayerBullet() {

        if (this.countPlayerBull == 20) {

            Bullet bullet = new Bullet();
            bullet.position.set(player.position);
            bullet.image = this.loadImage("resources/images/star.png");
            bullet.velocity.set(5, 0);
            this.bullets.add(bullet);
            this.countPlayerBull = 0;


        }else {
            this.countPlayerBull ++;
        }
    }




    public void creatBullet() {

        if (this.countBull == 10) {

            Bullet bullet = new Bullet();
            bullet.position.set(enemy.position);
            bullet.image = this.loadImage("resources/images/star.png");
            bullet.velocity.set(5, 0);
            this.bullets.add(bullet);
            this.countBull = 0;


        }else {
            this.countBull ++;
        }
    }

        private void createStar() {
            if (this.countStar == 20) {
                Star star = new Star();
                star.position.set(1024,random.nextInt(600));
                star.image = this.loadImage("resources/images/star.png");
                star.velocity.set(-this.random.nextInt(3)+1, 0);



//                Star star = new Star(
//                        1024,
//                        this.random.nextInt(600),
//                        this.loadImage("resources/images/star.png"),
//                        -this.random.nextInt(3) + 1,
//                        0
//                );

                this.stars.add(star);
                this.countStar = 0;
            } else {
                this.countStar += 1;
            }
        }

        private void runEnemy() {
            Vector2D velocity = this.player.position
                    .subtract(this.enemy.position)
                    .normalize()
                    .multiply(1.5f);
            this.enemy.velocity.set(velocity);
            this.enemy.run();
        }

        private BufferedImage loadImage(String path) {
            try {
                return ImageIO.read(new File(path));
            } catch (IOException e) {
                return null;
            }
        }
    }


package com.nighthawk.spring_portfolio.mvc.collision;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;


import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings 
    final int originalTileSize = 16;  // 16x16 default tile/character size 
    // scaling 
    final int scale = 3;

    final int tileSize = originalTileSize * scale ; 
    // 16 x 12 map 
    final int maxScreenCol = 16; 
    final int maxScreenRow = 12; 

    final int screenWidth = tileSize * maxScreenCol;  // 768 pixels 
    final int screenHeight = tileSize * maxScreenRow;  // 576 pixels 

    KeyHandler keyH = new KeyHandler(); 
    Thread gameThread; 

    // Setting player default position 
    int playerX = 100;
    int playerY = 100; 
    int playerSpeed = 4; 


    // FPS 
    int FPS = 60; 


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; // screen is redrawn 60 times per sec
        double nextDrawTime = System.nanoTime() + drawInterval; 
        while(gameThread != null) {

            
            // 1- UPDATE character info like position 
            update();
            // 2 - DRAW Screen with updated information
            repaint();
            
        try {
            double remainingTime = nextDrawTime - System.nanoTime() ; 
            // convert nano to milli
            remainingTime = remainingTime/1000000;

            if(remainingTime < 0) {
                remainingTime = 0;
            }
            
            Thread.sleep((long) remainingTime);

            nextDrawTime += drawInterval;

        } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void update() { // player position updating
        if(keyH.upPressed == true) {
            playerY -= playerSpeed;   
        } 
        else if(keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }

    
}

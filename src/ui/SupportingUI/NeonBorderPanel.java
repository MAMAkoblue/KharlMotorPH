package ui.SupportingUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class NeonBorderPanel extends JPanel {
    private Color glowColor = new Color(0, 255, 255); // Cyan glow by default
    private int glowSize = 10;
    private int cornerRadius = 15;
    private float alpha = 0.7f;
    private boolean isGlowing = true;
    private Thread glowThread;
    private boolean running = true;

    public NeonBorderPanel() {
        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        startGlowEffect();
    }

    public void setGlowColor(Color color) {
        this.glowColor = color;
        repaint();
    }

    public void setGlowSize(int size) {
        this.glowSize = size;
        repaint();
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        
        // Draw glow effect
        if (isGlowing) {
            for (int i = 1; i <= glowSize; i++) {
                float intensity = (glowSize - i + 1) / (float) glowSize;
                g2d.setColor(new Color(
                    glowColor.getRed(),
                    glowColor.getGreen(),
                    glowColor.getBlue(),
                    (int)(alpha * 255 * intensity * 0.5f)
                ));
                g2d.setStroke(new java.awt.BasicStroke(i * 2));
                drawRoundedBorder(g2d, width, height, cornerRadius + i);
            }
        }
        
        // Draw the main border
        g2d.setColor(glowColor);
        g2d.setStroke(new java.awt.BasicStroke(2f));
        drawRoundedBorder(g2d, width, height, cornerRadius);
        
        g2d.dispose();
    }
    
    private void drawRoundedBorder(Graphics2D g2d, int width, int height, int radius) {
        Shape border = new RoundRectangle2D.Double(
            1, 1, width - 2, height - 2, 
            radius * 2, radius * 2
        );
        g2d.draw(border);
    }
    
    private void startGlowEffect() {
        glowThread = new Thread(() -> {
            try {
                while (running) {
                    // Pulsing effect
                    for (int i = 0; i <= 10; i++) {
                        alpha = 0.3f + (0.7f * i / 10f);
                        repaint();
                        Thread.sleep(50);
                    }
                    for (int i = 10; i >= 0; i--) {
                        alpha = 0.3f + (0.7f * i / 10f);
                        repaint();
                        Thread.sleep(50);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        glowThread.setDaemon(true);
        glowThread.start();
    }
    
    public void stopGlowEffect() {
        running = false;
        if (glowThread != null) {
            glowThread.interrupt();
        }
    }
    
    @Override
    public void removeNotify() {
        stopGlowEffect();
        super.removeNotify();
    }
}

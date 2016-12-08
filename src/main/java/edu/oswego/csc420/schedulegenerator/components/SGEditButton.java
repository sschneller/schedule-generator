package edu.oswego.csc420.schedulegenerator.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SGEditButton extends JButton {
    private boolean editMode;

    public SGEditButton() {
        super();
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        editMode = true;
//        Image img;
//        try {
//            img = ImageIO.read(new File("src\\main\\resources\\ic_mode_edit_black_18dp.png"));
//            setIcon(new ImageIcon(img));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void switchState() {
        editMode = !editMode;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img;
        try {
            if(editMode) {
                img = ImageIO.read(new File("src\\main\\resources\\ic_check_black_18dp.png"));
                setIcon(new ImageIcon(img));
            }
            else {
                img = ImageIO.read(new File("src\\main\\resources\\ic_mode_edit_black_18dp.png"));
                setIcon(new ImageIcon(img));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

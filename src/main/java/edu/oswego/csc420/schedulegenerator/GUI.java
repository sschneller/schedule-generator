package edu.oswego.csc420.schedulegenerator;

import com.google.common.io.Files;
import edu.oswego.csc420.schedulegenerator.panels.AccordionPanel;
import edu.oswego.csc420.schedulegenerator.frames.GeneratedScheduleFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {
    private final Generator generator;
    private final AccordionPanel accordionPanel;
    private final JFileChooser fileChooser;
    private static final String fileExtension = "csg";
    private boolean dialogShown = false;

    public static void main(String[] args) {
        final GUI gui = new GUI();
        SwingUtilities.invokeLater(() -> gui.setVisible(true));
    }

    private GUI() {
        setResizable(true);
        setSize(890,650);
        setTitle("Course Schedule Generator");
        getContentPane().setBackground(Colors.LIGHT_PRIMARY.getColor());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        generator = new Generator();
        accordionPanel = new AccordionPanel(generator);
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Course File", fileExtension));
        setLayout(new MigLayout("","[grow 100, fill][grow 1, fill][grow 1, fill]30%[grow 100, fill]","[grow,fill][]"));

        add(accordionPanel, "wrap, span");
        add(new JButton("New Course", this));
        add(new JButton("Import", this));
        add(new JButton("Export", this));
        add(new JButton("Generate", this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(((JButton)e.getSource()).getText()) {
            case("New Course"): {
                generator.addCourse(new Course("","","",false));
                accordionPanel.update();
                break;
            }
            case("Import"): {
                final File selectedFile = getFileChooserFile("Import Courses");
                if(selectedFile != null) {
                    try {
                        generator.importCourses(selectedFile);
                        accordionPanel.update();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            }
            case("Export"): {
                final File selectedFile = getFileChooserFile("Export Courses");
                if(selectedFile != null) {
                    try {
                        generator.exportCourses(selectedFile);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            }
            case("Generate"): {
                System.out.println("Generate Clicked!");
                GeneratedScheduleFrame gsf = new GeneratedScheduleFrame();
                gsf.setSize(890,650);
                gsf.setLocationRelativeTo(null);
                gsf.setVisible(true);
                break;
            }
            default: {
                System.out.println("Something Bad Was Clicked!!\nSource Was: " + e.getSource());
            }
        }
    }

    private File getFileChooserFile(final String title) {
        fileChooser.showDialog(this, title);
        File selectedFile = fileChooser.getSelectedFile();

        if(selectedFile != null) {
            if(!Files.getFileExtension(selectedFile.toString()).equals(fileExtension)) {
                selectedFile = new File(selectedFile.toString() + "." + fileExtension);
            }
            return selectedFile;
        } else {
            return null;
        }
    }

    public void setDialogShown(boolean state) {
        dialogShown = state;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(dialogShown) {
            g.setColor(new Color(128, 128, 128, 128));
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
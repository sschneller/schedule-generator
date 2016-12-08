package edu.oswego.csc420.schedulegenerator.panels;

import com.google.common.io.Files;
import edu.oswego.csc420.schedulegenerator.Course;
import edu.oswego.csc420.schedulegenerator.Generator;
import edu.oswego.csc420.schedulegenerator.frames.GeneratedScheduleFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class SwitchViewPanel extends JPanel implements ActionListener {
    private final Generator generator;
    private final JFileChooser fileChooser;
    private static final String fileExtension = "csg";

    SwitchViewPanel(Generator generator) {
        this.generator = generator;
        this.fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Course File", fileExtension));
        setLayout(new MigLayout("","[grow,fill][][]300[]","[grow,fill][]"));
        add(new JButton("New Course", this));
        add(new JButton("Import", this));
        add(new JButton("Export", this));
        add(new JButton("Generate", this));
    }

    private class JButton extends javax.swing.JButton {

        private JButton(final String text, final SwitchViewPanel listener) {
            super(text);
            addActionListener(listener);
            putClientProperty("id", text);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        CardPanel rootPanel = (CardPanel)this.getParent().getParent();
        switch((String)clickedButton.getClientProperty("id")) {
            case("New Course"): {
                rootPanel.remove(rootPanel.courseOverview);
                rootPanel.courseOverview = new CoursePanel(new CourseHeaderPanel(false), new CourseInfoPanel(generator));
                rootPanel.add(rootPanel.courseOverview, "EXP");
                rootPanel.cl.show(rootPanel, "EXP");
                break;
            }
            case("Import"): {
                final File selectedFile = getFileChooserFile("Import Courses");
                if(selectedFile != null) {
                    try {
                        generator.importCourses(selectedFile);
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
}

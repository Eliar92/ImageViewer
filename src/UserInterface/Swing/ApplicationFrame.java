package UserInterface.Swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class ApplicationFrame extends JFrame {
    private final ActionListener[] listeners;
    private SwingImageViewerPanel imageViewer;
    private int index = 0;
    public ApplicationFrame(ActionListener[] listeners, SwingImageViewerPanel imageViewer) throws HeadlessException {
        super("Image Viewer");
        this.listeners = listeners;
        this.imageViewer = imageViewer;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1024, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.createComponents();
        this.setVisible(true);
    }
    private void createComponents() {
        this.add(imageViewer);
        this.add(createToolbar(), BorderLayout.SOUTH);
    }
    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add(createButton("Prev"));
        panel.add(createButton("Next"));
        return panel;
    }
    private JButton createButton(String title) {
        JButton button = new JButton(title);
        button.addActionListener(listeners[index++]);
        return button;
    }
}
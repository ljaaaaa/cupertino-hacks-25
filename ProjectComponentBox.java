import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProjectComponentBox extends JComponent {
 
    private JPanel panel;

    public ProjectComponentBox(String titleText, ImageIcon image, String linkText, String linkUrl) {
        setLayout(null);
        setSize(300, 400);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 240, 223));
        panel.setBounds(0, 0, 500, 200);

        // Title
        JLabel title = new JLabel(titleText);
        title.setBounds(20, 20, 460, 30);
        title.setFont(new Font("Parkinsans", Font.BOLD, 25));
        panel.add(title);

        // Image
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(50, 70, 200, 200);
        panel.add(imageLabel);

        // Hyperlink
        JButton linkButton = new JButton("<html><a href='#'>" + "Try it out!" + "</a></html>");
        linkButton.setForeground(Color.BLUE);
        linkButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkButton.setBounds(50, 300, 200, 40);

        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(linkUrl));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to open the link.");
                }
            }
        });
        
        panel.add(linkButton);
        add(panel);
    }

    // Allow positioning via setBounds(x, y, width, height)
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (panel != null) {
            panel.setBounds(0, 0, width, height);
        }
    }
}

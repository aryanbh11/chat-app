import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class ChatFrame extends JFrame implements ActionListener {

    private JPanel chatPanel;
    private JPanel contactPanel;

    private JLabel talkingTo;
    private JLabel activeNow;

    private JButton sendButton;

    private JScrollPane textPane;
    private JScrollPane messagePane;

    private JTextArea textArea;
    private JTextArea messageBox;

    private final Font bf = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    private final Font f = new Font(Font.SANS_SERIF, Font.ITALIC, 10);

    private User user;

    private final static int WIDTH = 400;
    private final static int HEIGHT = 800;

    public ChatFrame(User user, String from, String to, boolean centre) {
        this.user = user;

        chatPanel = new JPanel();
        chatPanel.setLayout(null);
        chatPanel.setBackground(new Color(233, 219, 232));
        chatPanel.setBounds(1, 50, WIDTH, HEIGHT - 50);
        chatPanel.setVisible(true);

        contactPanel = new JPanel();
        contactPanel.setLayout(null);
        contactPanel.setBackground(new Color(137, 41, 133));
        contactPanel.setBounds(1, 1, WIDTH, 50);
        contactPanel.setVisible(true);

        talkingTo = new JLabel(to);
        talkingTo.setFont(bf);
        talkingTo.setForeground(Color.WHITE);
        talkingTo.setBounds(10, 1, 200, 40);
        contactPanel.add(talkingTo);

        activeNow = new JLabel("Active Now");
        activeNow.setFont(f);
        activeNow.setForeground(Color.WHITE);
        activeNow.setBounds(10, 35, 100, 10);
        contactPanel.add(activeNow);
        activeNow.setVisible(false);

        textArea = new JTextArea(5, 20);
        textArea.setBounds(1, 1, WIDTH, HEIGHT - 200);
        textArea.setBackground(new Color(233, 219, 232));
        textArea.setText("");
        textArea.setEditable(false);
        textPane = new JScrollPane(textArea);
        textPane.setBounds(1, 1, WIDTH, HEIGHT - 200);
        textPane.setBackground(new Color(233, 219, 232));
        chatPanel.add(textPane);

        messageBox = new JTextArea(5, 20);
        messageBox.setText("");
        messageBox.setBounds(1, HEIGHT - 200, WIDTH - 50, 200);
        messagePane = new JScrollPane(messageBox);
        messagePane.setBounds(1, HEIGHT - 200, WIDTH - 50, 200);
        chatPanel.add(messagePane);

        sendButton = new JButton("Send");
        sendButton.setOpaque(true);
        sendButton.setBounds(WIDTH - 50, HEIGHT - 200, 50, 20);
        sendButton.addActionListener(this);
        chatPanel.add(sendButton);

        setTitle(from);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(chatPanel);
        add(contactPanel);

        if (centre) {
            this.setLocationRelativeTo(null);
        }
        this.setVisible(true);
    }

    public void setActive(boolean active) {
        activeNow.setVisible(active);
    }

    public String getMessage() {
        return messageBox.getText();
    }

    public void addMessage(String from, String message) {
        textArea.append(from + ": " + message + "\n");
    }

    public void resetChat() {
        textArea.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            try {
                user.sendMessage();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            messageBox.setText("");
        }
    }

}
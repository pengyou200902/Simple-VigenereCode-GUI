import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guiGLM {

    private JButton cipherBtn;
    private JButton resetBtn;
    private JButton exitBtn;
    private JPanel wholePanel;
    private JPanel botmPanl;
    private JPanel botmR;
    private JPanel botmL;
    private JPanel botmM;
    private JPanel topPanl;
    private JTextField key;
    private JTextField plainText;
    private JTextField cipherText;
    private JPanel midPanl;
    private JPanel midM;
    private JPanel midL;
    private JLabel keyL;
    private JLabel cipherL;
    private JLabel plainL;
    private JPanel midR;

    public guiGLM() {
        cipherBtn.addActionListener(new ActionListener() {  //cipherBtn
            @Override
            public void actionPerformed(ActionEvent e) {
                String k = key.getText();
                String plainString = plainText.getText();
                cipherText.setText(cipher(k, plainString).toString());
            }
        });

        exitBtn.addActionListener(new ActionListener() {    //exitBtn
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        resetBtn.addActionListener(new ActionListener() {   //resetBtn
            @Override
            public void actionPerformed(ActionEvent e) {
                key.setText("");
                plainText.setText("");
                cipherText.setText("");
            }
        });
    }

    private StringBuilder cipher(String k, String plainString) {
        if(plainString.isEmpty()) {
            return new StringBuilder("");
        }
        else {
            // 此处全部转为大写了
            return Vigenere(k.toUpperCase(), plainString.toUpperCase());
        }

    }

    private StringBuilder Vigenere(String k, String s) {
        int mod_A = 26;
        int delta = (int)'A';   //便于计算
        int x = 0;
        int j = 0;
        char[] plain = s.toCharArray();
        char[] kee = k.toCharArray();
        int len = k.length();
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < plain.length; i++) {
            x = (int)plain[i];
            if(x >= (int)'A' && x<= (int)'Z') { //大写字母
                x = (x - delta + (int)kee[j] - delta) % mod_A + delta; // 移位
                j = (j + 1) % len; // 循环用kee密钥的每一位去参与移位
            }
            else {continue; }
            cipher.append((char)(x));

        }
        return cipher;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("guiGLM");
        frame.setContentPane(new guiGLM().wholePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

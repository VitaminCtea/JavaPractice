package permissions;

import javax.swing.*;

public class WordCheckTextArea extends JTextArea {
    @Override
    public void append(String text) {
        WordCheckPermission wordCheckPermission = new WordCheckPermission(text, "insert");
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(wordCheckPermission);
        }
        super.append(text);
    }
}
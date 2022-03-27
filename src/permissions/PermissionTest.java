package permissions;

import javax.swing.*;
import java.awt.*;

public class PermissionTest {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "permissions/PermissionTest.policy");
        System.setSecurityManager(new SecurityManager());
        EventQueue.invokeLater(() -> {
            PermissionTestFrame permissionTestFrame = new PermissionTestFrame();
            permissionTestFrame.setTitle("PermissionTest");
            permissionTestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            permissionTestFrame.setVisible(true);
        });
    }
}
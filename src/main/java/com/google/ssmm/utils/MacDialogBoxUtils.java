package com.google.ssmm.utils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MacDialogBoxUtils {
    private static Map<String,Long> lastSendUnixMap =  new HashMap<>();

    public static void displayDialog(String value) {
        String title = "Mac Dialog Box";
        int messageType = JOptionPane.PLAIN_MESSAGE;
        lastSendUnixMap.putIfAbsent(value,0L);
        if(System.currentTimeMillis() - lastSendUnixMap.get(value) <  5 * 1000 ){
            return;
        }

        JOptionPane pane = new JOptionPane(value, messageType);
        JDialog dialog = pane.createDialog(null, title);

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the dialog position to force it to appear on the screen
        int dialogWidth = dialog.getWidth();
        int dialogHeight = dialog.getHeight();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int x = (screenWidth - dialogWidth) / 2;
        int y = (screenHeight - dialogHeight) / 2;
        dialog.setLocation(x, y);

        // Show the dialog
        dialog.setVisible(true);
        lastSendUnixMap.put(value,System.currentTimeMillis());
    }
}

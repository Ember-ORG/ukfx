/**
 * Created by Execodeable on 11/19/16.
 */
package stuff;

import stuff.filters.filters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui {
    private JButton fade;
    private JPanel panelMain;
    private JButton flicker;
    public JButton sendtotray;
    public static boolean fade_enabled=false;
    public gui() {
        fade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fade_enabled=true;
                new Thread(filters::fade).start();
                JOptionPane.showMessageDialog(null, "Fade Effect Activated!");
            }
        });
        flicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Thread(filters::flickerOnTouch).start();
                JOptionPane.showMessageDialog(null, "Flicker Effect Activated!");
            }
        });
        sendtotray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stop();
            }
        });
    }

    public static JFrame frame = new JFrame("gui"); //Initializing public gui variable

    public static void main(String[] args) {
        //All code below with frame. is setting up the gui interface
        frame.setContentPane(new gui().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setTitle("UKFX");
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public static void stop() {
        /*
        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(getDefaultToolkit().createImage("images/icon.png"), "UKFX", popup);
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                //Below code shows information about UKFX when clicked about on the icon tray
                JOptionPane.showMessageDialog(null, "UKFX was created by developers\n working at Execodeable.\n UKFX is a universal way\n to let computers with\n backlighting on their keyboard\n have special effects.");
            }
        });
        MenuItem openItem = new MenuItem("Open");
        openItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setVisible(true);
            }
        });
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(1);
            }
        });

        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.add(openItem);
        popup.add(exitItem);
        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
        */
        if (fade_enabled = true) {
            new Thread(filters::disablefade).start();
        }
        }
    }

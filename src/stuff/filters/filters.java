package stuff.filters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.out;

public class filters /*implements NativeKeyListener*/ {

    private static String LED0_PATH = "/sys/class/leds/asus::kbd_backlight"; //FIND WAY TO DETECT
/*
    public void nativeKeyPressed(NativeKeyEvent e) {
        out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(LED0_PATH + "/trigger"));
            bw.write("none");
            bw.close();
            bw = new BufferedWriter(new FileWriter(LED0_PATH + "/brightness"));
            bw.write("0");
            bw.close();
        } catch (IOException et) {
            String converted = et.toString();
            if (converted.contains("Permission"))
                out.println("This app can not be run without root permissions");
            else {
                out.println("An uncaught error has occurred:\n" + e);
            }
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) try {
            GlobalScreen.unregisterNativeHook();
        } catch (Exception illmer) {
            illmer.printStackTrace();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        try {
            String line = null;
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(LED0_PATH + "/max_brightness");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                maximumbrightness = Integer.parseInt(line);
            }

            // Always close files.
            bufferedReader.close();
            out.println("Maximum Brightness: " + maximumbrightness);
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(LED0_PATH + "/trigger"));
            bw.write("none");
            bw.close();
            bw = new BufferedWriter(new FileWriter(LED0_PATH + "/brightness"));
            bw.write(maximumbrightness);
            bw.close();
        } catch (IOException et) {
            String converted = et.toString();
            if (converted.contains("Permission"))
                out.println("This app can not be run without root permissions");
            else {
                out.println("An uncaught error has occurred:\n" + e);
            }
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

*/
    public static void fade(){
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", "sudo modprobe ledtrig_timer");
        pb.redirectErrorStream(true); //Outputs to stderr in-case of Error
        Process shell = null;
        try {
            shell = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream shellIn = shell.getInputStream();
        try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(LED0_PATH + "/trigger"));
                bw.write("timer");
                bw.close();
            } catch (IOException e) {
                String converted = e.toString();
                if (converted.contains("Permission"))
                    out.println("This app can not be run without root permissions");
                else {
                    out.println("An uncaught error has occurred:\n" + e);
                }
            }
        }
    public static void disablefade(){
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", "sudo modprobe ledtrig_transient");
        pb.redirectErrorStream(true); //Outputs to stderr in-case of Error
        Process shell = null;
        try {
            shell = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream shellIn = shell.getInputStream();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(LED0_PATH + "/trigger"));
            bw.write("transient");
            bw.close();
        } catch (IOException e) {
            String converted = e.toString();
            if (converted.contains("Permission"))
                out.println("This app can not be run without root permissions");
            else {
                out.println("An uncaught error has occurred:\n" + e);
            }
        }
    }
   //Temporary Keyboard testing
/*
    public static void main(String[] args) {
        try {
            Thread.sleep(10000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        flickerOnTouch();
    }

*/

    public static void flickerOnTouch() {
        /*
    if (flicker_toggle == 0) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new filters());
        flicker_toggle = 1;
    }
    else if (flicker_toggle == 1) {
        try {
            GlobalScreen.unregisterNativeHook();
        }
        catch (Exception ef) {
            ef.printStackTrace();
        }

    }
    */
        while (true) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(LED0_PATH + "/trigger"));
                bw.write("none");
                bw.close();
            } catch (IOException e) {
                String converted = e.toString();
                if (converted.contains("Permission"))
                    out.println("This app can not be run without root permissions");
                else {
                    out.println("An uncaught error has occurred:\n" + e);
                }
            }
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(LED0_PATH + "/brightness"));
                bw.write(Integer.toString(flicker_toggle));
                bw.close();
            } catch (IOException e) {
                String converted = e.toString();
                if (converted.contains("Permission"))
                    out.println("This app can not be run without root permissions");
                else {
                    out.println("An uncaught error has occurred:\n" + e);
                }
            }
            if (flicker_toggle == 3)
                flicker_toggle = 0;
            else
                flicker_toggle = 3;
            try {
                Thread.sleep(100);
            } catch (Exception barb) {
                barb.printStackTrace();
            }
        }
    }
    public static int fade_loop = 1;
    public static boolean fade_enabled = true; //NEED TO GET OUTPUT FROM GUI
    public static int maximumbrightness;
    public static int direction = 1;
    public static int sleeptime = 200;
    public static int flicker_toggle = 0;
}


/* //Example code
public class GlobalKeyListenerExample implements NativeKeyListener {
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            GlobalScreen.unregisterNativeHook();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
    }
}
*/
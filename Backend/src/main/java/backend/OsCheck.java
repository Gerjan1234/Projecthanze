package backend;

import java.util.Locale;

/**
 * Hulp klasse om te bepalen op welk platform het programma draait.
 */

public final class OsCheck {

    public static String OS;
    public static String LocalPath;

    public enum OSType {
        Windows, MacOS, Linux, Other
    };

    // cached result of OS detection
    protected static OSType detectedOS;

    /**
     * detect the operating system from the os.name System property and cache the result
     * @returns - the operating system detected
     */

    public static OSType getOperatingSystemType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                detectedOS = OSType.MacOS;
            } else if (OS.indexOf("win") >= 0) {
                detectedOS = OSType.Windows;
            } else if (OS.indexOf("nux") >= 0) {
                detectedOS = OSType.Linux;
            } else {
                detectedOS = OSType.Other;
            }
        }

        return detectedOS;
    }

    /**
     * Methode om padnaam te bepalen bij verschillende operatingsystems
     * @author Teo
     * @version (18-08-2019)
     */

    public static void setPathLocal() {

        OS = getOperatingSystemType().toString();

        String str = OS;
        switch(str)
        {
            case "Windows":
                LocalPath = "E:/tmp/";
                break;
            case "Linux":
                LocalPath = "/var/tmp/";
                break;
            case "MacOS":
                LocalPath = "/var/tmp/"; // Henk pad naar map testen
                break;
            default:
        }
        OS = System.getProperty("os.name", "generic");
    }
}

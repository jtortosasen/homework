package server;

public class Main {

    private static final int LISTENER_PORT = 5555;
    private static final int MAX_USERS = 4;
    private static String FTP_PATH = "/home/wasdf/ftp-server";

    public static void main(String[] args) {
        if (args.length == 1) {
            FTP_PATH = args[0];
        } else if (args.length > 1) {
            System.err.println("args max length 1: [ftp_path]");
        }
        new ServerListener().start();
    }

    public static int getListenerPort() {
        return LISTENER_PORT;
    }

    public static int getMaxUsers() {
        return MAX_USERS;
    }

    public static String getFtpPath() {
        return FTP_PATH;
    }
}

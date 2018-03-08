package server;

import util.IO;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerCommunication extends Thread {

    private ServerListener serverListener;
    private Socket socket;
    private OutputStream out;
    private InputStream in;

    private String fptPath;
    private String currentPath;

    public ServerCommunication(Socket socket, ServerListener serverListener) {
        this.serverListener = serverListener;
        this.socket = socket;
        this.fptPath = Main.getFtpPath();
        this.currentPath = Main.getFtpPath();
    }

    public void run() {
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
            IO.sendLine("WELCOME", out);
            IO.sendLine("", out);
            IO.sendLine(currentPath, out);

            boolean exit_flag = false;
            while (!exit_flag) {
                String command = IO.getLine(in);
                if (!command.equals("")) {
                    String parts[] = command.split("[ ]+");
                    switch (parts[0]) {
                        case "PWD": {
                            pwdCommand();
                            break;
                        }
                        case "DIR": {
                            lsCommand();
                            break;
                        }
                        case "LS": {
                            lsCommand();
                            break;
                        }
                        case "CD": {
                            cdCommand(parts);
                            break;
                        }
                        case "PUT": {
                            putCommand(parts);
                            break;
                        }
                        case "GET": {
                            getCommand(parts);
                            break;
                        }
                        case "EXIT": {
                            exitCommand();
                            exit_flag = true;
                        }
                        default:
                            wrongCommand("400");
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        serverListener.endSession();
    }

    private void putCommand(String[] parts) throws IOException {

        String putFile = currentPath + "/" + parts[1];
        File f = new File(putFile);

        if (!f.exists()) {
            IO.sendLine("200 PUT OK", out);
            IO.sendLine("", out);
            File file = new File(putFile);
            FileOutputStream fout = new FileOutputStream(file);
            IO.copy(in, fout, Long.parseLong(parts[2]));

        } else {
            wrongCommand("403");
        }

    }

    private void getCommand(String[] parts) throws IOException {
        String file = currentPath + "/" + parts[1];
        File f = new File(file);
        FileInputStream fin = new FileInputStream(f);
        if (!f.exists()) {
            wrongCommand("404");
        } else if (f.isDirectory()) {
            wrongCommand("405");
        } else {
            IO.sendLine("200 GET OK " + parts[1] + " " + f.length(), out);
            IO.sendLine("", out);
            long sizeFile = IO.copy(fin, out);
            if (sizeFile != f.length()) {
                System.err.println("Transfer error");
            }
        }
    }

    private void cdCommand(String[] parts) throws IOException {
        String folder = parts[1];
        switch (folder) {
            case ".":
                IO.sendLine("200 CD OK", out);
                IO.sendLine("", out);
                IO.sendLine(currentPath, out);
                break;
            case "..":
                if (currentPath.equals(fptPath)) {
                    wrongCommand("405");
                } else {
                    File f = new File(currentPath);
                    IO.sendLine("200 CD OK", out);
                    IO.sendLine("", out);
                    currentPath = f.getParent();
                    IO.sendLine(currentPath, out);
                }
                break;
            default:
                String file = currentPath + "/" + folder;
                File f = new File(file);
                if (!f.exists()) {
                    wrongCommand("404");
                } else if (f.isFile()) {
                    wrongCommand("406");
                } else {
                    IO.sendLine("200 CD OK", out);
                    IO.sendLine("", out);
                    currentPath = file;
                    IO.sendLine(currentPath, out);
                }
        }
    }

    private void exitCommand() throws IOException {
        IO.sendLine("200 EXIT OK", out);
        IO.sendLine("", out);
        IO.sendLine("BYE", out);
    }

    private void lsCommand() throws IOException {
        String reply = "";
        File f = new File(currentPath);
        File[] elements = f.listFiles();
        for (File file : elements) {
            if (file.isFile()) {
                reply += file.getName() + "#";
            } else {
                reply += "<" + file.getName() + ">#";
            }
        }
        reply += "\b";
        IO.sendLine("200 LS OK", out);
        IO.sendLine("", out);
        IO.sendLine(reply, out);
    }

    private void pwdCommand() throws IOException {
        IO.sendLine("200 PWD OK", out);
        IO.sendLine("", out);
        IO.sendLine(currentPath, out);
    }

    private void wrongCommand(String errorCode) throws IOException {
        switch (errorCode) {
            case "400": {
                IO.sendLine("400 PETICION MAL FORMADA", out);
                IO.sendLine("", out);
                break;
            }
            case "403": {
                IO.sendLine("403 ALREADY EXISTS", out);
                IO.sendLine("", out);
                break;
            }
            case "404": {
                IO.sendLine("404 NOT FOUND", out);
                IO.sendLine("", out);
                break;
            }

            case "405": {
                IO.sendLine("405 CANNOT GO UP TO BASEPATH", out);
                IO.sendLine("", out);
                break;
            }
            case "406": {
                IO.sendLine("406 IS FILE", out);
                IO.sendLine("", out);
                break;
            }
        }
    }

}

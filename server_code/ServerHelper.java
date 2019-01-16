import java.io.*;
import java.net.Socket;

class ServerHelper extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerHelper(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    @Override
    public void run() {
        String word;
        try {

            while (true) {
                word = in.readLine();
                if(word.equals("stop")) {
                    break;                }
                for (ServerHelper vr : ServerMain.serverList) {
                    vr.send(word);
                }
            }

        } catch (IOException e) {
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }
}
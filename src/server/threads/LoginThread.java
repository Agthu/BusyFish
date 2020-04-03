package server.threads;

import java.io.*;
import java.net.Socket;

/**
 * 登录线程
 */
public class LoginThread extends Thread {
    private Socket socket;
    public LoginThread(Socket socket) {
        this.socket = socket;
    }

    //启动线程，响应客户端请求
    @Override
    public void run() {
        try {
            InputStream ins = socket.getInputStream(); // 输入流，读取账号、密码
            OutputStream outs = socket.getOutputStream(); // 输出流，输出登录结果数据

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outs));

            String info = br.readLine();
            System.out.println(br);
            bw.write(info);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

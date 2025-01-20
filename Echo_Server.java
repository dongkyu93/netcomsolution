import java.io.*;    // 입출력 관련 패키지 임포트
import java.net.*;   // 네트워크 관련 패키지 임포트

/**
 * Echo_Server 클래스
 * 클라이언트로부터 받은 메시지를 그대로 돌려보내는 에코 서버
 */
public class Echo_Server {
    public static void main(String[] args) throws IOException {
        // 9999 포트에서 서버 소켓 생성
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("에코 서버가 9999 포트에서 실행 중입니다...");

        // 서버 무한 루프 시작
        while (true) {
            // 클라이언트의 연결 요청을 수락
            Socket clientSocket = serverSocket.accept();
            System.out.println("클라이언트가 연결되었습니다: " + clientSocket);

            // 클라이언트와의 입출력 스트림 생성
            // InputStreamReader: 바이트 스트림을 문자 스트림으로 변환
            // BufferedReader: 입력의 효율성을 높이기 위한 버퍼링 제공
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            // PrintWriter: 문자열 출력을 위한 편리한 메서드 제공
            // autoFlush를 true로 설정하여 println() 호출 시 자동으로 버퍼를 비움
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            // 클라이언트로부터 메시지를 읽어 처리하는 루프
            while ((inputLine = in.readLine()) != null) {
                // 수신한 메시지를 서버 콘솔에 출력
                System.out.println("클라이언트로부터 수신: " + inputLine);

                // 받은 메시지를 클라이언트에게 다시 전송
                out.println(inputLine);

                // 클라이언트가 "bye"를 보내면 연결 종료
                if (inputLine.equals("bye")) {
                    break;
                }
            }

            // 클라이언트와의 연결 종료
            clientSocket.close();
        }
    }
}

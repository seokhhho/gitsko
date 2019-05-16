
import java.io.*;
import java.security.*;
import javax.xml.bind.*; // 데이터 타입 변경 

public class DigestRunnable implements Runnable { // runnable 인터페이스로 작성

  private String filename;

  public DigestRunnable(String filename) {
   this.filename = filename;
  }

  @Override
  public void run() {
    try {
  
       FileInputStream in = new FileInputStream(filename); 
          // 해당 파일의 데이터 내용에 대한 객체
       
       // security 패키지 안에 있는 클래스 중 하나로 
       MessageDigest sha = MessageDigest.getInstance("SHA-256"); // 인자로는 MD5, SHA-512, SHA-1과 가은 다양한 암호화 방식을 받음 
          // 메시지 다이제스트
          // 임의 길이 메시지에 단방향 해시 함수가 반복 적용되어 일정한 길이로 축약 생성된 비트열.
          // 메시지마다 단 하나의 메시지 다이제스트가 산출, 서로 다른 문서에서 같은 메시지 다이제스트가 산출될 수 없음, 메시지마다 고유하게 산출되는 문자열이다.
          // 단방향이기 때문에, 추후에 이걸 복호화하는 용도가 아니라
          // 송신한 메시지가 정상적으로 도착하였는지, 주로 데이터의 오류 검출에 이용된다.
       DigestInputStream din = new DigestInputStream(in, sha); // 파일을 모두 읽은 후 
          // 인자로 입력 스트림과 메세지 다이제스트로 받아서, 호출한 입력 
       
       //스트림에 대한 메시지 다이제스트를 업데이트함.
       // 객체에는 그 값이 저장됨
   
   while (din.read() != -1) ; // while문 왜 이따구로 생김??
   din.close();
   byte[] digest = sha.digest(); 
   //파일을 읽고  다이제스트 결과 값을 바이트 배열 변수에 차례대로 저장하고
   
   StringBuilder result = new StringBuilder(filename); // 출력할 모든 내용을 변수에 출력하여 전체 문자열을 만든 다음 출력 메소드를 호출하여 한 번에 콘솔에 출력
   // 각각의 주어진 데이터를 문자열로 효과적으로 변환한 다음 해당 문자열에 append나 insert 메소드를 사용에 뮨자열을 추가 및 삽입하는 용도로 
   // 많이 씀.
   result.append(" : ");
   result.append(DatatypeConverter.printHexBinary(digest));// 16진수로 인코딩하여 sysout 출력
   System.out.println(result);
 } catch (IOException ex) { // 인풋스트림에 대한 익셉션이고
   System.err.println(ex);
 } catch (NoSuchAlgorithmException ex) {  // 메시지다이제스트의 인자로받은  암호화 방식인 알고리즘이 잘못되었을 경우, 출력 
   System.err.println(ex);
 }
}

public static void main(String[] args) {
 // System.out.println(args);
  for (String filename : args) { // for each 문
     for (int i=0 ; i<1 ; i++) { 
    System.out.println("2");
   DigestRunnable dr = new DigestRunnable("aaa2.txt"); // 파일명 목록을 Runnable 인자로 받고 각각의 파일에 대해서 새로운 쓰레드를 시작할 것임.
   Thread t = new Thread(dr); // 쓰레드의 객체 생성할 때, 내가 만들어 놓았던 Runnable 객체를 인자로!
   t.start();
 }
}
}
}

       
       
       
       
       
       
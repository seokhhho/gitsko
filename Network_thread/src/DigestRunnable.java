
import java.io.*;
import java.security.*;
import javax.xml.bind.*; // ������ Ÿ�� ���� 

public class DigestRunnable implements Runnable { // runnable �������̽��� �ۼ�

  private String filename;

  public DigestRunnable(String filename) {
   this.filename = filename;
  }

  @Override
  public void run() {
    try {
  
       FileInputStream in = new FileInputStream(filename); 
          // �ش� ������ ������ ���뿡 ���� ��ü
       
       // security ��Ű�� �ȿ� �ִ� Ŭ���� �� �ϳ��� 
       MessageDigest sha = MessageDigest.getInstance("SHA-256"); // ���ڷδ� MD5, SHA-512, SHA-1�� ���� �پ��� ��ȣȭ ����� ���� 
          // �޽��� ��������Ʈ
          // ���� ���� �޽����� �ܹ��� �ؽ� �Լ��� �ݺ� ����Ǿ� ������ ���̷� ��� ������ ��Ʈ��.
          // �޽������� �� �ϳ��� �޽��� ��������Ʈ�� ����, ���� �ٸ� �������� ���� �޽��� ��������Ʈ�� ����� �� ����, �޽������� �����ϰ� ����Ǵ� ���ڿ��̴�.
          // �ܹ����̱� ������, ���Ŀ� �̰� ��ȣȭ�ϴ� �뵵�� �ƴ϶�
          // �۽��� �޽����� ���������� �����Ͽ�����, �ַ� �������� ���� ���⿡ �̿�ȴ�.
       DigestInputStream din = new DigestInputStream(in, sha); // ������ ��� ���� �� 
          // ���ڷ� �Է� ��Ʈ���� �޼��� ��������Ʈ�� �޾Ƽ�, ȣ���� �Է� 
       
       //��Ʈ���� ���� �޽��� ��������Ʈ�� ������Ʈ��.
       // ��ü���� �� ���� �����
   
   while (din.read() != -1) ; // while�� �� �̵����� ����??
   din.close();
   byte[] digest = sha.digest(); 
   //������ �а�  ��������Ʈ ��� ���� ����Ʈ �迭 ������ ���ʴ�� �����ϰ�
   
   StringBuilder result = new StringBuilder(filename); // ����� ��� ������ ������ ����Ͽ� ��ü ���ڿ��� ���� ���� ��� �޼ҵ带 ȣ���Ͽ� �� ���� �ֿܼ� ���
   // ������ �־��� �����͸� ���ڿ��� ȿ�������� ��ȯ�� ���� �ش� ���ڿ��� append�� insert �޼ҵ带 ��뿡 ���ڿ��� �߰� �� �����ϴ� �뵵�� 
   // ���� ��.
   result.append(" : ");
   result.append(DatatypeConverter.printHexBinary(digest));// 16������ ���ڵ��Ͽ� sysout ���
   System.out.println(result);
 } catch (IOException ex) { // ��ǲ��Ʈ���� ���� �ͼ����̰�
   System.err.println(ex);
 } catch (NoSuchAlgorithmException ex) {  // �޽�����������Ʈ�� ���ڷι���  ��ȣȭ ����� �˰����� �߸��Ǿ��� ���, ��� 
   System.err.println(ex);
 }
}

public static void main(String[] args) {
 // System.out.println(args);
  for (String filename : args) { // for each ��
     for (int i=0 ; i<1 ; i++) { 
    System.out.println("2");
   DigestRunnable dr = new DigestRunnable("aaa2.txt"); // ���ϸ� ����� Runnable ���ڷ� �ް� ������ ���Ͽ� ���ؼ� ���ο� �����带 ������ ����.
   Thread t = new Thread(dr); // �������� ��ü ������ ��, ���� ����� ���Ҵ� Runnable ��ü�� ���ڷ�!
   t.start();
 }
}
}
}

       
       
       
       
       
       
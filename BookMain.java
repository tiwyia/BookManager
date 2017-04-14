import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BookMain {
	static Scanner input = new Scanner(System.in); 
	static String filePath = "C:\\test\\book.txt";
	public static void main(String[] args) throws IOException{
		
		printMenu();
	
		int choice = -1;
		
		while(choice != 0){
		choice = inputChoice();
		
		switch(choice){
		case 1:
			printbooks();
			break;//1�� ������ å ��� ���
			
		case 2:
			searchbook();
			break;//2�� ������ å �˻����� �̵�
			
		case 3:
			insertbook();
			break;//3�� ������ å �߰��� �̵�
			
		case 4:
			deletebook();
			break; //4�� ������ å ������ �̵�
			
		case 0:
			System.out.println("���α׷��� �����մϴ�.");
			System.exit(0); //0�� ������ ���α׷� ����
		}
	}
}
	
	private static int inputChoice() {
		int choice = -1;
		
		System.out.println("�޴��� �����ϼ��� : ");
		
		choice = input.nextInt();
		
		System.out.println("������ �޴� : "+choice);
		return choice;
	}//������ ���� �޴� ��Ȯ��
	
	private static void printMenu() {
		System.out.println("�������� ���α׷� �Դϴ�.");
		System.out.println("1 : ��ü ���� ���");
		System.out.println("2 : ���� �˻�");
		System.out.println("3 : ���� �߰�");
		System.out.println("4 : ���� ����");
		System.out.println("0 : ���α׷� ����");
	}//���� �޴� ���

	private static void deletebook() throws IOException {
		
		String tmpFilePath = filePath +".tmp";
		int count = 1;
		System.out.println("�����Ͻ� å ��ȣ�� �Է����ּ��� : " );
		int booknumber = input.nextInt();
		
		System.out.println("�Է��Ͻ� å ��ȣ : "+booknumber);
	
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFilePath));
		
		String str="";
		
		while((str = br.readLine()) != null){
			if(count != booknumber){
			bw.write(str);
			bw.write("\r\n");
			}
			count ++;
		}
		bw.close();
		br.close();
		
		FileInputStream fis = new FileInputStream(tmpFilePath);
		FileOutputStream fos = new FileOutputStream(filePath);
		
		int data = 0;
		while((data = fis.read()) != -1){
		fos.write(data);
		}
		fis.close();
		fos.close();
		
		File f = new File(tmpFilePath);
		f.deleteOnExit();
	}//�������

	private static void insertbook() throws IOException {
		System.out.println("�߰��� å ������ �Է��ϼ���");
		input.nextLine();
		System.out.println("å ���� : ");
		String name = input.nextLine();
		
		System.out.println("���� : ");
		String author = input.nextLine();
		
		System.out.println("���ǻ� : ");
		String publisher = input.nextLine();
		
		System.out.println("���� : ");
		String cost = input.nextLine();
		
		System.out.println("�Է��� å ����");
		System.out.println("å ���� : "+name);
		System.out.println("����: "+author);
		System.out.println("���ǻ� : "+publisher);
		System.out.println("����: "+cost);
		

		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
		
		bw.write(name+"\t"+author+"\t"+publisher+"\t"+cost);
		bw.newLine();
		bw.close();
	}//å �Է� ���

	private static void searchbook() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		System.out.println("�˻��� Ű���带 �Է��ϼ��� : ");
		input.nextLine();
		int count = 0;
		int bookLine=1;
		String keyword;
		String str= "";
		keyword = input.nextLine();

		try {
			while((str = br.readLine()) != null){
			if(str.contains(keyword)){
				System.out.println(bookLine+" : "+str);
				count++;
				bookLine++;
			}

			}
			System.out.println("�˻��Ͻ� å�� �� �Ǽ��� : " +count+ "�� �Դϴ�.");
			br.close();
		} catch (IOException e) {
			System.out.println("å ������ �о� �� �� �����ϴ�.");
			e.printStackTrace();
		}
	}//�˻� ���

	private static void printbooks() throws FileNotFoundException  {
		System.out.println("å ���");
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String str= "";
		int count = 0;
		int bookLine=1;
		try {
			while((str = br.readLine()) != null){
				System.out.println(bookLine+" : "+str);
				count ++;
				bookLine ++;
			}
			System.out.println("å�� �� �Ǽ��� : "+count+"�� �Դϴ�.");
		} catch (IOException e) {
			System.out.println("å ������ �о� �� �� �����ϴ�.");
			e.printStackTrace();
		}
	}
}//å ��� ���
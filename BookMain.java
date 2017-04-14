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
			break;//1을 누르면 책 목록 출력
			
		case 2:
			searchbook();
			break;//2를 누르면 책 검색으로 이동
			
		case 3:
			insertbook();
			break;//3을 누르면 책 추가로 이동
			
		case 4:
			deletebook();
			break; //4를 누르면 책 삭제로 이동
			
		case 0:
			System.out.println("프로그램을 종료합니다.");
			System.exit(0); //0을 누르면 프로그램 종료
		}
	}
}
	
	private static int inputChoice() {
		int choice = -1;
		
		System.out.println("메뉴를 선택하세요 : ");
		
		choice = input.nextInt();
		
		System.out.println("선택한 메뉴 : "+choice);
		return choice;
	}//유저의 선택 메뉴 재확인
	
	private static void printMenu() {
		System.out.println("도서관리 프로그램 입니다.");
		System.out.println("1 : 전체 도서 출력");
		System.out.println("2 : 도서 검색");
		System.out.println("3 : 도서 추가");
		System.out.println("4 : 도서 삭제");
		System.out.println("0 : 프로그램 종료");
	}//관리 메뉴 출력

	private static void deletebook() throws IOException {
		
		String tmpFilePath = filePath +".tmp";
		int count = 1;
		System.out.println("삭제하실 책 번호를 입력해주세요 : " );
		int booknumber = input.nextInt();
		
		System.out.println("입력하신 책 번호 : "+booknumber);
	
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
	}//삭제기능

	private static void insertbook() throws IOException {
		System.out.println("추가할 책 정보를 입력하세요");
		input.nextLine();
		System.out.println("책 제목 : ");
		String name = input.nextLine();
		
		System.out.println("저자 : ");
		String author = input.nextLine();
		
		System.out.println("출판사 : ");
		String publisher = input.nextLine();
		
		System.out.println("가격 : ");
		String cost = input.nextLine();
		
		System.out.println("입력한 책 정보");
		System.out.println("책 제목 : "+name);
		System.out.println("저자: "+author);
		System.out.println("출판사 : "+publisher);
		System.out.println("가격: "+cost);
		

		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
		
		bw.write(name+"\t"+author+"\t"+publisher+"\t"+cost);
		bw.newLine();
		bw.close();
	}//책 입력 기능

	private static void searchbook() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		System.out.println("검색할 키워드를 입력하세요 : ");
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
			System.out.println("검색하신 책의 총 권수는 : " +count+ "권 입니다.");
			br.close();
		} catch (IOException e) {
			System.out.println("책 정보를 읽어 올 수 없습니다.");
			e.printStackTrace();
		}
	}//검색 기능

	private static void printbooks() throws FileNotFoundException  {
		System.out.println("책 출력");
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
			System.out.println("책의 총 권수는 : "+count+"권 입니다.");
		} catch (IOException e) {
			System.out.println("책 정보를 읽어 올 수 없습니다.");
			e.printStackTrace();
		}
	}
}//책 출력 기능
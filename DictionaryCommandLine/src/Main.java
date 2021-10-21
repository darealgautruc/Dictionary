import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException ,FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Dictionary dic = new Dictionary();
		DictionaryCommandline DC = new DictionaryCommandline();
		DictionaryManagement DM = new DictionaryManagement();

//		DM.insertFromCommandline(dic);
//		DM.dictionaryExportToFile(dic);
		while (true) {
			System.out.println("---------------------------------");
			System.out.println("Tôi có thể giúp gì cho bạn?\n" +
					"1:Tra từ\n" +
					"2:Thêm, sửa, xóa từ\n" +
					"3:Hiện tất cả các từ trong thư viện\n" +
					"4:Thoát");
			System.out.println("---------------------------------");
			int x = sc.nextInt();
			sc.nextLine();
			if (x == 1) {
//				DM.insertFromFile(dic);
				System.out.println("Nhập từ bạn cần tìm: ");
				String search = sc.nextLine().toLowerCase();
				DC.dictionarySearcher(dic,search);
			}
			else if (x == 2) {
				DM.themSuaXoa(dic, DC);
//				DM.removeFile(dic);
//				DM.dictionaryExportToFile(dic);
			}
			else if (x == 3) {
				DM.insertFromFile(dic);
				DC.showAllWords(dic);
			}
			else if(x == 4) {

				return;
			}
		}
	}

}
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Dictionary dic = new Dictionary();
		DictionaryCommandline DC = new DictionaryCommandline();
		DictionaryManagement DM = new DictionaryManagement();

		DM.insertFromFile(dic);
		while (true) {
			System.out.println("---------------------------------");
			System.out.println("Tôi có thể giúp gì cho bạn?\n" +
					"1:Tra từ\n" +
					"2:Thêm, sửa, xóa từ\n" +
					"3:Hiện tất cả các từ trong thư viện\n" +
					"4:Thoát");
			System.out.println("---------------------------------");
			Scanner sc = new Scanner(System.in);
			int x = sc.nextInt();
			sc.nextLine();
			if (x == 1) {
				System.out.println("Từ bạn muốn tìm là: ");
				String search = sc.nextLine();
				DC.dictionarySearcher(dic, search);
			}
			if (x == 2) {
				DM.themSuaXoa(dic, DC);
			}
			if (x == 3) {
				DM.insertFromFile(dic);
				DC.showAllWords(dic);
			}

			while (dic.word_list.size() >= 0) {
				DC.dictionaryBasic(dic);
//			DM.themSuaXoa(dic);
//			if(dic.word_list.size() > 3) DC.dictionarySearcher(dic);

			}
		}
	}

}

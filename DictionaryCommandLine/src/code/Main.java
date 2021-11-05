package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
        stage.setResizable(false);
        stage.setTitle("Dictionary English-Vietnamese");
        stage.setScene(new Scene(root));
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
//		Scanner sc = new Scanner(System.in);
//		Dictionary dic = new Dictionary();
//		DictionaryCommandline DC = new DictionaryCommandline();
//		DictionaryManagement DM = new DictionaryManagement();
//		DM.insertFromFile(dic);
//		while (true) {
//			System.out.println("---------------------------------");
//			System.out.println("Tôi có thể giúp gì cho bạn?\n" +
//					"1:Tra từ\n" +
//					"2:Thêm, sửa, xóa từ\n" +
//					"3:Hiện tất cả các từ trong thư viện\n" +
//					"4:Thoát");
//			System.out.println("---------------------------------");
//			int x = sc.nextInt();
//			sc.nextLine();
//			if (x == 1) {
//				System.out.println("Nhập từ bạn cần tìm: ");
//				String search = sc.nextLine().toLowerCase();
//				DC.dictionarySearcher(dic,search);
//			}
//			else if (x == 2) {
//				DM.themSuaXoa(dic, DC);
////				DM.removeFile(dic);
////				DM.dictionaryExportToFile(dic);
//			}
//			else if (x == 3) {
//				DC.showAllWords(dic);
//			}
//			else if(x == 4) {
//				return;
//			}
//		}
////		DM.delete(dic);


}
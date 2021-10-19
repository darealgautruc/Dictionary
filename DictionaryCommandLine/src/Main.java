import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Dictionary dic = new Dictionary();
		DictionaryCommandline DC = new DictionaryCommandline();
		DictionaryManagement DM = new DictionaryManagement();
		while(dic.word_list.size() >=0 ) {
			DC.dictionaryBasic(dic);
			DM.themSuaXoa(dic);
		}

	}

}

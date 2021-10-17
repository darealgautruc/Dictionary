import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Dictionary dic = new Dictionary();
		DictionaryCommandline dim = new DictionaryCommandline();
		DictionaryManagement dm = new DictionaryManagement();
		while(dic.word_list.size() >=0 ) {
			dm.insertFromFile(dic);
			dim.dictionaryBasic(dic);

		}

	}

}

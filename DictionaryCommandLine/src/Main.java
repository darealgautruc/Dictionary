import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Dictionary dic = new Dictionary();
		DictionaryCommandline dim = new DictionaryCommandline();

		while(dic.word_list.size() >=0 ) {
			dim.dictionaryBasic(dic);
		}

	}

}

import java.io.FileNotFoundException;

public class DictionaryCommandline {
    private DictionaryManagement dim = new DictionaryManagement();
    public void showAllWords(Dictionary x) {
        System.out.println("No   | English      | Vietnamese");
        for(int i=0; i<x.word_list.size(); i++) {
            System.out.print(x.word_list.get(i).getWord_target() + " ");
            System.out.println(x.word_list.get(i).getWord_explain());
        }
    }
    public void dictionaryBasic(Dictionary a) throws FileNotFoundException {
    	showAllWords(a);
        dim.insertFromCommandline(a);
    }
    public void dictionaryAdvanced(Dictionary dic) throws FileNotFoundException {
        dim.insertFromFile(dic);
        showAllWords(dic);
        dim.dictionaryLookup(dic);
    }
}

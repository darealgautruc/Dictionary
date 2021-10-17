import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class DictionaryManagement {
    public void insertFromCommandline(Dictionary dic) {
        Scanner sc = new Scanner(System.in);
        String TA;
        String TV;
        System.out.println("Nhap tu Tieng Anh: ");
        TA = sc.nextLine();
        System.out.println("Nhap nghia Tieng Viet: ");
        TV = sc.nextLine();
        Word tuMoi = new Word(TA, TV);
        dic.word_list.add(tuMoi);
    }
    
    public void insertFromFile(Dictionary dic) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/dictionary.txt"));
	    Scanner sc = new Scanner(System.in);
	    
	    while (sc.hasNext()) {
	    	Word word = new Word();
            String s1 = sc.next();
            String s2 = sc.next();
            word.setWord_target(s1);
            word.setWord_explain(s2);
            dic.word_list.add(word);
	    }
        sc.close();        
    }
    public void dictionaryLookup(Dictionary dic){
        String s;
        System.out.println("Nhap tu can tra");
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        for(int i = 0; i<dic.word_list.size(); i++) {
            if(dic.word_list.get(i).getWord_target().equals(s) ||
            dic.word_list.get(i).getWord_explain().equals(s)) {
                System.out.print(dic.word_list.get(i).getWord_target() +
                        " " + dic.word_list.get(i).getWord_explain());
            }
        }
    }

}

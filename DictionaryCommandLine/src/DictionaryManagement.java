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
        System.setIn(new FileInputStream("dictionary.txt"));
                Scanner sc = new Scanner(System.in);
                Word word = new Word();
                while (sc.hasNext()) {
                        String s1 = sc.next();
                        String s2 = sc.next();
                        word.setWord_target(s1);
                        word.setWord_explain(s2);
                        }
    }

}

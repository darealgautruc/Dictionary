import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement dim = new DictionaryManagement();

    /** In ra danh sách các từ.*/
    public void showAllWords(Dictionary x) {
        System.out.println("No  | English                  | Vietnamese");
        for(int i=0; i<x.word_list.size(); i++) {
            System.out.printf("%-3s| %-26s| %s\n",i+1,x.word_list.get(i).getWord_target(),x.word_list.get(i).getWord_explain());
        }
    }
    /** gọi 2 hàm thêm từ (dòng lệnh) và in ra từ.*/
    public void dictionaryBasic(Dictionary a) throws FileNotFoundException {
    	showAllWords(a);
        dim.insertFromCommandline(a);
    }
    /** gọi hàm thêm từ(file), in ra từ, kiểm tra từ*/
    public void dictionaryAdvanced(Dictionary dic) throws FileNotFoundException, IOException {
        dim.insertFromFile(dic);
        showAllWords(dic);
        Scanner sc = new Scanner(System.in);
        String tu = sc.next().toLowerCase();
        dim.dictionaryLookup(dic,tu);
    }
    /** Hàm tra từ.*/
    public void dictionarySearcher(Dictionary dic,String s) {
        System.out.println("Nhập từ cần tra: ");
        Dictionary a = new Dictionary();
        Scanner sc= new Scanner(System.in);
        s = sc.next();
        for(int i = 0; i < dic.word_list.size(); i++) {
            if(dic.word_list.get(i).getWord_target().substring(0, s.length()).equals(s)) {
                a.word_list.add(dic.word_list.get(i));
                }
            }
            showAllWords(a);
        }

}


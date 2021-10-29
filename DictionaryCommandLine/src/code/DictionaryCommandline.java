package code;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement DM = new DictionaryManagement();

    /** In ra danh sách các từ.*/
    public void showAllWords(Dictionary x) {
        sort(x);
        System.out.println("No  | English                  | Vietnamese");
        for(int i=0; i<x.word_list.size(); i++) {
            System.out.printf("%-3s| %-26s| %s\n",i+1,x.word_list.get(i).getWord_target(),x.word_list.get(i).getWord_explain());
        }
    }
    /** gọi 2 hàm thêm từ (dòng lệnh) và in ra từ.*/
    public void dictionaryBasic(Dictionary a) throws FileNotFoundException {
      DM.insertFromCommandline(a);
      showAllWords(a);
    }
    /** gọi hàm thêm từ(file), in ra từ, kiểm tra từ*/
//    public void dictionaryAdvanced(Dictionary dic) throws FileNotFoundException, IOException {
//        DM.insertFromFile(dic);
//        showAllWords(dic);
//        Scanner sc = new Scanner(System.in);
//        String tu = sc.next().toLowerCase();
//        DM.dictionaryLookup(dic,tu);
//    }
    /** Hàm tra từ.*/
    public Dictionary dictionarySearcher(Dictionary dic,String s) {
        Dictionary a = new Dictionary();
        for(Word w : dic.word_list) {
            if(w.getWord_target().startsWith(s)) {
                a.word_list.add(w);
                }
        }
        return a;
    }
    
    public void sort(Dictionary dic) {
        Collections.sort(dic.word_list, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
    }
}


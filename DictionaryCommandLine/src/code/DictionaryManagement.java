package code;

import com.sun.speech.freetts.VoiceManager;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {


    /**
     * Thêm từ mới từ dòng lệnh vào danh sách từ.
     */
    public void insertFromCommandline(Dictionary dic) {
        Scanner sc = new Scanner(System.in);
        String TA;
        String TV;
        System.out.println("Nhập từ Tiếng Anh: ");
        TA = sc.nextLine().toLowerCase();
        if (existsWord(dic, TA)) {
            System.out.println("Từ này đã có trong từ điển. Vui lòng nhập từ khác");
            return;
        }
        System.out.println("Nhập nghĩa Tiếng Việt: ");
        TV = sc.nextLine();
        Word tuMoi = new Word(TA, TV);
        dic.word_list.add(tuMoi);
        System.out.println("Đã thêm " + TA + " vào từ điển");


    }

    /**
     * Nhập dữ liệu từ điển từ file.
     */

    public void insertFromFile(Dictionary list) throws IOException {
        Scanner scanner = new Scanner(Paths.get("src\\dictionary.txt"),"UTF-8");
        while (scanner.hasNext()) {
            while (scanner.hasNextLine()) {
                Word x = new Word();
                String s = scanner.nextLine();
                String[] word = s.split("\\t", 2);
                x.setWord_target(word[0]);
                x.setWord_explain(word[1]);
                list.word_list.add(x);
            }
        }
        scanner.close();
    }

    /**
     * Tra cứu từ điển từ dòng lệnh.
     */
    public Word dictionaryLookup(Dictionary dic, String s) {
    	int count = 0;
        String nghia = null;
        for (int i = 0; i < dic.word_list.size(); i++) {
            if (dic.word_list.get(i).getWord_target().equals(s)) {
                nghia = dic.word_list.get(i).getWord_explain();
                count += 1;
            }
        }
        if(count > 0) {
        	Word w = new Word(s, nghia);
        	return w;
        }
        else {
            Word w = new Word();
            return w;
        }

    }

    /**
     * Hàm kiếm tra từ trong từ điển
     */
    public boolean existsWord(Dictionary dic, String a) {
        for (int i = 0; i < dic.word_list.size(); i++) {
            if (a.equals(dic.word_list.get(i).getWord_target())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hàm ghi từ vào file.
     */
    public void dictionaryExportToFile(Dictionary dic) throws IOException {
        FileWriter file = new FileWriter("src/dictionary.txt");
        for(int i = 0; i < dic.word_list.size(); i++) {
            file.write(dic.word_list.get(i).getWord_target() + '\t' + dic.word_list.get(i).getWord_explain() + '\n');
        }
        file.close();
    }

    /**
     * Xóa từ giống nhau.
     * @param dic .
     */
    public void delete(Dictionary dic) {
        for(int i= 0; i < dic.word_list.size() - 1; i++) {
            for(int j = i+1; j < dic.word_list.size(); j++) {
                if(dic.word_list.get(i).getWord_target().equals(dic.word_list.get(j).getWord_target())) {
                    dic.word_list.remove(j);
                }
            }
        }
    }

    /**
     * Xóa từ.
     * @param dic .
     * @param s .
     * @throws . IOException .
     */
    public void xoaTu(Dictionary dic, String s) throws IOException {
        if(existsWord(dic, s)) {
            for(int i = 0; i < dic.word_list.size(); i++) {
                if(dic.word_list.get(i).getWord_target().equals(s)) {
                    dic.word_list.remove(i);
                    dictionaryExportToFile(dic);
                }
            }
        }
    }

    /**
     * Thêm từ
     * @param dic .
     * @param target .
     * @param explain .
     */
    public void themTu(Dictionary dic, String target, String explain) throws IOException {
        if(!existsWord(dic, target)) {
            Word w = new Word(target, explain);
            dic.word_list.add(w);
            dictionaryExportToFile(dic);
        }
    }

    /**
     * Sửa từ.
     * @param dic .
     * @param target .
     * @param explain .
     * @throws . IOException .
     */
    public void suaTu(Dictionary dic, String target, String explain) throws IOException {
        if(existsWord(dic, target)) {
            for(int i = 0; i < dic.word_list.size(); i++) {
                if(dic.word_list.get(i).getWord_target().equals(target)) {
                    dic.word_list.remove(i);
                    Word w = new Word(target, explain);
                    dic.word_list.add(w);
                    dictionaryExportToFile(dic);
                }
            }
        }
    }



}





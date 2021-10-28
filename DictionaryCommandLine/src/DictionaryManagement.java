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
        Scanner scanner = new Scanner(Paths.get("D:\\Git_Dictionary\\DictionaryCommandLine\\src\\dictionary.txt"),"UTF-8");
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
    public void dictionaryLookup(Dictionary dic, String s) {
        System.out.println("Nhập từ cần tìm kiếm: ");
        int count = 0;
        String nghia = null;
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        for (int i = 0; i < dic.word_list.size(); i++) {
            if (dic.word_list.get(i).getWord_target().equals(s)) {
                nghia = dic.word_list.get(i).getWord_explain();
                count += 1;
            }
        }
        if(count > 0) {
            System.out.println("TA: " + s + " TV:" + nghia);
            ReadText.speech(s);
        }
        else {
            System.out.println("Từ này không có trong từ điển!");
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
     * Thêm sửa xóa từ.
     */
    public void themSuaXoa(Dictionary dic,DictionaryCommandline DC) throws IOException {
        String s = new String();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Bạn sẽ làm gì?\n 1:Thêm từ.\n 2:Sửa từ.\n 3:Xóa từ.\n 4:Không làm gì cả!");
            int n = sc.nextInt();
            sc.nextLine();
            if (n == 1) {
                insertFromCommandline(dic);
    // Xóa file ghi dữ liệu và tạo lại file mới có lấy kết quả của list mới
                dictionaryExportToFile(dic);
            }
            // Note sửa từ.
            if (n == 2) {
                System.out.println("Nhập từ bạn muốn sửa: ");
                s = sc.nextLine().toLowerCase();
                if (!existsWord(dic, s)) {
                    System.out.println("Không có từ này trong từ điển. Vui lòng kiểm tra lại");
                } else {
                    for(int i = 0; i < dic.word_list.size(); i++) {
                        if(s.equals(dic.word_list.get(i).getWord_target())) {
                            System.out.println("Nhập nghĩa mới của từ: ");
                            String nghia = sc.nextLine();
                            dic.word_list.get(i).setWord_explain(nghia);
                        }
                    }
                }
//<<<<<<< Updated upstream
                dictionaryExportToFile(dic);
//=======
////               removeFile(dic);
//               dictionaryExportToFile(dic);
//>>>>>>> Stashed changes
            }

            if (n == 3) {
                System.out.println("Nhập từ bạn muốn xóa: ");
                s = sc.next().toLowerCase();
                if (!existsWord(dic, s)) {
                    System.out.println("Không có từ này trong từ điển. Vui lòng kiểm tra lại");
                } else {
                    for (int i = 0; i < dic.word_list.size(); i++) {
                        if (s.equals(dic.word_list.get(i).getWord_target())) {
                            dic.word_list.remove(i);
                            System.out.println("Đã xóa.");
                        }
                    }
                }
                dictionaryExportToFile(dic);
            }
            if( n==4) {
                return;
            }
        }
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

    public void delete(Dictionary dic) {
        for(int i= 0; i < dic.word_list.size() - 1; i++) {
            for(int j = i+1; j < dic.word_list.size(); j++) {
                if(dic.word_list.get(i).getWord_target().equals(dic.word_list.get(j).getWord_target())) {
                    dic.word_list.remove(j);
                }
            }
        }
    }
}





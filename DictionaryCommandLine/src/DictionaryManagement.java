import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
     * Thêm từ mới từ file.
     */
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

    /**
     * Tra cứu từ điển từ dòng lệnh.
     */
    public void dictionaryLookup(Dictionary dic, String s) {
        System.out.println("Nhập từ cần tìm kiếm: ");
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        for (int i = 0; i < dic.word_list.size(); i++) {
            if (dic.word_list.get(i).getWord_target().equals(s)) {
                System.out.print("English: " + dic.word_list.get(i).getWord_target() +
                        " " + "Vietnamese: " + dic.word_list.get(i).getWord_explain());
            } else {
                System.out.println("Từ này không có trong từ điển");
            }
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
    public void themSuaXoa(Dictionary dic) {
        String s = new String();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Bạn sẽ làm gì?\n 1:Thêm từ\n 2:Sửa từ\n 3:Xóa từ\n 4:Trở lại");
            int n = sc.nextInt();
            sc.nextLine();
            if (n == 1) {
                insertFromCommandline(dic);
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
                            break;
                        }
                    }
                }
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
                        }
                    }
                }
            }
            if( n==4) {
            	return;
            }
        }
    }
    public void dictionaryExportToFile(Dictionary dic) throws FileNotFoundException {
        FileOutputStream file = new FileOutputStream("src/dictionary.txt");

    }
}





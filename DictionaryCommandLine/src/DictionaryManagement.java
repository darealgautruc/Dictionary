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

}

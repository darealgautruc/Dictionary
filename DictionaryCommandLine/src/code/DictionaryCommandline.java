package code;

import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryCommandline {
    private DictionaryManagement DM = new DictionaryManagement();

    /**
     * Gợi ý từ
     * @param dic .
     * @param s .
     * @return .
     */
    public List dictionarySearcher(Dictionary dic, String s) {
        List<String> listSearch = new ArrayList<>();
        for(int i = 0; i < dic.word_list.size(); i++) {
            if(dic.word_list.get(i).getWord_target().startsWith(s)) {
                listSearch.add(dic.word_list.get(i).getWord_target());
            }
        }
        return listSearch;
    }

    /**
     * Sắp xếp từ .
     * @param dic .
     */
    public void sort(Dictionary dic) {
        Collections.sort(dic.word_list, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
    }

    /**
     * Sửa chính tả.
     * @param dic .
     * @param s .
     * @return .
     */
    public String suaChinhTa(Dictionary dic, String s) {
        List<String> dsSua = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (Word w : dic.word_list) {
                if (w.getWord_target().startsWith(s.substring(0, i))) {
                    dsSua.add(w.getWord_target());
                }
            }
        }
        return dsSua.get(dsSua.size()-1);
    }

}


package code;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import code.Dictionary;
import code.DictionaryManagement;
import code.Word;

public class ControllerSuaTu {
	Dictionary dic=new Dictionary();
    DictionaryManagement DM = new DictionaryManagement();
    @FXML
    private TextField tuDeSua;
    @FXML
    private TextField nghiaDeSua;
    
    public void sua() throws IOException {
        DM.insertFromFile(dic);
        DM.suaTu(dic, tuDeSua.getText(), nghiaDeSua.getText());
    }
}

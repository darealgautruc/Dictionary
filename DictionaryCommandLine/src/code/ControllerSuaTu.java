package code;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        if(DM.existsWord(dic,tuDeSua.getText())){
            DM.deleteFile(dic);
            DM.suaTu(dic, tuDeSua.getText(), nghiaDeSua.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thành công");
            alert.setHeaderText("Đã sửa từ này trong từ điển");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Từ này không có trong từ điển \n Bạn có muốn thêm từ này?");
            alert.showAndWait();
        }

    }
}

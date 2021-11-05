package code;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerThemTu {
	Dictionary dic = new Dictionary();
	DictionaryManagement DM = new DictionaryManagement();
	
	
	@FXML
	private TextField tuDeThem;
	
	@FXML
	private TextArea nghiaDeThem;
	
	@FXML
	public void themTu() throws Exception {
        DM.insertFromFile(dic);
		Word tuCanThem = new Word(tuDeThem.getText(), nghiaDeThem.getText());
        if(!DM.existsWord(dic, tuDeThem.getText())) {
            dic.word_list.add(tuCanThem);
            DM.dictionaryExportToFile(dic);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thành công");
            alert.setHeaderText("Đã thêm từ này vào từ điển");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Từ này đã có trong từ điển \n Nếu bạn muốn thêm nghĩa của từ này, bấm nút sửa");
            alert.showAndWait();
        }
	}
}

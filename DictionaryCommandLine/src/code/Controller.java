package code;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import code.Word;

public class Controller implements Initializable {
	private ObservableList<String> target;
	private ObservableList<Word> word;
	
	@FXML
	private TextField inputWord;
	@FXML
	private TextField selectedWord;
	@FXML
	private TextArea selectedWordExplain;
	@FXML
	private ListView<String> recommendWordList;
	
	Dictionary dic = new Dictionary();
	DictionaryCommandline DC = new DictionaryCommandline();
	DictionaryManagement DM = new DictionaryManagement();
	List<String> wordList = new ArrayList<>();
	List<Word> wordSearchedList = new ArrayList<>();

	public Controller() throws Exception {
		DM.insertFromFile(dic);
		DC.sort(dic);
		for(Word word : dic.word_list) {
			wordList.add(word.getWord_target());
			wordSearchedList.add(word);
		}
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
        target = FXCollections.observableList(wordList);
        word = FXCollections.observableArrayList(dic.word_list);
        recommendWordList.setItems(target);
        inputWord.setOnKeyReleased(event -> {
			try {
				inputFromTextField(event);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
	
	@FXML
	private void inputFromTextField(KeyEvent event) throws Exception {
		String s = inputWord.getText();
		List<String> wordStartWithS = DC.dictionarySearcher(dic, s);
		target.clear();
		target.addAll(wordStartWithS);
		if (event.getCode() == KeyCode.ENTER) {
			if (target.size() == 0) {
				s = DC.suaChinhTa(dic, s);
			} else {
				s = target.get(0);
			}
			selectedWord.setText(s);
			Word w = DM.dictionaryLookup(dic, s);
			selectedWordExplain.setText(w.getWord_explain());
		}
	}
	
//	@FXML
//	public void enterFromTextField(KeyEvent event) throws Exception {
//		if (event.getCode() == KeyCode.ENTER) {
//			String s = target.get(0);
//			selectedWord.setText(s);
//			inputWord.setText(s);
//			Word w = DM.dictionaryLookup(dic, s);
//			selectedWordExplain.setText(s);
//		}
//	}
	
	@FXML
    public void selectWord(MouseEvent event) throws Exception {
        String s = recommendWordList.getSelectionModel().getSelectedItem();
        selectedWord.setText(s);
        inputWord.setText(s);
        Word w = DM.dictionaryLookup(dic, s);
        selectedWordExplain.setText(w.getWord_explain());
    }
	
	public void speech() {
		String text = selectedWord.getText();
		VoiceManager voiceManager = VoiceManager.getInstance();
		com.sun.speech.freetts.Voice syntheticVoice = voiceManager.getVoice("kevin16");
		syntheticVoice.allocate();
		syntheticVoice.speak(text);
		syntheticVoice.deallocate();
	}
	 
	public void addWord() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Themtu.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Thêm từ");
        primaryStage.setScene(new Scene(root));
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();
        dic.word_list.clear();
        DM.insertFromFile(dic);
        DC.sort(dic);
        newPage();
	}
	
	public void modifyWord() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Suatu.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sửa từ");
        primaryStage.setScene(new Scene(root));
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();
        dic.word_list.clear();
        DM.insertFromFile(dic);
        DC.sort(dic);
        newPage();
	}

	public void deleteWord() throws Exception {
		String string = selectedWord.getText();
		Word w = DM.dictionaryLookup(dic, string);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete Word");
		alert.setHeaderText("Bạn có chắc xóa từ này?");
		Optional<ButtonType> option = alert.showAndWait();
		if(option.get() == ButtonType.OK) {
			DM.xoaTu(dic, string);
			newPage();
		}
	}

	void newPage() {
		inputWord.setText("");
		selectedWord.setText("");
		selectedWordExplain.setText("");
		target.clear();
	}
	
	public void API() throws Exception {
		selectedWordExplain.setText(API.translate("en","vi",selectedWord.getText()));
	}

}

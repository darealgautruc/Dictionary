package code;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        inputWord.setOnKeyReleased(event -> inputFromTextField());
    }
	
	@FXML
	private void inputFromTextField() {
		String s = inputWord.getText();
		List<Word> wordStartWithS = DC.dictionarySearcher(dic, s).word_list;
		List<String> recommendWord = new ArrayList<>();
		for(Word w : wordStartWithS) {
			recommendWord.add(w.getWord_target());
		}
		target.clear();
		target.addAll(recommendWord);
		word.clear();
		word.addAll(wordStartWithS);
	    if (word.size() == 0 || s == null) {
	    	selectedWordExplain.setText("");
	    	selectedWord.setText("");
	    } else if (s == "") {
	    	selectedWordExplain.setText("");
	    	selectedWord.setText("");
	    } else {
	    	selectedWord.setText(word.get(0).getWord_target());
	    	selectedWordExplain.setText(word.get(0).getWord_explain());
	    }
	}
	
	@FXML
    public void selectWord(MouseEvent event) throws Exception {
        DM.dictionaryExportToFile(dic);
        String s = recommendWordList.getSelectionModel().getSelectedItem();
        selectedWord.setText(s);
        inputWord.setText(s);
        Word w = DM.dictionaryLookup(dic, s);
        selectedWordExplain.setText(w.getWord_explain());
    }
	
	public void speech() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        Voice voice = vm.getVoice("kevin16");
        voice.allocate();
        voice.speak(selectedWord.getText());
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
        inputFromTextField();
	} 
}

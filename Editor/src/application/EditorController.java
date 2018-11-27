package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditorController implements Initializable {
	private final String INITIAL_TEXT = "<html><body></body></html>";
	@FXML
	private MenuItem mnuVer;
	@FXML
	private HTMLEditor editor;
	@FXML
	private WebView browser;
	@FXML
	private Text txtBrowser;

	private String fileName = null;
	private Stage stage;
	private EventHandler<ActionEvent> verHandler;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	private FileChooser getFileChooser(String titulo) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(titulo);
		fileChooser.setInitialDirectory(new File("./"));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Todos", "*.*"),
				new FileChooser.ExtensionFilter("HTML", "*.HTML"));

		return fileChooser;
	}

	public void onNuevo(ActionEvent e) {
		editor.setHtmlText(INITIAL_TEXT);
		fileName = null;
	}

	public void onAbrir(ActionEvent e) {
		FileChooser fileChooser = getFileChooser("Abrir");
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			try {
				fileName = file.getPath();
				editor.setHtmlText(new String(Files.readAllBytes(Paths.get(fileName))));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void onGuardar(ActionEvent e) {
		if (fileName == null) {
			onGuardarComo(e);
		} else {
			guardar(fileName);
		}
	}

	public void onGuardarComo(ActionEvent e) {
		FileChooser fileChooser = getFileChooser("Guardar como");
		File file = fileChooser.showSaveDialog(stage);
		if (file != null) {
			guardar(file.getPath());
		}
	}

	private void guardar(String fileName) {
		Charset charset = Charset.forName("UTF-8");
		String s = editor.getHtmlText();
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName), charset)) {
			writer.write(s, 0, s.length());
			this.fileName = fileName;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void onSalir(ActionEvent e) {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		onNuevo(null);
		browser.getEngine().loadContent(INITIAL_TEXT);
		verHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				browser.getEngine().loadContent(editor.getHtmlText());
				txtBrowser.setText(editor.getHtmlText());
			}
		};
		mnuVer.setOnAction(verHandler);
	}

}
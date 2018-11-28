package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.editor.EditorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {
	@FXML
	private BorderPane root;
	@FXML
	Accordion acordeon;

	private Object cargarFXML(String plantilla) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(plantilla));
		try {
			root.setCenter(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loader.getController();
	}

	public void calculadora(Event ev) {
		cargarFXML("calculadora/calculadora.fxml");
	}

	public void editor(Event ev) {
		EditorController controller = (EditorController) cargarFXML("editor/editor.fxml");
		controller.setStage(Main.getStage());
	}

	public void cambiaEscena(Event ev) {
		Main.goDashboard();
	}

	public void pieChart(Event ev) {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges", 25), new PieChart.Data("Plums", 10),
				new PieChart.Data("Pears", 22), new PieChart.Data("Apples", 30));
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Imported Fruits");
		root.setCenter(chart);
	}

	public void lineChart(Event ev) {
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Month");
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		lineChart.setTitle("Stock Monitoring, 2010");
		XYChart.Series series = new XYChart.Series();
		series.setName("My portfolio");
		series.getData().add(new XYChart.Data(1, 23));
		series.getData().add(new XYChart.Data(2, 14));
		series.getData().add(new XYChart.Data(3, 15));
		series.getData().add(new XYChart.Data(4, 24));
		series.getData().add(new XYChart.Data(5, 23));
		series.getData().add(new XYChart.Data(6, 14));
		series.getData().add(new XYChart.Data(7, 17));
		series.getData().add(new XYChart.Data(8, 22));
		series.getData().add(new XYChart.Data(9, 19));
		series.getData().add(new XYChart.Data(10, 12));
		series.getData().add(new XYChart.Data(11, 23));
		series.getData().add(new XYChart.Data(12, 25));

		lineChart.getData().add(series);
		root.setCenter(lineChart);
	}

	public void verAyuda(Event ev) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load("https://docs.oracle.com/javase/8/javase-clienttechnologies.htm");
		root.setCenter(browser);
	}
	public void verInforme(Event ev) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><style>");
		sb.append("body { color: red; }");
		sb.append("th { background-color: red; color: white; }");
		sb.append("</style><head><body>");
		sb.append("<h1>Listado</h1>");
		sb.append("<p><b>Nota</b>Lorem ipsum dolor sit amet consectetur adipiscing elit malesuada feugiat commodo sollicitudin mus laoreet hendrerit, purus morbi montes platea vestibulum posuere cras per ad massa vel netus eu. </p>");
		sb.append("<table border=\"1\">");
		sb.append("<tr><th>C�digo</th><th>Nombre</th><th>Apellidos</th></tr>");
		for(int i = 1; i++ < 100;) {
		sb.append(String.format("<tr><td>%d</td><td>Nombre %d</td><td>Apellidos %d</td></tr>", i, i, i));
		}
		sb.append("</table></body></html>");
		webEngine.loadContent(sb.toString());
		root.setCenter(browser);
	}
	public void verVideo(Event ev) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load("https://ia600208.us.archive.org/31/items/ace_200907_06/012daf06069bb4c0b8171a68d176d258.mts-mp430-272.mp4");
		root.setCenter(browser);
	}
	public void reproductor(Event evento) {
//		Media media = new Media("https://ia600208.us.archive.org/31/items/ace_200907_06/012daf06069bb4c0b8171a68d176d258.mts-mp430-272.mp4");
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.setAutoPlay(true);
//		MediaView player = new MediaView(mediaPlayer);
//		player.setMediaPlayer(mediaPlayer);
//		root.setCenter(player);
		VideoPlayerController c = (VideoPlayerController)cargarFXML("video-player.fxml");
		c.setUrlVideo("https://ia800301.us.archive.org/15/items/CuteWhiteDogsSitting-20050731/CuteWhiteDogsSitting-20050731_512kb.mp4");
	}
	
	public void alerta(Event ev) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("I have a great message for you!");

		alert.showAndWait();
	}

	public void ventanaModal(Event ev) throws IOException {
		Stage dialogStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CDialogo.fxml"));
		Parent ventana = (Parent) loader.load();
		((CDialogoController) loader.getController()).setStage(dialogStage);
		Scene scene = new Scene(ventana, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogStage.setTitle("Cuadro de dialogo");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(Main.getStage());
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
		// En okHandle o cancelHandle: dialogStage.close();
	}

	public void ventanaModaless(Event ev) throws IOException {
		Stage dialogStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ventana.fxml"));
		Parent ventana = (Parent) loader.load();
		Scene scene = new Scene(ventana, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogStage.setTitle("Ventana con padre");
		dialogStage.initModality(Modality.NONE);
		dialogStage.initOwner(Main.getStage());
		dialogStage.setScene(scene);
		dialogStage.show();
	}

	public void ventanaIndependiente(Event ev) throws IOException {
		Stage dialogStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ventana.fxml"));
		Parent ventana = (Parent) loader.load();
		Scene scene = new Scene(ventana, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogStage.setTitle("Ventana independiente");
		dialogStage.initModality(Modality.NONE);
		dialogStage.initOwner(null);
		dialogStage.setScene(scene);
		dialogStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		acordeon.setExpandedPane(acordeon.getPanes().get(1));
	}
}

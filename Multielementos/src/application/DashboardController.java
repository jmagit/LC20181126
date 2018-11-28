package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class DashboardController implements Initializable {
	@FXML
	private PieChart pieChart;
	@FXML
	private LineChart lineChart;
	@FXML
	private BarChart barChart;
	@FXML
	private ScatterChart scatterChart;
	@FXML
	private StackedBarChart stackedBarChart;
	@FXML
	private TableView tabla;
	@FXML
	private MediaView player;

	public void cambiaEscena(Event ev) {
		Main.goPrincipal();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createPieChart();
		createLineChart();
		createBarChart();
		createStackedAreaChart();
		createStackedBarChart();
		bindTable();
		ponVideo();
	}

	private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
			new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges", 25), new PieChart.Data("Plums", 10),
			new PieChart.Data("Pears", 22), new PieChart.Data("Apples", 30));

	public void createPieChart() {
		pieChart.setData(pieChartData);
		pieChart.setTitle("Imported Fruits");
		pieChart.setLabelLineLength(10);
		pieChart.setLegendSide(Side.RIGHT);
		pieChart.setLegendVisible(false);

		Random rnd = new Random();
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), ev -> {
			pieChart.getData().stream().forEach((data) -> {
				int nuevo = rnd.nextInt();
				if (nuevo != data.getPieValue())
					data.setPieValue(nuevo);
			});
		}));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.setAutoReverse(true);
		tl.play();
	}

	public void bindTable() {
		((TableColumn) tabla.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<>("name"));
		((TableColumn<PieChart.Data, Double>) tabla.getColumns().get(1))
				.setCellValueFactory(new PropertyValueFactory<PieChart.Data, Double>("pieValue"));
		tabla.setItems(pieChartData);
	}

	private String[] meses = { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" };

	private XYChart.Series SerieConCategoria(String titulo, boolean rotar) {
		Random rnd = new Random();
		XYChart.Series series = new XYChart.Series();
		series.setName(titulo);
		for (String mes : meses)
			if (rotar) {
				series.getData().add(new XYChart.Data(rnd.nextInt() % 100 + 1, mes));
				if (rotar && "Jun".equals(mes))
					break;
			} else
				series.getData().add(new XYChart.Data(mes, rnd.nextInt() % 100 + 1));
		return series;
	}

	private XYChart.Series SerieConNumeros(String titulo) {
		Random rnd = new Random();
		XYChart.Series series = new XYChart.Series();
		series.setName(titulo);
		for (int i = 1; i <= 12; i++)
			series.getData().add(new XYChart.Data(i, rnd.nextInt() % 100 + 1));
		return series;
	}

	private void animaConCategoria(XYChart<String, Number> chart, double millis) {
		Random rnd = new Random();
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(millis), (ActionEvent actionEvent) -> {
			chart.getData().stream().forEach((series) -> {
				series.getData().stream().forEach((data) -> {
					int nuevo = rnd.nextInt(100);
					data.setYValue(nuevo);
				});
			});
		}));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.setAutoReverse(true);
		tl.play();
	}

	private void animaConCategoriaRotada(XYChart<Number, String> chart, double millis) {
		Random rnd = new Random();
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(millis), (ActionEvent actionEvent) -> {
			chart.getData().stream().forEach((series) -> {
				series.getData().stream().forEach((data) -> {
					int nuevo = rnd.nextInt(100);
					data.setXValue(nuevo);
				});
			});
		}));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.setAutoReverse(true);
		tl.play();
	}

	private void animaConNumeros(XYChart<Number, Number> chart, double millis) {
		Random rnd = new Random();
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(millis), (ActionEvent actionEvent) -> {
			chart.getData().stream().forEach((series) -> {
				series.getData().stream().forEach((data) -> {
					int nuevo = rnd.nextInt(100);
					data.setYValue(nuevo);
				});
			});
		}));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.setAutoReverse(true);
		tl.play();

	}

	public void createLineChart() {
		lineChart.getXAxis().setLabel("Meses");
		lineChart.setTitle("Stock Monitoring, 2018");
		lineChart.setLegendSide(Side.BOTTOM);
		lineChart.getData().add(SerieConCategoria("Uvas", false));
		lineChart.getData().add(SerieConCategoria("Manzanas", false));
		lineChart.getData().add(SerieConCategoria("Peras", false));
		animaConCategoria(lineChart, 750);
	}

	public void createBarChart() {
		barChart.setLegendVisible(false);
		barChart.getXAxis().setLabel("Meses");
		barChart.getXAxis().setTickLabelRotation(90);
		barChart.setTitle("Temperature Monitoring (in Degrees C)");
		barChart.setLegendSide(Side.BOTTOM);
		barChart.getData().add(SerieConCategoria("Madrid", true));
//        barChart.getData().add(SerieConCategoria("Barcelona", true));
//        barChart.getData().add(SerieConCategoria("Sevilla", true));
		animaConCategoriaRotada(barChart, 600);
	}

	public void createStackedAreaChart() {
		scatterChart.getXAxis().setLabel("Meses");
		scatterChart.setTitle("Investment Overview");
		scatterChart.setLegendSide(Side.BOTTOM);
		scatterChart.getData().add(SerieConNumeros("Uvas"));
		scatterChart.getData().add(SerieConNumeros("Manzanas"));
		scatterChart.getData().add(SerieConNumeros("Peras"));
		animaConNumeros(scatterChart, 550);
	}

	public void createStackedBarChart() {
		stackedBarChart.setLegendVisible(false);
		stackedBarChart.getXAxis().setLabel("Paises");
		stackedBarChart.setTitle("Country, 2018");
		stackedBarChart.setLegendSide(Side.RIGHT);
		stackedBarChart.getData().add(SerieConCategoria("USA", false));
		stackedBarChart.getData().add(SerieConCategoria("Francia", false));
		stackedBarChart.getData().add(SerieConCategoria("España", false));
		animaConCategoria(stackedBarChart, 700);
	}

	public void ponVideo() {
		Media media = new Media(getClass().getResource("oow2010-2.flv").toExternalForm());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		player.setMediaPlayer(mediaPlayer);
	}

}

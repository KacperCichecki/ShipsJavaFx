package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Field;
import model.Game;
import model.State;
import model.XY;

public class Controller implements Initializable{

	Game game = new Game();

	@FXML
	private GridPane enemyField;

	@FXML
	private ProgressBar progressBarMe, progressBarEnemy;

	@FXML
	private Button eneButton00, eneButton10, eneButton20, eneButton30, eneButton40;
	@FXML
	private Button eneButton01, eneButton11, eneButton21, eneButton31, eneButton41;
	@FXML
	private Button eneButton02, eneButton12, eneButton22, eneButton32, eneButton42;
	@FXML
	private Button eneButton03, eneButton13, eneButton23, eneButton33, eneButton43;
	@FXML
	private Button eneButton04, eneButton14, eneButton24, eneButton34, eneButton44;

	@FXML
	private GridPane myField;

	@FXML
	private Button button00, button10, button20, button30, button40;
	@FXML
	private Button button01, button11, button21, button31, button41;
	@FXML
	private Button button02, button12, button22, button32, button42;
	@FXML
	private Button button03, button13, button23, button33, button43;
	@FXML
	private Button button04, button14, button24, button34, button44;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int centerX = screenSize.width / 2;
	private int centerY = screenSize.height / 2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progressBarMe.setProgress(1);
		progressBarEnemy.setProgress(1);
	}

	public void restartGame() {
		game.startGame();


	}

	public void showGameInfo() {

	}

	public void hitField(ActionEvent e) {
		Button button = (Button) e.getSource();
		int number = Integer.parseInt(button.getText());
		int x = number / 10;
		int y = number % 10;
		// first field is enemy's field and second is mine
		Field[] fields = game.nextRound(new XY(x, y));
		setEnemyField(fields[0]);
		setMyField(fields[1]);

	}

	private void setMyField(Field field) {
		if(field.getState() == State.HIT){
			int points = game.getEnemy().getPoints();
			progressBarMe.setProgress((double)(4 - points) /4);
			if(game.getEnemy().getPoints() == 4){
				showAnnouncement("YOU LOST\n\n learn how to play!");
			}
		}

		XY xy = field.getXY();
		int x = xy.getX();
		int y = xy.getY();
		String buttonNr = "" + x + y;
		String state = field.getState().toString();

		ObservableList<Node> buttons = myField.getChildren();
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i) instanceof Button) {
				Button but = (Button) buttons.get(i);
				if ((but).getText().endsWith(buttonNr)) {
					but.setId(state);
				}
			}
		}
	}

	private void setEnemyField(Field field) {
		if(field.getState() == State.ENEMYHIT){
			int points = game.getMe().getPoints();
			progressBarEnemy.setProgress((double)(4 - points) /4);
			if( points == 4){
				showAnnouncement("YOU WON\n\n Congratulation!");
			}
		}

		XY xy = field.getXY();
		int x = xy.getX();
		int y = xy.getY();
		String buttonNr = "" + x + y;
		String state = field.getState().toString();

		ObservableList<Node> buttons = enemyField.getChildren();
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i) instanceof Button) {
				Button but = (Button) buttons.get(i);
				if ((but).getText().endsWith(buttonNr)) {
					but.setDisable(true);
					but.setId(state);
				}
			}
		}


	}

	// show Pop up window with custom text
	private void showAnnouncement(String message) {

		Toolkit.getDefaultToolkit().beep();
		Stage stage = (Stage) enemyField.getScene().getWindow();

		final Popup popup = new Popup();
		popup.setX(centerX - 200);
		popup.setY(centerY - 250);

		Button hideButton = new Button("OK");
		hideButton.setId("okButton");
		hideButton.setOnAction((a) -> popup.hide());

		Label label = new Label(message);
		label.setId("popLabel");

		VBox layout1 = new VBox();
		layout1.setId("popup");
		layout1.setMinHeight(300);
		layout1.setMinWidth(400);
		layout1.setAlignment(Pos.CENTER);
		layout1.getChildren().addAll(label, hideButton);

		popup.getContent().addAll(layout1);
		popup.show(stage);
	}

}

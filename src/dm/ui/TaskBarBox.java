package dm.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TaskBarBox extends HBox{
	
	Button addUrlB=new Button(),
			deleteItemB=new Button(),
			homeB=new Button(),
			pauseB=new Button(),
			resumeB=new Button(),
			downloadB=new Button(),
			settingB=new Button();
	HBox hBox=new HBox();
	
	public TaskBarBox(){

	addUrlB=setImage(addUrlB,"addUrl.png");
	deleteItemB=setImage(deleteItemB,"delete.jpg");
	homeB=setImage(homeB,"home.jpg");
	pauseB=setImage(pauseB,"pause.png");
	resumeB=setImage(resumeB,"play.png");
	downloadB=setImage(downloadB,"download.jpg");
	settingB=setImage(settingB,"setting.png");
	
	deleteItemB.setVisible(false);
	deleteItemB.setManaged(false);
	pauseB.setVisible(false);
	pauseB.setManaged(false);
	resumeB.setVisible(false);
	resumeB.setManaged(false);
	
	hBox.getChildren().addAll(homeB,addUrlB,deleteItemB,downloadB,pauseB,resumeB,settingB);
	hBox.setAlignment(Pos.CENTER);
	}
	
	public Button setImage(Button imageButton,String imageUrl){
		Image image=new Image(getClass().getResourceAsStream(imageUrl));
		imageButton.setGraphic(new ImageView(image));
		imageButton.setPrefSize(10,10);
		imageButton.setStyle("-fx-background-color:black");
		return imageButton;
	}
		
}

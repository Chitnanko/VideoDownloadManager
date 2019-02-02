package dm.ui;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class ToolBarBox extends HBox{
	
	TextField urlField=new TextField();
	
	Button goB=new Button(),
			refreshB=new Button(),
			downloadB=new Button("download");
	
	Label title=new Label("VDM ");
	Button		allB=new Button("All");
	Button		incompleteB=new Button("Incomplete");
	Button		completeB=new Button("Complete");
	
	HBox downloadOP=new HBox();
	HBox toolBox=new HBox();
	MenuBarBox menu=new MenuBarBox();
	
	Rectangle2D visualBounds=Screen.getPrimary().getVisualBounds();
	
	public ToolBarBox(){
		
		title.setFont(new Font(40));
		title.setTextFill(Color.web("#ffffff"));
		title.setWrapText(true);
		
		allB.setPrefWidth(100);
		allB.setPrefHeight(visualBounds.getHeight());
		allB.setStyle("-fx-background-color:white;-fx-color:white;-fx-font-size:15;");
		allB.setOnAction(e->{
			allB.setStyle("-fx-background-color:white;-fx-color:white;-fx-font-size:15;");
			incompleteB.setStyle("-fx-background-color:black;-fx-color:black;-fx-font-size:15;");
			completeB.setStyle("-fx-background-color:black;-fx-color:black;-fx-font-size:15;");
		});
		
		incompleteB.setPrefWidth(150);
		incompleteB.setPrefHeight(visualBounds.getHeight());
		incompleteB.setStyle("-fx-background-color:black;-fx-color:black;-fx-font-size:15;");
		incompleteB.setOnAction(e->{
			incompleteB.setStyle("-fx-background-color:white;-fx-color:white;-fx-font-size:15;");
			completeB.setStyle("-fx-background-color:black;-fx-color:black;-fx-font-size:15;");
			allB.setStyle("-fx-background-color:black;-fx-color:black;-fx-font-size:15;");
		});
		
		completeB.setPrefWidth(150);
		completeB.setPrefHeight(visualBounds.getHeight());
		completeB.setStyle("-fx-background-color:black;-fx-color:black;-fx-font-size:15;");
		completeB.setOnAction(e->{
			completeB.setStyle("-fx-background-color:white;-fx-color:white;-fx-font-size:15;");
			allB.setStyle("-fx-background-color:black;-fx-color:black;-fx-font-size:15;");
			incompleteB.setStyle("-fx-background-color:black;-fx-color:black;-fx-font-size:15;");
		});
		
		downloadOP.getChildren().addAll(allB,incompleteB,completeB);
		downloadOP.setStyle("-fx-background-color:black;-fx-color:black");
		downloadOP.setVisible(false);
		downloadOP.setManaged(false);
		goB=setImage(goB,"go.png");
		refreshB=setImage(refreshB,"refresh.png");
		
		urlField.setText("");
		HBox.setMargin(urlField,new Insets(40,0,0,0));
		HBox.setMargin(goB,new Insets(35,0,0,0));
		HBox.setMargin(refreshB,new Insets(35,0,0,0));
		HBox.setMargin(downloadB,new Insets(38,0,0,0));
		HBox.setMargin(title,new Insets(20,0,0,0));
		
		toolBox.getChildren().addAll(title,urlField,goB,refreshB,downloadB,menu.menuBox);
		downloadB.setVisible(false);
		downloadB.setManaged(false);
		HBox.setHgrow(title, Priority.NEVER);
		HBox.setHgrow(urlField, Priority.ALWAYS);
		HBox.setHgrow(goB, Priority.NEVER);
		HBox.setHgrow(refreshB,Priority.NEVER);
		HBox.setHgrow(menu.menuBox, Priority.NEVER);
		HBox.setHgrow(toolBox, Priority.ALWAYS);
	}
	public Button setImage(Button imageButton,String imageUrl){
		Image image=new Image(getClass().getResourceAsStream(imageUrl));
		imageButton.setGraphic(new ImageView(image));
		imageButton.setStyle("-fx-background-color:black");
		return imageButton;
	}

}

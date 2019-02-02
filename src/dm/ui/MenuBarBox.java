package dm.ui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class MenuBarBox extends HBox{
	
	MenuBar menuBar=new MenuBar();
	
	Menu menuFile=new Menu("File"),
			menuDownload=new Menu("Download"),
			menuView=new Menu("View"),
			menuTool=new Menu("Tool"),
			menuHelp=new Menu("Help");
	
	MenuItem addUrl=new MenuItem("Add Url"),
			deleteDownload=new MenuItem("Delete Download"),
			deleteComplete=new MenuItem("Delete Complete"),
			exit=new MenuItem("Exit");
	
	MenuItem pause=new MenuItem("Pause"),
			resume=new MenuItem("Resume"),
			restart=new MenuItem("Restart");
	
	MenuItem openFolder=new MenuItem("Open Folder"),
			saveAs=new MenuItem("Save As..."),
			copyUrl=new MenuItem("Copy Url"),
			copyFile=new MenuItem("Copy File");
	
	MenuItem option=new MenuItem("Options"),
			refresh=new MenuItem("Refresh Link");
	
	MenuItem contents=new MenuItem("Contents"),
			dmHome=new MenuItem("DM Home"),
			about=new MenuItem("About");
	HBox menuBox=new HBox();
			
	public MenuBarBox(){
		
		menuBar.getMenus().addAll(menuFile,menuDownload,menuView,menuTool,menuHelp);
		menuBar.setStyle("-fx-color:black;-fx-background-color:black;-fx-font-size:15;");
		
		menuFile.getItems().addAll(addUrl,deleteDownload,deleteComplete,exit);
		menuDownload.getItems().addAll(pause,resume,restart);
		menuView.getItems().addAll(openFolder,saveAs,copyUrl,copyFile);
		menuTool.getItems().addAll(option,refresh);
		menuHelp.getItems().addAll(contents,dmHome,about);
		
		menuBox.getChildren().addAll(menuBar);
		menuBox.setAlignment(Pos.TOP_RIGHT);
		
		exit.setOnAction(e->Platform.exit());
		about.setOnAction(e->{
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.setHeaderText("VDM");
			alert.setContentText("This software is developed by \n Chit Nan Ko and Ravi Kant."
					+ "\n This software version is beta version 1.0 now.");
			alert.show();
		});
		
	}

}

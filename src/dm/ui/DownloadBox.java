package dm.ui;

import java.util.Observable;
import java.util.Observer;

import dm.abstractclass.URLInfo;
import dm.download.Download;
import dm.download.DownloadManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Screen;

public class DownloadBox extends HBox{
	
	static int countListItem;//for inner class ListItem
	protected float fileSize;
	protected float finishedSize;
	protected String title;
	HBox category=new HBox();
	HBox DownloadSection=new HBox();
	ListView<String> categoryList=new ListView<String>();
	ListView<Label> downloadSectList=new ListView<>();
	HBox DBox=new HBox();
	DownloadManager dm;
	Rectangle2D visualBounds=Screen.getPrimary().getVisualBounds();
	
	
	
	public DownloadBox(){
		
		dm=DownloadManager.getInstance();
		categoryList.getItems().add("All Categories");
		categoryList.getItems().add("Documents");
		categoryList.getItems().add("Compressed");
		categoryList.getItems().add("Music");
		categoryList.getItems().add("Video");
		categoryList.getItems().add("Programs");
		//categoryList.setPrefWidth(visualBounds.getWidth()/4);
		downloadSectList.setPrefWidth(visualBounds.getWidth());
		category.getChildren().add(categoryList);
		DownloadSection.getChildren().add(downloadSectList);
		downloadSectList.setEditable(true);
		DBox.getChildren().addAll(DownloadSection);
		HBox.setHgrow(category, Priority.NEVER);
		HBox.setHgrow(DownloadSection, Priority.ALWAYS);
		HBox.setHgrow(DBox, Priority.ALWAYS);
		
		categoryList.setStyle("-fx-background-color:white;-fx-font-size:15;-fx-control-inner-background:rgb(238,232,232);"
				+ "-fx-control-inner-background-alt:derive(-fx-control-inner-background,10%)");
		downloadSectList.setStyle("-fx-background-color:white;-fx-control-inner-background:white;"
				+ "-fx-control-inner-background-alt:derive(-fx-control-inner-background,10%)");
		DBox.setVisible(false);
		DBox.setManaged(false);
	}
	
	
	public void update(int index,String st){
		
		System.out.println("i am downloadBox update");
		downloadSectList.getItems().get(index).setText(st);
	}
	
	public void addDownload(int index,Label label){
		System.out.println("i am download addDownload");
		downloadSectList.getItems().add(index,label);
	}
}

package dm.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;

import java.util.ArrayList;
import java.util.List;

import dm.abstractclass.URLInfo;
import dm.download.Download;
import dm.download.DownloadManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class DownloadLinkDialog {
	
	ProgressIndicator indicator;
	HBox hbox,bbox;
	ListView<String> listView;
	List<URLInfo> downloadList;
	List<String> downloadInfoList;
	Stage searchStage;
	Scene searchScene;
	String searching="searching download link .......";
	Rectangle2D listSize;
	VBox vbox;
	Button downloadB,cancelB;
	
	public DownloadLinkDialog(Window owner,Modality modility){
		
			listSize=Screen.getPrimary().getVisualBounds();
			searchStage=new Stage();
			searchStage.initModality(modility);
			searchStage.initOwner(owner);
			searchStage.setWidth(listSize.getWidth()/4);
			searchStage.setHeight(listSize.getHeight()/4);
			
			downloadInfoList=new ArrayList<String>();
			indicator=new ProgressIndicator(-1);
			
			listView=new ListView<String>();
			listView.setPrefSize(searchStage.getWidth(),(searchStage.getHeight()/4)*3);
			
			hbox=new HBox();
			bbox=new HBox();
			vbox=new VBox();
			downloadB=new Button("download");
			cancelB=new Button("cancel");
			bbox.getChildren().addAll(downloadB,cancelB);
			bbox.setPrefSize(searchStage.getWidth(),searchStage.getHeight()/4);
			bbox.setAlignment(Pos.CENTER);
			bbox.setSpacing(10);
			
			hbox.getChildren().add(indicator);
			indicator.setMinSize(100,100);;
			hbox.setAlignment(Pos.CENTER);
			vbox.getChildren().add(hbox);
			vbox.setAlignment(Pos.CENTER);
			searchScene=new Scene(vbox);
			searchStage.setTitle("Searching download links");
			searchStage.setScene(searchScene);
			searchStage.setResizable(false);
			searchStage.show();
			
			cancelB.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					searchStage.close();
					
				}
			});
	
	}
	
	/** start download here
	 * 
	 * @param index
	 */
	public void createDownload(int index,DownloadBox dBox){
		
		int size=downloadList.size();
		URLInfo info=downloadList.get(index);
		if(size!=0 && index<size){
			new DownloadFile(info.getDownloadUrl(),info.getTitle(),info.getFileType());
		}
	}
}

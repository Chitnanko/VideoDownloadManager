package dm.ui;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import dm.download.Download;
import dm.download.DownloadManager;
import javafx.application.Platform;
import javafx.scene.control.Label;;

public class DownloadFile implements Observer{
	
	private URL downloadUrl;
	private DownloadManager downloadManager;
	private Download download;
	public static String DEFAULT_OUT_FOLDER="/home/skin/Downloads/";
	private DmProgress dmProgress;
	private String status;
	private float percent;
	private DownloadBox downloadBox;
	private Label l;
	private String title;
	public DownloadFile(URL url,String title,String fileType){
		
		
		downloadUrl=url;
		this.title=title;
		downloadManager=DownloadManager.getInstance();
		download=downloadManager.createDownload(downloadUrl,DEFAULT_OUT_FOLDER,title
				  +fileType);
		download.addObserver(this);
		download.download();
		status=Download.STATUSES[download.getState()]+" "+download.getDownloadSize()+" of "+download.getSize();
		percent=download.getProgress();
		//System.out.println("main:"+percent);
		dmProgress=new DmProgress(status,title,this);
		l=new Label(title+"\n"+status);
		downloadBox.downloadSectList.getItems().add(l);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if(o.equals(download)){
			
		status=Download.STATUSES[download.getState()]+" "+download.getDownloadSize()+" of "+download.getSize();
		percent=download.getProgress();
		//System.out.println(percent+"%");
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				dmProgress.update(status, percent);
				l.setText(title+"\n"+status);

				if(Download.COMPLETED==download.getState()){
					dmProgress.closeStage();
					//download.
				}
				
				
			}
			
		});
		
	   }
		
	}
	
	public void pause(){
		download.pause();
	}

	public void cancel(){
		download.cancel();
	}
	
	public void resume(){
		download.resume();
	}
	
	public static void setDownloadBox(DownloadBox box){
		
	}
}

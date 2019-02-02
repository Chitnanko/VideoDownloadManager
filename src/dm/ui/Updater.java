package dm.ui;

import java.util.Observable;
import java.util.Observer;

import dm.download.Download;

public class Updater implements Observer {
	
	DmProgress dmProgress;
	Download download;
	String status;
	float percent;

	public Updater(Observable o,DmProgress progress){
		
		download=(Download)o;
		dmProgress=progress;
		status=Download.STATUSES[download.getState()]+download.getFileSize()+download.getDownloaded();
		percent=download.getProgress();
	}
	@Override
	public void update(Observable o, Object arg) {
		
	}

}

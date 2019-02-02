package dm.download;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;

public abstract class Download extends Observable implements Runnable{
	
		static long Kb=1*1000;
		static long Mb=Kb*1000;
		static long Gb=Mb*1000;
		
		// Member variables
		/** The URL to download the file */
		protected URL Url;
		
		/** Output folder for downloaded file */
		protected String outputFolder;
		
		/** Number of connections (threads) to download the file */
		protected int numConnections;
		
		/** The file name, extracted from URL */
		protected String fileName;
		
		/** Size of the downloaded file (in bytes) */
		protected float fileSize;
		
		/** The state of the download */
		protected int state;
		
		/** downloaded size of the file (in bytes) */
		protected float downloaded;
		
		/** List of download threads */
		protected ArrayList<DownloadThread> listDownloadThread;
		
		// Contants for block and buffer size
		protected static final int BLOCK_SIZE = 4096;
		protected static final int BUFFER_SIZE = 4096;
		protected static final int MIN_DOWNLOAD_SIZE = BLOCK_SIZE * 100;
		
		// These are the status names.
	    public static final String STATUSES[] = {"Downloading",
	    				"Paused", "Complete", "Cancelled", "Error"};
		
		// Contants for download's state
		public static final int DOWNLOADING = 0;
		public static final int PAUSED = 1;
		public static final int COMPLETED = 2;
		public static final int CANCELLED = 3;
		public static final int ERROR = 4;
		
		/**
		 * Constructor
		 * @param fileURL
		 * @param outputFolder
		 * @param fileName
		 * @param numConnections
		 */
		protected Download(URL url, String outputFolder,String fileName, int numConnections) {
			Url = url;
			this.outputFolder = outputFolder;
			this.numConnections = numConnections;
			
			// set output fileName
			this.fileName = fileName;
			fileSize = -1;
			state = DOWNLOADING;
			downloaded = 0;
			
			listDownloadThread = new ArrayList<DownloadThread>();
		}
		
		/**
		 * Pause the downloader
		 */
		public void pause() {
			
			setState(PAUSED);
		}
		
		/**
		 * Resume the downloader
		 */
		public void resume() {
			setState(DOWNLOADING);
			download();
		}
		
		/**
		 * Cancel the downloader
		 */
		public void cancel() {
			setState(CANCELLED);
		}
		
		/**
		 * Get the URL (in String)
		 */
		public String getURL() {
			return Url.toString();
		}
		
		/**
		 * Get the downloaded file's size
		 */
		public float getFileSize() {
			return fileSize;
		}
		
		/**
		 * Get the current progress of the download
		 */
		public float getProgress() {
			return ((float)downloaded / fileSize/2);
		}
		/**
		 * 
		 * @return
		 */
		public float getDownloaded(){
			System.out.println(downloaded+" of "+fileSize);
			return downloaded;
		}
		
		public String getSize(){
			String size="0 bytes";
			float sizeInByte=getFileSize();
			size=unitConvector(sizeInByte);
			return size;
		}
		/**
		 * 
		 * @return String
		 */
		public String getDownloadSize(){
			String size="0 bytes";
			float sizeInByte=getDownloaded()/2;
			System.out.println(sizeInByte);
			size=unitConvector(sizeInByte);
			return size;
		}
		/**
		 * 
		 * @param sizeInByte
		 * @return
		 */
		private String unitConvector(float sizeInByte){
			String size="0 bytes";
			if(sizeInByte<Kb){
				return size=sizeInByte+" bytes";
			}
			if(sizeInByte>=Kb && sizeInByte<Mb){
				return size=getFormat(sizeInByte/Kb)+" kb";
			}
			if(sizeInByte>=Mb && sizeInByte<Gb){
				return size=getFormat(sizeInByte/Mb)+" Mb";
			}
			if(sizeInByte>=Gb){
				return size=getFormat(sizeInByte/Gb)+" Gb";
			}
			return size;
		}
		/**
		 * 
		 * @param value
		 * @return
		 */
		public float getFormat(float value){
			DecimalFormat df=new DecimalFormat("#.##");
			float f=Float.valueOf(df.format(value));
			return f;
		}
		
		/**
		 * Get current state of the downloader
		 */
		public int getState() {
			return state;
		}
		
		/**
		 * Set the state of the downloader
		 */
		protected void setState(int value) {
			state = value;
			stateChanged();
		}
		
		/**
		 * Start or resume download
		 */
		public void download() {
			Thread t = new Thread(this);
			t.start();
		}
		
		/**
		 * Increase the downloaded size
		 */
		protected synchronized void downloaded(int value) {
			downloaded += value;
			stateChanged();
		}
		
		/**
		 * combine part file into one
		 */
		public abstract boolean join();
		
		/**
		 * Set the state has changed and notify the observers
		 */
		protected void stateChanged() {
			setChanged();
			notifyObservers();
		}
		
		/**
		 * Thread to download part of a file
		 */
		protected abstract class DownloadThread implements Runnable {
			protected int mThreadID;
			protected URL Url;
			protected String mOutputFile;
			protected int startByte;
			protected float endByte;
			protected boolean isFinished;
			protected Thread thread;
			
			public DownloadThread(int threadID, URL url, String outputFile, int startByte, float endByte) {
				mThreadID = threadID;
				Url = url;
				mOutputFile = outputFile;
				this.startByte = startByte;
				this.endByte = endByte;
				isFinished = false;
				
				download();
			}
			
			/**
			 * Get whether the thread is finished download the part of file
			 */
			public boolean isFinished() {
				return isFinished;
			}
			
			/**
			 * Start or resume the download
			 */
			public void download() {
				thread = new Thread(this);
				thread.start();
			}
			
		
			/**
			 * Waiting for the thread to finish
			 * @throws InterruptedException
			 */
			public void waitFinish() throws InterruptedException {
				thread.join();			
			}
			
		}
	}


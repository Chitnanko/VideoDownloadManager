package dm.download;
import java.net.URL;
import java.util.ArrayList;

public class DownloadManager {
	
	// The unique instance of this class
	private static DownloadManager instance = null;
	
	// Constant variables
	private static final int DEFAULT_NUM_CONN_PER_DOWNLOAD = 8;
	public static final String DEFAULT_OUTPUT_FOLDER = "";

	// Member variables
	private int numConnPerDownload;
	private ArrayList<Downloader> downloadList;
	
	/** Protected constructor */
	protected DownloadManager() {
		numConnPerDownload = DEFAULT_NUM_CONN_PER_DOWNLOAD;
		downloadList = new ArrayList<Downloader>();
	}
	
	/**
	 * Get the max. number of connections per download
	 */
	public int getNumConnPerDownload() {
		return numConnPerDownload;
	}
	
	/**
	 * Set the max number of connections per download
	 */
	public void SetNumConnPerDownload(int value) {
		numConnPerDownload = value;
	}
	
	/**
	 * Get the downloader object in the list
	 * @param index
	 * @return
	 */
	public Downloader getDownload(int index) {
		return downloadList.get(index);
	}
	
	public void removeDownload(int index) {
		downloadList.remove(index);
	}
	
	/**
	 * Get the download list
	 * @return
	 */
	public ArrayList<Downloader> getDownloadList() {
		return downloadList;
	}
	
	
	public Download createDownload(URL verifiedURL, String outputFolder,String fileName) {
		Downloader fd = new Downloader(verifiedURL, outputFolder,fileName, numConnPerDownload);
		downloadList.add(fd);
		
		return fd;
	}
	
	/**
	 * Get the unique instance of this class
	 * @return the instance of this class
	 */
	public static DownloadManager getInstance() {
		if (instance == null)
			instance = new DownloadManager();
		
		return instance;
	}
	
	/**
	 * Verify whether an URL is valid
	 * @param fileURL
	 * @return the verified URL, null if invalid
	 */
	public static URL verifyURL(String fileURL) {
		// Only allow HTTP URLs.
        if (!fileURL.toLowerCase().startsWith("http[s]?://"))
            return null;
        
        // Verify format of URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(fileURL);
        } catch (Exception e) {
            return null;
        }
        
        // Make sure URL specifies a file.
        if (verifiedUrl.getFile().length() < 2)
            return null;
        
        return verifiedUrl;
	}

}

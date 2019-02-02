package dm.download;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Downloader extends Download{
	
	public Downloader(URL url, String outputFolder,String fileName, int numConnections) {
		super(url, outputFolder,fileName, numConnections);
		download();
	}
	
	private void error() {
		System.out.println("ERROR");
		setState(ERROR);
	}
	
	@Override
	public void run() {
		HttpURLConnection conn = null;
		try {
			// Open connection to URL
			conn = (HttpURLConnection)Url.openConnection();
			conn.setConnectTimeout(10000);
			
			// Connect to server
			conn.connect();
			
			// Make sure the response code is in the 200 range.
           /* if (conn.getResponseCode() / 100 != 2) {
                error();
            }*/
            
            // Check for valid content length.
            float contentLength = conn.getContentLengthLong();
            if (contentLength < 1) {
                error();
            }
			
            if (fileSize == -1) {
            	fileSize = contentLength;
            	stateChanged();
            	System.out.println("File size: " + fileSize);
            }
               
            // if the state is DOWNLOADING (no error) -> start downloading
            if (state == DOWNLOADING) {
            	// check whether we have list of download threads or not, if not -> init download
            	if (listDownloadThread.size() == 0)
            	{  
            		/*if (fileSize > MIN_DOWNLOAD_SIZE) {
		                // downloading size for each thread
						int partSize = Math.round(((float)fileSize / numConnections) / BLOCK_SIZE) * BLOCK_SIZE;
						System.out.println("Part size: " + partSize);
						
						// start/end Byte for each thread
						int startByte = 0;
						int endByte = partSize - 1;
						HttpDownloadThread aThread = new HttpDownloadThread(1, Url, outputFolder +"vdm/"+ fileName+".part1", startByte, endByte);
						listDownloadThread.add(aThread);
						int i = 2;
						String partFile=fileName;
						while (endByte < fileSize) {
							startByte = endByte + 1;
							endByte += partSize;
							String part=partFile+".part"+i;
							aThread = new HttpDownloadThread(i, Url, outputFolder+"vdm/"+part, startByte, endByte);
							listDownloadThread.add(aThread);
							++i;
						}
            		} else
            		{*/
            			HttpDownloadThread aThread = new HttpDownloadThread(1, Url, outputFolder + fileName, 0, fileSize);
						listDownloadThread.add(aThread);
            		//}
            	} else { // resume all downloading threads
            		for (int i=0; i<listDownloadThread.size(); ++i) {
            			if (!listDownloadThread.get(i).isFinished())
            				listDownloadThread.get(i).download();
            		}
            	}
				
				// waiting for all threads to complete
				for (int i=0; i<listDownloadThread.size(); ++i) {
					listDownloadThread.get(i).waitFinish();
				}
				// check the current state again
				if (state == DOWNLOADING) {
					
					join();
					System.out.println("join finish");
					setState(COMPLETED);
				}
            }
		} catch (Exception e) {
			error();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
	}
	
	/**
	 * Thread using Http protocol to download a part of file
	 */
	private class HttpDownloadThread extends DownloadThread {
		
		/**
		 * Constructor
		 * @param threadID
		 * @param url
		 * @param outputFile
		 * @param startByte
		 * @param endByte
		 */
		public HttpDownloadThread(int threadID, URL url, String outputFile, int startByte, float endByte) {
			super(threadID, url, outputFile, startByte, endByte);
		}

		@Override
		public void run() {
			BufferedInputStream in = null;
			RandomAccessFile raf = null;
			
			try {
				// open Http connection to URL
				HttpURLConnection conn = (HttpURLConnection)Url.openConnection();
				
				// set the range of byte to download
				String byteRange = startByte + "-" + endByte;
				//conn.setRequestProperty("Range", "bytes=" + byteRange);
				//System.out.println("bytes=" + byteRange);
				
				// connect to server
				conn.connect();
				
				// Make sure the response code is in the 200 range.
	           /* if ((conn.getResponseCode()) / 100 != 2) {
	            	System.out.println(conn.getResponseCode());
	                error();
	            }*/
				
				// get the input stream
				in = new BufferedInputStream(conn.getInputStream());
				
				// open the output file and seek to the start location
				raf = new RandomAccessFile(mOutputFile, "rw");
				//raf.seek(startByte);
				
				byte data[] = new byte[BUFFER_SIZE];
				int numRead;
				while((state == DOWNLOADING) && ((numRead = in.read(data,0,BUFFER_SIZE)) != -1))
				{
					// write to buffer
					raf.write(data,0,numRead);
					// increase the startByte for resume later
					startByte += numRead;
					//System.out.println(numRead);
					// increase the downloaded size
					downloaded(numRead);
				}
				
				
				
				if (state == DOWNLOADING && state!=PAUSED) {
						isFinished = true;
				}
			} catch (IOException e) {
				System.out.println("i am here");
				e.printStackTrace();
				error();
			} finally {
				if (raf != null) {
					try {
						raf.close();
					} catch (IOException e) {}
				}
				
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {}
				}
			}
			
			System.out.println("End thread " + mThreadID);
		}
	}
	
	/**
	 * @return boolean
	 * 
	 */

	@Override
	public boolean join() {
		int count=1;
		int data=0;
		int numFile=0;
		String part=".part";
		try{
			String f=outputFolder+fileName;
			File file=new File(f);
			String partFolder=outputFolder+"vdm/"+fileName;
			RandomAccessFile outFile=new RandomAccessFile(file,"rw");
			while(true){
				String path=partFolder+part+count;
				file=new File(path);
				if(file.exists()){
					
					RandomAccessFile readFile=new RandomAccessFile(file,"r");
					data=readFile.read();
					while(data!=-1){
						outFile.write(data);
						data=readFile.read();
					}
					numFile++;
					count++;
					readFile.close();
					Files.delete(Paths.get(path));
					
				}else break;
			}
			outFile.close();
			System.out.println("num of file:"+numFile);
			
				
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
}

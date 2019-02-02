package dm.download;

import java.net.MalformedURLException;
import java.net.URL;

public class Test {
	public static void main(String args[]) throws MalformedURLException{
		DownloadManager dm=new DownloadManager();
		URL url=new URL("https://r3---sn-h2uht5o-gboe.googlevideo.com/videoplayback?"
				+ "mime=audio%2Fwebm&key=yt6&ei=j2l3WZKAO9fOoAO_077oCA&expire=1501019632&signature"
				+ "=ADBF40CB4D9D674E0E59CA545C915457C73FFCA9.1F7C12C0CDC875C90459A55F0EBCB6F272EF39AB"
				+ "&initcwndbps=1562500&ip=203.81.71.25&mm=31&itag=250&ipbits=0&id=o-AIT9sWeEAim0craXZc9"
				+ "_hwI908PqhO2zYP984KCKfyMk&keepalive=yes&pl=22&ms=au&mt=1500997934&mv=m&source=youtube&"
				+ "requiressl=yes&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2C"
				+ "keepalive%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cexpire&gir=yes&c"
				+ "len=29881119&dur=4392.741&lmt=1500540973625208&mn=sn-h2uht5o-gboe&signature=ADBF40CB4D9D6"
				+ "74E0E59CA545C915457C73FFCA9.1F7C12C0CDC875C90459A55F0EBCB6F272EF39AB");
		Download downloader=dm.createDownload(url,"/home/skin/Desktop/","testdownload.mp4");
		downloader.download();
	}

}

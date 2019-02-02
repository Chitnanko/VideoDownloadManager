package dm.info;

import java.net.URL;

import dm.abstractclass.URLInfo;

public class DailymotionInfo extends URLInfo{
	
	public enum DailymotionQuality{
		p1080,p720,p480,p384,p240,p144;
	}
	
	private DailymotionQuality dq;
	
	public DailymotionQuality getDailymotionQuality(){
		return this.dq;
	}
	public void setDailymotionQuality(DailymotionQuality dq){
		this.dq=dq;
	}
	
	public DailymotionInfo(URL webUrl){
		
		super(webUrl);
		super.setSite(Site.DAILYMOTION);
	}

}

package dm.info;

import java.net.URL;

import dm.abstractclass.URLInfo;

public class FacebookInfo extends URLInfo{
	
	public FacebookInfo(URL webUrl){
		super(webUrl);
		super.setSite(Site.FACEBOOK);
	}
	
}

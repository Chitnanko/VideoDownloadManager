package dm.info;

import java.net.URL;

import dm.abstractclass.URLInfo;

public class VimeoInfo extends URLInfo{
	
	public enum VimeoQuality{
		p1080,p720,p540,p360;
	}
	
	public VimeoInfo(URL webUrl){
		super(webUrl);
		super.setSite(Site.VIMEO);
	}
	
	// vimeo video quality
	private VimeoQuality vq;
	
	public VimeoQuality getVimeoQuality(){
		return this.vq;
	}
	
	public void setVimeoQuality(VimeoQuality vq){
		this.vq=vq;
	}
	

}

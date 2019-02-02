package dm.abstractclass;

import java.net.URL;
import java.util.List;

public abstract class Parser {
	
	/**
	 * 
	 * @param webUrl
	 * @return
	 */
	public abstract URLInfo info(URL webUrl);
	
	
	/**
	 * 
	 * @param vinfo
	 * @return
	 */
	public abstract List<URLInfo> extract(final URLInfo vinfo);
	

}

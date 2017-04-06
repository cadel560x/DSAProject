package ie.gmit.java2.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import ie.gmit.java2.parser.Parser;


public class UrlParser extends Parser {
//	 Fields
	private URL site;
	private HttpsURLConnection secSite;
	private InputStream is;
	private MalformedURLException mue;

	
//	 Constructor
	public UrlParser(String site) throws IOException {
		if ( site.startsWith("http://") ) {
			this.site = new URL(site);
			is = this.site.openStream();
		}
		else if ( site.startsWith("https://") ) {
			this.site =  new URL(site);
			secSite = (HttpsURLConnection)this.site.openConnection();
			is = secSite.getInputStream();
		}
		else {
			mue = new MalformedURLException();
			throw mue;
		}
//			(BufferedReader) 'br' is inherited from the parent abstract class 'Parser' with 'protected' access mode
			br = new BufferedReader(new InputStreamReader(is));
			parse(br);

	}


}

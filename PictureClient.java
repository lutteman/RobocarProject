package carProject;

public class PictureClient extends ClientClass {
	
	String pictureUrl;
	int identifier;
	
	public PictureClient() {
		pictureUrl = "somepictureadress";
		//Identifier so that server will know what type of class it is
		identifier = 2;
	}
	/**
	 * 
	 * @return a link to the current picture to be displayed
	 */
	public String getPictureUrl() {
	return pictureUrl;
	}
}

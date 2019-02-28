package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Login {

	/**
	 * 
	 * Method for finding the password for a User.
	 * 
	 * @param s is the inputed User we are searching its password for.
	 * @return password if found else null
	 * @throws IOException
	 */
	public String getPwforUser(String s) throws IOException {
		String csvFile = "C:\\Users\\anton\\Desktop\\testcred3.csv";
		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				String[] text = line.split(",");

				if (text[0].contentEquals(s)) {
					return text[1];
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		br.close();
		return null;
	}

	/**
	 * A method for checking that the entered password for a user is the same as the
	 * password in the csv file.
	 *
	 * @param pwInput is the user inputed password
	 * @param pwFile  is the found password for the user
	 * @return the result of the credentials check
	 */
	public boolean checkCredentials(String pwInput, String pwFile) {
		return (pwInput.equals(pwFile));

	}

}

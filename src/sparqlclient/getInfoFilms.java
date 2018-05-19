package sparqlclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;

public class getInfoFilms {
	
	// Reste à parser le json ( requette HTTP fonctionnel )
	
	public static final String KEY = "c9579896f44ca1412cb66e548f39711";
	
	//request api example : https://api.themoviedb.org/3/search/movie?api_key=f&query=<movie_name>
    public static void get_json_tmb() throws IOException {
    	// GET HTTP
    	URL obj = new URL("https://api.themoviedb.org/3/search/movie?api_key=c9579896f44ca1412cb66e548f39711f&query=COUP+DE+FOUDRE+A+JAIPUR");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		
		
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			//JsonObject json = con.getInputStream();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			//String inputLine;
			//StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			//System.out.println(json);
			//in.close();

			// print result
			//System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}
	

}

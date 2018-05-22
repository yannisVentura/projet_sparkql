package sparqlclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONObject; // penser à rajouter la bibliothèque json-20141113 disponible dans le dossier lib


public class OMDBProxy {
	private final String OMDBKEY = "85c498d8";
	private final String baseUrl = "http://www.omdbapi.com/?apikey=" + OMDBKEY + "&plot=short&r=json";//base de l'url correspondant à la requête get qui devra être compléter avec le nom du film à considérer
	
	public OMDBProxy()
	{
		
	}
	private HashMap<String, String> movieInfos;
	
	public boolean loadMovieInfos(String movieTitle, int year)
	{ //permet pour un titre de film de récupérer un hachage contenant les couples (propriété du film / valeur) retournés par OMDB
		HashMap<String, String> ret = new HashMap<>();
		
		 URL url;
	      HttpURLConnection conn;
	      BufferedReader rd;
	      String line;
	      String result = "";
	      try {
	         url = new URL(this.baseUrl + "&t=" + URLEncoder.encode(movieTitle, "UTF-8") + "&y=" + year);
	         System.out.println(this.baseUrl + "&t=" + URLEncoder.encode(movieTitle, "UTF-8") + "&y=" + year);
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;
	         }
	         rd.close();
         JSONObject obj = new JSONObject(result);
         for(String key : obj.keySet())
         {
        	 String val = new String(obj.get(key).toString().getBytes(), "UTF-8");
        	 ret.put(key, val);
         }
	         
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		movieInfos = ret;
		return !movieInfos.containsKey("Error");
		
	}

	public int getRuntime() {
		
		try
		{
			String runtime = movieInfos.get("Runtime").split(" ")[0];
			return Integer.parseInt(runtime);
		}
		catch (Exception e)
		{
			return 0;
		}
	}
	
	public String[] getActors()
	{	
		return movieInfos.get("Actors").split(",");
	}
	
	public String[] getGenres()
	{	
		return movieInfos.get("Genre").split(",");
	}
	
	public String getBudget()
	{
		return movieInfos.get("BoxOffice");
	}
	
	public String getPlot()
	{	
		return movieInfos.get("Plot");
	}
	
	public String[] getPays()
	{	
		return movieInfos.get("Country").split(",");
	}
	
	public float getNote()
	{
		try
		{
			return Float.parseFloat(movieInfos.get("imdbRating"));
		}
		catch (Exception e)
		{
			return 0;
		}
	}
	
	public String getImdbID()
	{
		return movieInfos.get("imdbID");
	}
}

package sparqlclient;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ConvertCSVParis {

	public static String Request() throws IOException
	{
		System.out.println("Prepare request: ");
		Reader in = new FileReader("bin\\sparqlclient\\paris_film.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		StringBuffer request = new StringBuffer();
		request.append(
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "prefix : <http://www.w3.org/projet/individu#>\n");
		request.append("INSERT { \n");
		for (CSVRecord record : records) {
			System.out.print(".");
			// Titre du film.
			String titre = new String(record.get("titre").getBytes(), "UTF-8");
			String filmID = titre.replaceAll("[\\W]|_", "");
			request.append(":" + filmID + " rdfs:label \"" + titre + "\".\n");
			
			// Realisateur du film.
			String realisateur = new String(record.get("realisateur").getBytes(), "UTF-8");
			String realID = realisateur.replaceAll("[\\W]|_", "");
			request.append(":" + filmID + " ?real :" + realID + ".\n");
			request.append(":" + realID + " rdfs:label \"" + realisateur + "\".\n");
			
			// Addresse du film. 
			String adresse = new String(record.get("adresse").getBytes(), "UTF-8");
			String adresseID = adresse.replaceAll("[\\W]|_", "");
			request.append(":" + filmID + " ?adresse :" + adresseID + ".\n");
			request.append(":" + adresseID + " rdfs:label \"" + adresse + "\".\n");
			
			// Données chargées depuis OMDB.
			if(record.get("type_de_tournage") == "LONG METRAGE")
				request.append(OMDB(titre, record.get("date_debut")));
		}
		request.append("}\nWHERE {"
						+ "\n?real rdfs:label \"a pour réalisateur\"@fr.\n"
						+ "?adresse rdfs:label \"se déroule à\"@fr.\n"
						+ "?duree rdfs:label \"a pour durée\"@fr.\n"
						+ "?recette rdfs:label \"a pour recette\"@fr.\n"
						+ "?acteur rdfs:label \"a joué dans\"@fr.\n"
						+ "?genre rdfs:label \"a pour genre\"@fr.\n"
						+ "?note rdfs:label \"a pour note\"@fr.\n"
						+ "}");
		System.out.print("\n");
		return request.toString();
	}
	
	public static String OMDB(String movieTitle, String year)
	{		
		// Get OMDB informations.
		OMDBProxy omdb = new  OMDBProxy();
		int annee = 0;
		try
		{
			annee = Integer.parseInt(year.substring(0, year.indexOf("-")));
		}
		catch (Exception e)
		{
			System.out.println("Failed to parse année.");
		}
		if(!omdb.loadMovieInfos(movieTitle, annee))
		{
			return "";
		}
		StringBuffer request = new StringBuffer();
		String filmID = movieTitle.replaceAll("\\s+", "");
		request.append(":" + filmID + " ?duree " + omdb.getRuntime() + ";");
		request.append(":" + filmID + " ?recette \"" + omdb.getBudget() + "\";");
		request.append(":" + filmID + " ?note " + omdb.getNote() + ".\n");
		
		// Acteurs du film.
		String[] acteurs = omdb.getActors();
		for (int i = 0; i < acteurs.length; i++)
		{
			String acteurID = acteurs[i].replaceAll("\\s+", "");
			request.append(":" + acteurID + " ?acteur :" + filmID + ".\n");
			request.append(":" + acteurID + " rdfs:label \"" + acteurs[i] + "\".\n");
		}
		
		// Genres du film.
		String[] genres = omdb.getGenres();
		for (int i = 0; i < genres.length; i++)
		{
			String genreID = genres[i].replaceAll("\\s+", "");
			request.append(":" + filmID + " ?genre :" + genreID + ".\n");
			request.append(":" + genreID + " rdfs:label \"" + genres[i] + "\".\n");
		}
		
		
		return request.toString();
	}
	
}
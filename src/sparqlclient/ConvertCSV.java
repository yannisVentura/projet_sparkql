package sparqlclient;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ConvertCSV {
	public static final String PREFIX = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
			"prefix : <http://www.w3.org/projet/individu#>\n"; 
	public static String RequestReal() throws IOException
	{
		Reader in = new FileReader("D:\\ProjetWebSem\\src\\download.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		StringBuffer request = new StringBuffer();
		int iLine = 1;
		request.append(PREFIX +"INSERT { \n");
		for (CSVRecord record : records) {
			request.append(":F" + iLine + " ?real :R" + iLine + ".\n");
			request.append(":F" + iLine + " rdfs:label \"" + record.get("titre") + "\".\n");
			request.append(":R" + iLine + " rdfs:label \"" + record.get("realisateur") + "\".\n");
			iLine++;
		}
		request.append("}\nWHERE {\n?real rdfs:label \"a pour réalisateur\"@fr.\n}");
		return request.toString();
	}
	
	public static String RequestAdresseTournage() throws IOException
	{
		Reader in = new FileReader("D:\\ProjetWebSem\\src\\download.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		StringBuffer request = new StringBuffer();
		int iLine = 1;
		request.append(PREFIX +"INSERT { \n");
		for (CSVRecord record : records) {
			request.append(":F" + iLine + " ?real :A" + iLine + ".\n");
			request.append(":F" + iLine + " rdfs:label \"" + record.get("titre") + "\".\n");
			request.append(":A" + iLine + " rdfs:label \"" + record.get("adresse") + "\".\n");
			iLine++;
		}
		request.append("}\nWHERE {\n?real rdfs:label \"se déroule à\"@fr.\n}");
		return request.toString();
	}
}

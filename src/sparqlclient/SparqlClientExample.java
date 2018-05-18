package sparqlclient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
//import sparqlclient.getInfoFilms;

public class SparqlClientExample {

    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String args[]) throws IOException {
  //  	getInfoFilms.get_json_tmb();
        
    	/*SparqlClient sparqlClient = new SparqlClient("127.0.0.1:3030/films");
        String query = "ASK WHERE { ?s ?p ?o }";
        boolean serverIsUp = sparqlClient.ask(query);
        if (serverIsUp) {
        	
        	query = ConvertCSV.Request();
        	System.out.println(query);
            sparqlClient.update(query);

          //  Iterable<Map<String, String>> results = sparqlClient.select(query);
            
            //System.out.println(results);
        } else {
            System.out.println("service is DOWN");
        }*/
    }
    
    
	
}

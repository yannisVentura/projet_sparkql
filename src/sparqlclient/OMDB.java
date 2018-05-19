package sparqlclient;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class OMDB {
	
	public static void main(String args[])
   {
	System.out.println("IMDB........................................");
	OMDBProxy omdbProxy = new OMDBProxy();
     System.out.println(omdbProxy.getMovieInfos("the artist").get("imdbRating"));
   
   }

}

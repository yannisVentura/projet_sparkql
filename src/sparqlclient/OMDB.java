package sparqlclient;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class OMDB {
	
	public static void main(String args[])
   {
     OMDBProxy omdbProxy = new OMDBProxy();
     System.out.println(omdbProxy.getMovieInfos("the artist").get("imdbRating"));
   
   }

}

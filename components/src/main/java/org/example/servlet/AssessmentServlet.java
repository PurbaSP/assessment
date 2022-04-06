package org.example.servlet;
import org.hippoecm.hst.site.HstServices;
import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssessmentServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5202948823341259709L;
	private static Logger log = LoggerFactory.getLogger(AssessmentServlet.class);
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Repository repository = HstServices.getComponentManager().getComponent(Repository.class.getName());
	Session session = null;
	PrintWriter printWriter =  response.getWriter();

	
	printWriter.println("The assessment servlet is up and running \n \n");   		 

	try {
	     String searchText =request.getParameter("searchText"); 


		session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));	
	    QueryManager queryManager = session.getWorkspace().getQueryManager();
	    Node rootNode = session.getRootNode();	
	 
	  
	    Node startingNode=  rootNode.getNode("content/documents/");
	    printWriter.println("The Descendant Nodes under Starting Node " + startingNode.getPath() + " are as follows  : ");
	    findRecursivelyDescendantNodes(startingNode,null,printWriter);
	    
		
	    if(searchText  != null )
	    searchTextInRepository (queryManager , printWriter , searchText );		 
	 
	} catch (RepositoryException e) {
        log.error("doGet Http method failed  "+  e);

} finally {
	
	printWriter.flush();
	printWriter.close();

}
	}
	

public void	searchTextInRepository( QueryManager queryManager , PrintWriter printWriter , String searchString) 
	{
	 if(searchString.length() == 0 )
	 
		 printWriter.println("\nPlease provide an valid input ");

	 else {
     String expression =     "//*[jcr:contains(., '"+ searchString +"')]";
     
	try {
		Query query = queryManager.createQuery(expression,Query.XPATH);
		   QueryResult result = query.execute();
			 NodeIterator nodeIterator = result.getNodes();
			 if(nodeIterator.hasNext()) {
	
			 printWriter.println( "\nNodes and thier properties which contains the search text  [ "+ searchString  + " ] ");
			   while ( nodeIterator.hasNext() ) {
		           Node resultNode = nodeIterator.nextNode();
		     	    printWriter.println("\nNode : " +resultNode.getName() + " Properties :" );
		     	   PropertyIterator propIterator=  resultNode.getProperties();
		     	    while(propIterator.hasNext())
		     	    {
		     	    	printWriter.println(   propIterator.nextProperty().getName() ) ;
		     	    	
		     	    }
			}
			 }
			 else
				 printWriter.println( "\nNo Node contains the requested text  [ "+ searchString  + " ] ");

	} catch (RepositoryException e) {
        log.error("searching of user defined text in the repository failed  "+  e);
	}
  
	
	 }
}


	
	 public  ArrayList<Node> findRecursivelyDescendantNodes(Node startingNode,  ArrayList<Node> nodesFound , PrintWriter printWriter) throws IOException {
	        if(nodesFound  == null)
	        	nodesFound  = new ArrayList<Node>();	             
	        try{   
	        	NodeIterator nodeiterator= startingNode.getNodes();
	            while(nodeiterator.hasNext())   {
	                Node currentSubNode = nodeiterator.nextNode();             
	                if(!currentSubNode.isNodeType("hippofacnav:facetnavigation")) {            
	                	printWriter.println(currentSubNode.getName());
	        		    nodesFound.add(currentSubNode);
	                    findRecursivelyDescendantNodes(currentSubNode, nodesFound ,printWriter);
	                }
	            }
	            return nodesFound ;
	        } catch (RepositoryException rpe){
	            log.error("Recursive listing of nodes with the search text API failed " + rpe);
	        }
	        return null;
	    }	
	}
	
	

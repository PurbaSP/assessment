package org.example.restapiservices.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.example.restapiservices.model.RepositoryNode;
import org.hippoecm.hst.site.HstServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NodeRepositoryDAO {
	
	private static Logger log = LoggerFactory.getLogger(NodeRepositoryDAO.class);
	
	public static  String getNodeDetailsbyUuid(String uuid)
	{
	     String expression =     " //*[@jcr:uuid = '"+ uuid +"'] ";
	     String nodeName = null;
		try {
			   QueryResult result = executeQueryOnRepository(expression);
				 NodeIterator nodeIterator = result.getNodes();
				 if(nodeIterator.hasNext()) {	
					 log.info( "\nNode exists with the uuid   [ "+ uuid  + " ] ");
				   while ( nodeIterator.hasNext() ) {
					   nodeName = nodeIterator.nextNode().getName();
			    	 }
				 }
				 else
					 log.info( "\nNo Node contains the requested uuid  [ "+ uuid  + " ] ");

		} catch (RepositoryException e) {
	        log.error(" Fetching Node detais by uuid failed  "+  e);
		}
		return nodeName;
		// TODO return the node object instead of just the nodeNAme
	}
	
	
	public static  List<String> getNodeDetailsbyName(String name)
	{
	
		   String expression =     " //"+ name +" ";
		     List<String> resultlist = new ArrayList<String>();
			try {
				   QueryResult result = executeQueryOnRepository(expression);
					 NodeIterator nodeIterator = result.getNodes();
					 if(nodeIterator.hasNext()) {	
						 log.info( "\nNode exists with the name   [ "+ name  + " ] ");
					   while ( nodeIterator.hasNext() ) {
						   resultlist.add( nodeIterator.nextNode().getPath());
				    	 }
					 }
					 else
						 log.info( "\nNo Node contains the requested name  [ "+name   + " ] ");

			} catch (RepositoryException e) {
		        log.error(" Fetching Node detais by name failed  "+  e);
			}
			return resultlist;
			// TODO return the node object instead of just the nodepaths
		
	}
	
	public static QueryResult executeQueryOnRepository( String expression )
	{
		Repository repository = HstServices.getComponentManager().getComponent(Repository.class.getName());
		Session session = null;
		QueryResult result = null;
		try {
			log.info("execute jcr xpath query = "+ expression);
		session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
		QueryManager  queryManager = session.getWorkspace().getQueryManager();
		Query query = queryManager.createQuery(expression,Query.XPATH);
		 result = query.execute();

		} catch (RepositoryException e) {
	        log.error(" queryExecute method failed "+  e);

		}
		return result;
	}
	
}
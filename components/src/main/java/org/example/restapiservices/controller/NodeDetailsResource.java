
package org.example.restapiservices.controller;

import java.util.List;

import javax.jcr.Node;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.example.restapiservices.service.NodeRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
@Path("/getNodeDetails/")

public class NodeDetailsResource extends org.hippoecm.hst.jaxrs.services.AbstractResource {


	
    @GET
    @Path("/findByuuid/{uuid}")
    @Produces("application/json")

    public  String getNodeDetailsbyUuid( @Context HttpServletRequest servletRequest,
            @Context HttpServletResponse servletResponse,
            @Context UriInfo uriInfo,
            @PathParam("uuid") String uuid) {

    	  return NodeRepositoryService.getNodeDetailsbyUuid(uuid);
    }
    
    
    @GET
    @Path("/findByName/{name}/")
    @Produces("application/json")
    
    public List<String> getNodeDetailsbyName( @Context HttpServletRequest servletRequest,
            @Context HttpServletResponse servletResponse,
            @Context UriInfo uriInfo,
            @PathParam("name") String name) {

    	return NodeRepositoryService.getNodeDetailsbyName(name); 
    
    }
}

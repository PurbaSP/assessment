package org.example.restapiservices.service;

import java.util.List;

import org.example.restapiservices.dao.NodeRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeRepositoryService {


	public static  String getNodeDetailsbyUuid (String uuid)
	 {
		 return NodeRepositoryDAO.getNodeDetailsbyUuid(uuid);
	 }
	 
	 
	 public  static List<String>  getNodeDetailsbyName (String name)
	 {
		 return NodeRepositoryDAO.getNodeDetailsbyName(name);
	 }
	
	
}

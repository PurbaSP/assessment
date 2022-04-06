package org.example.restapiservices.model;

public class RepositoryNode {
	
String nodeName;
String nodePAth;
String uuid;
String identifer;
int index;
public String getNodeName() {
	return nodeName;
}
public void setNodeName(String nodeName) {
	this.nodeName = nodeName;
}
public String getNodePAth() {
	return nodePAth;
}
public void setNodePAth(String nodePAth) {
	this.nodePAth = nodePAth;
}
public String getUuid() {
	return uuid;
}
public void setUuid(String uuid) {
	this.uuid = uuid;
}
public String getIdentifer() {
	return identifer;
}
public void setIdentifer(String identifer) {
	this.identifer = identifer;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
@Override
public String toString() {
	return "RepositoryNode [nodeName=" + nodeName + ", nodePAth=" + nodePAth + ", uuid=" + uuid + ", identifer="
			+ identifer + ", index=" + index + "]";
}


}

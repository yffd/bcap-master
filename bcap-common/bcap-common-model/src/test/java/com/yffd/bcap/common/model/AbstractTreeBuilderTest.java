package com.yffd.bcap.common.model;

import com.yffd.bcap.common.model.tree.AbstractTreeBuilder;
import com.yffd.bcap.common.model.tree.Treeable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AbstractTreeBuilderTest {
	
	@Test
	public void test() {
		List<CustObj> nodeList = getList();
		CustObj root = new CustObj("root", "", "虚根");
		Node node = builder.buildTree(root, nodeList);
		System.out.println(node);
	}
	
	AbstractTreeBuilder<CustObj, Node> builder = new AbstractTreeBuilder<CustObj, Node>() {

		@Override
		public Node convert(CustObj obj) {
			Node n = new Node();
			n.setNodeCode(obj.getId());
			n.setParentCode(obj.getPid());
			n.setNodeName(obj.getName());
			return n;
		}

		@Override
		protected void afterFilterNode(Node child, Node parent) {
			child.setParentName(parent.getNodeName());
		}
		
	};
	
	public class CustObj {
		private String id;
		private String pid;
		private String name;
		
		public CustObj(String id, String pid, String name) {
			this.id = id;
			this.pid = pid;
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	public class Node implements Treeable<Node> {
		private String nodeCode;
		private String parentCode;
		private String nodeName;
		private String parentName;
		
		private List<Node> children;

		public String getNodeCode() {
			return nodeCode;
		}
		public void setNodeCode(String nodeCode) {
			this.nodeCode = nodeCode;
		}
		public String getParentCode() {
			return parentCode;
		}
		public void setParentCode(String parentCode) {
			this.parentCode = parentCode;
		}
		public String getNodeName() {
			return nodeName;
		}
		public void setNodeName(String nodeName) {
			this.nodeName = nodeName;
		}
		public String getParentName() {
			return parentName;
		}
		public void setParentName(String parentName) {
			this.parentName = parentName;
		}
		public List<Node> getChildren() {
			return children;
		}
		public void setChildren(List<Node> children) {
			this.children = children;
		}

		@Override
		public Object getIdValue() {
			return this.nodeCode;
		}

		@Override
		public Object getPidValue() {
			return this.parentCode;
		}
	}
	
	private List<CustObj> getList() {
		List<CustObj> list = new ArrayList<>();
		list.add(new CustObj("1-a1", "root", "1-A1"));
		list.add(new CustObj("1-b1", "1-a1", "1-B1"));
		list.add(new CustObj("1-b2", "1-a1", "1-B2"));
		list.add(new CustObj("1-c1", "1-b1", "1-C1"));
		list.add(new CustObj("1-c2", "1-b1", "1-C2"));
		list.add(new CustObj("1-c3", "1-b1", "1-C3"));
		list.add(new CustObj("1-d1", "1-c2", "1-D1"));
		list.add(new CustObj("1-d2", "1-c2", "1-D2"));
		list.add(new CustObj("1-e1", "1-d1", "1-E1"));
		
		list.add(new CustObj("2-a2", "root", "2-A2"));
		list.add(new CustObj("2-b1", "2-a2", "2-B1"));
		list.add(new CustObj("2-c1", "2-b1", "2-C1"));
		list.add(new CustObj("2-d1", "2-c1", "2-D1"));
		list.add(new CustObj("2-e1", "2-d1", "2-E1"));
		list.add(new CustObj("2-f1", "2-e1", "2-F1"));
		return list;
	}
}


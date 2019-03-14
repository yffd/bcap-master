package com.yffd.bcap.common.core.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class EasyDefaultTreeBuilderTest {
	
	@Test
	public void test() {
		List<CustObj> nodeList = getList();
		CustObj root = new CustObj("root", "", "虚根");
		CustObj node = builder.buildTree(root, nodeList);
		String json = JSON.toJSONString(node);
		System.out.println(json);
	}
	DefaultTreeBuilder<CustObj> builder = new DefaultTreeBuilder<CustObj>();
	
	public class CustObj implements Treeable<CustObj> {
		private String id;
		private String pid;
		private String name;
		private List<CustObj> children;
		
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
		public List<CustObj> getChildren() {
			return children;
		}
		public void setChildren(List<CustObj> children) {
			this.children = children;
		}
		@Override
		public Object getIdValue() {
			return this.id;
		}
		@Override
		public Object getPidValue() {
			return this.pid;
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


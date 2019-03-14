package com.yffd.bcap.common.core.tree;

import java.util.List;

public interface Treeable<T> {
	
	public Object getIdValue();
	
	public Object getPidValue();
	
	public List<T> getChildren();
	
	public void setChildren(List<T> children);
	
}


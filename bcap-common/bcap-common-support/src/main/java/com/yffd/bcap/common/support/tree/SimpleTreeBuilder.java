package com.yffd.bcap.common.support.tree;

public class SimpleTreeBuilder<N extends Treeable<N>> extends AbstractTreeBuilder<N, N> {

	@Override
	public N convert(N obj) {
		return obj;
	}

}

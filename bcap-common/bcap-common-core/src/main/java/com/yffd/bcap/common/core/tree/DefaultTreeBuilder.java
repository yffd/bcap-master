package com.yffd.bcap.common.core.tree;

public class DefaultTreeBuilder<N extends Treeable<N>> extends AbstractTreeBuilder<N, N> {

	@Override
	public N convert(N obj) {
		return obj;
	}

}

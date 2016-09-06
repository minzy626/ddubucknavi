package com.hurryup.traffic.junga.hahaha.search;


import java.util.List;

public interface OnFinishSearchListener {
	void onSuccess(List<Item> itemList);
	void onFail();
}

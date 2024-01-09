package com.example.CustomJavaBeckend;

import java.util.Comparator;

public class UploadEntityComparator implements Comparator<UploadEntity> {
	
	@Override
	public int compare(UploadEntity o1, UploadEntity o2) {
		// TODO Auto-generated method stub
		int order1= o1.getId();
		int order2= o2.getId();
		if (order1>order2) return 1;
		if (order2>order1) return -1;
		return 0;
	}

}

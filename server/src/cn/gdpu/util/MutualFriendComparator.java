package cn.gdpu.util;

import java.util.Comparator;

import cn.gdpu.vo.MutualFriend;

public class MutualFriendComparator<T> implements Comparator<T> {

	public int compare(T arg0, T arg1) {
        MutualFriend mf1 = (MutualFriend) arg0;
        MutualFriend mf2 = (MutualFriend) arg1;
		int size1 = mf1.getMutual().size();
		int size2 = mf2.getMutual().size();
		return size1 < size2 ? 1 : size1 == size2 ? 0 : -1 ;
	}

}

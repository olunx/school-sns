package cn.gdpu.util;

import java.util.Comparator;

import cn.gdpu.vo.Vote;

public class VoterComparator<T> implements Comparator<T> {

	public int compare(T arg0, T arg1) {
        Vote vote1 = (Vote) arg0;
        Vote vote2 = (Vote) arg1;
		int size1 = vote1.getVoters().size();
		int size2 = vote2.getVoters().size();
		return size1 < size2 ? 1 : size1 == size2 ? 0 : -1 ;
	}

}

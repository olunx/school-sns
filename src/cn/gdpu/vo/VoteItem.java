package cn.gdpu.vo;

public class VoteItem {
	private int id;
	/**
	 * 投票选项.当前标数
	 */
	private int num;
	/**
	 * 投票选项.内容
	 */
	private String content;
	/**
	 * 所属投票
	 */
	private Vote vote;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

}

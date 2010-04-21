package cn.gdpu.action;

public class ChatAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	public String pub() {
		return "publicchat";
	}
	
	public String pri() {
		return "privatechat";
	}
}

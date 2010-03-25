package cn.gdpu.action;

@SuppressWarnings("serial")
public class TestAction extends BaseAction {
	private String val;

	public String test() throws Exception {
		this.getRequest().put("name", val);
		System.out.println(this.getRequest());
		return SUCCESS;
	}

	public void setVal(String val) {
		this.val = val;
	}

}

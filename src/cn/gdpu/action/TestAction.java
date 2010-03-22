package cn.gdpu.action;

@SuppressWarnings("serial")
public class TestAction extends BaseAction {

	public String test() throws Exception {
		String val = (String) this.getRequest().get("val");
		System.out.println(val);
		return SUCCESS;
	}

}

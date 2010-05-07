package com.fatkun.fetion;

public class Contact {
    public String uri;
    public String lname;
    public String sid;
    public String userid;
    public int groupid;
    public int status;
    public int sc;
    public String impresa;
    public String mobile;
	@Override
	public String toString() {
		return "Contact [groupid=" + groupid + ", lname=" + lname + ", mobile=" + mobile + ", status=" + status + ", uri=" + uri
				+ ", userid=" + userid + "]";
	}
	public Contact(String uri) {
		this.uri = uri;
        if(uri.indexOf('@') > 0)
            sid = uri.substring(4, uri.indexOf('@'));
        else
            sid = uri.substring(4);
	}
}

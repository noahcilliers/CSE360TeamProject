
package guiManageInvitations;

public class InvitationRow
{
	
	private String code;
	private String email;
	private String role;
	
	public InvitationRow(String code, String email, String role)
	{
		this.code = code;
		this.email = email;
		this.role = role;
	}
	
	public String getCode() {return code; }
	public String getEmail() {return email; }
	public String getRole() {return role; }
}
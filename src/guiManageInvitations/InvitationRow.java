
package guiManageInvitations;

/*
 * Title: InvitationRow
 * Description:
 * This class represents a single row of invitation data
 * displayed in the manage invitations TableView
 * 
 * each obj has invitation code, email, and role
 * 
 * This class is used as the data model for the javaFX TableView
 * The greater methods allow the TableView columns to access
 * the values through property binding.
 */
public class InvitationRow
{
	
	private String code;
	private String email;
	private String role;
	
	//constructor to initialize an InvitationRow obj
	public InvitationRow(String code, String email, String role)
	{
		this.code = code;
		this.email = email;
		this.role = role;
	}
	
	//each method returns code, email, and role.
	public String getCode() {return code; }
	public String getEmail() {return email; }
	public String getRole() {return role; }
}
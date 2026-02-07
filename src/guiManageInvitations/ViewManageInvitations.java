
package guiManageInvitations;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewManageInvitations 
{
	public static void displayManageInvitations(Stage parentStage)
	{
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(parentStage);
		
		ControllerManageInvitations controller = new ControllerManageInvitations(stage);
		
		Scene scene = new Scene(controller.getRoot(), 600, 420);
		
		stage.setTitle("Manage Invitations");
		stage.setScene(scene);
		stage.showAndWait();
	}

}

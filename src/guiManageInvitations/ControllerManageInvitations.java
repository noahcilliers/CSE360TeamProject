
package guiManageInvitations;

import database.Database;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import applicationMain.FoundationsMain;


import java.util.ArrayList;


public class ControllerManageInvitations
{
	private Stage stage;
	private BorderPane root;
	private TableView<InvitationRow> table;
	
	private final Database db;
	
	public ControllerManageInvitations(Stage stage)
	{
		this.stage =stage;
		this.root = new BorderPane();
		this.table = new TableView<>();
		this.db = FoundationsMain.database;
		
		setupTable();
		setupButtons();
		loadInvitations();
	}
	
	public Parent getRoot()
	{
		return root;
	}
	
	private void setupTable()
	{
		TableColumn<InvitationRow, String> codeCol = new TableColumn<>("Code");
		codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
		codeCol.setPrefWidth(220);
		
		TableColumn<InvitationRow, String> emailCol = new TableColumn<>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailCol.setPrefWidth(260);
		
		TableColumn<InvitationRow, String> roleCol = new TableColumn<>("Role");
		roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
		roleCol.setPrefWidth(140);
		
		table.getColumns().add(codeCol);
		table.getColumns().add(emailCol);
		table.getColumns().add(roleCol);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		root.setCenter(table);
		
		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	private void setupButtons()
	{
		Button refreshButton = new Button("Refreash");
		Button deleteButton = new Button("Delete Selected");
		Button closeButton = new Button("Close");
		
		refreshButton.setOnAction((_) -> loadInvitations());
		
		deleteButton.setOnAction((_) -> {
			InvitationRow selected = table.getSelectionModel().getSelectedItem();
			
			if (selected == null)
			{
				Alert a = new Alert(Alert.AlertType.INFORMATION);
				a.setHeaderText(null);
				a.setContentText("Please select an invitation to delete.");
				a.showAndWait();
				return;
			}
			
			Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
			confirm.setHeaderText("Revoke Invitation");
			confirm.setContentText(
					"Delete invitation code:\n" + selected.getCode() +
					"\n\nEmail: " + selected.getEmail()
					);
			
			boolean ok = db.removeInvitationByCode(selected.getCode());
			if(!ok)
			{
				Alert a = new Alert(Alert.AlertType.ERROR);
				a.setHeaderText("Delete failed");
				a.setContentText("Could not delete invitation. Check console for details.");
				a.showAndWait();
				return;
			}
			
			loadInvitations();
			table.getSelectionModel().clearSelection();
		});
		
		closeButton.setOnAction((_) -> stage.close());
		
		HBox buttonBox = new HBox(10, refreshButton, deleteButton, closeButton);
		buttonBox.setPadding(new Insets(10));
		root.setBottom(buttonBox);
	}
	
	private void loadInvitations()
	{
		table.getItems().clear();
		
		if (db == null)
		{
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setHeaderText("Database not available");
			a.setContentText("The database reference is null. Make sure Foundationsmain.database is created before opening this page.");
			a.showAndWait();
			return;
		}
		
		ArrayList<String[]> invites = db.getAllInvitations();
		
		for (String[] row : invites)
		{
			if (row != null && row.length >= 3)
			{
				table.getItems().add(new InvitationRow(row[0], row[1], row [2]));
			}
			
		}
	}
}




















package application;

import java.sql.SQLException;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Student;

public class LeftPane extends VBox {
	
	int TempId;
	
	UserController uc = new UserController();
	
	Label labelTitle = new Label("ADD NEW STUDENT");
	
	TextField txtName = new TextField();
	TextField txtAge = new TextField();
	TextField txtYear	 = new TextField();
	
	Button btnSave = new Button("Save");
	Button btnUpdate = new Button("Update");
	Button btnDelete = new Button("Delete");
	
	Button btnClear = new Button("Clear");
	
	private void clearInputField()
	{
		txtName.setText("");
		txtAge.setText("");
		txtYear.setText("");
	}
	
	public LeftPane() {
		
		this.setSpacing(20);
		this.setStyle("-fx-background-color: #37474F");
		this.setPadding(new Insets(20));
		// center VBox
		this.setAlignment(Pos.CENTER);
		
		txtName.setPromptText("Enter your Name");
		txtAge.setPromptText("Enter your age");
		txtYear.setPromptText("Enter your year");
		
		txtName.setFont(new Font(20));
		txtAge.setFont(new Font(20));
		txtYear.setFont(new Font(20));
		
		btnSave.setPrefWidth(150);
		btnUpdate.setPrefWidth(150);
		btnDelete.setPrefWidth(150);
		btnClear.setPrefWidth(150);
		
		btnClear.setStyle("-fx-background-color:#e67e22;-fx-text-fill:#FFF;-fx-font-size:18px;");
		btnDelete.setStyle("-fx-background-color:#e74c3c;-fx-text-fill:#FFF;-fx-font-size:18px;");
		btnUpdate.setStyle("-fx-background-color:#EC407A;-fx-text-fill:#FFF;-fx-font-size:18px;");
		btnSave.setStyle("-fx-background-color:#27ae60;-fx-text-fill:#FFF;-fx-font-size:18px;");
		labelTitle.setStyle("-fx-font-size:20px;-fx-text-fill:#FFF");
		
		this.getChildren().addAll(labelTitle, txtName, txtAge, txtYear, btnSave, btnUpdate, btnDelete, btnClear);
		
		// action
		btnSave.setOnMouseClicked(e -> {
			Student s = new Student(txtName.getText(), Integer.parseInt(txtAge.getText()), Integer.parseInt(txtYear.getText()));
			if (uc.insert(s))
			{
				clearInputField();
				try {
					App.rightpane.table.setItems(uc.fetchAll());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				System.out.println("FAILED TO INSERT");
			}
		});
		
		btnUpdate.setOnMouseClicked(e -> {
			Student s  = new Student(TempId,
																txtName.getText(),
												                Integer.parseInt(txtAge.getText()),
												                Integer.parseInt(txtYear.getText()));
			
			if (uc.update(s))
			{
				try {
					App.rightpane.table.setItems(uc.fetchAll());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnDelete.setOnMouseClicked(e -> {
			Alert alert  = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setContentText("Are you sure you want to delete this student?");
			
			ButtonType OKbutton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
			ButtonType Nobutton = new ButtonType("No", ButtonBar.ButtonData.NO);
			ButtonType CANCELbutton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
			
			alert.getButtonTypes().setAll(OKbutton, Nobutton, CANCELbutton);
			
			alert.showAndWait().ifPresent(type -> {
				System.out.println(type);
				if (type == OKbutton) {
					System.out.println("Click OK Button!!" + "Id to remove: " + TempId);
					if (uc.delete(TempId)) {
						try {
							App.rightpane.table.setItems(uc.fetchAll());
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else if (type== Nobutton)
				{
					System.out.println("Click NO Button!!");
				}
				else if (type == CANCELbutton)
				{
					System.out.println("Click CANCEL Button!!");
				}
			});
		});
		
		btnClear.setOnMouseClicked(e -> {
			clearInputField();
		});
	}
}

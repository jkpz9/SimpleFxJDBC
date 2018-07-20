package application;

import java.sql.SQLException;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Student;

public class RightPane extends VBox {
	
		HBox searchPane = new HBox();
		
		TextField txtSearch = new TextField();
		
		Button btnSearch = new Button("Search");
		
		TableView<Student> table = new TableView<Student>();
		
		UserController uc = new UserController();
		
		public RightPane()  {
			
			//  initial table column
			TableColumn<Student, Integer> columnId = new TableColumn<Student, Integer>("id");
			TableColumn<Student, String> columnName = new TableColumn<Student, String>("name");
			TableColumn<Student, Integer> columnAge = new TableColumn<Student, Integer>("age");
			TableColumn<Student, Integer> columnYear = new TableColumn<Student, Integer>("year");
			
			columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
			columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
			columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
			
			columnId.prefWidthProperty().bind(table.widthProperty().divide(4));
			columnName.prefWidthProperty().bind(table.widthProperty().divide(4));
			columnAge.prefWidthProperty().bind(table.widthProperty().divide(4));
			columnYear.prefWidthProperty().bind(table.widthProperty().divide(4));
			
			table.getColumns().addAll(columnId,columnName,  columnAge, columnYear);
			
			try {
				table.setItems(uc.fetchAll());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.setPadding(new Insets(20));
			searchPane.setAlignment(Pos.CENTER);
			searchPane.setSpacing(20);
			searchPane.setPadding(new Insets(20));
			searchPane.getChildren().addAll(txtSearch, btnSearch);
			this.getChildren().addAll(searchPane, table);
			
			
			// event
			table.setOnMouseClicked(e -> {
				Student s = table.getSelectionModel().getSelectedItems().get(0);
				App.leftpane.txtName.setText(s.getName());
				App.leftpane.txtAge.setText(Integer.toString(s.getAge()));
				App.leftpane.txtYear.setText(Integer.toString(s.getYear()));
				
				App.leftpane.TempId = s.getId();
			});
			
			btnSearch.setOnMouseClicked(e -> {
				try {
					table.setItems(uc.search(txtSearch.getText()));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}
		
	
}

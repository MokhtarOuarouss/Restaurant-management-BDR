package bdr.bdr;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjetBDRApplication extends Application {
    //if db == 0 => BDR else => (Restaurant1 || Restaurant2)
    int db = 0 ;
    int customers_id;
    Connection con;
    String DBName = "BDR";
    public Pane chouseOne(){
        Pane pane = new Pane();
        pane.setPrefSize(701.0, 475.0);


        Image backgroundImage = new Image("C:\\Users\\mk oussen\\Downloads\\image.jpg");

        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitHeight(600.0);
        imageView.setFitWidth(800.0);
        imageView.setLayoutY(-1.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // Create the UI elements
        Label titleLabel = new Label("  Choose Restaurant ");
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setLayoutX(202);
        titleLabel.setLayoutY(79);
        titleLabel.setPrefHeight(51);
        titleLabel.setPrefWidth(285);
        titleLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        titleLabel.getStyleClass().add("title-label");
        titleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titleLabel.setFont(new Font(30));


        Button button1 = new Button();
        button1.setLayoutX(70.0);
        button1.setLayoutY(201.0);
        button1.setPrefHeight(74.0);
        button1.setPrefWidth(226.0);
        button1.setText("Bella Vita");
        button1.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        button1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button1.setOnAction(actionEvent -> {
            DBName = "RESTAURANT1";
            if(db==0) {
                Pane newContent = Admin(); // Replace this with your own method to create the new content
                pane.getChildren().setAll(newContent);
            }
            else {
                Pane newContent = null; // Replace this with your own method to create the new content
                try {
                    newContent = AddOrder();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                pane.getChildren().setAll(newContent);
            }
        });

        Button button2 = new Button();
        button2.setLayoutX(395.0);
        button2.setLayoutY(201.0);
        button2.setPrefHeight(74.0);
        button2.setPrefWidth(226.0);
        button2.setText("Your Second House");
        button2.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        button2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button2.setOnAction(actionEvent -> {
            DBName = "RESTAURANT2";
            if(db==0) {
                Pane newContent = Admin(); // Replace this with your own method to create the new content
                pane.getChildren().setAll(newContent);
            }
            else {
                Pane newContent = null; // Replace this with your own method to create the new content
                try {
                    newContent = AddOrder();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                pane.getChildren().setAll(newContent);
            }
        });

        pane.getChildren().addAll(imageView,titleLabel,button1, button2);

        return pane;
    }

    public AnchorPane Commun(AnchorPane rightPane){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(412.0, 474.0);
        anchorPane.setStyle("-fx-background-color: #E6FFFD;"); // Set the background color to red



        Button addButton = new Button("Add Employee");
        addButton.setLayoutX(37.0);
        addButton.setLayoutY(79.0);
        addButton.setPrefHeight(30);
        addButton.setPrefWidth(120);
        addButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        addButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        addButton.setOnAction(actionEvent -> {
            AnchorPane newContent = AdminAddEMP(); // Replace this with your own method to create the new content
            rightPane.getChildren().setAll(newContent);
        });

        Button deleteButton = new Button("Delete Employee");
        deleteButton.setLayoutX(37.0);
        deleteButton.setLayoutY(134.0);
        deleteButton.setPrefHeight(30);
        deleteButton.setPrefWidth(120);
        deleteButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        deleteButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        deleteButton.setOnAction(actionEvent -> {
            AnchorPane newContent = AdminDeleteEmp(); // Replace this with your own method to create the new content
            rightPane.getChildren().setAll(newContent);
        });

        Button updateButton = new Button("Update Employee");
        updateButton.setLayoutX(37.0);
        updateButton.setLayoutY(197.0);
        updateButton.setPrefHeight(30);
        updateButton.setPrefWidth(120);
        updateButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        updateButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        updateButton.setOnAction(actionEvent -> {
            AnchorPane newContent = AdminupdateEMP(); // Replace this with your own method to create the new content
            rightPane.getChildren().setAll(newContent);
        });

        Button AddMenuButton = new Button("Add Menu");
        AddMenuButton.setLayoutX(37.0);
        AddMenuButton.setLayoutY(247.0);
        AddMenuButton.setPrefHeight(30);
        AddMenuButton.setPrefWidth(120);
        AddMenuButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        AddMenuButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        AddMenuButton.setOnAction(actionEvent -> {
            AnchorPane newContent = AddItem(); // Replace this with your own method to create the new content
            rightPane.getChildren().setAll(newContent);
        });

        Button UpdateMenuAdmin = new Button("Update Menu");
        UpdateMenuAdmin.setLayoutX(37.0);
        UpdateMenuAdmin.setLayoutY(307.0);
        UpdateMenuAdmin.setPrefHeight(30);
        UpdateMenuAdmin.setPrefWidth(120);
        UpdateMenuAdmin.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        UpdateMenuAdmin.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        UpdateMenuAdmin.setOnAction(actionEvent -> {
            AnchorPane newContent = UpdateItem(); // Replace this with your own method to create the new content
            rightPane.getChildren().setAll(newContent);
        });

        Button DeleteMenuAdmin = new Button("Delete Menu");
        DeleteMenuAdmin.setLayoutX(37.0);
        DeleteMenuAdmin.setLayoutY(367.0);
        DeleteMenuAdmin.setPrefHeight(30);
        DeleteMenuAdmin.setPrefWidth(120);
        DeleteMenuAdmin.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        DeleteMenuAdmin.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        DeleteMenuAdmin.setOnAction(actionEvent -> {
            AnchorPane newContent = DeleteItem(); // Replace this with your own method to create the new content
            rightPane.getChildren().setAll(newContent);
        });



        anchorPane.getChildren().addAll(addButton, deleteButton, updateButton, AddMenuButton,UpdateMenuAdmin,DeleteMenuAdmin);
        return anchorPane;
    }

    public AnchorPane ShowItems(){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(100.0, 160.0);


        return anchorPane;
    }
    public AnchorPane DeleteItem(){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(100.0, 160.0);



        Label titleLabel = new Label("Delete Items");
        titleLabel.setFont(new Font(30.0));
        titleLabel.setLayoutX(170.0);
        titleLabel.setLayoutY(80.0);



        Label ItemIdLabel = new Label("Item Id");
        ItemIdLabel.setAlignment(javafx.geometry.Pos.CENTER);
        ItemIdLabel.setLayoutX(79.0);
        ItemIdLabel.setLayoutY(159.0);
        ItemIdLabel.setPrefHeight(32.0);
        ItemIdLabel.setPrefWidth(130.0);
        ItemIdLabel.setFont(new Font(15.0));

        TextField ItemIdTextField = new TextField();
        ItemIdTextField.setLayoutX(244.0);
        ItemIdTextField.setLayoutY(159.0);
        ItemIdTextField.setPrefHeight(32.0);
        ItemIdTextField.setPrefWidth(166.0);

        Button deleteButton = new Button("Delete Item");
        deleteButton.setLayoutX(99.0);
        deleteButton.setLayoutY(205.0);
        deleteButton.setMnemonicParsing(false);
        deleteButton.setPrefHeight(32.0);
        deleteButton.setPrefWidth(310.0);
        deleteButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        deleteButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        deleteButton.setOnAction(Event->{
            int id = Integer.parseInt(ItemIdTextField.getText());

            Menu emp = new Menu();
            try {
                emp.deleteMenu(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ItemIdTextField.setText("");
        });


        anchorPane.getChildren().addAll(deleteButton, ItemIdLabel, ItemIdTextField,titleLabel);

        return anchorPane;

    }

    public AnchorPane UpdateItem(){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(100.0, 160.0);

        Label titleLabel = new Label("Update Item");
        titleLabel.setFont(new Font(30.0));
        titleLabel.setLayoutX(170.0);
        titleLabel.setLayoutY(30);





        Label idLabel = new Label("Item Id");
        idLabel.setAlignment(javafx.geometry.Pos.CENTER);
        idLabel.setLayoutX(85.0);
        idLabel.setLayoutY(81.0);
        idLabel.setPrefHeight(32.0);
        idLabel.setPrefWidth(130.0);
        idLabel.setFont(new javafx.scene.text.Font(15.0));

        TextField idTextField = new TextField();
        idTextField.setLayoutX(250.0);
        idTextField.setLayoutY(81.0);
        idTextField.setPrefHeight(32.0);
        idTextField.setPrefWidth(166.0);

        Label nameLabel = new Label("Item Name");
        nameLabel.setAlignment(javafx.geometry.Pos.CENTER);
        nameLabel.setLayoutX(84.0);
        nameLabel.setLayoutY(127.0);
        nameLabel.setPrefHeight(32.0);
        nameLabel.setPrefWidth(130.0);
        nameLabel.setFont(new javafx.scene.text.Font(15.0));

        TextField nameTextField = new TextField();
        nameTextField.setLayoutX(249.0);
        nameTextField.setLayoutY(127.0);
        nameTextField.setPrefHeight(32.0);
        nameTextField.setPrefWidth(166.0);


        Label priceLabel = new Label("Price");
        priceLabel.setAlignment(javafx.geometry.Pos.CENTER);
        priceLabel.setLayoutX(84.0);
        priceLabel.setLayoutY(173.0);
        priceLabel.setPrefHeight(32.0);
        priceLabel.setPrefWidth(130.0);
        priceLabel.setFont(new javafx.scene.text.Font(15.0));

        TextField priceTextField = new TextField();
        priceTextField.setLayoutX(249.0);
        priceTextField.setLayoutY(173.0);
        priceTextField.setPrefHeight(32.0);
        priceTextField.setPrefWidth(166.0);

        Label descriptionLabel = new Label("Description");
        descriptionLabel.setAlignment(javafx.geometry.Pos.CENTER);
        descriptionLabel.setLayoutX(84.0);
        descriptionLabel.setLayoutY(252.0);
        descriptionLabel.setPrefHeight(32.0);
        descriptionLabel.setPrefWidth(130.0);
        descriptionLabel.setFont(new javafx.scene.text.Font(15.0));

        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setLayoutX(250.0);
        descriptionTextArea.setLayoutY(222.0);
        descriptionTextArea.setPrefHeight(91.0);
        descriptionTextArea.setPrefWidth(166.0);

        Button updateButton = new Button("Update Item");
        updateButton.setLayoutX(105.0);
        updateButton.setLayoutY(329.0);
        updateButton.setPrefHeight(32.0);
        updateButton.setPrefWidth(310.0);
        updateButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        updateButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        updateButton.setOnAction(Event-> {
            int id = Integer.parseInt(idTextField.getText());
            String ItemName = nameTextField.getText();
            float price = Float.parseFloat(priceTextField.getText());
            String desctiption = descriptionTextArea.getText();

            Menu emp = new Menu();
            try {
                emp.UpdateMenu(id, ItemName, price, desctiption);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            idTextField.setText("");
            nameTextField.setText("");
            priceTextField.setText("");
            descriptionTextArea.setText("");

        });

        anchorPane.getChildren().addAll(titleLabel,updateButton, idLabel, idTextField, nameLabel, nameTextField,priceTextField,priceLabel ,descriptionLabel, descriptionTextArea);

        return anchorPane;
    }

    public AnchorPane AddItem(){
        AnchorPane anchorPane = new AnchorPane();

        Label titleLabel = new Label("Add Items");
        titleLabel.setFont(new Font(30.0));
        titleLabel.setLayoutX(170.0);
        titleLabel.setLayoutY(60.0);




        Label nameLabel = new Label("Item Name");
        nameLabel.setAlignment(javafx.geometry.Pos.CENTER);
        nameLabel.setLayoutX(79.0);
        nameLabel.setLayoutY(109.0);
        nameLabel.setPrefHeight(32.0);
        nameLabel.setPrefWidth(130.0);
        nameLabel.setFont(new javafx.scene.text.Font(15.0));

        TextField nameTextField = new TextField();
        nameTextField.setLayoutX(244.0);
        nameTextField.setLayoutY(109.0);
        nameTextField.setPrefHeight(32.0);
        nameTextField.setPrefWidth(166.0);

        Label priceLabel = new Label("Item Price");
        priceLabel.setAlignment(javafx.geometry.Pos.CENTER);
        priceLabel.setLayoutX(79.0);
        priceLabel.setLayoutY(159.0);
        priceLabel.setPrefHeight(32.0);
        priceLabel.setPrefWidth(130.0);
        priceLabel.setFont(new javafx.scene.text.Font(15.0));

        TextField PriceTextField = new TextField();
        PriceTextField.setLayoutX(244.0);
        PriceTextField.setLayoutY(159.0);
        PriceTextField.setPrefHeight(32.0);
        PriceTextField.setPrefWidth(166.0);

        Label descriptionLabel = new Label("Description");
        descriptionLabel.setAlignment(javafx.geometry.Pos.CENTER);
        descriptionLabel.setLayoutX(79.0);
        descriptionLabel.setLayoutY(225.0);
        descriptionLabel.setPrefHeight(32.0);
        descriptionLabel.setPrefWidth(130.0);
        descriptionLabel.setFont(new javafx.scene.text.Font(15.0));

        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setLayoutX(244.0);
        descriptionTextArea.setLayoutY(205.0);
        descriptionTextArea.setPrefHeight(104.0);
        descriptionTextArea.setPrefWidth(166.0);

        Button addButton = new Button("Add Item");
        addButton.setLayoutX(107.0);
        addButton.setLayoutY(340.0);
        addButton.setPrefHeight(32.0);
        addButton.setPrefWidth(310.0);
        addButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        addButton.setFont(Font.font("Arial"));
        addButton.setOnAction(Event->{
            String nameItem = nameTextField.getText();
            float price = Float.parseFloat(PriceTextField.getText());
            String description = descriptionTextArea.getText();

            Menu menu = new Menu();
            try {
                menu.AddMenu(DBName,nameItem,price,description);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            nameTextField.setText("");
            PriceTextField.setText("");
            descriptionTextArea.setText("");
        });

        anchorPane.getChildren().addAll(titleLabel,addButton, nameLabel, nameTextField, descriptionLabel, descriptionTextArea,priceLabel,PriceTextField);


        return anchorPane;
    }
    public AnchorPane AdminDeleteEmp(){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(100.0, 160.0);


        Label titleLabel = new Label("Delete Employee");
        titleLabel.setFont(new Font(30.0));
        titleLabel.setLayoutX(140.0);
        titleLabel.setLayoutY(20.0);



        Label EmployeeIdLabel = new Label("Employee Id");
        EmployeeIdLabel.setAlignment(javafx.geometry.Pos.CENTER);
        EmployeeIdLabel.setLayoutX(79.0);
        EmployeeIdLabel.setLayoutY(159.0);
        EmployeeIdLabel.setPrefHeight(32.0);
        EmployeeIdLabel.setPrefWidth(130.0);
        EmployeeIdLabel.setFont(new Font(15.0));

        TextField EmployeeIdTextField = new TextField();
        EmployeeIdTextField.setLayoutX(244.0);
        EmployeeIdTextField.setLayoutY(159.0);
        EmployeeIdTextField.setPrefHeight(32.0);
        EmployeeIdTextField.setPrefWidth(166.0);

        Button deleteButton = new Button("Delete");
        deleteButton.setLayoutX(99.0);
        deleteButton.setLayoutY(205.0);
        deleteButton.setMnemonicParsing(false);
        deleteButton.setPrefHeight(32.0);
        deleteButton.setPrefWidth(310.0);
        deleteButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        deleteButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        deleteButton.setOnAction(Event->{
            int id = Integer.parseInt(EmployeeIdTextField.getText());

            Employee emp = new Employee();
            try {
                emp.deleteEmployee(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            EmployeeIdTextField.setText("");
        });

        anchorPane.getChildren().addAll(deleteButton, EmployeeIdLabel, EmployeeIdTextField,titleLabel);


        return anchorPane;
    }

    public AnchorPane AdminupdateEMP(){



        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(160.0);
        anchorPane.setPrefWidth(100.0);

        Label titleLabel = new Label("Update Employee");
        titleLabel.setFont(new Font(30.0));
        titleLabel.setLayoutX(140.0);
        titleLabel.setLayoutY(20.0);

        TextField idTextField = new TextField();
        idTextField.setLayoutX(273.0);
        idTextField.setLayoutY(108.0);

        TextField fullNameTextField = new TextField();
        fullNameTextField.setLayoutX(273.0);
        fullNameTextField.setLayoutY(154.0);




        TextField SalaryTextField = new TextField();
        SalaryTextField.setLayoutX(273.0);
        SalaryTextField.setLayoutY(300.0);


        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setLayoutX(273.0);
        phoneNumberTextField.setLayoutY(250.0);


        Label fullNameLabel = new Label("Full Name : ");
        fullNameLabel.setAlignment(javafx.geometry.Pos.CENTER);
        fullNameLabel.setLayoutX(96.0);
        fullNameLabel.setLayoutY(154.0);
        fullNameLabel.setPrefHeight(32.0);
        fullNameLabel.setPrefWidth(130.0);
        fullNameLabel.setFont(new Font(15.0));



        Label positionLabel = new Label("Position : ");
        positionLabel.setAlignment(javafx.geometry.Pos.CENTER);
        positionLabel.setLayoutX(88.0);
        positionLabel.setLayoutY(203.0);
        positionLabel.setPrefHeight(32.0);
        positionLabel.setPrefWidth(130.0);
        positionLabel.setFont(new Font(15.0));

        Label phoneNumberLabel = new Label("Phone number : ");
        phoneNumberLabel.setAlignment(javafx.geometry.Pos.CENTER);
        phoneNumberLabel.setLayoutX(88.0);
        phoneNumberLabel.setLayoutY(250.0);
        phoneNumberLabel.setPrefHeight(32.0);
        phoneNumberLabel.setPrefWidth(130.0);
        phoneNumberLabel.setFont(new Font(15.0));

        Label salaryLabel = new Label("Salary : ");
        salaryLabel.setAlignment(javafx.geometry.Pos.CENTER);
        salaryLabel.setLayoutX(88.0);
        salaryLabel.setLayoutY(300.0);
        salaryLabel.setPrefHeight(32.0);
        salaryLabel.setPrefWidth(130.0);
        salaryLabel.setFont(new Font(15.0));





        MenuButton PositionmenuButton = new MenuButton("Position");
        PositionmenuButton.setLayoutX(273.0);
        PositionmenuButton.setLayoutY(203.0);
        PositionmenuButton.setMnemonicParsing(false);
        PositionmenuButton.setPrefSize(150.0, 30.0);

        MenuItem pos1MenuItem = new MenuItem("Chef");
        MenuItem pos2MenuItem = new MenuItem("Sous Chef");
        MenuItem pos3MenuItem = new MenuItem("Server");
        MenuItem pos4MenuItem = new MenuItem("Manager");
        MenuItem pos5MenuItem = new MenuItem("Waiter");
        PositionmenuButton.getItems().addAll(pos1MenuItem, pos2MenuItem,pos3MenuItem,pos4MenuItem,pos5MenuItem);

        for (MenuItem MI:PositionmenuButton.getItems()){
            MI.setOnAction(Event->{
                PositionmenuButton.setText(MI.getText());
            });
        }


        Label idLabel = new Label("Employee Id");
        idLabel.setAlignment(javafx.geometry.Pos.CENTER);
        idLabel.setLayoutX(96.0);
        idLabel.setLayoutY(108.0);
        idLabel.setPrefHeight(32.0);
        idLabel.setPrefWidth(130.0);
        idLabel.setFont(new Font(15.0));

        Button UpdateEmployeeButton = new Button("Update Employee");
        UpdateEmployeeButton.setLayoutX(320.0);
        UpdateEmployeeButton.setLayoutY(343.0);
        UpdateEmployeeButton.setMnemonicParsing(false);
        UpdateEmployeeButton.setPrefHeight(32.0);
        UpdateEmployeeButton.setPrefWidth(118.0);
        UpdateEmployeeButton.setOnAction(Event->{
            int id = Integer.parseInt(idTextField.getText());
            String fname = fullNameTextField.getText();
            String Position = PositionmenuButton.getText();
            String phone = phoneNumberTextField.getText();
            float sal = Float.parseFloat(SalaryTextField.getText());

            Employee emp = new Employee();
            try {
                emp.UpdateEmployee(id,fname,Position,phone,sal);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            idTextField.setText("");
            fullNameTextField.setText("");
            PositionmenuButton.setText("Position");
            phoneNumberTextField.setText("");
            SalaryTextField.setText("");




        });
        UpdateEmployeeButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        UpdateEmployeeButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));


        anchorPane.getChildren().addAll(
                titleLabel,fullNameTextField, SalaryTextField,
                phoneNumberTextField, fullNameLabel,
                positionLabel, phoneNumberLabel, salaryLabel, UpdateEmployeeButton,
                PositionmenuButton, idLabel, idTextField
        );



        return anchorPane;
    }
    public AnchorPane AdminAddEMP(){

        AnchorPane anchorPane2 = new AnchorPane();
        anchorPane2.setPrefSize(100.0, 160.0);


        Label titleLabel = new Label("Add Employee");
        titleLabel.setFont(new Font(30.0));
        titleLabel.setLayoutX(140.0);
        titleLabel.setLayoutY(20.0);

        TextField fullNameTextField = new TextField();
        fullNameTextField.setLayoutX(273.0);
        fullNameTextField.setLayoutY(104.0);

        TextField SalaryTextField = new TextField();
        SalaryTextField.setLayoutX(273.0);
        SalaryTextField.setLayoutY(253.0);



        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setLayoutX(273.0);
        phoneNumberTextField.setLayoutY(203);

        Label fullNameLabel = new Label("Full Name: ");
        fullNameLabel.setLayoutX(88.0);
        fullNameLabel.setLayoutY(104.0);
        fullNameLabel.setPrefSize(130.0, 32.0);
        fullNameLabel.setAlignment(javafx.geometry.Pos.CENTER);
        fullNameLabel.setFont(new Font(15.0));


        Label positionLabel = new Label("Position: ");
        positionLabel.setLayoutX(88.0);
        positionLabel.setLayoutY(154.0);
        positionLabel.setPrefSize(130.0, 32.0);
        positionLabel.setAlignment(javafx.geometry.Pos.CENTER);
        positionLabel.setFont(new Font(15.0));

        Label phoneNumberLabel = new Label("Phone number: ");
        phoneNumberLabel.setLayoutX(88.0);
        phoneNumberLabel.setLayoutY(203.0);
        phoneNumberLabel.setPrefSize(130.0, 32.0);
        phoneNumberLabel.setAlignment(javafx.geometry.Pos.CENTER);
        phoneNumberLabel.setFont(new Font(15.0));

        Label salaryLabel = new Label("Salary: ");
        salaryLabel.setLayoutX(88.0);
        salaryLabel.setLayoutY(253.0);
        salaryLabel.setPrefSize(130.0, 32.0);
        salaryLabel.setAlignment(javafx.geometry.Pos.CENTER);
        salaryLabel.setFont(new Font(15.0));

        MenuButton PosimenuButton = new MenuButton("Position");
        PosimenuButton.setLayoutX(273.0);
        PosimenuButton.setLayoutY(154.0);
        PosimenuButton.setPrefSize(150.0, 30.0);
        MenuItem pos1MenuItem = new MenuItem("Chef");
        MenuItem pos2MenuItem = new MenuItem("Sous Chef");
        MenuItem pos3MenuItem = new MenuItem("Server");
        MenuItem pos4MenuItem = new MenuItem("Manager");
        MenuItem pos5MenuItem = new MenuItem("Waiter");
        PosimenuButton.getItems().addAll(pos1MenuItem, pos2MenuItem,pos3MenuItem,pos4MenuItem,pos5MenuItem);

        for (MenuItem MI:PosimenuButton.getItems()){
            MI.setOnAction(Event->{
                PosimenuButton.setText(MI.getText());
            });
        }




        Button addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setLayoutX(320.0);
        addEmployeeButton.setLayoutY(290.0);
        addEmployeeButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        addEmployeeButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        addEmployeeButton.setOnAction(Event->{
            String fullName = fullNameTextField.getText();
            String phone = phoneNumberTextField.getText();
            String position = PosimenuButton.getText();
            String salary = SalaryTextField.getText();
            float sal = Float.parseFloat(salary);

            Employee emp = new Employee();
            try {
                emp.AddEmployee(DBName,fullName,position,phone,sal);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            fullNameTextField.setText("");
            phoneNumberTextField.setText("");
            PosimenuButton.setText("Position");
            SalaryTextField.setText("");



        });


        anchorPane2.getChildren().addAll(fullNameTextField, SalaryTextField, PosimenuButton,
                phoneNumberTextField, fullNameLabel, positionLabel, phoneNumberLabel,
                salaryLabel, addEmployeeButton,titleLabel);



        return anchorPane2;
    }

    public Pane Admin(){
        Pane rootPane = new Pane();
        rootPane.setPrefSize(701.0, 475.0);


        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPositions(0.3);
        splitPane.setLayoutX(1.0);
        splitPane.setLayoutY(1.0);
        splitPane.setPrefSize(701.0, 475.0);

        AnchorPane anchorPane2 = AdminAddEMP();
        anchorPane2.setStyle("-fx-background-color: #ECF8F9;"); // Set the background color to red

        AnchorPane anchorPane1 = Commun(anchorPane2);
        splitPane.getItems().addAll(anchorPane1, anchorPane2);
        rootPane.getChildren().add(splitPane);
        return rootPane;
    }
    public Pane AdminLogInInterface(){
        // Create the root pane
        Pane rootPane = new Pane();
        rootPane.setPrefSize(701, 475);
        Image backgroundImage = new Image("C:\\Users\\mk oussen\\Downloads\\image.jpg");

        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitHeight(600.0);
        imageView.setFitWidth(800.0);
        imageView.setLayoutY(-1.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);



        Label titleLabel = new Label(" Admin Log In ");
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setLayoutX(202);
        titleLabel.setLayoutY(79);
        titleLabel.setPrefHeight(51);
        titleLabel.setPrefWidth(285);
        titleLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        titleLabel.getStyleClass().add("title-label");
        titleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titleLabel.setFont(new Font(30));

        TextField fullNameTextField = new TextField();
        fullNameTextField.setLayoutX(357);
        fullNameTextField.setLayoutY(148);
        fullNameTextField.setPrefHeight(32);
        fullNameTextField.setPrefWidth(172);

        PasswordField  passwordField = new PasswordField ();
        passwordField.setPromptText("Enter your password");
        passwordField.setLayoutX(357);
        passwordField.setLayoutY(206);
        passwordField.setPrefHeight(32);
        passwordField.setPrefWidth(172);

        Label UserNameLabel = new Label("UserName");
        UserNameLabel.setAlignment(javafx.geometry.Pos.CENTER);
        UserNameLabel.setLayoutX(152);
        UserNameLabel.setLayoutY(148);
        UserNameLabel.setPrefHeight(32);
        UserNameLabel.setPrefWidth(139);
        UserNameLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        UserNameLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        UserNameLabel.setTextOverrun(javafx.scene.control.OverrunStyle.CENTER_ELLIPSIS);
        UserNameLabel.setFont(new Font(15));

        Label PasswordLabel = new Label("Password");
        PasswordLabel.setAlignment(javafx.geometry.Pos.CENTER);
        PasswordLabel.setLayoutX(152);
        PasswordLabel.setLayoutY(206);
        PasswordLabel.setPrefHeight(32);
        PasswordLabel.setPrefWidth(139);
        PasswordLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        PasswordLabel.setFont(new Font(15));

        Button logInButton = new Button("Log In");
        logInButton.setLayoutX(432);
        logInButton.setLayoutY(251);
        logInButton.setPrefHeight(32);
        logInButton.setPrefWidth(97);
        logInButton.setMnemonicParsing(false);
        logInButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        logInButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        logInButton.setOnAction(actionEvent -> {
            String username = fullNameTextField.getText();
            String pwd = passwordField.getText();

            con = DBUtil.getConnection(username, pwd);
            if (con !=null) {
                Pane newContent = chouseOne(); // Replace this with your own method to create the new content
                rootPane.getChildren().setAll(newContent);
            }
            else {
                //fullNameTextField.setStyle("-fx-text-fill: red;");
                fullNameTextField.setStyle("-fx-border-color: red;");
                passwordField.setStyle("-fx-border-color: red;");
            }
        });

        Button PrevieusButton = new Button("Previeus");
        PrevieusButton.setLayoutX(152);
        PrevieusButton.setLayoutY(251);
        PrevieusButton.setPrefHeight(32);
        PrevieusButton.setPrefWidth(97);
        PrevieusButton.setMnemonicParsing(false);
        PrevieusButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        PrevieusButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        PrevieusButton.setOnAction(actionEvent -> {
            Pane newContent = FirstInterface(); // Replace this with your own method to create the new content
            rootPane.getChildren().setAll(newContent);
        });

        // Add the elements to the root pane
        rootPane.getChildren().addAll(
                imageView,
                titleLabel,
                fullNameTextField,
                passwordField,
                UserNameLabel,
                PasswordLabel,
                logInButton,
                PrevieusButton
        );

        return rootPane;
    }
    public Pane AddOrder() throws SQLException {
        Pane rootPane = new Pane();
        rootPane.setPrefSize(701, 475);
        Image backgroundImage = new Image("C:\\Users\\mk oussen\\Downloads\\image.jpg");

        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitHeight(600.0);
        imageView.setFitWidth(800.0);
        imageView.setLayoutY(-1.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // Create the UI elements
        Label titleLabel = new Label("  Add Orders ");
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setLayoutX(202);
        titleLabel.setLayoutY(79);
        titleLabel.setPrefHeight(51);
        titleLabel.setPrefWidth(285);
        titleLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        titleLabel.getStyleClass().add("title-label");
        titleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titleLabel.setFont(new Font(30));

        Label itemNameLabel = new Label("Item Name");
        itemNameLabel.setAlignment(javafx.geometry.Pos.CENTER);
        itemNameLabel.setLayoutX(148.0);
        itemNameLabel.setLayoutY(141.0);
        itemNameLabel.setPrefHeight(38.0);
        itemNameLabel.setPrefWidth(173.0);
        itemNameLabel.setFont(new Font(16.0));

        MenuButton menuButton = new MenuButton("MenuButton");
        menuButton.setLayoutX(374.0);
        menuButton.setLayoutY(141.0);
        menuButton.setPrefHeight(38.0);
        menuButton.setPrefWidth(173.0);


        Orders order = new Orders();
        ArrayList<String> itemsName = order.restaurantItems(DBName);

        for (String item : itemsName){
            menuButton.getItems().addAll(new MenuItem(item));
        }

        for(MenuItem mt : menuButton.getItems()){
            mt.setOnAction(actionEvent -> {
                menuButton.setText(mt.getText());
            });
        }

        Label quantityLabel = new Label("Quantity");
        quantityLabel.setAlignment(javafx.geometry.Pos.CENTER);
        quantityLabel.setLayoutX(148.0);
        quantityLabel.setLayoutY(206.0);
        quantityLabel.setPrefHeight(38.0);
        quantityLabel.setPrefWidth(173.0);
        quantityLabel.setFont(new Font(16.0));

        TextField quantityTextField = new TextField();
        quantityTextField.setLayoutX(374.0);
        quantityTextField.setLayoutY(206.0);
        quantityTextField.setPrefHeight(38.0);
        quantityTextField.setPrefWidth(173.0);

        Button PrevieusButton = new Button("Previeus");
        PrevieusButton.setLayoutX(181.0);
        PrevieusButton.setLayoutY(310.0);
        PrevieusButton.setPrefHeight(38.0);
        PrevieusButton.setPrefWidth(364.0);
        PrevieusButton.setFont(new Font(16.0));
        PrevieusButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        PrevieusButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        PrevieusButton.setOnAction(Event->{
            Pane newContent = chouseOne(); // Replace this with your own method to create the new content
            rootPane.getChildren().setAll(newContent);
        });

        Button addOrderButton = new Button("Add Order");
        addOrderButton.setLayoutX(181.0);
        addOrderButton.setLayoutY(265.0);
        addOrderButton.setPrefHeight(38.0);
        addOrderButton.setPrefWidth(364.0);
        addOrderButton.setFont(new Font(16.0));
        addOrderButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        addOrderButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        addOrderButton.setOnAction(Event ->{
            String ItemName = menuButton.getText();
            int quantity = Integer.parseInt(quantityTextField.getText());

            Orders ord = new Orders();
            try {
                ord.createOrders(ItemName,quantity,DBName,customers_id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            menuButton.setText("");
            quantityTextField.setText("");

        });

        rootPane.getChildren().addAll(imageView,titleLabel,itemNameLabel, menuButton, quantityLabel, quantityTextField, addOrderButton,PrevieusButton);


        return rootPane;
    }

    public Pane LogInInterface(){
        // Create the root pane
        Pane rootPane = new Pane();
        rootPane.setPrefSize(701, 475);
        Image backgroundImage = new Image("C:\\Users\\mk oussen\\Downloads\\image.jpg");

        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitHeight(600.0);
        imageView.setFitWidth(800.0);
        imageView.setLayoutY(-1.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);



        Label titleLabel = new Label(" Log IN ");
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setLayoutX(202);
        titleLabel.setLayoutY(79);
        titleLabel.setPrefHeight(51);
        titleLabel.setPrefWidth(285);
        titleLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        titleLabel.getStyleClass().add("title-label");
        titleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titleLabel.setFont(new Font(30));

        TextField fullNameTextField = new TextField();
        fullNameTextField.setLayoutX(357);
        fullNameTextField.setLayoutY(148);
        fullNameTextField.setPrefHeight(32);
        fullNameTextField.setPrefWidth(172);

        TextField emailTextField = new TextField();
        emailTextField.setLayoutX(357);
        emailTextField.setLayoutY(206);
        emailTextField.setPrefHeight(32);
        emailTextField.setPrefWidth(172);

        Label fullNameLabel = new Label("Full Name");
        fullNameLabel.setAlignment(javafx.geometry.Pos.CENTER);
        fullNameLabel.setLayoutX(152);
        fullNameLabel.setLayoutY(148);
        fullNameLabel.setPrefHeight(32);
        fullNameLabel.setPrefWidth(139);
        fullNameLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        fullNameLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        fullNameLabel.setTextOverrun(javafx.scene.control.OverrunStyle.CENTER_ELLIPSIS);
        fullNameLabel.setFont(new Font(15));

        Label emailLabel = new Label("Email");
        emailLabel.setAlignment(javafx.geometry.Pos.CENTER);
        emailLabel.setLayoutX(152);
        emailLabel.setLayoutY(206);
        emailLabel.setPrefHeight(32);
        emailLabel.setPrefWidth(139);
        emailLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        emailLabel.setFont(new Font(15));

        Button logInButton = new Button("Log In");
        logInButton.setLayoutX(432);
        logInButton.setLayoutY(251);
        logInButton.setPrefHeight(32);
        logInButton.setPrefWidth(97);
        logInButton.setMnemonicParsing(false);
        logInButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        logInButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        logInButton.setOnAction(actionEvent -> {
            String name = fullNameTextField.getText();
            String email = emailTextField.getText();

            Customer customer = new Customer();
            Boolean isExists = customer.isUserExists(name,email);

            if (isExists) {
                customers_id = customer.getCustomerId(name, email);
                db =1;
                Pane newContent = chouseOne(); // Replace this with your own method to create the new content
                rootPane.getChildren().setAll(newContent);
            }
            else {
                fullNameTextField.setStyle("-fx-border-color: red;");
                emailTextField.setStyle("-fx-border-color: red;");
            }
        });

        Button PrevieusButton = new Button("Previeus");
        PrevieusButton.setLayoutX(152);
        PrevieusButton.setLayoutY(251);
        PrevieusButton.setPrefHeight(32);
        PrevieusButton.setPrefWidth(97);
        PrevieusButton.setMnemonicParsing(false);
        PrevieusButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        PrevieusButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        PrevieusButton.setOnAction(actionEvent -> {
            Pane newContent = FirstInterface(); // Replace this with your own method to create the new content
            rootPane.getChildren().setAll(newContent);
        });

        // Add the elements to the root pane
        rootPane.getChildren().addAll(
                imageView,
                titleLabel,
                fullNameTextField,
                emailTextField,
                fullNameLabel,
                emailLabel,
                logInButton,
                PrevieusButton
        );

        return rootPane;
    }

    public  Pane SignInInterface(){
        // Create the root pane
        Pane rootPane = new Pane();
        rootPane.setPrefSize(701, 475);
        Image backgroundImage = new Image("C:\\Users\\mk oussen\\Downloads\\image.jpg");

        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitHeight(600.0);
        imageView.setFitWidth(800.0);
        imageView.setLayoutY(-1.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);



        // Create the UI elements
        Label titleLabel = new Label(" SIGN IN ");
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setLayoutX(202);
        titleLabel.setLayoutY(79);
        titleLabel.setPrefHeight(51);
        titleLabel.setPrefWidth(285);
        titleLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        titleLabel.getStyleClass().add("title-label");
        titleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titleLabel.setFont(new Font(30));

        TextField fullNameTextField = new TextField();
        fullNameTextField.setLayoutX(345);
        fullNameTextField.setLayoutY(148);
        fullNameTextField.setPrefHeight(32);
        fullNameTextField.setPrefWidth(172);

        TextField emailTextField = new TextField();
        emailTextField.setLayoutX(345);
        emailTextField.setLayoutY(206);
        emailTextField.setPrefHeight(32);
        emailTextField.setPrefWidth(172);

        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setLayoutX(345);
        phoneNumberTextField.setLayoutY(262);
        phoneNumberTextField.setPrefHeight(32);
        phoneNumberTextField.setPrefWidth(172);

        TextField AdressTextField = new TextField();
        AdressTextField.setLayoutX(345);
        AdressTextField.setLayoutY(322);
        AdressTextField.setPrefHeight(32);
        AdressTextField.setPrefWidth(172);

        Label fullNameLabel = new Label("Full Name");
        fullNameLabel.setAlignment(javafx.geometry.Pos.CENTER);
        fullNameLabel.setLayoutX(152);
        fullNameLabel.setLayoutY(148);
        fullNameLabel.setPrefHeight(32);
        fullNameLabel.setPrefWidth(139);
        fullNameLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        fullNameLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        fullNameLabel.setTextOverrun(javafx.scene.control.OverrunStyle.CENTER_ELLIPSIS);
        fullNameLabel.setFont(new Font(15));

        Label emailLabel = new Label("Email");
        emailLabel.setAlignment(javafx.geometry.Pos.CENTER);
        emailLabel.setLayoutX(152);
        emailLabel.setLayoutY(206);
        emailLabel.setPrefHeight(32);
        emailLabel.setPrefWidth(139);
        emailLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        emailLabel.setFont(new Font(15));

        Label phoneNumberLabel = new Label("Phone Number");
        phoneNumberLabel.setAlignment(javafx.geometry.Pos.CENTER);
        phoneNumberLabel.setLayoutX(152);
        phoneNumberLabel.setLayoutY(262);
        phoneNumberLabel.setPrefHeight(32);
        phoneNumberLabel.setPrefWidth(139);
        phoneNumberLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        phoneNumberLabel.setFont(new Font(15));

        Label AdressLabel = new Label("Adress");
        AdressLabel.setAlignment(javafx.geometry.Pos.CENTER);
        AdressLabel.setLayoutX(152);
        AdressLabel.setLayoutY(322);
        AdressLabel.setPrefHeight(32);
        AdressLabel.setPrefWidth(139);
        AdressLabel.setStyle("-fx-background-color: rgb(255,255,255);");
        AdressLabel.setFont(new Font(15));



        Button signInButton = new Button("Sign In");
        signInButton.setLayoutX(416);
        signInButton.setLayoutY(368);
        signInButton.setPrefHeight(32);
        signInButton.setPrefWidth(97);
        signInButton.setMnemonicParsing(false);
        signInButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        signInButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        signInButton.setOnAction(actionEvent -> {
            String name = fullNameTextField.getText();
            String email = emailTextField.getText();
            String phone_number = phoneNumberTextField.getText();
            String address = AdressTextField.getText();

            Customer customerCreator = new Customer();
            customerCreator.createCustomer(name, email, phone_number, address);


            Pane newContent = LogInInterface(); // Replace this with your own method to create the new content
            rootPane.getChildren().setAll(newContent);
        });

        Button PrevieusButton = new Button("Previeus");
        PrevieusButton.setLayoutX(152);
        PrevieusButton.setLayoutY(368);
        PrevieusButton.setPrefHeight(32);
        PrevieusButton.setPrefWidth(97);
        PrevieusButton.setMnemonicParsing(false);
        PrevieusButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        PrevieusButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        PrevieusButton.setOnAction(actionEvent -> {
            Pane newContent = FirstInterface(); // Replace this with your own method to create the new content
            rootPane.getChildren().setAll(newContent);
        });

        // Add the elements to the root pane
        rootPane.getChildren().addAll(

                imageView,
                titleLabel,
                fullNameTextField,
                emailTextField,
                phoneNumberTextField,
                fullNameLabel,
                emailLabel,
                phoneNumberLabel,
                AdressLabel,
                AdressTextField,
                PrevieusButton,
                signInButton
        );

        return rootPane;
    }
    public Pane FirstInterface(){
        Pane rootPane = new Pane();
        Image backgroundImage = new Image("C:\\Users\\mk oussen\\Downloads\\image.jpg");
        rootPane.setPrefSize(701.0, 475.0);

        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitHeight(600.0);
        imageView.setFitWidth(800.0);
        imageView.setLayoutY(-1.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        Label titleLabel = new Label();
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setLayoutX(140.0);
        titleLabel.setLayoutY(79.0);
        titleLabel.setPrefHeight(51.0);
        titleLabel.setPrefWidth(400.0);
        titleLabel.getStyleClass().add("title-label");
        titleLabel.setText("WELCOME IN AGADIR");
        titleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titleLabel.setFont(new Font(30.0));
        titleLabel.setBackground(new Background(new BackgroundFill(Color.SILVER, null, null)));
        AnchorPane.setBottomAnchor(titleLabel, 353.0);
        AnchorPane.setLeftAnchor(titleLabel, 215.0);
        AnchorPane.setRightAnchor(titleLabel, 214.0);
        AnchorPane.setTopAnchor(titleLabel, 79.0);

        Button signUpButton = new Button();
        signUpButton.setLayoutX(372.0);
        signUpButton.setLayoutY(180.0);
        signUpButton.setMnemonicParsing(false);
        signUpButton.setPrefHeight(32.0);
        signUpButton.setPrefWidth(210.0);
        signUpButton.setText("SIGN IN");
        signUpButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        signUpButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        signUpButton.setOnAction(actionEvent -> {
            Pane newContent = SignInInterface(); // Replace this with your own method to create the new content
            rootPane.getChildren().setAll(newContent);
        });


        Button logInButton = new Button();
        logInButton.setLayoutX(372.0);
        logInButton.setLayoutY(244.0);
        logInButton.setMnemonicParsing(false);
        logInButton.setPrefHeight(32.0);
        logInButton.setPrefWidth(210.0);
        logInButton.setText("LOG IN");
        logInButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        logInButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        logInButton.setOnAction(actionEvent -> {
            Pane newContent = LogInInterface(); // Replace this with your own method to create the new content
            rootPane.getChildren().setAll(newContent);
        });


        Button adminButton = new Button();
        adminButton.setLayoutX(372.0);
        adminButton.setLayoutY(308.0);
        adminButton.setMnemonicParsing(false);
        adminButton.setPrefHeight(32.0);
        adminButton.setPrefWidth(210.0);
        adminButton.setText("ADMIN");
        adminButton.setStyle("-fx-background-color: rgb(135, 206, 250); -fx-font-weight: bold;");
        adminButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        adminButton.setOnAction(actionEvent -> {
            Pane newContent = AdminLogInInterface(); // Replace this with your own method to create the new content
            rootPane.getChildren().setAll(newContent);
        });


        TextFlow textFlow = new TextFlow();
        textFlow.setLayoutX(49.0);
        textFlow.setLayoutY(202.0);
        textFlow.setLineSpacing(1.0);
        textFlow.setPrefHeight(122.0);
        textFlow.setPrefWidth(260.0);
        textFlow.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);


        Text text = new Text();
        text.setText("RESTAURANT MANAGEMENT");
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setWrappingWidth(241.791015625);
        text.setFont(new Font(30.0));
        text.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        textFlow.getChildren().add(text);

        rootPane.getChildren().addAll(imageView, titleLabel, signUpButton, logInButton, adminButton, textFlow);

        return rootPane;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Pane rootPane= FirstInterface();
        Scene scene = new Scene(rootPane);
        stage.setTitle("Restaurant Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
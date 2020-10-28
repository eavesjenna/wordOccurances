package Eaves_module7_word_occurence;
//Author Name: Jenna Eaves
// Date: 10/04/2020
//Program Name: Eaves_module7_word_occurence
//Purpose: GUI that displays top 20 words in play Macbeth

import javafx.geometry.Insets;
import java.awt.TextField;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Eaves_module7_word_occurence extends Application {
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		TextField tb = new TextField();
		
		Label label = new Label();
		label.setLayoutX(300);
		label.setLayoutY(100);
		label.setFont(Font.font("Times New Roman"
				, FontWeight.NORMAL,FontPosture.ITALIC,24));
		
		Button startButton = new Button("Start");
			startButton.setLayoutX(120);
			startButton.setOnAction((ActionEvent e) -> {
				try {
					Eaves_text_anaylzer();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			
			VBox RBpane = new VBox();
			RBpane.setPadding(new Insets(10,10,10,10));
			RBpane.setSpacing(5);
			RBpane.getChildren().addAll(startButton);
			
			//Verticle Box
			
			VBox vbox = new VBox();
			vbox.setMaxWidth(700);
			vbox.setSpacing(5);
			vbox.setPadding(new Insets(10,10,10,10));
			
				vbox.getChildren().addAll(label, RBpane);
				
			((Group)scene.getRoot()).getChildren().addAll(vbox);
			
			//Prepare Stage
			
			stage.setScene(scene);
			stage.setTitle("Word Occurences");
			stage.setWidth(700);
			stage.setHeight(550);
			stage.show();
	}
	
	private void Eaves_text_anaylzer() throws FileNotFoundException, Exception {
		
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>(); 

		URL url = new URL("http://shakespeare.mit.edu/macbeth/full.html");
		
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			
			String currentLine = reader.readLine();
			
			while(currentLine != null) {
				String[] words = currentLine.toLowerCase().split(" ");
				
				for(String word : words) {
					if(wordCount.containsKey(word)) {
						wordCount.put(word, wordCount.get(word)+1);
					}else {
						wordCount.put(word, 1);
					}
				}
				currentLine = reader.readLine();
			}
		}	catch(Exception e) {
			System.out.println(e);
		}
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.addAll(wordCount.values());
		
		Collections.sort(values, Collections.reverseOrder());
		
		int last_i = -1;
		
		for(Integer i : values.subList(4, 23)) {
			if (last_i == i)
				continue;
			last_i = i;
			
			for(String s : wordCount.keySet()) {
				
				if (wordCount.get(s) == i)
					System.out.println(s + " " + i);
			}
		}
	}

	public static void main(String[] args) throws 
		FileNotFoundException {
		launch(args);
	}
}

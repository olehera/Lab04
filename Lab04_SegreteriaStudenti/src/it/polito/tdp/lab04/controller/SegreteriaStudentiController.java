package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cbxCorso;

    @FXML
    private Button btnCercaIscrittiCorso;
    
    @FXML
    private Button btnVerifica;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    private Model m;
    
    public void setModel(Model m) {
    	this.m = m;
    	cbxCorso.getItems().addAll(m.getElencoCorsi());
    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	Studente s = null;
    	try {
    		s = m.getS(Integer.parseInt(txtMatricola.getText().trim()));
    	} catch(NumberFormatException nfe) {
    		txtResult.appendText("Matricola inserita in modo errato!\n");
    		return;
    	}
    	
    	if (s != null)
    		txtResult.setText(m.getStudenteCorsi(s.getMatricola()));
    	else 
    		txtResult.appendText("Studente non trovato!\n");

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	txtResult.clear();
    	String s = cbxCorso.getValue();

    	if (s.compareTo("")==0)
    		txtResult.appendText("Selezionare un corso dal menù a tendina\n");
    	else
    		txtResult.appendText(m.getIscrittiCorso(s));
    }

    @FXML
    void doCercaMatricola(ActionEvent event) {
    	txtResult.clear();
    	Studente s = null;
    	try {
    		s = m.getS(Integer.parseInt(txtMatricola.getText().trim()));
    	} catch(NumberFormatException nfe) {
    		txtResult.appendText("Matricola inserita in modo errato!\n");
    		return;
    	}
    	
    	if (s != null) {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    	}
    	else 
    		txtResult.appendText("Studente non trovato!\n");
    }
    
    @FXML
    void doVerifica(ActionEvent event) {
    	txtResult.clear();
    	String nomeCorso = cbxCorso.getValue();
    	int matricola = 0;
    	
    	if (nomeCorso.compareTo("")==0) {
    		txtResult.appendText("Selezionare un corso dal menù a tendina\n"); 
    		return; 
    	}
    	
    	try {
            matricola = Integer.parseInt(txtMatricola.getText().trim());
    	} catch(NumberFormatException nfe) {
    		txtResult.appendText("Matricola inserita in modo errato!\n");
    		return;
    	}
    	
    	if (m.getS(matricola)==null) {
    		txtResult.appendText("Studente non trovato!\n"); 
    		return;
    	}
    	
    	if (m.isIscritto(nomeCorso, matricola)) 
    		txtResult.appendText("Lo studente "+matricola+" è iscritto al corso "+nomeCorso+"\n");
    	else 
    		txtResult.appendText("Lo studente "+matricola+" non è iscritto al corso "+nomeCorso+"\n");
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtResult.clear();
    	String nomeCorso = cbxCorso.getValue();
    	int matricola = 0;
    	
    	if (nomeCorso.compareTo("")==0) {
    		txtResult.appendText("Selezionare un corso dal menù a tendina\n"); 
    		return; 
    	}
    	
    	try {
            matricola = Integer.parseInt(txtMatricola.getText().trim());
    	} catch(NumberFormatException nfe) {
    		txtResult.appendText("Matricola inserita in modo errato!\n");
    		return;
    	}
    	
    	if (m.getS(matricola)==null) {
    		txtResult.appendText("Studente non trovato!\n"); 
    		return;
    	}
    	
    	if (m.isIscritto(nomeCorso, matricola)) {
    		txtResult.appendText("Lo studente "+matricola+" è già iscritto al corso "+nomeCorso+"\n");
    		return; 
    	} else if (m.iscrivi(nomeCorso, matricola))
    		txtResult.appendText("Lo studente "+matricola+" è stato iscritto al corso "+nomeCorso+"\n");
    	else
    		txtResult.appendText("Lo studente "+matricola+" non è stato iscritto al corso "+nomeCorso+"\n");
    }

    @FXML
    void doReset(ActionEvent event) {
    	cbxCorso.setValue("");
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    }

    @FXML
    void initialize() {
        assert cbxCorso != null : "fx:id=\"cbxCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnVerifica != null : "fx:id=\"btnVerifica\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnMatricola != null : "fx:id=\"btnMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		cbxCorso.setValue("");
    }
}


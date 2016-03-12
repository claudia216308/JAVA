package com.claudia.myfilezipper.affichage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelChemin extends JPanel {

	JTextField nom;
	JLabel label;
	JLabel result;
	JButton boutonDestination;
	
	public PanelChemin(){
		
	    nom = new JTextField();
	    nom.setPreferredSize(new Dimension(200, 25));
	    label = new JLabel("Archive location :");
	    boutonDestination = new JButton("Explore");
	    result = new JLabel("");
	    
	    
	    this.add(label);
	    this.add(nom);	  
	    this.add(boutonDestination);
	    this.add(result);
	    
	    
	    //listener 
	    boutonDestination.addActionListener(new DestinationListener());
	    
	}
	
	public String getInputPath(){
		return nom.getText();
	}

	public void setLabelResult(String texte){
		result.setText(texte);
	}
	
	//Classe interne
	public class DestinationListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser choix = new JFileChooser();
			choix.setDialogTitle("Destination folder");
			choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //on ne peut sélectionner qu'un dossier 
			
			int retour = choix.showOpenDialog(null);
			
			if(retour == JFileChooser.APPROVE_OPTION){
				nom.setText(choix.getSelectedFile().getAbsolutePath());
			}
			
		}
		
	}
}

package com.claudia.myfilezipper.affichage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.claudia.myfilezipper.outils.ZipFile;

public class Fenetre extends JFrame {
	
	JTable tableau;
	JButton ajouter = new JButton("Add file");
	JButton zip  = new JButton("Zip this list");
	PanelChemin panChemin;
	Object[][] data = new Object[10][3];
	ArrayList <File> listeFichiers = new ArrayList<File>();
	
	
	
	public Fenetre(){
		
		//Fenetre
		this.setTitle("My File Zipper");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	
		//Tableau
		String titre[] = {"File name", "Size", "Location"};
		
		JTable tableau = new JTable(data, titre);
		
		panChemin = new PanelChemin();
		
		//panel qui contient tableau + panelChemin
		JPanel tab = new JPanel();
		tab.add(new JScrollPane(tableau), BorderLayout.CENTER);
		tab.add(panChemin, BorderLayout.SOUTH);
		
		
		//panel qui contient les boutons 
		JPanel panelBouton = new JPanel();
		panelBouton.add(ajouter);
		panelBouton.add(zip);
		
		
		//clic sur les boutons  
		ajouter.addActionListener(new AddBoutonListener());
		zip.addActionListener(new ZipBoutonListener());
			
		
		//ajout des éléments à la fenêtre
		this.getContentPane().add(tab, BorderLayout.CENTER);		
		this.getContentPane().add(panelBouton, BorderLayout.SOUTH);	
	
	}
	
	
	
	//Classe interne listener 	
	public class AddBoutonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			//Choix du fichier 
			JFileChooser choix = new JFileChooser();		
			choix.setMultiSelectionEnabled(true) ;
			
			int retour = choix.showOpenDialog(null);
			
			if(retour == JFileChooser.APPROVE_OPTION){
				
			   // des fichiers ont été choisis 
				File[] fs = choix.getSelectedFiles();
			
				//nombres de lignes avec des valeurs dans le tableau data 
				int curseur=0;
				while(data[curseur][0]!= null){
					curseur++;
				}
				
				System.out.println("Curseur : ligne " + curseur);
				
				int j= 0;
				//ajout des nouvelles données à la fin du tableau
				for(int i = curseur; i< (fs.length + curseur) ; i++){
					
					//ajout des fichiers à la liste 
					listeFichiers.add(fs[j]);
				
					//ajout au tableau
					data[i][0] = fs[j].getName();
					data[i][1] = fs[j].getTotalSpace();
					data[i][2] = fs[j].getAbsolutePath();
					
					j++;
				}		
				
			} //if 	
	
		}
	}//listener

	
	public class ZipBoutonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
	
			String chemin = panChemin.getInputPath() + "/archive.zip";
			System.out.println(chemin);
			String resultat = ZipFile.zipAllFiles(chemin, listeFichiers);
			
			panChemin.setLabelResult(resultat);
		}
		
	}
	

	
	
}


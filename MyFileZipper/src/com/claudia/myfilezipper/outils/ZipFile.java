package com.claudia.myfilezipper.outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {

	static FileOutputStream fos;
	static ZipOutputStream zos;
		

	public static String zipAllFiles(String zipPath, ArrayList<File> fileList){
					
			try {
				
				zipPath= zipPath.replace("/", "//"); //permet de modifier le chemin du fichier pour échapper le caractère / 
				
				fos = new FileOutputStream(zipPath);
				zos = new ZipOutputStream(fos);
				
				for( File filePath: fileList){
					
					//entrée Zip 
					ZipEntry entree = new ZipEntry(filePath.getName());
					zos.putNextEntry(entree);
					
					//lecture du fichier 
					FileInputStream fis = new FileInputStream(filePath);
					byte[] buffer = new byte[1024];
					int bytesRead = 0;
					
					
					while((bytesRead = fis.read(buffer)) > 0 ){
						
						zos.write(buffer, 0, bytesRead);
					}
					
					//fermeture de l'entrée
					zos.closeEntry();		
					
				}//for
				
				
				//fermeture de l'archive 
				zos.close();
				fos.close();
			 
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "Invalide path for archive location";
			} catch (IOException e) {
				e.printStackTrace();
				return "An error occurs, please try again";
			}
			
			return "Archive create successfully";
						
		} 
}



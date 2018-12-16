/*
 * LecturaEmpleadoXml.java
 * 
 * Copyright 2018 ERIC <ERIC@DESKTOP-5T1N085>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * Crea un programa Java que lea el documento xml y muestre toda la información que contenga.
 * 
 * Fet per Eric Visier Sánchez
 * 
 */

package dom;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
public class LecturaEmpleadoXml {
	public static void main (String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File ("/home/cf17eric.visier/Escriptori/musica.xml"));
			System.out.printf ("Elemento raíz : %s %n", document.getDocumentElement().getNodeName());
			NodeList albumes = document.getElementsByTagName("album");
			System.out.printf ("Nodos album a recorrer: %d %n", albumes.getLength());
			for (int i = 0; i < albumes.getLength(); i++) {
				Node album = albumes.item(i);
				if (album.getNodeType() == Node.ELEMENT_NODE){
					Element elemento = (Element) album;
					System.out.printf("Autor = %s %n", elemento.getElementsByTagName("autor").item(0).getTextContent());
					System.out.printf(" * Titulo = %s %n", 
							elemento.getElementsByTagName("titulo").item(0).getTextContent());
					System.out.printf(" * Formato = %s %n", 
							elemento.getElementsByTagName("formato").item(0).getTextContent());
				}
			}
		}catch (Exception e) {e.printStackTrace();}
	}
}

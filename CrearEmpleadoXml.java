/*
 * CrearEmpleadoXml.java
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
 * Copia el programa anterior y ejecútalo
 * 
 * Fet per Eric Visier Sánchez
 * 
 */

package dom;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;  
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;
public class CrearEmpleadoXml {
	public static void main (String args[]) throws IOException {
		File fichero = new File ("/home/cf17eric.visier/Escriptori/Empleados.txt");
		BufferedReader file = new BufferedReader ( new FileReader(fichero)); 
		String[] partes1;
		ArrayList<String> apellidos = new ArrayList<String>();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<Integer> deps = new ArrayList<Integer>();
		ArrayList<Double> salarios = new ArrayList<Double>();
		String linea;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument (null,"Empleados", null);
			document.setXmlVersion("1.0");
				while((linea = file.readLine()) != null) {
			       partes1 = linea.split(":");
				   ids.add(Integer.parseInt(partes1[0]));
				   apellidos.add(partes1[1]);
				   deps.add(Integer.parseInt(partes1[2]));
				   salarios.add(Double.parseDouble(partes1[3])); 
				}
			 	file.close();
				
			 	for(int i = 0; i < apellidos.size(); i++) {
					Element raiz = document.createElement ("empleado");
					document.getDocumentElement().appendChild(raiz);
					CrearElemento ("id", Integer.toString(ids.get(i)), raiz, document);
					CrearElemento ("apellido",apellidos.get(i).trim(), raiz, document);
					CrearElemento ("dep", Integer.toString(deps.get(i)), raiz, document);
					CrearElemento ("salario", Double.toString(salarios.get(i)),raiz, document);
				}
				
			Source source = new DOMSource (document);
			Result result = new StreamResult (new java.io.File ("/home/cf17eric.visier/Escriptori/Empleados.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform (source, result);
		} catch (Exception e ) { System.err.println ("Error: " + e);}
		file.close();
	}

	static void CrearElemento (String datoEmpleado, String valor, Element raiz, Document document) {
		Element elem = document.createElement (datoEmpleado);
		Text text = document.createTextNode(valor);
		raiz.appendChild (elem);
		elem.appendChild (text);
	}
}

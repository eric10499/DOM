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

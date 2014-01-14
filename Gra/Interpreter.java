package Gra;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Interpreter {
	
	private String id;
	private String pietro;
	private String klucz1;
	private String klucz2;
	private String klucz3;
	private int dl;
	private static String tab[][];
	
	
	public Interpreter(){
		try {
			 
			File fXmlFile = new File("src/Gra/xml/term.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("rozkaz");
			dl =  nList.getLength();
			tab = new String[dl][5];
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					tab[temp][0] =  eElement.getAttribute("id");
					tab[temp][1] = eElement.getElementsByTagName("pietro").item(0).getTextContent();
					tab[temp][2] =  eElement.getElementsByTagName("klucz1").item(0).getTextContent();
					tab[temp][3] =  eElement.getElementsByTagName("klucz2").item(0).getTextContent();
					tab[temp][4] = eElement.getElementsByTagName("klucz3").item(0).getTextContent();
					
				}
				
				
			}
			
			
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		    
		  }
	
	public int sprawdzPolecenie(String polecenie, int pietro){
		polecenie = polecenie.toLowerCase();
		polecenie = polecenie.replace("ą", "a");
		polecenie = polecenie.replace("ć", "c");
		polecenie = polecenie.replace("ę", "e");
		polecenie = polecenie.replace("ó", "o");
		polecenie = polecenie.replace("ź", "z");
		polecenie = polecenie.replace("ż", "z");
		System.out.println(polecenie);
		
		
		
		int spr = 0;
		int pietropom = 0;
		for (int i=0; i<dl; i++){
			pietropom = Integer.parseInt(tab[i][1]);
			if ((polecenie.contains(tab[i][4])) || (polecenie.contains(tab[i][2]) && polecenie.contains(tab[i][3]))){
				spr = Integer.parseInt(tab[i][0]);
				break;
			}
		}
		
		if (pietropom == 0 || pietropom == pietro){
			return spr;
		}
		else {
			spr = 0;
			return spr;
		}
	}
}
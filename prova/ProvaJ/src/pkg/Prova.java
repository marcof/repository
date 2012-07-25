package pkg;

import it.fotino.java.util.FileOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLEditorKit.Parser;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class Prova {

	public static String getHTML(String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static HashMap<String, String> pageToValues(String page){
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		// TIPO PRODOTTO
		Integer selectTipoStart=page.indexOf("<SELECT CLASS=formin NAME=\"tipo_protezione\">")+44;
		Integer selectTipoEnd=page.indexOf("</SELECT>",selectTipoStart);
		
		String selectTipo=page.substring(selectTipoStart, selectTipoEnd);
		
		Integer selectedIndex=selectTipo.indexOf("option selected");
		selectTipoStart=selectTipo.indexOf(">", selectedIndex)+1;
		selectTipoEnd=selectTipo.indexOf("</option>", selectedIndex);
		
		selectTipo=selectTipo.substring(selectTipoStart, selectTipoEnd);

		//TODO salvare in banca dati
		System.out.println(selectTipo);
		result.put("tipo", selectTipo);
		
		// CATEGORIA PRODOTTO
		Integer selectCategoriaStart=page.indexOf("<SELECT CLASS=formin NAME=\"categoria_prodotto\">",selectTipoEnd)+45;
		Integer selectCategoriaEnd=page.indexOf("</SELECT>",selectCategoriaStart);
		
		String selectCategoria=page.substring(selectCategoriaStart, selectCategoriaEnd);
		
		selectedIndex=selectCategoria.indexOf("option selected");
		selectCategoriaStart=selectCategoria.indexOf(">", selectedIndex)+1;
		selectCategoriaEnd=selectCategoria.indexOf("</option>", selectedIndex);
		
		selectCategoria=selectCategoria.substring(selectCategoriaStart, selectCategoriaEnd);

		//TODO salvare in banca dati
		System.out.println(selectCategoria);
		result.put("categoria", selectCategoria);
		
		// NOME PRODOTTO
		Integer nomeIndex=page.indexOf("<B>Nome del prodotto</B>");
		Integer nomeStart=page.indexOf("VALUE=",nomeIndex)+7;
		Integer nomeEnd=page.indexOf("\"",nomeStart);
		String nome=page.substring(nomeStart, nomeEnd);
		
		//TODO salvare in banca dati
		System.out.println(nome);
		result.put("nome", nome);
		
		// SINONIMO PRODOTTO
		Integer sinonimoIndex=page.indexOf("<B>Sinonimo</B>");
		Integer sinonimoStart=page.indexOf("VALUE=",sinonimoIndex)+7;
		Integer sinonimoEnd=page.indexOf("\"",sinonimoStart);
		String sinonimo=page.substring(sinonimoStart, sinonimoEnd);
		
		//TODO salvare in banca dati
		System.out.println(sinonimo);
		result.put("sinonimo", sinonimo);
		
		// NOME DIALETTALE PRODOTTO
		Integer dialettaleIndex=page.indexOf("<B>Nome dialettale</B>");
		Integer dialettaleStart=page.indexOf("VALUE=",dialettaleIndex)+7;
		Integer dialettaleEnd=page.indexOf("\"",dialettaleStart);
		String dialettale=page.substring(dialettaleStart, dialettaleEnd);
		
		//TODO salvare in banca dati
		System.out.println(dialettale);
		result.put("nome_dialettale", dialettale);
		
		// TERRITORIO
		Integer selectTerritorioStart=page.indexOf("<SELECT CLASS=formin NAME=\"territorio_produzione\">",dialettaleEnd)+49;
		Integer selectTerritorioEnd=page.indexOf("</SELECT>",selectTerritorioStart);
		
		String selectTerritorio=page.substring(selectTerritorioStart, selectTerritorioEnd);
		
		selectedIndex=selectTerritorio.indexOf("option selected");
		selectTerritorioStart=selectTerritorio.indexOf(">", selectedIndex)+1;
		selectTerritorioEnd=selectTerritorio.indexOf("</option>", selectedIndex);
		
		selectTerritorio=selectTerritorio.substring(selectTerritorioStart, selectTerritorioEnd);

		//TODO salvare in banca dati
		System.out.println(selectTerritorio);
		result.put("territorio", selectTerritorio);
		
		// DESCRIZIONE
		Integer descrizioneIndex=page.indexOf("<B>Descrizione prodotto</B>");
		Integer descrizioneStart=page.indexOf("TEXTAREA",descrizioneIndex)+1;
		descrizioneStart=page.indexOf(">", descrizioneStart)+1;
		Integer descrizioneEnd=page.indexOf("<",descrizioneStart);
		String descrizione=page.substring(descrizioneStart, descrizioneEnd);
		
		//TODO salvare in banca dati
		System.out.println(descrizione);
		result.put("descrizione", descrizione);
		
		// LAVORAZIONE
		Integer lavorazioneIndex=page.indexOf("<B>Descrizione delle metodiche di lavorazione, conservazione e stagionatura</B>");
		Integer lavorazioneStart=page.indexOf("TEXTAREA",lavorazioneIndex)+1;
		lavorazioneStart=page.indexOf(">", lavorazioneStart)+1;
		Integer lavorazioneEnd=page.indexOf("<",lavorazioneStart);
		String lavorazione=page.substring(lavorazioneStart, lavorazioneEnd);
		
		//TODO salvare in banca dati
		System.out.println(lavorazione);
		result.put("lavorazione", lavorazione);
		
		// TRADIZIONE
		Integer tradizioneIndex=page.indexOf("<B>Elementi che comprovano la tradizionalità</B>");
		Integer tradizioneStart=page.indexOf("TEXTAREA",tradizioneIndex)+1;
		tradizioneStart=page.indexOf(">", tradizioneStart)+1;
		Integer tradizioneEnd=page.indexOf("<",tradizioneStart);
		String tradizione=page.substring(tradizioneStart, tradizioneEnd);
		
		//TODO salvare in banca dati
		System.out.println(tradizione);
		result.put("tradizione", tradizione);
		
		// DISCIPLINARE
		Integer disciplinareIndex=page.indexOf("<B>Disciplinare di produzione</B>");
		Integer disciplinareStart=page.indexOf("TEXTAREA",disciplinareIndex)+1;
		disciplinareStart=page.indexOf(">", disciplinareStart)+1;
		Integer disciplinareEnd=page.indexOf("<",disciplinareStart);
		String disciplinare=page.substring(disciplinareStart, disciplinareEnd);
		
		//TODO salvare in banca dati
		System.out.println(disciplinare);
		result.put("disciplinare", disciplinare);
		
		// RICETTE
		Integer ricetteIndex=page.indexOf("<B>Ricette riferibili al prodotto</B>");
		Integer ricetteStart=page.indexOf("TEXTAREA",ricetteIndex)+1;
		ricetteStart=page.indexOf(">", ricetteStart)+1;
		Integer ricetteEnd=page.indexOf("<",ricetteStart);
		String ricette=page.substring(ricetteStart, ricetteEnd);
		
		//TODO salvare in banca dati
		System.out.println(ricette);
		result.put("ricette", ricette);
		
		// ANEDDOTI
		Integer aneddotiIndex=page.indexOf("<B>Aneddoti riferibili al prodotto</B>");
		Integer aneddotiStart=page.indexOf("TEXTAREA",aneddotiIndex)+1;
		aneddotiStart=page.indexOf(">", aneddotiStart)+1;
		Integer aneddotiEnd=page.indexOf("<",aneddotiStart);
		String aneddoti=page.substring(aneddotiStart, aneddotiEnd);
		
		//TODO salvare in banca dati
		System.out.println(aneddoti);
		result.put("aneddoti", aneddoti);
		
		// PROVERBI
		Integer proverbiIndex=page.indexOf("<B>Proverbi riferibili al prodotto</B>");
		Integer proverbiStart=page.indexOf("TEXTAREA",proverbiIndex)+1;
		proverbiStart=page.indexOf(">", proverbiStart)+1;
		Integer proverbiEnd=page.indexOf("<",proverbiStart);
		String proverbi=page.substring(proverbiStart, proverbiEnd);
		
		//TODO salvare in banca dati
		System.out.println(proverbi);
		result.put("proverbi", proverbi);
		
		// NOTE
		Integer noteIndex=page.indexOf("<B>Note </B>");
		Integer noteStart=page.indexOf("TEXTAREA",noteIndex)+1;
		noteStart=page.indexOf(">", noteStart)+1;
		Integer noteEnd=page.indexOf("<",noteStart);
		String note=page.substring(noteStart, noteEnd);
		
		//TODO salvare in banca dati
		System.out.println(note);
		result.put("note", note);
		
		return result;
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		StringBuilder stringBuilder=new StringBuilder();
		
		for (int i = 5; i < 317; i++) {

			String page=getHTML("http://qualita.assagricalabria.it/prg_main.php?parametro=vedi_articoli&id="+i+"&dir=0&centro=prg_prodotti&guest=ok");

			stringBuilder.append("\nID = "+i+"\n");
			Map<String, String> values=pageToValues(page);
			
			for (String key : values.keySet()) {
				stringBuilder.append(key+" = "+values.get(key)+"\n");
			}

		}
		
		FileOperations.writeStringToFile(stringBuilder.toString(), new File("C:/calabria.txt"));
		

	}

}


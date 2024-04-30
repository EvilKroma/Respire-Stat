
package vue;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import controleur.ConvertCSV;
import controleur.StatEtab;
import model.Etablissement;

public class FenetrePrincipale extends JFrame{
	private static final long serialVersionUID = 1L;
	private String csvPath;
	private static String csvFileName = "ecoles-creches-idf.csv";
	public FenetrePrincipale(){
		super();
		build();
	}
	
	private void build(){
		File f;
		String message="";
        do {
        	csvPath = "C:/Users/deneu/eclipse-workspace/Projet-Respire/RespireStatEtu";
			f = new File(csvPath+"/"+csvFileName);
			if(!f.exists())
				message = "Le fichier n'a pas été trouvé.";
			else
				message = "Le fichier a été trouvé.";
			JOptionPane.showMessageDialog(null, message+"\n"+csvPath+"/"+csvFileName);	
        }while(!f.exists());
		ConvertCSV.chargerEtablissements(csvPath+"/"+csvFileName);
		
		setTitle("RespireStat");
		setSize(960,820);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPaneHomePage());
	}
	private JPanel buildContentPaneHomePage(){
		System.out.println("Build content pane");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
	
		//ONGLETS
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		onglets.setPreferredSize(new Dimension(900,780));

		//ONGLET 1
		JPanel onglet1 = new JPanel();
		JLabel label;
		label = new JLabel();
		label.setText("<html><body><p style='text-align:center'>Ouverture du fichier"+csvPath+"/"+csvFileName+"<br>Bienvenue sur l'application RespireStat<br /><br />Cliquez sur un des onglets pour accéder aux statistiques</p></body></html>");
		panel.add(label);
		onglet1.add(label);


		onglets.addTab("Accueil", onglet1);


		//ONGLET 2
		JPanel onglet2 = new JPanel();
		JLabel labelTableau= new JLabel();
		Etablissement etabNO2,etabPM10,etabPM25;
		JTable table;
		JScrollPane spane;
		TableauStat1 tab1;

		onglet2.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		for(int annee=2012; annee<=2017; annee++) {

			labelTableau.setText(" " + annee);

			etabNO2 = StatEtab.getPlusPolluantNO2(ConvertCSV.listeEtab, annee);
			etabPM10 = StatEtab.getPlusPolluantPM10(ConvertCSV.listeEtab, annee);			
			etabPM25 = StatEtab.getPlusPolluantPM25(ConvertCSV.listeEtab, annee);
			
			if(etabNO2!=null && etabPM10!=null && etabPM25!=null) {
				Etablissement[] etabs = new Etablissement[3];
				etabs[0] = etabNO2;
				etabs[1] = etabPM10;
				etabs[2] = etabPM25;
				tab1 = new TableauStat1(etabs, annee);

				table = new JTable(tab1);
				spane = new JScrollPane(table);

				table.setCellSelectionEnabled(false);

				table.setPreferredSize(new Dimension(500,96));
				table.setPreferredScrollableViewportSize(table.getPreferredSize());
				table.setFillsViewportHeight(true);

				//onglet2.add(labelTableau,c);
				onglet2.add(spane,c);
				c.gridy++;
			}
			
		}

		onglets.addTab("Les plus pollués", onglet2);

		//ONGLET 3
		JPanel onglet3 = new JPanel();

		HashMap<String, Double> moyenneVillesNO2 = new HashMap<String, Double>();
		HashMap<String, Double> moyenneVillesPM10 = new HashMap<String, Double>();
		HashMap<String, Double> moyenneVillesPM25 = new HashMap<String, Double>();
		for(String ville : ConvertCSV.listeVilles) {
			moyenneVillesNO2.put(ville, StatEtab.getMoyennePolluantNO2Ville(ConvertCSV.listeEtab, ville, 2017));
			moyenneVillesPM10.put(ville, StatEtab.getMoyennePolluantPM10Ville(ConvertCSV.listeEtab, ville, 2017));
			moyenneVillesPM25.put(ville, StatEtab.getMoyennePolluantPM25Ville(ConvertCSV.listeEtab, ville, 2017));
		}

		TableauStat2 tab2 = new TableauStat2(moyenneVillesNO2, moyenneVillesPM10,moyenneVillesPM25);

		table = new JTable(tab2);
		spane = new JScrollPane(table);

		onglet3.add(spane);

		onglets.addTab("Moyenne par ville 2017", onglet3);

		//ONGLET 4
		//TODO Faire l'onglet 4 : il ressemble beaucoup à l'onglet 3 !
		JPanel onglet4 = new JPanel();
		
		
		HashMap<String, Double> moyenneDptNO2 = new HashMap<String, Double>();
		HashMap<String, Double> moyenneDptPM10 = new HashMap<String, Double>();
		HashMap<String, Double> moyenneDptPM25 = new HashMap<String, Double>();
		for(String dpt : ConvertCSV.listeDepartements) {
			moyenneDptNO2.put(dpt, StatEtab.getMoyennePolluantNO2Dpt(ConvertCSV.listeEtab, dpt, 2017));
			moyenneDptPM10.put(dpt, StatEtab.getMoyennePolluantPM10Dpt(ConvertCSV.listeEtab, dpt, 2017));
			moyenneDptPM25.put(dpt, StatEtab.getMoyennePolluantPM25Dpt(ConvertCSV.listeEtab, dpt, 2017));
		}

		TableauStat3 tab3 = new TableauStat3(moyenneDptNO2, moyenneDptPM10,moyenneDptPM25);

		table = new JTable(tab3);
		spane = new JScrollPane(table);

		onglet4.add(spane);

		onglets.addTab("Moyenne par département en 2017", onglet4);


		panel.add(onglets);
		
		


		
	
	//ONGLET 5
	//TODO Faire l'onglet 5 : Pour chaque département, donnez le pourcentage d'évolution (en moyenne de tous les établissements) de chaque polluant entre chaque année de 2012 à 2017.
		
		JPanel onglet5 = new JPanel();
		JLabel labelTableau2 = new JLabel();
		Etablissement etabsNO2,etabsPM10,etabsPM25;
		JTable table5;
		JScrollPane spane2;
		TableauStat4 tab4;

		onglet5.setLayout(new GridBagLayout());
		GridBagConstraints a = new GridBagConstraints();
		a.gridy = 0;
		for(int annee=2012; annee<=2017; annee++) {

			labelTableau2.setText(" " + annee);

			etabsNO2 = StatEtab.getPlusPolluantNO2(ConvertCSV.listeEtab, annee);
			etabsPM10 = StatEtab.getPlusPolluantPM10(ConvertCSV.listeEtab, annee);			
			etabsPM25 = StatEtab.getPlusPolluantPM25(ConvertCSV.listeEtab, annee);
			
			if(etabsNO2!=null && etabsPM10!=null && etabsPM25!=null) {
				Etablissement[] etabs = new Etablissement[3];
				etabs[0] = etabsNO2;
				etabs[1] = etabsPM10;
				etabs[2] = etabsPM25;
				tab4 = new TableauStat4(moyenneDptNO2, moyenneDptPM10,moyenneDptPM25,etabs, annee);

				table5 = new JTable(tab4);
				spane2  = new JScrollPane(table5);

				table5.setCellSelectionEnabled(false);
				table5.setPreferredScrollableViewportSize(table5.getPreferredSize());
				table5.setFillsViewportHeight(true);
				table5.setPreferredSize(new Dimension(500, 96)); // Ajouter +16 par ligne

				//onglet2.add(labelTableau,c);
				onglet5.add(spane2,a);
				a.gridy++;
			}
			
		}

		onglets.addTab("Pourcentage 2012 à 2017", onglet5);

		
		
		return panel;
	}
}

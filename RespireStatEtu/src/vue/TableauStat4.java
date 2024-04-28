package vue;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import controleur.ConvertCSV;
import model.Etablissement;

public class TableauStat4 extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final String[] entetes = { "", "NO2", "PM10", "PM25" };
    private final Etablissement[] etabs;
    private final int annee;
    private final HashMap<String, Double> moyenneDptNO2;
	private final HashMap<String, Double> moyenneDptPM10;
	private final HashMap<String, Double> moyenneDptPM25;

	public TableauStat4(HashMap<String, Double> moyenneDptNO2, HashMap<String, Double> moyenneDptPM10, HashMap<String, Double> moyenneDptPM25, Etablissement[] contents, int annee) {
		this.moyenneDptNO2 = moyenneDptNO2;
		this.moyenneDptPM10 = moyenneDptPM10;
		this.moyenneDptPM25 = moyenneDptPM25;
		etabs = contents;
        this.annee = annee;
        entetes[0] = String.valueOf(annee);
	}
	
	
    @Override
    public int getColumnCount() {
        return entetes.length;
    }
	
	@Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

	@Override
    public int getRowCount() {
        return 5;  
    }
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return ConvertCSV.listeDepartements.get(rowIndex);
		case 1:
			return moyenneDptNO2.get(ConvertCSV.listeDepartements.get(rowIndex));

		case 2:
			return moyenneDptPM10.get(ConvertCSV.listeDepartements.get(rowIndex));

		case 3:
			return moyenneDptPM25.get(ConvertCSV.listeDepartements.get(rowIndex));

		default:
			throw new IllegalArgumentException();
		}
				
	

	}
    }
			

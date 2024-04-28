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
    private final HashMap<String, Double> pourcentageDptNO2;
	private final HashMap<String, Double> pourcentageDptPM10;
	private final HashMap<String, Double> pourcentageDptPM25;

	public TableauStat4(HashMap<String, Double> pourcentageDptNO2, HashMap<String, Double> pourcentageDptPM10, HashMap<String, Double> pourcentageDptPM25, Etablissement[] contents, int annee) {
		this.pourcentageDptNO2 = pourcentageDptNO2;
		this.pourcentageDptPM10 = pourcentageDptPM10;
		this.pourcentageDptPM25 = pourcentageDptPM25;
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
			return pourcentageDptNO2.get(ConvertCSV.listeDepartements.get(rowIndex));

		case 2:
			return pourcentageDptPM10.get(ConvertCSV.listeDepartements.get(rowIndex));

		case 3:
			return pourcentageDptPM25.get(ConvertCSV.listeDepartements.get(rowIndex));

		default:
			throw new IllegalArgumentException();
		}
				
	

	}
    }
			

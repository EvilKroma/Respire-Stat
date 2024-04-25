package vue;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import controleur.ConvertCSV;

public class TableauStat4 extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private final String[] entetes = { "Départements", "NO2", "PM10", "PM25" };	
	private final HashMap<String, Double> pourcentageDptNO2;
	private final HashMap<String, Double> pourcentageDptPM10;
	private final HashMap<String, Double> pourcentageDptPM25;

	
	public TableauStat4(HashMap<String, Double> moyenneDptNO2, HashMap<String, Double> moyenneDptPM10, HashMap<String, Double> moyenneDptPM25) {
		this.pourcentageDptNO2 = moyenneDptNO2;
		this.pourcentageDptPM10 = moyenneDptPM10;
		this.pourcentageDptPM25 = moyenneDptPM25;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Compléter $$
		return entetes.length;
	}
	@Override
	public String getColumnName(int columnIndex) {
		// TODO Compléter getColumnName
		return entetes[columnIndex];
	}

	@Override
	public int getRowCount() {
		return ConvertCSV.listeDepartements.size();
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
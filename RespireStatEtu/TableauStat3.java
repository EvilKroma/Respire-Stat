package vue;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import controleur.ConvertCSV;

public class TableauStat3 extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private final String[] entetes = { "Départements", "NO2", "PM10", "PM25" };	
	private final HashMap<String, Double> moyenneDptNO2;
	private final HashMap<String, Double> moyenneDptPM10;
	private final HashMap<String, Double> moyenneDptPM25;

	
	public TableauStat3(HashMap<String, Double> moyenneDptNO2, HashMap<String, Double> moyenneDptPM10, HashMap<String, Double> moyenneDptPM25) {
		this.moyenneDptNO2 = moyenneDptNO2;
		this.moyenneDptPM10 = moyenneDptPM10;
		this.moyenneDptPM25 = moyenneDptPM25;
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
		return ConvertCSV.listeDepartements.size();
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

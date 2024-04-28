package vue;

import javax.swing.table.AbstractTableModel;
import model.Etablissement;

public class TableauStat4 extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final String[] entetes = { "", "NO2", "PM10", "PM25" };
    private final Etablissement[] etabs;
    private final int annee;

    public TableauStat4(Etablissement[] contents, int annee) {
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
        if (columnIndex == 0) {
            switch (rowIndex) {
                case 0:
                    return "Identifiant";
                case 1:
                    return "Nom";
                case 2:
                    return "Ville";
                case 3:
                    return "Département";
                case 4:
                    return "Taux";
                default:
                    throw new IllegalArgumentException("Invalid row index");
            }
        } else {
            Etablissement etablissement = etabs[columnIndex - 1];
            switch (rowIndex) {
                case 0:
                    return etablissement.getIdentifiant();
                case 1:
                    return etablissement.getNomEtablissement();
                case 2:
                    return etablissement.getLieu().getVille();
                case 3:
                    return etablissement.getLieu().getDepartement();
                case 4:
                    return etablissement.getPollutionPM25(annee);
                default:
                    throw new IllegalArgumentException("Invalid row index");
            }
        }
    }
				
	

}

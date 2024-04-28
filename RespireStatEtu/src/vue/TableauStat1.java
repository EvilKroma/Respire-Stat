package vue;

import javax.swing.table.AbstractTableModel;
import model.CoordGPS;
import model.Etablissement;

public class TableauStat1 extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final String[] entetes = { "", "NO2", "PM10", "PM25" };
    private final Etablissement[] etabs;
    private final int annee;

    public TableauStat1(Etablissement[] contents, int annee) {
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
        return 6;  // + 1 pour une nouvelle ligne
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
                case 5:
                    return "Distance à Paris (km)"; // La nouvelle ligne
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
                case 5:
                	CoordGPS coord = etablissement.getCoordonnees(); // Affiche les coo
                    return calculateDistanceToParis(coord);

                default:
                    throw new IllegalArgumentException("Invalid row index");
            }
        }
    }

    private double calculateDistanceToParis(CoordGPS coord) {
        final double parisLatitude = 48.8566;
        final double parisLongitude = 2.3522;
        CoordGPS parisCoords = new CoordGPS(parisLatitude, parisLongitude);
        return distance(parisCoords, coord);
    }

    private static double distance(CoordGPS coord1, CoordGPS coord2) {
        double earthRadius = 6371; 
        double lat1 = Math.toRadians(coord1.getLatitude());
        double lat2 = Math.toRadians(coord2.getLatitude());
        double lon1 = Math.toRadians(coord1.getLongitude());
        double lon2 = Math.toRadians(coord2.getLongitude());
        double dLon = lon2 - lon1;
        double dLat = lat2 - lat1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

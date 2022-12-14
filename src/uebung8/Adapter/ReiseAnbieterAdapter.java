package uebung8.Adapter;

import uebung8.LegacySystem.QueryObject;
import uebung8.LegacySystem.ReiseAnbieter;
import uebung8.System.SuchAuftrag;
import uebung8.System.SuchErgebnis;

import java.util.HashMap;
import java.util.List;

public class ReiseAnbieterAdapter implements Adapter{
    private final ReiseAnbieter reiseAnbieter = new ReiseAnbieter();
    @Override
    public SuchErgebnis suche(SuchAuftrag suchAuftrag) {
        QueryObject querySuche = transform(suchAuftrag);
        return receive(querySuche);
    }

    private QueryObject transform(SuchAuftrag suchAuftrag) {
        return new QueryObject(suchAuftrag.land());
    }

    private SuchErgebnis receive(QueryObject suchAuftrag) {
        List<String> querys = reiseAnbieter.execute(suchAuftrag).hotelNamen_Preis();
        // Aufteilung von Hotelname und Preis in zwei Listen und dann in ein SuchErgebnis als HashMap packen
        return new SuchErgebnis(new HashMap<>());
    }
}

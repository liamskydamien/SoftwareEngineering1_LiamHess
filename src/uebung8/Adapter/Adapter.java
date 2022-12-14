package uebung8.Adapter;

import uebung8.System.SuchAuftrag;
import uebung8.System.SuchErgebnis;

public interface Adapter {
    public SuchErgebnis suche(SuchAuftrag suchAuftrag);
}

package fr.utt.if26.if26project;

import java.util.ArrayList;
import java.util.Iterator;

public class ListeModule {
    private ArrayList<Module> modules;

    public ListeModule() {
        modules = new ArrayList();
        init();
    }

    public void ajoute(Module m) {
        modules.add(m);
    }

    public void init() {
        ajoute(new Module(1,"GL02", "TCBR", "CS", 6));
        ajoute(new Module(2,"NF16", "TCBR", "CS", 6));
        ajoute(new Module(1,"NF20", "TCBR", "CS", 6));
        ajoute(new Module(1,"IF09", "TCBR", "TM", 6));
        ajoute(new Module(1,"IF14", "TCBR", "TM", 6));
        ajoute(new Module(1,"LO02", "TCBR", "TM", 6));
        ajoute(new Module(1,"NF21", "TCBR", "TM", 6));

        ajoute(new Module(1,"IF02", "TCBR", "CS", 6));
        ajoute(new Module(1,"LO12", "TCBR", "CS", 6));
        ajoute(new Module(1,"IF08", "TCBR", "CS", 6));
        ajoute(new Module(1,"EG23", "TCBR", "TM", 6));
        ajoute(new Module(1,"IF03", "TCBR", "TM", 6));
        ajoute(new Module(1,"LO07", "TCBR", "TM", 6));
        ajoute(new Module(1,"NF19", "TCBR", "TM", 6));

        ajoute(new Module(1,"IF05", "MPL", "CS", 6));
        ajoute(new Module(1,"IF19", "MSI", "CS", 6));
        ajoute(new Module(1,"IF11", "MPL", "TM", 6));
        ajoute(new Module(1,"IF22", "MSI", "TM", 6));
        ajoute(new Module(1,"IF24", "MSI", "TM", 6));
        ajoute(new Module(1,"LO10", "MPL", "TM", 6));

        ajoute(new Module(1,"IF10", "MPL", "CS", 6));
        ajoute(new Module(1,"IF15", "MSI", "CS", 6));
        ajoute(new Module(1,"IF16", "MSI", "TM", 6));
        ajoute(new Module(1,"IF17", "MPL", "TM", 6));
        ajoute(new Module(1,"IF20", "MSI", "TM", 6));
        ajoute(new Module(1,"IF26", "MPL", "TM", 6));


        ajoute(new Module(1,"MCS1", "MCS", "CS", 3));
        ajoute(new Module(1,"MCS2", "MCS", "TM", 3));
        ajoute(new Module(1,"MCS3", "MCS", "TM", 3));
        ajoute(new Module(1,"MCS4", "MCS", "TM", 3));
        ajoute(new Module(1,"MCS5", "MCS", "TM", 3));
        ajoute(new Module(1,"MCS6", "MCS", "TM", 3));
        ajoute(new Module(1,"MCS7", "MCS", "TM", 3));
        ajoute(new Module(1,"MCS8", "MCS", "CS", 3));
    }

    public ArrayList<String> getSigles() {
        ArrayList res = new ArrayList();
        Iterator iterateur = modules.iterator();
        while (iterateur.hasNext()) {
            Module m = (Module) (iterateur.next());
            res.add(m.getSigle());
        }
        return res;
    }


    public ArrayList<Module> getModules() {
        System.out.println(modules.toString());
        return modules;
    }
}

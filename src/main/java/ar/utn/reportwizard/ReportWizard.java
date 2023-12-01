package ar.utn.reportwizard;

import ar.utn.reportwizard.util.JPAUtil;
import ar.utn.reportwizard.view.MainView;

/**
 *
 * @author Gaston Ezequiel Giacobini
 */
public class ReportWizard {

    public static void main(String[] args) {

        JPAUtil.getEntityManagerFactory().createEntityManager();
        
        MainView.run();
    }
}

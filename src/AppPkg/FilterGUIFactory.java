/**
 * FilterGUIFactory : Created the filter gui object with all the necessary parameters
 /)/)
 ( . .)
 ( づ♡
 */
package AppPkg;

import Classes.APIClass;
import Classes.Settings.*;
import Classes.Filter.*;

import javax.swing.*;

public class FilterGUIFactory {
    public static FilterGUI create(JFrame parent) {

        // required parameters
        APIClass animalProvider = new APIClass();
        ViewModel vm = new ViewModel();
        Presenter presenter = new Presenter(vm);
        AnimalNamesProviderI nameProvider = new AnimalNamesProvider("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");
        FilterInteractor interactor = new FilterInteractor(nameProvider, presenter, animalProvider);
        FilterController filterController = new FilterController(interactor);

        // build GUI
        return new FilterGUI(parent, filterController, vm);
    }
}
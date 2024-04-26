package FOMS.branch_manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import FOMS.FOMS_entity.*;
import FOMS.account_manager.BranchFilter;
import FOMS.account_manager.IStaffFilter;
import FOMS.account_manager.ReadStaffList;
import FOMS.account_manager.UserCredentials;
import FOMS.account_manager.ViewFilteredStaffList;


/**
 * The DisplayManagerBranchStaff class provides methods to display details of staff members managed by a branch manager.
 * It extends the ViewFilteredStaffList class.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class DisplayManagerBranchStaff extends ViewFilteredStaffList {
    Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
    
    /**
     * Constructs a DisplayManagerBranchStaff object with the provided credentials map.
     * 
     * @param credentialsMap The map containing user credentials.
     */
    public DisplayManagerBranchStaff(Map<String, UserCredentials> credentialsMap) {
        super(credentialsMap); 
    }

    /**
     * Displays details of staff members managed by a specific branch manager.
     * 
     * @param bm The branch manager whose staff details are to be displayed.
     */
    public void displaystaffDetails(BranchManager bm){
        IStaffFilter filter = new BranchFilter(bm.getBranch());
       
        List<UserCredentials> filteredCredentials = filter.filter(new ArrayList<>(credentialsMap.values()));

        displayStaff(filteredCredentials);
    }
}

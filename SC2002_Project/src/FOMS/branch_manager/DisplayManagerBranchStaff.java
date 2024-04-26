package FOMS.branch_manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import FOMS.FOMS_entity.*;
import FOMS.FOMS_entity.Staff.Gender;
import FOMS.account_manager.BranchFilter;
import FOMS.account_manager.IStaffFilter;
import FOMS.account_manager.ReadStaffList;
import FOMS.account_manager.UserCredentials;
import FOMS.account_manager.ViewFilteredStaffList;

public class DisplayManagerBranchStaff extends ViewFilteredStaffList {
    Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
       
    public DisplayManagerBranchStaff(Map<String, UserCredentials> credentialsMap) {
        super(credentialsMap); 
    }
    public void displaystaffDetails(BranchManager bm){
        IStaffFilter filter = new BranchFilter(bm.getBranch());
       
        List<UserCredentials> filteredCredentials = filter.filter(new ArrayList<>(credentialsMap.values()));

        displayStaff(filteredCredentials);


    }

    public static void main(String[] args) {
        BranchManager m = new BranchManager("a","a",Gender.M,12, "JP", "salt", "assdfkb", false);
        Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
        DisplayManagerBranchStaff a = new DisplayManagerBranchStaff(credentialsMap);
        a.displaystaffDetails(m);
    }



    





}

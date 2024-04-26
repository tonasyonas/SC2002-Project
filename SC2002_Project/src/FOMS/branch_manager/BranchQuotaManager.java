package FOMS.branch_manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The BranchQuotaManager class manages branch quotas for staff members.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 * 
 */
public class BranchQuotaManager {
    private Map<String, Integer> branchQuotas = new HashMap<>();
    private static final String FILE_PATH = "SC2002_Project/src/FOMS/branch_manager/branch_list.txt";

    /**
     * Constructs a BranchQuotaManager and loads branch quotas from file.
     */
    public BranchQuotaManager() {
        loadQuotas();
    }

    /**
     * Loads branch quotas from the file specified by FILE_PATH.
     */
    private void loadQuotas() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    branchQuotas.put(parts[0], Integer.parseInt(parts[2]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_PATH);
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the quota for the specified branch ID.
     * 
     * @param branchId The ID of the branch.
     * @return The quota for the specified branch.
     */
    public int getQuota(String branchId) {
        return branchQuotas.getOrDefault(branchId, 0);
    }

    /**
     * Checks if a staff member can be added to the specified branch based on its current staff count and quota.
     * 
     * @param branchId The ID of the branch.
     * @param currentStaffCount The current staff count of the branch.
     * @return true if a staff member can be added, false otherwise.
     */
    public boolean canAddStaff(String branchId, int currentStaffCount) {
        return currentStaffCount < getQuota(branchId);
    }
}

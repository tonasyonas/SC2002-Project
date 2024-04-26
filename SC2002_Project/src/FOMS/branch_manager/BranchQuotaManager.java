package FOMS.branch_manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class BranchQuotaManager {
    private Map<String, Integer> branchQuotas = new HashMap<>();
    private static final String FILE_PATH = "SC2002_Project/src/FOMS/branch_manager/branch_list.txt";

    public BranchQuotaManager() {
        loadQuotas();
    }

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

    public int getQuota(String branchId) {
        return branchQuotas.getOrDefault(branchId, 0);
    }

    public boolean canAddStaff(String branchId, int currentStaffCount) {
        return currentStaffCount < getQuota(branchId);
    }
}

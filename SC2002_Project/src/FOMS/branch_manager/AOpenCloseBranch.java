package FOMS.branch_manager;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AOpenCloseBranch implements IOpenCloseBranch {

    protected List<String> readBranchList(String filename) {
        List<String> branchList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                branchList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return branchList;
    }

    protected void writeToTextFile(String filename, List<String> branchList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String branchEntry : branchList) {
                writer.write(branchEntry + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

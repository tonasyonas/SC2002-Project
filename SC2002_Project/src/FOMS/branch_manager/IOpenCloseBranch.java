package FOMS.branch_manager;

public interface IOpenCloseBranch {
    void editOpenclose(String branchID, String branchName, int quota);  // Method to add or remove a branch entry from the text file

}

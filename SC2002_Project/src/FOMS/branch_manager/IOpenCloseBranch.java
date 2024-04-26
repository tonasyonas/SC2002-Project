package FOMS.branch_manager;

/**
 * The IOpenCloseBranch interface defines the contract for classes that handle opening and closing branches.
 * It provides a method to edit the status of a branch (open or closed) and its associated quota.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public interface IOpenCloseBranch {
    /**
     * Edits the status of a branch (open or closed) and its associated quota.
     * 
     * @param branchID The ID of the branch to be edited.
     * @param branchName The name of the branch to be edited.
     * @param quota The quota associated with the branch.
     */
    void editOpenclose(String branchID, String branchName, int quota);  

}

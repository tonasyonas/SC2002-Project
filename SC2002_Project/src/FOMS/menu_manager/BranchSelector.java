package FOMS.menu_manager;

/**
 * An interface for selecting a branch.
 * Implementing classes should provide functionality to select a branch.
 *
 * The BranchSelector interface declares a single method, selectBranch(),
 * which should return the selected branch as a String.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public interface BranchSelector {
    /**
     * Selects a branch.
     *
     * @return The selected branch as a String.
     */
    String selectBranch();
}

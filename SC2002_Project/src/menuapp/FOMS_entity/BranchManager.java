public class BranchManager extends Staff {

    // Constructor for Manager class
    public BranchManager(String staffName, Role staffRole, Gender staffGender, int staffAge,  int branch_ID) {
        // Call the superclass constructor using super()
        super(staffName, staffRole, staffGender, staffAge,  branch_ID);
        setStaffRole(Role.Branch_Manager);

    }

    public static void main (String[] args){
        BranchManager Donovan = new BranchManager("Don", Role.Staff, Gender.MALE, 12, 0005);
        Donovan.print();
        Staff Don = new Staff("Don", Role.Staff, Gender.MALE, 12, 0005);
        Don.print();
   

   
   }

    
}

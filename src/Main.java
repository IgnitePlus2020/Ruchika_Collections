import java.util.*;

/**
 * Created by user on 4/17/2020.
 */
public class Main {
    private static Map<String,List<IgniteMembers>>departments = new HashMap<>();

    //Creates a new Department
    static void createDepartment(){
        String depName;
        Scanner in = new Scanner(System.in);
        //Checking if department already exists
        boolean flag = false;
        do{
            System.out.println("Enter the name of the department to be created");
            depName = in.nextLine();
            depName = depName.toUpperCase();
            flag = false;
            for(String department:departments.keySet()){
                if(department.equals(depName)){
                    flag = true;
                    System.out.println("Department already exists. Please enter new department name.");
                    break;
                }
            }
        }
        while(flag);
        System.out.println("Creating new Department---------");
        System.out.println("Add a member as each department must have at least one member");
        createMember(depName); //Creates a new member into the new department because department cannot be empty.
        System.out.println("Department created");
        System.out.println("-------------------");
    }
    //--------------------------------------------------------------------------------------------------------
    //Creates a new Member into the given department given as parameter
    static void createMember(String depName){
        Scanner in = new Scanner(System.in);
        String name;
        String college;
        int age;
        Set<String>skillSet = new HashSet<>();
        depName = depName.toUpperCase();
        boolean flag = false;
        System.out.println("Creating new member------------------- ");
        //Checking for unique name in the department
        do{
            System.out.println("Enter the name:");
            name = in.nextLine();
            flag = false;
            if(departments.containsKey(depName)) {
                List<IgniteMembers> people = new ArrayList<>();
                people = departments.get(depName);
                for (IgniteMembers person : people) {
                    if (person.name.equalsIgnoreCase(name)) {
                        flag = true;
                        System.out.println("This name already exists in the department. Please enter unique name by adding initial or surname.");
                        break;
                    }
                }
            }
        }
        while(flag);
        System.out.println("Enter the age:");
        age = Integer.parseInt(in.nextLine());
        System.out.println("Enter the college:");
        college = in.nextLine();
        System.out.println("Enter the skills and done after entering:");
        String skill;
        while(!(skill = in.nextLine()).equalsIgnoreCase("done")){
            skill = skill.toLowerCase();
            skillSet.add(skill);
        }
        IgniteMembers member = new IgniteMembers(name,college,age,skillSet);
        if(departments.containsKey(depName)){ //dep already exists so append the new member to the existing list
            departments.get(depName).add(member);
        }
        //Create a new list of members and add the department and members to the map of department
        else{
            List<IgniteMembers>depMembers = new ArrayList<>();
            depMembers.add(member);
            departments.put(depName,depMembers);
        }
        System.out.println("IgniteMember created");
        System.out.println("-----------------------");
    }
//---------------------------------------------------------------------------------------
    //displays all the departments
    static void displayDepartments(){
        System.out.println("Displaying departments------");
        System.out.println("The departments present are:");
        for(String department:departments.keySet()){
            System.out.println(department);
        }
        if(departments.size() == 0)
            System.out.println("There are no departments yet!");
        System.out.println("---------------------");
    }

    //----------------------------------------------------------------------------------------

    static void displayAllMembersByDepartment(){
        if(departments.size() == 0){
            System.out.println("There are no members yet!");
        }
        else{
            System.out.println("Viewing members---------");
            for(String dep : departments.keySet()){
                System.out.println("---------------");
                System.out.println("Department:" + dep);
                for(IgniteMembers member:departments.get(dep)){
                    System.out.println(member.name);
                }
            }
        }
        System.out.println("---------------------------");
    }

    static void deleteDepartment(){
        displayDepartments();
        Scanner in = new Scanner(System.in);
        boolean found = false;
        String dep;
        if(departments.size() == 0)
            System.out.println("There are no departments remaining.");
        //Check if department exists
        else {
            do {
                System.out.println("Enter the department name you want to delete");
                dep = in.nextLine();
                dep = dep.toUpperCase();
                found = false;
                for (String department : departments.keySet()) {
                    if (dep.equals(department)) {
                        found = true;
                        System.out.println("Department found!");
                    }
                }
                if(!found)
                    System.out.println("Department does not exist. Please enter valid department name.");
            }
            while (!found);
            String yesorno;
            System.out.println("Are you sure to delete the entire department? (Y/N)");
            yesorno = in.nextLine();
            if(yesorno.equalsIgnoreCase("y")) {
                departments.remove(dep);
                System.out.println("Department deleted successfully.");
            }
        }
        System.out.println("-----------------------------");
    }
    //-------------------------------------------------------------------------------------------------------

    static void addSkillToAllDepartmentMembers(){
        displayDepartments();
        Scanner in = new Scanner(System.in);
        boolean found = false;
        String dep;
        //Check if department exists
        if(departments.size() == 0)
            System.out.println("There are no departments remaining.");
            //Check if department exists
        else {
            do {
                System.out.println("Enter the department name you want to add skill");
                dep = in.nextLine();
                dep = dep.toUpperCase();
                found = false;
                for (String department : departments.keySet()) {
                    if (dep.equals(department)) {
                        found = true;
                        System.out.println("Department found!");
                    }
                }
                if(!found)
                    System.out.println("Department does not exist. Please enter valid department name.");
            }
            while (!found);
            String skill;
            System.out.println("Enter name of skill to add to each member");
            skill = in.nextLine();
            List<IgniteMembers> members = new ArrayList<>();
            members = departments.get(dep);
            for(IgniteMembers im : members){
                im.skills.add(skill);
            }
            System.out.println("Skill added to each member of department.");
        }
        System.out.println("-----------------");
    }
    //------------------------------------------------------------------------------------------------------------------

    static void allMembersWithParticularSkill(){
        String skill;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the skill:");
        skill = in.nextLine();
        skill = skill.toLowerCase();
        System.out.println("Members with particular skill are:");
        int count = 0;
        for(String dep:departments.keySet()){
            List<IgniteMembers> members = departments.get(dep);
            for(IgniteMembers member:members){
                if(member.skills.contains(skill)){
                    count++;
                    System.out.println(member.name);
                }
            }
        }
        if(count == 0){
            System.out.println("Oops!No members have this skill yet!");
        }
        System.out.println("-------------------");
    }
    //-----------------------------------------------------------------------------------------------------------------
    static void replaceMember(){
        String name;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name of member to change his/her department");
        name = in.nextLine();
        IgniteMembers MemberToSwap = null;
        boolean found = false,cannotSwap = false;
        for(String dep:departments.keySet()){
            List<IgniteMembers> members = departments.get(dep);
            for(IgniteMembers member:members){
                if(member.name.equalsIgnoreCase(name)){
                    found = true;
                    System.out.println("Member found.");
                    System.out.println("The department of the member is "+ dep);
                    if(members.size() == 1){
                        found = true;
                        cannotSwap = true;
                        System.out.println("This department has only one member.Cannot move this person to other department!");
                    }
                    MemberToSwap = member;
                    if(found && !cannotSwap)
                        members.remove(member);
                    if(found)
                        break;
                }
            }
            if(found)
                break;
        }
        if(found){
            displayDepartments();
            String newDep;
            System.out.println("Enter name of existing department or new department to insert this member");
            newDep = in.nextLine();
            newDep.toUpperCase();
            if(departments.containsKey(newDep)){ //dep already exists so append the new member to the existing list
                departments.get(newDep).add(MemberToSwap);
            }
            //Create a new list of members and add the department and members to the map of department
            else{
                List<IgniteMembers>depMembers = new ArrayList<>();
                depMembers.add(MemberToSwap);
                departments.put(newDep,depMembers);
            }
            System.out.println("IgniteMember successfully changed department");

        }
        if(!found && cannotSwap == false){
            System.out.println("Member not found!");
        }
        System.out.println("------------------------------");
    }
    //----------------------------------------------------------------------------------------------

    /*static void deleteMemberFromDepartment(String dep){

    }*/
    public static void main(String args[]){
        int choice = 0;
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("Press the option (Menu):");
            System.out.println("1. Create a new Department\n"+
                    "2: Create a member and add the member to a department\n"+
            "3: Display all the Departments\n"+
            "4: Display all members as per Department\n"+
            "5: Delete a department\n"+
            "6: Add a new Skill to all the members of a specific department\n"+
            "7: List all members who have a particular skill along with department name\n"+
            "8: Replace (Swap) a member from one department to another\n"+
            //"9: Delete a member from a department permanently:"+
            "9: Exit\n");
            choice = in.nextInt();
            in.nextLine();
            switch(choice){
                case 1:createDepartment();
                     break;
                case 2:displayDepartments();
                    boolean flag = false;
                    String dep = "";
                    //Check if department exists

                    System.out.println("Enter the department name or new department where you want to add a member");
                    dep = in.nextLine();
                    createMember(dep);
                    break;
                case 3:displayDepartments();
                    break;
                case 4:displayAllMembersByDepartment();
                    break;
                case 5:deleteDepartment();
                    break;
                case 6:addSkillToAllDepartmentMembers();
                    break;
                case 7:allMembersWithParticularSkill();
                    break;
                case 8:replaceMember();
                    break;
                /*case 9:deleteMemberFromDepartment();
                    break;*/
                case 9:System.exit(0);
                    break;
                default:System.out.println("Please enter valid option.");
            }

        }
        while(choice != 9);

    }

}

/*
OUTPUT:

"C:\Program Files\jdk8u242-b08\bin\java" -Didea.launcher.port=7541 "-Didea.launcher.bin.path=C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2016.2.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\jdk8u242-b08\jre\lib\charsets.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\access-bridge-64.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\cldrdata.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\dnsns.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\jaccess.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\localedata.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\nashorn.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\sunec.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\sunjce_provider.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\sunmscapi.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\sunpkcs11.jar;C:\Program Files\jdk8u242-b08\jre\lib\ext\zipfs.jar;C:\Program Files\jdk8u242-b08\jre\lib\jce.jar;C:\Program Files\jdk8u242-b08\jre\lib\jsse.jar;C:\Program Files\jdk8u242-b08\jre\lib\management-agent.jar;C:\Program Files\jdk8u242-b08\jre\lib\resources.jar;C:\Program Files\jdk8u242-b08\jre\lib\rt.jar;C:\Users\user\Desktop\Projects Ruchi\Target\Java Collections\out\production\Java Collections;C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2016.2.4\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain Main
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

10
Please enter valid option.
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

3
Displaying departments------
The departments present are:
There are no departments yet!
---------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

4
There are no members yet!
---------------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

5
Displaying departments------
The departments present are:
There are no departments yet!
---------------------
There are no departments remaining.
-----------------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

6
Displaying departments------
The departments present are:
There are no departments yet!
---------------------
There are no departments remaining.
-----------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

7
Enter the skill:
java
Members with particular skill are:
Oops!No members have this skill yet!
-------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

8
Enter name of member to change his/her department
divya
Member not found!
------------------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

1
Enter the name of the department to be created
Cloud
Creating new Department---------
Add a member as each department must have at least one member
Creating new member-------------------
Enter the name:
Divya
Enter the age:
24
Enter the college:
North Western Univ
Enter the skills and done after entering:
cloud
java
sql
done
IgniteMember created
-----------------------
Department created
-------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

2
Displaying departments------
The departments present are:
Cloud
---------------------
Enter the department name or new department where you want to add a member
Dojo
Creating new member-------------------
Enter the name:
gautham
Enter the age:
32
Enter the college:
VTU
Enter the skills and done after entering:
java
android
ds
done
IgniteMember created
-----------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

8
Enter name of member to change his/her department
divya
Member found.
The department of the member is Cloud
This department has only one member.Cannot move this person to other department!
------------------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

2
Displaying departments------
The departments present are:
Dojo
Cloud
---------------------
Enter the department name or new department where you want to add a member
security
Creating new member-------------------
Enter the name:
riya
Enter the age:
23
Enter the college:
CMRIT
Enter the skills and done after entering:
java
cpp
sql
done
IgniteMember created
-----------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

1
Enter the name of the department to be created
web dev
Creating new Department---------
Add a member as each department must have at least one member
Creating new member-------------------
Enter the name:
Barkha
Enter the age:
26
Enter the college:
SMVIT
Enter the skills and done after entering:
react
angular
js
done
IgniteMember created
-----------------------
Department created
-------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

3
Displaying departments------
The departments present are:
Dojo
security
Cloud
web dev
---------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

4
Viewing members---------
---------------
Department:Dojo
gautham
---------------
Department:security
riya
---------------
Department:Cloud
Divya
---------------
Department:web dev
Barkha
---------------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

2
Displaying departments------
The departments present are:
Dojo
security
Cloud
web dev
---------------------
Enter the department name or new department where you want to add a member
Cloud
Creating new member-------------------
Enter the name:
jaya
Enter the age:
23
Enter the college:
CMRIT
Enter the skills and done after entering:
java
sql
done
IgniteMember created
-----------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

4
Viewing members---------
---------------
Department:Dojo
gautham
---------------
Department:security
riya
---------------
Department:Cloud
Divya
jaya
---------------
Department:web dev
Barkha
---------------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

5
Displaying departments------
The departments present are:
Dojo
security
Cloud
web dev
---------------------
Enter the department name you want to delete
security
Department found!
Are you sure to delete the entire department? (Y/N)
y
Department deleted successfully.
-----------------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

7
Enter the skill:
java
Members with particular skill are:
gautham
Divya
jaya
-------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

6
Displaying departments------
The departments present are:
Dojo
Cloud
web dev
---------------------
Enter the department name you want to add skill
web dev
Department found!
Enter name of skill to add to each member
Javascript
Skill added to each member of department.
-----------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

4
Viewing members---------
---------------
Department:Dojo
gautham
---------------
Department:Cloud
Divya
jaya
---------------
Department:web dev
Barkha
---------------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

8
Enter name of member to change his/her department
jaya
Member found.
The department of the member is Cloud
Displaying departments------
The departments present are:
Dojo
Cloud
web dev
---------------------
Enter name of existing department or new department to insert this member
Dojo
IgniteMember successfully changed department

------------------------------
Press the option (Menu):
1. Create a new Department
2: Create a member and add the member to a department
3: Display all the Departments
4: Display all members as per Department
5: Delete a department
6: Add a new Skill to all the members of a specific department
7: List all members who have a particular skill along with department name
8: Replace (Swap) a member from one department to another
9: Exit

9

Process finished with exit code 0
 */
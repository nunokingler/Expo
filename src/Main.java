
import java.util.Iterator;
import java.util.Scanner;

import Exeptions.*;
import Expo.ExpoFCT;
import Expo.ExpoFCTClass;
//test

public class Main {
    //unregistered user operations
    private static final String EVENTS = "EVENTS";
    private static final String LISTBYCOMMENTS = "LISTBYCOMMENTS";
    private static final String BYTAG = "BYTAG";
    private static final String COMMENTS = "COMMENTS";
    private static final String REGISTER = "REGISTER";
    private static final String LIKE = "LIKE";
    private static final String DESLIKE = "DESLIKE";
    private static final String LIKECOMMENT = "LIKECOMMENT";
    private static final String TOPCOMMENT = "TOPCOMMENT";
    private static final String EXIT = "EXIT";
    private static final String LOGIN = "LOGIN";
    private static final String LOGOUT = "LOGOUT";
    private static final String NEWEVENT = "NEWEVENT";
    private static final String USERS = "USERS";
    private static final String DEPT = "DEPT";
    private static final String NEWACTIVITY = "NEWACTIVITY";



    //unregistered user messeges and error messeges
    private static final String EVENTS_1 = "All events:";
    private static final String LISTBYCOMMENTS_1 = "Most commented events:";
    private static final String BY_TAG_1 = "Activities with tag ";
    private static final String BY_TAG_2 = " :";
    private static final String COMMENTS_1 = " comments:";
    private static final String COMMENTS_ERROR = "Event does not exist";
    private static final String REGISTER_1 = "User was registered: ";
    private static final String REGISTER_ERROR_1 = "There is a user logged in";
    private static final String REGISTER_ERROR_2 = "User already exists";
    private static final String REGISTER_ERROR_3 = "Department does not exist";
    private static final String LIKE_1 = "Likes: ";
    private static final String LIKE_DESLIKE_ERROR = COMMENTS_ERROR;
    private static final String DESLIKE_1 = "Deslikes: ";
    private static final String LIKECOMMENT_1 = "Comment likes: ";
    private static final String LIKECOMMENT_ERROR_1 = COMMENTS_ERROR;
    private static final String LIKECOMMENT_ERROR_2 = "User does not exist";
    private static final String LIKECOMMENT_ERROR_3 = "Comment does not exist";
    private static final String TOPCOMMENT_ERROR_1 = "No comments registered";
    private static final String NEWEVENT_1 = "Event registered";
    private static final String NEWEVENT_ERROR_1 = "User cannot execute this command";
    private static final String NEWEVENT_ERROR_2 = "Event name already exists";
    private static final String LOGIN_1 = "Welcome ";
    private static final String LOGIN_ERROR_1 = "User does not exist";
    private static final String LOGIN_ERROR_2 = "User already logged in";
    private static final String LOGIN_ERROR_3 = "Another user is logged in";
    private static final String LOGIN_ERROR_4 = "Wrong password";
    private static final String LOGOUT_1 = "Goodbye ";
    private static final String LOGOUT_ERROR_1 = "No user is logged in";
    private static final String USERS_ERROR_1 = "User cannot execute this command";
    private static final String USERS_1 = "All users:";
    private static final String DEPT_1 = "Department registered";
    private static final String DEPT_ERROR_1 = "User cannot execute this command";
    private static final String DEPT_ERROR_2 = "Department name or id already exists";


    private static final String WRONG_INITIAL_LOCATION = "Wrong initial location.";
    private static final String WRONG_FINAL_LOCATION = "Wrong destination location.";
    private static final String NO_VEHICLE = "No vehicle capable.";
    private static final String NO_TRANSPORTATION = "No transportation has occurred.";


    //keywords
    private static final String ADMIN = "ADMIN";
    private static final String STAFF = "STAFF";
    private static final String VISITOR = "VISITOR";


    private static ExpoFCT exp;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String opcao = in.nextLine().toUpperCase();
        exp = new ExpoFCTClass();
        while (!opcao.equals(EXIT)) {
            switch (opcao) {
                case LOGIN:
                    function_login(in);
                    break;
                case REGISTER:
                    try {
                        String pass;
                        pass = function_register(in);
                        System.out.println(REGISTER_1 + pass);
                    } catch (UserLoggedIn userLoggedIn) {
                        System.out.println(REGISTER_ERROR_1);
                    } catch (DepartmentDoesNotExist departmentDoesNotExist) {
                        System.out.println(REGISTER_ERROR_3);
                    } catch (EmailTaken emailTaken) {
                        System.out.println(REGISTER_ERROR_2);
                    }
                    break;
                case LOGOUT:
                    function_logout();
                    break;
                case USERS:
                    function_users();
                    break;
                case DEPT:
                    function_dept(in);
                    break;
                case NEWACTIVITY:
                    funtion_newActivity(in);
                    break;
                case NEWEVENT:
                    function_newEvent(in);
                    break;
                case EVENTS:
                    funtion_events();
                    break;
                case BYTAG:
                    funtion_byTag(in);
                    break;

                default:
                    System.out.println("ERRO bitch");
                    break;
            }
            opcao = in.nextLine().toUpperCase();
        }
        System.out.println("Exiting.");
        System.out.println();
        in.close();
    }

    private static void funtion_byTag(Scanner in) {
        String tag = in.nextLine();
        System.out.println(BY_TAG_1 + tag + BY_TAG_2);
        Iterator<String> it = exp.EventIterator(tag);
        while (it.hasNext())
            System.out.println(it.next());

    }

    private static void funtion_events() {
        Iterator<String> it = exp.EventIterator();
        System.out.println(EVENTS_1);
        while (it.hasNext())
            System.out.println(it.next());
    }

    private static void funtion_newActivity(Scanner in) {
        String name = in.nextLine();
        String desc = in.nextLine();
        int i = in.nextInt();
        if (in.hasNextLine())
            in.nextLine();
        String[] str = new String[0];
        if (i > 0) {
            str = new String[i];
            for (i--; i >= 0; i--) {
                str[i] = in.nextLine();
            }
        }
        try {
            exp.RegisterEvent(name, desc, str);
            System.out.println(NEWEVENT_1);
        } catch (UserNotAllowed userNotAllowed) {
            System.out.println(NEWEVENT_ERROR_1);
        } catch (EventNameTaken eventNameTaken) {
            System.out.println(NEWEVENT_ERROR_2);
        }
    }

    private static void function_newEvent(Scanner in) {
        String name = in.nextLine();
        String desc = in.nextLine();
        try {
            exp.RegisterEvent(name, desc);
            System.out.println(NEWEVENT_1);
        } catch (UserNotAllowed userNotAllowed) {
            System.out.println(NEWEVENT_ERROR_1);
        } catch (EventNameTaken eventNameTaken) {
            System.out.println(NEWEVENT_ERROR_2);
        }
    }

    private static void function_dept(Scanner in) {
        String dept = in.nextLine();
        String init = in.nextLine();
        String location = in.nextLine();
        try {
            exp.AddDepartment(dept, init, location);
            System.out.println(DEPT_1);
        } catch (UserNotAllowed userNotAllowed) {
            System.out.println(DEPT_ERROR_1);
        } catch (DepartmentAlreadyExists departmentAlreadyExists) {
            System.out.println(DEPT_ERROR_2);
        }
    }

    private static void function_logout() {
        try {
            String s = exp.Logout();
            System.out.println(LOGOUT_1 + s);
        } catch (NoUserLoggedIn noUserLoggedIn) {
            System.out.println(LOGOUT_ERROR_1);
        }

    }

    private static void function_users() {
        for (int i = 0; i < 3; i++) {
            try {
                Iterator<String> it = null;
                switch (i) {
                    case 0:
                        it = exp.AdminIterator();
                        System.out.println(USERS_1);
                        break;
                    case 1:
                        it = exp.VisitorIterator();
                        break;
                    case 2:
                        it = exp.StaffIterator();
                        break;
                }
                while (it.hasNext())
                    System.out.println(it.next());
            } catch (UserNotAllowed userNotAllowed) {
                System.out.println(USERS_ERROR_1);
                i = Integer.MAX_VALUE;
            }

        }

    }

    private static String function_register(Scanner in) throws UserLoggedIn, DepartmentDoesNotExist, EmailTaken {
        String type = in.nextLine().toUpperCase();
        String email = in.nextLine();
        String s = null;
        switch (type) {
            case ADMIN:
                s = exp.AddUser(email, 0);
                break;
            case STAFF:
                String Department = in.nextLine();
                s = exp.AddUser(email, 1, Department);
                break;
            case VISITOR:
                s = exp.AddUser(email, 2);
                break;
        }
        return s;
    }

    private static void function_login(Scanner in) {
        String email, password;
        email = in.nextLine();
        password = in.nextLine();
        try {
            exp.LogIn(email, password);
            System.out.println(LOGIN_1 + email);
        } catch (UserLoggedIn userLoggedIn) {
            System.out.println(LOGIN_ERROR_2);
        } catch (WrongPassword wrongPassword) {
            System.out.println(LOGIN_ERROR_4);
        } catch (UserDoesNotExist userDoesNotExist) {
            System.out.println(LOGIN_ERROR_1);
        } catch (AnotherUserLoggedIn anotherUserLoggedIn) {
            System.out.println(LOGIN_ERROR_3);
        }
    }



/*
    private static void funcao_view_user(Scanner in) {
		String email = in.nextLine();
		try {
			UserInfo u = uber.viewUser(email);
			Iterator it= u.getIterator();
			System.out.println(u);
			while (it.hasNext())
				System.out.println(it.next());
		} catch (NonExistingUser nonExistingUser) {

			System.out.println(nonExistingUser.getMessage());

		}
	}

	private static void funcao_list_vehicles(Scanner in) {
		int xStart = in.nextInt();
		int yStart = in.nextInt();
		in.nextLine();
		if(!(xStart>MAX_X || xStart< MIN_X || yStart > MAX_Y|| yStart< MIN_Y)) {
			Iterator it = uber.transportIterator(xStart,yStart);
			while(it.hasNext())
				System.out.println(it.next());
		}
		else System.out.println(WRONG_LOCATION);
	}

	private static void funcao_list_users( Scanner in) {
		Iterator<UserInfo> it= uber.userIterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}

	private static void funcao_transportPeople(Scanner in, String tipo){
		String email = in.nextLine();
		int numeroPessoas = in.nextInt();
		int xStart = in.nextInt();
		int yStart = in.nextInt();
		int xFinal = in.nextInt();
		int yFinal = in.nextInt();
		in.nextLine();
		int i=-1;
		if(tipo.equals(T_GOODS))
			i=0;
		else if(tipo.equals(T_PEOPLE))
			i=1;
		try {

			String result = uber.addTrip(email,numeroPessoas,xStart,yStart,xFinal,yFinal,i);
			System.out.println(NRE_REQUEST + result + '.');
		} catch (NonExistingUser nonExistingUser) {
			System.out.println("User " + email + " does not exist.");
		} catch (InvalidMaxWeight invalidMaxWeight) {
			invalidMaxWeight.printStackTrace();
		} catch (OutOfBounds outOfBounds) {
			if(outOfBounds.getMessage().contains("location"))
				System.out.println(WRONG_INITIAL_LOCATION);
			else System.out.println(WRONG_FINAL_LOCATION);

		} catch (WrongUserType wrongUserType) {
			System.out.println("User " + email + " does not exist.");
		} catch (TypeNotSupported typeNotSupported) {

		} catch (Exeptions.cantTransport cantTransport) {
			System.out.println(NO_VEHICLE);
		}
	}





	private static void funcao_add_transportation( Scanner in){
		String email = in.nextLine();
		String good = in.nextLine().toUpperCase();
		int number = in.nextInt();
		int x = in.nextInt();
		int y = in.nextInt();
		in.nextLine();
		int type = good.equals(GOODS)? 0:good.equals(PERSON)?1:90000;

		try {
			uber.addTransport(email, number, x,y, type);
			System.out.println(NEW_VEHICLE);
		} catch (NonExistingUser nonExistingUser) {
			System.out.println(nonExistingUser.getMessage());
		} catch (WrongUserType wrongUserType) {
			System.out.println(WRONG_TYPEUSER);
		} catch (OutOfBounds outOfBounds) {
			System.out.println(WRONG_LOCATION);
		} catch (TypeNotSupported typeNotSupported) {
			System.out.println(WRONG_TYPETRANSPORTATION);
		}
	}

	private static void funcao_add_user( Scanner in)
	{
		String name = in.nextLine();
		String email = in.nextLine();
		String s=in.nextLine();
		int type = s.toUpperCase().equals(PROVIDER)? 0 : s.toUpperCase().equals(Customer)?1:100;

		try {
			uber.addUser(name,email,type);
			System.out.println(NEW_USER);
		} catch (DuplicateName duplicateName) {
			System.out.println(SAME_EMAIL);
		} catch (TypeNotSupported typeNotSupported) {
			System.out.println(WRONG_USER);
		}

	}
*/
}
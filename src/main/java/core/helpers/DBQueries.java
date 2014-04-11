package core.helpers;

public class DBQueries {

	private String createUser;

	public void formCreateUserQuery(String id,String  user_name, String user_description, String user_password, String user_roles, String  user_active,
	                                String user_record_creation_time, String user_last_login_time, String first_name,
	                                String last_name, String middle_name, String email, String phone ) {
		String query_template = "INSERT INTO login_user(user_name, user_description, user_password, user_roles, user_active,user_record_creation_time, user_last_login_time, first_name,last_name, middle_name, email, phone)\n" +
				"\" +VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s,%s, %s, %s, %s)";
		createUser= String.format(query_template, id, user_name, user_description, user_password, user_roles, user_active, user_record_creation_time, user_last_login_time, first_name,last_name, middle_name, email,phone);
	/*	createUser = "INSERT INTO login_user(user_name, user_description, user_password, user_roles, user_active,user_record_creation_time, user_last_login_time, first_name,last_name, middle_name, email, phone)\n" +
				"\" +VALUES (id, user_name, user_description, user_password, user_roles, user_active, user_record_creation_time, user_last_login_time, first_name,last_name, middle_name, email, phone)"; */

	}

	public void formCreateUserQuery(String id,String  user_name, String user_description, String user_password, String user_roles,  String first_name,
	                                String last_name ){
		String query_template = "INSERT INTO login_user( id, user_name, user_description, user_password, user_roles, first_name,last_name) VALUES ($s,$s, $s, $s, $s, $s, $s)";
		createUser= String.format(query_template, id, user_name, user_description, user_password, user_roles,first_name,last_name);

	}
}

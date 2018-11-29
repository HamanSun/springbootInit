package cn.jjsunw.dbgen;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PWDGen {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("1234@admin"));

	}

}

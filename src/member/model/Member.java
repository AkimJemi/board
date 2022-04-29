package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Member {
	private String id;
	private String name;
	private String password;
	private Date regDate;

	public Member(String id, String name, String password, Date regDate) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void changePassword(String newPwd) {
		this.password = newPwd;
	}

	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}

	public void update(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update member set name = ?, password = ? where memberid = ?")) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getId());
			pstmt.executeUpdate();
		}
	}
}

package member.service;

import java.sql.Connection;
import java.util.Date;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import util.JdbcUtil;

public class JoinService {

	private MemberDao memberDao = new MemberDao();

public void join(JoinRequest jr)	{
	Connection conn = null;
	try {
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		
		Member member = memberDao.selectById(conn, jr.getId());
		
		if(member !=null) {
			JdbcUtil.rollback(conn);
			throw new DuplicateIdException();
		}
		
		memberDao.insert(conn, new Member(jr.getId(),jr.getName(),jr.getPassword(), new Date()));
		
		conn.commit();
	}catch (Exception e) {
		JdbcUtil.rollback(conn);
		throw new RuntimeException();
		
	}finally {
		JdbcUtil.close(conn);
	}
	

}
}

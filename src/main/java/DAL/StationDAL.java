package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modalclasses.Station;
import modalclasses.StationsList;

public class StationDAL {

	private StationsList stations;
	private Db db;

	public StationDAL() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		stations = new StationsList();
		db = new Db();
	}

	public StationsList getAllStations() throws ClassNotFoundException, SQLException {
		ResultSet rs = getData("select * from railstations");
		while (rs.next()) {
			Station s = new Station();
			s.setStationId(rs.getInt(1));
			s.setStationCode(rs.getString(2));
			s.setStationName(rs.getString(3));
			stations.add(s);
		}
		return stations;
	}

	private ResultSet getData(String query) throws SQLException, ClassNotFoundException {
		Connection conn = db.connect();
		PreparedStatement ps = conn.prepareStatement(query);
		return ps.executeQuery();
	}
}

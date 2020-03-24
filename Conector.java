
package spotify;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Conector {

	
	private static Connection con;

	private static Conector INSTANCE = null;

	// constructor
	private Conector() {

	}

	// Crear instancia
	private synchronized static void crearInstancia() {

		if (INSTANCE == null) {
			INSTANCE = new Conector();
			crearConexion();
		}
	}

	// Obtener instancia
	public static Conector getInstancia() {

		if (INSTANCE == null) {
			crearInstancia();
		}
		return INSTANCE;
	}

	// crear conexion
	private static void crearConexion() {

		String host = "127.0.0.1"; // ( local host)
		String user = "root"; 
		String password = "Q15s16e17r20ghfgt25s@"; // Mi MySQL no tiene contraseña
		String dataBase = "spotify";

		try {
			// Importando la libreria de conexion de mysql
			Class.forName("com.mysql.jdbc.Driver");
			String urlConexion = "jdbc:mysql://" + host + "/" 
			+ dataBase + "?" + "user=" + user + "&password="
			+ password; 
			con = DriverManager.getConnection(urlConexion);
			System.out.println("Succesful!!!");

		} catch (Exception e) {
			System.out.println("Error al conectar a la base de datos");
			System.out.println(e);
		}
	}
	
	

	
	public ArrayList<String> getNamesE1() throws SQLException {
		ArrayList<String> listAlbums = new ArrayList<String>();
			PreparedStatement ps = con.prepareStatement(
					"Select\r\n" + 
					"albums.title\r\n" + 
					"from \r\n" + 
					"albums\r\n" + 
					"Join songs on albums.id = songs.album\r\n" + 
					"where songs.title LIKE 'S%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listAlbums.add(rs.getString("albums.title"));
				
			}
			rs.close();
		return listAlbums;
	}
	
	public ArrayList<String> getCountE2() throws SQLException {
		ArrayList<String> listCount = new ArrayList<String>();
			PreparedStatement ps = con.prepareStatement(
					"select\r\n" + 
					"COUNT(songs.id) as contador  \r\n" + 
					"from\r\n" + 
					"songs\r\n" + 
					"join genres on songs.genre = genres.id\r\n" + 
					"where genres.name = 'Hip-hop'\r\n" + 
					"OR genres.name = 'Pop' \r\n" + 
					"OR  genres.name = 'Rap' ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listCount.add(rs.getString("contador"));
				
			}
			rs.close();
		return listCount;
	}
	
	public ArrayList<String> getArtistNameMorePlayedSongsE3() throws SQLException {
		ArrayList<String> listNameSongs = new ArrayList<String>();
			PreparedStatement ps = con.prepareStatement(
					"Select \r\n" + 
					"songs.title,\r\n" + 
					"songs.plays,\r\n" + 
					"artists.name\r\n" + 
					"from \r\n" + 
					"songs\r\n" + 
					" join artists on songs.artist = artists.id\r\n" + 
					" where songs.plays = (select max(plays) from songs)\r\n" + 
					"  group by artists.id;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listNameSongs.add(rs.getString("artists.name"));
						listNameSongs.add(rs.getString("songs.title"));
								listNameSongs.add(rs.getString("songs.plays"));
				
			}
			rs.close();
		return listNameSongs;
	}
}
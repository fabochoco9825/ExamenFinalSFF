package spotify;
//import spotify.Conector;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Testing");
		Conector instancia = Conector.getInstancia();
		try {
			
			ArrayList<String> listNamesAlbums = instancia.getNamesE1();
			ArrayList<String> listCount = instancia.getCountE2();
			ArrayList<String> listNameArtistSongs = instancia.getArtistNameMorePlayedSongsE3();
			
			System.out.println( "Albums: Name ");
			System.out.println("----------------");
			
			for(String name: listNamesAlbums) {
				System.out.println(name);
			}
			
			System.out.println("----------------");
			System.out.println();
			
			System.out.println( "Canciones: contador ");
			System.out.println("----------------");
			
			for(String count: listCount) {
				System.out.println(count);
			}
			
			System.out.println("----------------");
			System.out.println();
			System.out.println( "Artist: Name  (Popular) and Song: Name and Song: Plays");
			System.out.println("----------------");
			
			for(String popular: listNameArtistSongs) {
				System.out.println(popular);
			}
			
			System.out.println("----------------");
			System.out.println();
			
		}catch (SQLException e ) {
			e.printStackTrace();
		}
	}

}
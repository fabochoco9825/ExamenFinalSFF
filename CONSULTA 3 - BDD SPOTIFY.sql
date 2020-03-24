-- Consulta 3
-- Mostrar el artista y su cancion con mayor popularidad
use spotify;
Select 
songs.title,
songs.plays,
artists.name
from 
songs
 join artists on songs.artist = artists.id
 where songs.plays = (select max(plays) from songs)
  group by artists.id;
  
